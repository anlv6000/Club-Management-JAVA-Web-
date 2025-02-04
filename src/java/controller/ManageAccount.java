package Controller;

import DAO.dao;
import Model.Account;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageAccount", urlPatterns = {"/ManageAccount"})
public class ManageAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        dao dao = new dao();
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
    
    String role = request.getParameter("role");
    String status = request.getParameter("status");
    String keyword = request.getParameter("keyword");
    
    try {
        List<Account> accounts;
        if ("All".equals(role) && "All".equals(status) && (keyword == null || keyword.isEmpty())) {
            accounts = dao.getAllAccounts();  
        } else {
            accounts = dao. searchAccounts(role, status, keyword);  
        }

        request.setAttribute("accountlist", accounts);
        request.getRequestDispatcher("Account.jsp").forward(request, response);
    

    } catch (Exception e) {
        request.setAttribute("errorMessage", "An error occurred while fetching account data.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
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
        return "ManageAccount Servlet for handling account management";
    }
}
