package controller.event;

import Model.Event;
import Model.User;
import DAO.dao;
import DAO.projectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.EventHT.EventService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EditEvent", urlPatterns = {"/editEvent"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditEvent extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private EventService eventService = new EventService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        int id = Integer.parseInt(idRaw);

        projectDao projectDao = new projectDao();
        List<User> listUser = projectDao.getUsersByEventID(id);

        dao dao = new dao();
        Event event = dao.getEventById(id);

        request.setAttribute("listUser", listUser);
        request.setAttribute("o", event);
        request.getRequestDispatcher("/WEB-INF/event/EditEvent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = "";
        if (session != null) {
            User user = (User) session.getAttribute("acc");
            if (user != null) {
                username = user.getUserName();
                System.out.println("Username: " + username);
                request.setAttribute("username", username);
            } else {
                System.out.println("User không tồn tại trong session!");
                response.sendRedirect("login");
                return;
            }
        } else {
            System.out.println("Session không tồn tại!");
            response.sendRedirect("login");
            return;
        }

        String eventIdStr = request.getParameter("id");
        String clubIdStr = request.getParameter("ID");
        String eventName = request.getParameter("eventName");
        String description = request.getParameter("clubDescription");
        String eventTime = request.getParameter("eventTime");
        String eventTimeEnd = request.getParameter("eventTimeEnd");
        String eventTypeStr = request.getParameter("eventType");
        String eventStatusStr = request.getParameter("eventStatus");
        String leaderEventStr = request.getParameter("LeaderEvent");
        String participantCountStr = request.getParameter("NumberOFEvent");
        String currentImageURL = request.getParameter("currentImageURL");

        Part filePart = request.getPart("eventImage");
        Map<String, String> errors = new HashMap<>();

        String imageUrl = currentImageURL;
        if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            imageUrl = UPLOAD_DIR + "/" + fileName;
        }

        errors.putAll(eventService.validateEvent(clubIdStr, eventName, description, eventTime, eventTimeEnd, eventTypeStr, eventStatusStr));

        if (errors.isEmpty()) {
            int eventId = Integer.parseInt(eventIdStr);
            int clubId = Integer.parseInt(clubIdStr);
            boolean eventType = "true".equals(eventTypeStr);
            boolean eventStatus = "true".equals(eventStatusStr);
            Integer leaderEventId = (leaderEventStr != null && !leaderEventStr.isEmpty()) ? Integer.parseInt(leaderEventStr) : null;
            int participantCount = Integer.parseInt(participantCountStr);

            try {
                eventService.updateEvent(eventId, clubId, eventName, description, eventTime, eventTimeEnd, imageUrl, eventType, eventStatus, participantCount, leaderEventId, username);
                response.sendRedirect("listEvent");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Lỗi khi cập nhật sự kiện: " + e.getMessage());
                int eventIdError = Integer.parseInt(eventIdStr);
                dao dao = new dao();
                Event event = dao.getEventById(eventIdError);
                projectDao projectDao = new projectDao();
                List<User> listUser = projectDao.getUsersByEventID(eventIdError);
                request.setAttribute("o", event);
                request.setAttribute("listUser", listUser);
                request.getRequestDispatcher("/WEB-INF/event/EditEvent.jsp").forward(request, response);
            }
        } else {
            int eventId = Integer.parseInt(eventIdStr);
            dao dao = new dao();
            Event event = dao.getEventById(eventId);
            projectDao projectDao = new projectDao();
            List<User> listUser = projectDao.getUsersByEventID(eventId);

            request.setAttribute("errors", errors);
            request.setAttribute("o", event);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("/WEB-INF/event/EditEvent.jsp").forward(request, response);
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    @Override
    public String getServletInfo() {
        return "Servlet to edit an event";
    }
}