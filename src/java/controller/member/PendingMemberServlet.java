/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.member;

import DAO.ClubMemberDAO;
import Model.UserClub;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="PendingMemberServlet", urlPatterns={"/PendingMemberServlet"})
public class PendingMemberServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PendingMemberServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PendingMemberServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/Login.jsp");
            return;
        }

        ClubMemberDAO clubMemberDAO = new ClubMemberDAO();
        int userId = (int) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("role");

        int clubId = -1;
        try {
            clubId = clubMemberDAO.getClubIdByUserId(userId);
            if (clubId == -1) {
                response.sendRedirect("accessDenied.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("accessDenied.jsp");
            return;
        }
        List<UserClub> pendingMembers = clubMemberDAO.getMemberRegistrations(clubId);
         int page = 1;
        int recordsPerPage = 4; // Số tài khoản mỗi trang
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán vị trí bắt đầu và kết thúc danh sách
        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, pendingMembers.size());

        // Cắt danh sách tài khoản theo trang
        List<UserClub> pageAccounts = pendingMembers.subList(start, end);

        // Đưa danh sách mới vào request
        request.setAttribute("pendingMembers", pageAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) pendingMembers.size() / recordsPerPage));


       
        request.setAttribute("pendingMembers", pendingMembers);
        request.setAttribute("userRole", userRole);

        request.getRequestDispatcher("/WEB-INF/member/PendingMembers.jsp").forward(request, response);
    }
     

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
