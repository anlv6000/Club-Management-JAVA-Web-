package controller.account;

import DAO.dao;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.account.AccountService;

@WebServlet(name = "ManageAccount", urlPatterns = {"/ManageAccount"})
public class ManageAccount extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy roles và statuses từ AccountService
            List<String> roles = accountService.getRoles();
            List<String> statuses = accountService.getStatuses();
            request.setAttribute("roles", roles);
            request.setAttribute("statuses", statuses);

            // Lấy tham số từ request
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String keyword = request.getParameter("keyword");

            // Nếu giá trị là "All" thì gán lại thành `null`
            if ("All".equals(role)) role = null;
            if ("All".equals(status)) status = null;

            // Danh sách tài khoản theo điều kiện lọc
            List<User> accounts;
            if ((role == null || role.isEmpty()) && (status == null || status.isEmpty()) && (keyword == null || keyword.isEmpty())) {
                accounts = accountService.getAllAccounts(); // Lấy tất cả tài khoản nếu không có điều kiện lọc
            } else {
                accounts = accountService.searchAccounts(role, status, keyword); // Lọc theo điều kiện
            }

            // Xử lý phân trang
            int page = 1;
            int recordsPerPage = 5;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int start = (page - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, accounts.size());
            List<User> pageAccounts = accounts.subList(start, end);

            // Đặt vào request attribute
            request.setAttribute("accountlist", pageAccounts);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", (int) Math.ceil((double) accounts.size() / recordsPerPage));

            // Chuyển hướng tới JSP
RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/account/Account.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, "Lỗi khi xử lý tài khoản", ex);
            throw new ServletException("Lỗi trong quá trình xử lý dữ liệu tài khoản", ex);
        } catch (Exception ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ManageAccount Servlet for handling account management";
    }
}