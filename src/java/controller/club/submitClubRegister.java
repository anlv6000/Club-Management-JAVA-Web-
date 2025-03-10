/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.club;

import DAO.dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name = "submitClubRegister", urlPatterns = {"/submitClubRegister"})
public class submitClubRegister extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);
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
        doPost(request, response);
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
        String uids = request.getParameter("userID");
        String cids = request.getParameter("clubID");
        String note = request.getParameter("note");
        String phone = request.getParameter("phone");
        int uid = Integer.parseInt(uids);
        int cid = Integer.parseInt(cids);

        dao dao = new dao();
        if(dao.checkRegisExist(uid, cid) == true){
            request.setAttribute("kaka"," tài khoản đã tồn tại" );
                  response.sendRedirect("Categories?error=true");

        }else if(dao.checkRegisExist(uid, cid) == false){
        dao.insertbyRegistered(uid, cid, note, phone);
        dao.updateUseridWithClubs(uid,cid);
        dao.updateClubidWithUsers(uid, cid);
       request.setAttribute("kaka", "Đơn đăng kí của bạn đang chờ được người đứng đầu xét duyệt");
 response.sendRedirect("Categories?success=true");        }
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
