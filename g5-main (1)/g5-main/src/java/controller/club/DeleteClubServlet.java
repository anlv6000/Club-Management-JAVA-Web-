/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.club;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import DAO.ClubDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClubService;

/**
 *
 * @author admin
 */
@WebServlet("/DeleteClubServlet")
public class DeleteClubServlet extends HttpServlet {
    ClubService clubService = new ClubService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubID = Integer.parseInt(request.getParameter("clubID"));
        String page = request.getParameter("page");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        

        ClubDAO dao = new ClubDAO();
        clubService.deleteClub(clubID);
        request.getSession().setAttribute("deleteMessage","Club Deleted successfully!");
    response.sendRedirect("ClubServlet");
    }
}

