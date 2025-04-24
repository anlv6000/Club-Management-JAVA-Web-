package controller.finance;

import service.finance.FinanceDetailService;
import DAO.financeDAO;
import Model.Finance;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@WebServlet(name = "FinanceDetailsServlet", urlPatterns = {"/financeDetail"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class FinanceDetailsServlet extends HttpServlet {

    private FinanceDetailService financeDetailService;

    @Override
    public void init() {
        financeDetailService = new FinanceDetailService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String financeIdStr = request.getParameter("financeId");

        if (financeIdStr == null || financeIdStr.trim().isEmpty()) {
            response.getWriter().println("Finance ID is required.");
            return;
        }

        int financeId;
        try {
            financeId = Integer.parseInt(financeIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid Finance ID format.");
            return;
        }

        if (action == null || action.isEmpty()) {
            showFinanceDetail(request, response, financeId);
        } else {
            switch (action) {
                case "save", "submit", "approve", "reject" ->
                    updateFinanceWithImage(request, response, financeId, action);
                case "delete" ->
                    deleteFinance(request, response, financeId);
                default ->
                    response.getWriter().println("Invalid action: " + action);
            }
        }
    }

    private void showFinanceDetail(HttpServletRequest request, HttpServletResponse response, int financeId)
            throws ServletException, IOException {
        try {
            Finance finance = financeDetailService.getFinanceById(financeId);
            if (finance == null) {
                response.getWriter().println("No Finance record found for ID: " + financeId);
                return;
            }

            request.setAttribute("finance", finance);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/finance/FinanceDetail.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL error while fetching Finance record: " + e.getMessage());
        }
    }

    private void updateFinanceWithImage(HttpServletRequest request, HttpServletResponse response, int financeId, String action)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        try {
            String financeName = request.getParameter("financeName");
            String isExpense = request.getParameter("isExpense");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            String details = request.getParameter("details");
            String financeDate = request.getParameter("financeDate");

            // Xử lý upload ảnh nếu có
            Part filePart = request.getPart("newProofImage");
            String newImgurl = null;

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                newImgurl = "uploads/" + fileName; // Lưu đường dẫn ảnh mới
            }

            // Cập nhật dữ liệu và trạng thái
            financeDetailService.updateFinanceWithImage(financeId, financeName, isExpense, amount, details, financeDate, action, newImgurl);

            // Tạo thông báo trạng thái
            String notificationMessage = switch (action) {
                case "submit" ->
                    "Finance submitted successfully!";
                case "approve" ->
                    "Finance approved successfully!";
                case "reject" ->
                    "Finance rejected.";
                default ->
                    "Finance saved successfully!";
            };

            session.setAttribute("notificationMessage", notificationMessage);

            // Quay về trang danh sách
            response.sendRedirect("finance?action=list");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL Error: " + e.getMessage());
        }
    }

    private void deleteFinance(HttpServletRequest request, HttpServletResponse response, int financeId)
            throws ServletException, IOException {
        try {
            financeDetailService.deleteFinance(financeId);
            response.sendRedirect("finance?action=list");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL Error: " + e.getMessage());
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
}
