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
@WebServlet(name = "ApproveMemberServlet", urlPatterns = {"/ApproveMemberServlet"})
public class ApproveMemberServlet extends HttpServlet {
     private ClubMemberService ClubMemberService;

    @Override
    public void init() {
        ClubMemberService = new ClubMemberService();
    }


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
        processRequest(request, response);
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
        String userIdParam = request.getParameter("memberId");
        String clubIdParam = request.getParameter("clubId");

        if (userIdParam != null && clubIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            int clubId = Integer.parseInt(clubIdParam);

            try {
                 ClubMemberService.approveMember(userId, clubId);
                HttpSession session = request.getSession();
                session.setAttribute("messages", "Approve member successful!");
                session.setAttribute("messageTypes", "success");
                response.sendRedirect("ClubMember");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("memberList.jsp");
            }
        } else {
            response.sendRedirect("memberList.jsp");
        }
    
    }

    @Override
    public String getServletInfo() {
        return "ApproveMemberServlet handles approval of club members";
    }

}
