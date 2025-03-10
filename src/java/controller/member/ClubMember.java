/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.member;

import DAO.dao;
import Model.User;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import Model.UserClub;
import DAO.ClubMemberDAO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ClubMember", urlPatterns = {"/ClubMember"})
public class ClubMember extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // Lấy session nếu tồn tại
        // Lấy HttpSession từ request
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/Login.jsp");
            return;
        }
        ClubMemberDAO clubMemberDAO = new ClubMemberDAO();
        int userId = (int) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("role");
String accountId = request.getParameter("uid");
       if (accountId != null) {
    
}

        // Lấy clubId từ userId
        int clubId = -1;
        try {
            clubId = clubMemberDAO.getClubIdByUserId(userId);
            if (clubId == -1) {
                // Người dùng không thuộc câu lạc bộ nào
                response.sendRedirect("acessDenied.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("accessDenied.jsp");
            return;
        }

        // Lấy tham số tìm kiếm từ request
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        List<UserClub> officialMembers;
        

        try {
            if ((role != null && !role.isEmpty()) || (status != null && !status.isEmpty()) || (keyword != null && !keyword.isEmpty())) {
                officialMembers = clubMemberDAO.searchMembers(role, status, keyword, clubId);
            } else {
                officialMembers = clubMemberDAO.getOfficialMembers(clubId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            officialMembers = new ArrayList<>();
        }
         int page = 1;
        int recordsPerPage = 4; // Số tài khoản mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán vị trí bắt đầu và kết thúc danh sách
        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, officialMembers.size());

        // Cắt danh sách tài khoản theo trang
        List<UserClub> pageAccounts = officialMembers.subList(start, end);

        // Đưa danh sách mới vào request
        request.setAttribute("officialMembers", pageAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) officialMembers.size() / recordsPerPage));

 request.setAttribute("userRole", userRole);

        
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/memberList.jsp");
        dispatcher.forward(request, response);
    }

   
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
