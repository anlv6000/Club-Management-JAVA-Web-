package controller.finance;

import DAO.financeDAO;
import Model.Finance;
import database.DBContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/finance")
public class FinanceServlet extends HttpServlet {

    private financeDAO financeDAO;

    @Override
    public void init() {
        financeDAO = new financeDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra trạng thái cài đặt trước khi cho phép truy cập
        if (!isFinanceAccessActive()) {
            response.sendRedirect("accessDenied.jsp");
            return;
        }

        // Lấy HttpSession từ request
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/Login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        // Lấy clubId từ userId
        int clubId = -1;
        try {
            clubId = financeDAO.getClubIdByUserId(userId);
            if (clubId == -1) {
                // Người dùng không thuộc câu lạc bộ nào
                response.sendRedirect("acessDenied.jsp");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("accessDenied.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listFinances(request, response, clubId);
                    break;
                
                case "insert":
                    insertFinance(request, response, clubId, userId);
                    break;
                // Thêm các case khác nếu cần
                default:
                    listFinances(request, response, clubId);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private boolean isFinanceAccessActive() {
        boolean isActive = false;
        try (Connection conn = new DBContext().getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement("SELECT Status FROM Settings WHERE SettingName = 'FinanceAccess'"); 
            ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String status = rs.getString("Status");
                isActive = "Active".equalsIgnoreCase(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Có thể ghi log lỗi hoặc xử lý phù hợp
        }
        return isActive;
    }

    private void listFinances(HttpServletRequest request, HttpServletResponse response, int clubId)
            throws SQLException, IOException, ServletException {

        int currentPage = 1;
        int recordsPerPage = 6;

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int start = (currentPage - 1) * recordsPerPage;

        List<Finance> financeList = financeDAO.selectFinancesByClubWithPagination(clubId, start, recordsPerPage);

        int totalRecords = financeDAO.getTotalFinanceCountByClub(clubId);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("financeList", financeList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("FinanceList.jsp");
        dispatcher.forward(request, response);
    }



    private void insertFinance(HttpServletRequest request, HttpServletResponse response, int clubId, int userId)
            throws SQLException, IOException, ServletException {
        // Lấy dữ liệu từ form
        String financeName = request.getParameter("finance_name");
        String isExpense = request.getParameter("is_expense");
        String financeDateStr = request.getParameter("finance_date");
        String details = request.getParameter("details");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime financeDate = LocalDateTime.parse(financeDateStr, formatter);

        // Tạo đối tượng Finance
        Finance finance = new Finance();
        finance.setClubId(clubId);
        finance.setMemberId(userId);
        finance.setFinanceName(financeName);
        finance.setIsExpense(isExpense);
        finance.setAmount(amount);
        finance.setFinanceDate(financeDate);
        finance.setDetails(details);

        // Gọi DAO để insert
        financeDAO.insertFinance(finance);

        // Chuyển hướng về danh sách
        response.sendRedirect("finance");
    }

    // Các phương thức khác như updateFinance, deleteFinance nếu cần
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
