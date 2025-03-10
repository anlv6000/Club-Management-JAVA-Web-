package controller.finance;

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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "FinanceDetailsServlet", urlPatterns = {"/financeDetail"})
public class FinanceDetailsServlet extends HttpServlet {

    private financeDAO financeDAO;

    @Override
    public void init() {
        financeDAO = new financeDAO();
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

        int financeId = 0;
        try {
            financeId = Integer.parseInt(financeIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid Finance ID format.");
            return;
        }

        if (action == null) {
            showFinanceDetail(request, response, financeId);
        } else {
            try {
                if ("update".equals(action) || "save".equals(action) || "submit".equals(action) || "approve".equals(action) || "reject".equals(action)) {
                    updateFinance(request, response, financeId, action);
                } else if ("delete".equals(action)) {
                    deleteFinance(request, response, financeId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("SQL Error: " + e.getMessage());
            }
        }
    }

    private void showFinanceDetail(HttpServletRequest request, HttpServletResponse response, int financeId)
            throws ServletException, IOException {
        Finance finance = null;
        try {
            finance = financeDAO.selectFinanceById(financeId);
            if (finance == null) {
                response.getWriter().println("No Finance record found for ID: " + financeId);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL error while fetching Finance record: " + e.getMessage());
            return;
        }

        request.setAttribute("finance", finance);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/finance/FinanceDetail.jsp");
        dispatcher.forward(request, response);
    }

    private void updateFinance(HttpServletRequest request, HttpServletResponse response, int financeId, String action)
            throws ServletException, IOException, SQLException {
        // Lấy userId từ session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int sessionUserId = (int) session.getAttribute("userId");

        // Lấy userType từ cơ sở dữ liệu
        String sessionUserType;
        try {
            sessionUserType = financeDAO.getUserTypeByUserId(sessionUserId);
            if (sessionUserType == null) {
                response.getWriter().println("Không thể lấy loại người dùng từ cơ sở dữ liệu.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("SQL Error khi lấy loại người dùng: " + e.getMessage());

            return;
        }

        // Lấy bản ghi tài chính từ cơ sở dữ liệu
        Finance finance = financeDAO.selectFinanceById(financeId);
        if (finance == null) {
            response.getWriter().println("Không tìm thấy bản ghi tài chính với ID: " + financeId);
            return;
        }

        int recordOwnerId = finance.getMemberId();

        // Kiểm tra quyền chỉnh sửa
        if (!sessionUserType.equals("Admin") && !sessionUserType.equals("ClubLeader") && sessionUserId != recordOwnerId) {
            response.getWriter().println("Bạn không có quyền chỉnh sửa bản ghi tài chính này.");
            return;
        }

        // Tiếp tục xử lý logic cập nhật
        String financeName = request.getParameter("financeName");
        String isExpenseParam = request.getParameter("isExpense"); // Lấy giá trị từ form
        String isExpense = "true".equalsIgnoreCase(isExpenseParam) ? "Yes" : "No"; // Chuyển đổi thành Yes/No    
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String details = request.getParameter("details");
        LocalDateTime financeDate = parseLocalDateTime(request.getParameter("financeDate"));

        // Xác định trạng thái từ hành động
        String status = switch (action) {
            case "submit" ->
                "Submitted for Approval";
            case "approve" ->
                "Approve";
            case "reject" ->
                "Reject";
            default ->
                "Saved";
        };

        // Cập nhật bản ghi tài chính
        finance.setFinanceName(financeName);
        finance.setIsExpense(isExpense);
        finance.setAmount(amount);
        finance.setFinanceDate(financeDate);
        finance.setDetails(details);
        finance.setStatus(status);
        financeDAO.updateFinance(finance);

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

        request.getSession().setAttribute("notificationMessage", notificationMessage);
        response.sendRedirect("finance?action=list");
    }

    private void deleteFinance(HttpServletRequest request, HttpServletResponse response, int financeId)
            throws ServletException, IOException, SQLException {
        financeDAO.deleteFinance(financeId);
        response.sendRedirect("finance?action=list");
    }

    private LocalDateTime parseLocalDateTime(String dateTimeStr) {
        DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            return LocalDateTime.parse(dateTimeStr, formatterWithSeconds);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateTimeStr, formatterWithoutSeconds);

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
        return "Servlet to handle finance details";
    }
}
