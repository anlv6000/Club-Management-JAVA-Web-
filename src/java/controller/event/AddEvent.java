/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.event;

import DAO.ClubDAO;
import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import DAO.dao;
import Model.Club;
import Model.Event;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class AddEvent extends HttpServlet {
   
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
            out.println("<title>Servlet AddEvent</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEvent at " + request.getContextPath () + "</h1>");
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
        
        ClubDAO dao = new ClubDAO();
        List<Club> club = dao.getClubs();
        request.setAttribute("c", club);
        request.getRequestDispatcher("AddEvent.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
       String username_session="";
        if (session != null) {
            User user = (User) session.getAttribute("acc"); // Lấy đối tượng User từ session
            if (user != null) {
                 username_session = user. getUserName(); // Lấy Username
                System.out.println("Username: " + username_session);
                request.setAttribute("username", username_session); // Gửi đến JSP
            } else {
                System.out.println("User không tồn tại trong session!");
            }
        } else {
            System.out.println("Session không tồn tại!");
        }
  
 
    


    
        
        
        
      String NameID_raw = request.getParameter("clubName");
      
      int NameID = Integer.parseInt(NameID_raw);
        String eventName = request.getParameter("eventName");
        String clubDescription = request.getParameter("clubDescription");
        String eventTime = request.getParameter("eventTime");
      
        String Images = request.getParameter("Images");
        String contactInfo = request.getParameter("contactInfo");
        String currentTime = getCurrentTime();
      String eventTypeStr = request.getParameter("eventType");
      String eventStatusStr = request.getParameter("eventStatus");
        System.out.println(eventStatusStr);
// Chuyển đổi từ String → Boolean
boolean eventType = "true".equals(eventTypeStr);
boolean eventStatus = "true".equals(eventStatusStr);
        dao dao = new dao();
        int userId = dao.getUserIdByUsername(username_session);
        dao.addEvent(NameID, eventName, clubDescription, eventTime, userId, currentTime , Images, eventStatus, eventType);


        
       response.sendRedirect("listEvent");
    }
private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
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
