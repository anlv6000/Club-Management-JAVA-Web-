/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.dao;
import Model.Event;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class EditEvent extends HttpServlet {
   
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
            out.println("<title>Servlet EditEvent</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditEvent at " + request.getContextPath () + "</h1>");
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
       String id_raw = request.getParameter("id");
        int id =  Integer.parseInt(id_raw);
        dao dao = new DAO.dao();
        Event e = new Event();
        e = dao.getEventById(id);
        request.setAttribute("o", e);
        request.getRequestDispatcher("EditEvent.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        int clubId = Integer.parseInt(request.getParameter("ID"));
        String eventName = request.getParameter("eventName");
        String description = request.getParameter("clubDescription");
        String eventType = request.getParameter("eventType");
        String eventStatus = request.getParameter("eventStatus");
        String eventImageURL = request.getParameter("clubMembers");
        String contactInfo = request.getParameter("contactInfo");
        
        // Chuyển đổi thời gian từ String -> Date
        String eventTimeStr = request.getParameter("eventTime");
        String currentTime = getCurrentTime();
        dao dao = new dao();
        dao.updateEvent( id , clubId, eventName, description,  eventTimeStr, null, currentTime, eventImageURL);
        response.sendRedirect("listEvent");
        
    }
private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
