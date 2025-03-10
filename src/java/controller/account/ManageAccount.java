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
import java.util.List;

@WebServlet(name = "ManageAccount", urlPatterns = {"/ManageAccount"})
public class ManageAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> accounts = (List<User>) request.getAttribute("accountlist");

        // Lấy số trang hiện tại từ request, mặc định là 1 nếu không có
        int page = 1;
        int recordsPerPage = 5; // Số tài khoản mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán vị trí bắt đầu và kết thúc danh sách
        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, accounts.size());

        // Cắt danh sách tài khoản theo trang
        List<User> pageAccounts = accounts.subList(start, end);

        // Đưa danh sách mới vào request
        request.setAttribute("accountlist", pageAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) accounts.size() / recordsPerPage));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/account/Account.jsp");
        dispatcher.forward(request, response);
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
