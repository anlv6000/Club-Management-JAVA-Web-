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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name="submiteventRegistration_2", urlPatterns={"/submiteventRegistration_2"})
public class submiteventRegistration_2 extends HttpServlet {
   
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
            out.println("<title>Servlet submiteventRegistration_2</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet submiteventRegistration_2 at " + request.getContextPath () + "</h1>");
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
        String eventID = request.getParameter("eventID");
        String clubID = request.getParameter("clubID");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
       
        String fullname = request.getParameter("name");

                dao dao = new dao();
                int kakaaa = -1;
          HttpSession session = request.getSession();

        int eid= Integer.parseInt(eventID);
        int cid= Integer.parseInt(clubID);  
         kakaaa= dao.checklateEventRegistration(eid);
         if(kakaaa == 0){
             session.setAttribute("notification", "bạn đã quá hạn đăng kí sự kiện này !!");
session.setAttribute("notificationType", "error");
 
         }
        

        
       
       dao.registerEvent_2(eid, cid, fullname, email, phone, note);
       session.setAttribute("notification", " đăng kí thành công và đơn đang được chờ người quản lý xét duyệt !!!");
       session.setAttribute("notificationType", "success");
     
        response.sendRedirect("home1");
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
