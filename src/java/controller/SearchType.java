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
import java.util.List;

/**
 *
 * @author User
 */
public class SearchType extends HttpServlet {
   
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
            out.println("<title>Servlet SearchType</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchType at " + request.getContextPath () + "</h1>");
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
             String eventTypeStr = request.getParameter("type");
      
    

boolean eventType = "true".equals(eventTypeStr);

        dao dao = new dao();
         List<Event> event = dao.searchByType(eventType);
  
  

        request.setAttribute("e", event);
        request.getRequestDispatcher("EventList.jsp").forward(request, response);
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
//         String eventTypeStr = request.getParameter("type");
//      
//    
//
//boolean eventType = "true".equals(eventTypeStr);
//
//        dao dao = new dao();
//         List<Event> event = dao.searchByType(eventType);
//  
//  
//
//        request.setAttribute("e", event);
//        request.getRequestDispatcher("EventList.jsp").forward(request, response);
//    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    }

}
