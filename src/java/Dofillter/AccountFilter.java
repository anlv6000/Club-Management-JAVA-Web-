/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Dofillter;

import DAO.dao;
import Model.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebFilter(filterName = "AccountFilter", urlPatterns = {"/ManageAccount"})
public class AccountFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        dao dao = new dao();

        // Lấy thông tin lọc từ request
        String role = req.getParameter("role");
        String status = req.getParameter("status");
        String keyword = req.getParameter("keyword");
String accountId = request.getParameter("uid");
        if (accountId != null) {

            try {
                if (dao.deleteAccount(accountId)) {
                    request.setAttribute("mess", "tài khoản xóa thành công!");
                } else {
                    request.setAttribute("mess", "xóa thất bại.");
                }
            } catch (Exception e) {
                request.setAttribute("mess", "lỗi khi xóa.");
            }
        }
        List<User> accounts = null;
        if ((role == null || role.isEmpty()) && (status == null || status.isEmpty()) && (keyword == null || keyword.isEmpty())) {
            accounts = dao.getAllAccounts();
        } else {
            try {
                accounts = dao.searchAccounts(role, status, keyword);
            } catch (SQLException ex) {
                Logger.getLogger(AccountFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Đặt danh sách tài khoản vào request
        request.setAttribute("accountlist", accounts);

        // Chuyển tiếp request đến Servlet hoặc JSP tiếp theo
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
    
}
