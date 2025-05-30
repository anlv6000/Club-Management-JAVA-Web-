/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import DAO.dao;
import Model.Club;
import Model.Event;
import Model.Post;
import Model.Public_club;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Doan Quan
 */
@WebServlet(name = "home", urlPatterns = {"/home"})
public class home extends HttpServlet {

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
        String clubName = request.getParameter("clubName"); // Get clubName from the request
        dao dao = new dao();
        Club c = dao.getClubByName(clubName); // Use clubName to get club details

        if (c != null) {
            int ClubID = c.getClubID();
            List<Event> list1 = dao.getAllEventByClubID(ClubID);
            request.setAttribute("event", list1);
            request.setAttribute("detail", c); // Set the club details as a request attribute
            request.getRequestDispatcher("/WEB-INF/club/PublicClub.jsp").forward(request, response);
        }
        List<Post> postList = dao.publicPost();
request.setAttribute("postList", postList);
        List<Club> list = dao.getTop5();
        
        request.setAttribute("listP", list);
        request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request, response);

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
        dao dao = new dao();

        List<Club> list = dao.getTop5();
        List<Post> postList = dao.publicPost();

        request.setAttribute("postList", postList);

        request.setAttribute("listP", list);
        request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request, response);
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
