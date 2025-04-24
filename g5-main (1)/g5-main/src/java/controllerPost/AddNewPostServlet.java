package controllerPost;

import DAO.projectDao;
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
import service.PostHT.PostService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddNewPostServlet", urlPatterns = {"/AddNewPostServlet"})
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5, // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class AddNewPostServlet extends HttpServlet {

    private PostService postService = new PostService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("acc") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("acc");
        int userId = user.getId();

        try {
            List<Club> clubList = postService.getClubsByUserId(userId);
            request.setAttribute("clubList", clubList);
            request.getRequestDispatcher("/WEB-INF/post/AddPost.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi lấy danh sách câu lạc bộ: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/post/AddPost.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("acc") == null) {
            request.setAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User user = (User) session.getAttribute("acc");
        int userId = user.getId();

        // Lấy dữ liệu từ form
        String title = request.getParameter("title");
        String status = request.getParameter("status");
        String content = request.getParameter("content");
        String clubIdStr = request.getParameter("clubId");

        // Xử lý file hình ảnh
        String imageUrl = null;
        Part filePart = request.getPart("image");
        Map<String, String> errors = new HashMap<>();

        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String uniqueFileName = fileNameWithoutExtension + "_" + System.currentTimeMillis() + fileExtension;

            String uploadPath = getServletContext().getRealPath("") + "uploads";
            Files.createDirectories(Paths.get(uploadPath));
            String filePath = uploadPath + "/" + uniqueFileName;

            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                imageUrl = "uploads/" + uniqueFileName;
            } catch (IOException e) {
                e.printStackTrace();
                errors.put("imageError", "Lỗi khi upload file: " + e.getMessage());
            }
        } else {
            errors.put("imageError", "Vui lòng chọn một file hình ảnh!");
        }

        // Validation
        errors.putAll(postService.validatePost(title, status, content, clubIdStr, imageUrl));

        if (errors.isEmpty()) {
            int clubId = Integer.parseInt(clubIdStr);
            try {
                postService.addPost(userId, clubId, imageUrl, title, status, content, 0);
                request.setAttribute("message", "Thêm bài đăng thành công!");
                response.sendRedirect("BlogListMember");
            } catch (SQLException e) {
                e.printStackTrace();
                errors.put("dbError", "Lỗi khi lưu bài đăng vào cơ sở dữ liệu: " + e.getMessage());
                request.setAttribute("errors", errors);
                loadClubsAndForward(request, response, userId);
            }
        } else {
            request.setAttribute("errors", errors);
            loadClubsAndForward(request, response, userId);
        }
    }

    // Phương thức phụ để tải lại danh sách câu lạc bộ và forward về JSP
    private void loadClubsAndForward(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {
        try {
            List<Club> clubList = postService.getClubsByUserId(userId);
            request.setAttribute("clubList", clubList);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải danh sách câu lạc bộ: " + e.getMessage());
        }
        request.setAttribute("title", request.getParameter("title"));
        request.setAttribute("status", request.getParameter("status"));
        request.setAttribute("content", request.getParameter("content"));
        request.setAttribute("clubId", request.getParameter("clubId"));
        request.getRequestDispatcher("/WEB-INF/post/AddPost.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to add a new post";
    }
}