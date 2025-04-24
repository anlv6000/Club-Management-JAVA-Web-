package controller.Project;

import DAO.projectDao;
import Model.Project;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddProject")
public class AddProjectServlet extends HttpServlet {

    private final projectDao projectDAO = new projectDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy session
            HttpSession session = request.getSession();

            // Lấy User ID từ session
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                request.setAttribute("errorMessage", "Không tìm thấy User ID trong session.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Lấy Club ID từ User ID
            int clubId = projectDAO.getClubIdByUserId(userId);
            if (clubId <= 0) {
                request.setAttribute("errorMessage", "Không tìm thấy Club ID hợp lệ.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Lấy danh sách các thành viên của câu lạc bộ
            List<User> clubMembers = projectDAO.getMembersByClubId(clubId);
            request.setAttribute("clubMembers", clubMembers);

            // Forward to AddProject page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/AddProject.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình xử lý.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get session
            HttpSession session = request.getSession();

            // Get User ID from session
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                request.setAttribute("errorMessage", "Không tìm thấy User ID trong session.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Get Club ID from User ID
            int clubId = projectDAO.getClubIdByUserId(userId);
            if (clubId <= 0) {
                request.setAttribute("errorMessage", "Không tìm thấy Club ID hợp lệ.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Get list of club members and set it as an attribute
            List<User> clubMembers = projectDAO.getMembersByClubId(clubId);
            request.setAttribute("clubMembers", clubMembers);

            // Retrieve form data
            String name = request.getParameter("Name");
            String code = request.getParameter("Code");
            String fromDateStr = request.getParameter("From_Date");
            String toDateStr = request.getParameter("To_Date");
            String statusStr = request.getParameter("Status");
            String description = request.getParameter("Description");
            String leaderIdStr = request.getParameter("User_Id");

            int leaderId = 0;
            if (leaderIdStr != null && !leaderIdStr.trim().isEmpty()) {
                leaderId = Integer.parseInt(leaderIdStr);
            }

            // Validate input data
            Map<String, String> errors = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = null, toDate = null;

            if (name == null || name.trim().isEmpty()) {
                errors.put("nameError", "Tên dự án không được để trống.");
            }
            if (code == null || code.trim().isEmpty()) {
                errors.put("codeError", "Mã dự án không được để trống.");
            }
            if (fromDateStr == null || fromDateStr.trim().isEmpty()) {
                errors.put("fromDateError", "Ngày bắt đầu không được để trống.");
            } else {
                try {
                    fromDate = dateFormat.parse(fromDateStr);
                } catch (Exception e) {
                    errors.put("fromDateError", "Định dạng ngày bắt đầu không hợp lệ (yyyy-MM-dd).");
                }
            }
            if (toDateStr == null || toDateStr.trim().isEmpty()) {
                errors.put("toDateError", "Ngày kết thúc không được để trống.");
            } else {
                try {
                    toDate = dateFormat.parse(toDateStr);
                } catch (Exception e) {
                    errors.put("toDateError", "Định dạng ngày kết thúc không hợp lệ (yyyy-MM-dd).");
                }
            }
            if (fromDate != null && toDate != null && fromDate.after(toDate)) {
                errors.put("dateError", "Ngày bắt đầu phải trước ngày kết thúc.");
            }
            if (statusStr == null || statusStr.trim().isEmpty()) {
                errors.put("statusError", "Trạng thái không được để trống.");
            }

            // If there are errors, return to AddProject page with error data
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("Name", name != null ? name : "");
                request.setAttribute("Code", code != null ? code : "");
                request.setAttribute("From_Date", fromDateStr != null ? fromDateStr : "");
                request.setAttribute("To_Date", toDateStr != null ? toDateStr : "");
                request.setAttribute("Status", statusStr != null ? statusStr : "");
                request.setAttribute("Description", description != null ? description : "");
                request.setAttribute("User_Id", leaderIdStr != null ? leaderIdStr : "");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/AddProject.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Create a Project object and save it to the database
            boolean status = "Active".equalsIgnoreCase(statusStr);
            Project project = new Project(name, code, fromDate, toDate, leaderId, status, description, clubId);
            boolean isSuccess = projectDAO.addProject(project);

            if (isSuccess) {
                request.setAttribute("successMessage", "Dự án đã được thêm thành công!");
                List<Project> projectList = projectDAO.getAllProjects();
                request.setAttribute("ProjectList", projectList);
                response.sendRedirect("ListProject");

            } else {
                request.setAttribute("errorMessage", "Thêm dự án thất bại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình xử lý.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/project/errorP.jsp");
            dispatcher.forward(request, response);
        }
    }
}
