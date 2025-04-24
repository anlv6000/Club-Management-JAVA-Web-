/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.member;

import DAO.ClubMemberDAO;
import DAO.dao;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.clubmember.ClubMemberService;
import util.EmailUtility;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="RejectMemberServlet", urlPatterns={"/RejectMemberServlet"})
public class RejectMemberServlet extends HttpServlet {
    private ClubMemberService ClubMemberService;

    @Override
    public void init() {
        ClubMemberService = new ClubMemberService();
    }
   
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
            out.println("<title>Servlet RejectMemberServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RejectMemberServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
          // Lấy tham số từ yêu cầu AJAX
        String userIdParam = request.getParameter("memberId");
        String clubIdParam = request.getParameter("clubId");

        if (userIdParam != null && clubIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            int clubId = Integer.parseInt(clubIdParam);

            try {
                ClubMemberService.rejectMember(userId, clubId);
                HttpSession session = request.getSession();
                session.setAttribute("messages", "Reject member successful!");
                session.setAttribute("messageTypes", "danger");
                response.sendRedirect("ClubMember");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("memberList.jsp");
            }
        } else {
            response.sendRedirect("memberList.jsp");
        }
    
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
