/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.member;

import DAO.ClubMemberDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ApproveMemberServlet", urlPatterns = {"/ApproveMemberServlet"})
public class ApproveMemberServlet extends HttpServlet {

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
ClubMemberDAO userClubDAO = new ClubMemberDAO();
        if (userIdParam != null && clubIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            int clubId = Integer.parseInt(clubIdParam);

           try {
                userClubDAO.approveMember(userId, clubId);
                // Thêm thông báo thành công
            request.getSession().setAttribute("message", "Chấp nhận thành viên thành công!");
            request.getSession().setAttribute("messageType", "success");
               response.sendRedirect("PendingMemberServlet");
                // Chuyển hướng sau khi cập nhật thành công
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("memberList.jsp");  // Trang lỗi nếu có lỗi xảy ra
            }
           
        } else {
            response.sendRedirect("errorPage.jsp");  // Trang lỗi nếu thiếu tham số
        }
    
        
        
    }
      
        
    
        @Override
        public String getServletInfo
        
            () {
        return "ApproveMemberServlet handles approval of club members";
        }

    }
