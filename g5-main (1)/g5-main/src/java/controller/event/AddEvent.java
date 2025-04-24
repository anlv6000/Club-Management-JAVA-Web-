package controller.event;

import Model.Club;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.EventHT.EventService; // Đổi import theo yêu cầu của bạn

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddEvent", urlPatterns = {"/addEvent"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddEvent extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private EventService eventService = new EventService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = getUsernameFromSession(session);

        if (username == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                List<Club> clubList = eventService.getClubsByUsername(username);
                request.setAttribute("c", clubList);
                request.getRequestDispatcher("/WEB-INF/event/AddEvent.jsp").forward(request, response);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                handlePostRequest(request, response, username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private String getUsernameFromSession(HttpSession session) {
        if (session == null) return null;
        User user = (User) session.getAttribute("acc");
        return (user != null) ? user.getUserName() : null;
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response, String username)
            throws ServletException, IOException, SQLException {
        String clubIdStr = request.getParameter("clubName");
        String eventName = request.getParameter("eventName");
        String clubDescription = request.getParameter("clubDescription");
        String eventTime = request.getParameter("eventTime");
        String eventTimeEnd = request.getParameter("eventTimeEnd");
        String eventTypeStr = request.getParameter("eventType");
        String eventStatusStr = request.getParameter("eventStatus");

        Part filePart = request.getPart("Images");
        Map<String, String> errors = new HashMap<>();

        // Kiểm tra file upload không được để trống
//        if (filePart == null || filePart.getSize() == 0 || filePart.getSubmittedFileName() == null || filePart.getSubmittedFileName().isEmpty()) {
//            errors.put("imageError", "Hình ảnh không được để trống.");
//        }

          String tempImagePath = null;

        if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String tmpUploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR + File.separator + "tmp";
            File tmpUploadDir = new File(tmpUploadPath);
            if (!tmpUploadDir.exists()) {
                tmpUploadDir.mkdirs();
            }

            String fullTempPath = tmpUploadPath + File.separator + fileName;
            filePart.write(fullTempPath);

            tempImagePath = UPLOAD_DIR + "/tmp/" + fileName;
        } else {
            errors.put("imageError", "Hình ảnh không được để trống.");
        }


        // Kiểm tra các trường khác
        errors.putAll(eventService.validateEvent(clubIdStr, eventName, clubDescription, eventTime, eventTimeEnd, eventTypeStr, eventStatusStr));

        if (errors.isEmpty()) {
            // Nếu không có lỗi, xử lý file và thêm sự kiện
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            String imageUrl = UPLOAD_DIR + "/" + fileName;

            int clubId = Integer.parseInt(clubIdStr);
            boolean eventType = "true".equals(eventTypeStr);
            boolean eventStatus = "true".equals(eventStatusStr);
            eventService.addEvent(clubId, eventName, clubDescription, eventTime, eventTimeEnd, imageUrl, eventType, eventStatus, username);
            response.sendRedirect("listEvent?success=true");
        } else {
            // Nếu có lỗi, trả lại form
            request.setAttribute("imagePreviewPath", tempImagePath); 
            request.setAttribute("errors", errors);
            request.setAttribute("clubName", clubIdStr);
            request.setAttribute("eventName", eventName);
            request.setAttribute("clubDescription", clubDescription);
            request.setAttribute("eventTime", eventTime);
            request.setAttribute("eventTimeEnd", eventTimeEnd);
            request.setAttribute("eventType", eventTypeStr);
            request.setAttribute("eventStatus", eventStatusStr);
            List<Club> clubList = eventService.getClubsByUsername(username);
            request.setAttribute("c", clubList);
            request.getRequestDispatcher("/WEB-INF/event/AddEvent.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to add a new event";
    }
}