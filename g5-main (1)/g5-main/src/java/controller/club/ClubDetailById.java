/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.club;

import DAO.dao;
import Model.Club;
import Model.Event;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name="ClubDetailById", urlPatterns={"/ClubDetailById"})
public class ClubDetailById extends HttpServlet {
   
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
        String clubIds = request.getParameter("clubid");
        dao dao = new dao();
        String kaka = "";
        String kakaka = "";
        int clubId= Integer.parseInt(clubIds);
        Club c = dao.getClubById(clubId);
        List<Post> listPost = dao.getRecentPost(clubId);
        if(listPost ==null ||listPost.isEmpty() ){
             kaka += "không có bài post nào !!";
        }
        List<Event> eventList =dao.getAllEventByClubID(clubId);
        if(eventList == null || eventList.isEmpty()){
              kakaka += "không có bài event nào !!";
        }
        request.setAttribute("eventList", eventList);
         request.setAttribute("listPost", listPost);
         request.setAttribute("kaka", kaka);
         request.setAttribute("kakaka", kakaka);
        request.setAttribute("c", c);
        request.getRequestDispatcher("/WEB-INF/club/ClubDetailll.jsp").forward(request, response);
        
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
