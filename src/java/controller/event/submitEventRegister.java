/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.event;

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
@WebServlet(name="submitEventRegister", urlPatterns={"/submitEventRegister"})
public class submitEventRegister extends HttpServlet {
   
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
        String eventID = request.getParameter("eventID");
        String clubID = request.getParameter("clubID");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        String userID = request.getParameter("userID");
        String fullname = request.getParameter("name");
                int uid= Integer.parseInt(userID);
                dao dao = new dao();

        int eid= Integer.parseInt(eventID);
        int cid= Integer.parseInt(clubID);
        if(dao.checkeventExist(eid, uid, cid)){
            request.setAttribute("kaka", "bạn đã đăng kí sự kiện trước đó rồi hoặc đã được tham gia sự kiện ");
 response.sendRedirect("Categories?error=true");
        }else{
       
       dao.registerEvent(eid,uid, cid, fullname, email, phone, note);
       request.setAttribute("kaka", " đăng kí thành công và đơn đang được chờ người quản lý xét duyệt !!!");
 response.sendRedirect("Categories?success=true");    }}

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
