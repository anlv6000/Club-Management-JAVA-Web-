package controller.account;

import DAO.dao;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateAccount", urlPatterns = {"/UpdateAccount"})
public class UpdateAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // HTTP GET method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // HTTP POST method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         dao dao = new dao();
        String uid = request.getParameter("accountId");
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");
        String status = request.getParameter("status");
        String text = request.getParameter("text");

        try {
            User account = new User(Integer.parseInt(uid), username, email, userType , fullname, status, text);
            boolean isUpdated = dao.updateAccount(account);

            if (isUpdated) {
                request.setAttribute("message", "Cập nhật tài khoản thành công!");
                  response.sendRedirect("ManageAccount");

            } else {
                request.setAttribute("error", "Cập nhật thất bại. Vui lòng thử lại!");
                       request.getRequestDispatcher("/WEB-INF/account/AccountDetail.jsp").forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace(); 
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xảy ra khi cập nhật tài khoản");
        }


    }

    // Returns a short description of the servlet.
    @Override
    public String getServletInfo() {
        return "Displays account information and allows updates";
    }
}
