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
import Model.Club;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import service.ClubService;

/**
 *
 * @author admin
 */

@WebServlet("/DetailClubServlet")
public class DetailClubServlet extends HttpServlet {
    ClubService clubService = new ClubService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubID = Integer.parseInt(request.getParameter("clubID"));
         List<User> users = clubService.getUserUpdate(clubID);
         List<String> categories = clubService.getCategories();
        Club club = clubService.getClubByID(clubID);
        
        request.setAttribute("club", club);
        request.setAttribute("categories", categories);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/club/detailClub.jsp").forward(request, response);
    }
}


