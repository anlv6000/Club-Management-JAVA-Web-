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
@WebServlet("/DeactiveClubServlet")
public class DeactiveClubServlet extends HttpServlet {
    ClubService clubService = new ClubService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubID = Integer.parseInt(request.getParameter("clubID"));

        
        clubService.deactiveClub(clubID);
        request.getSession().setAttribute("deactiveMessage","Club Deactived successfully!");
        response.sendRedirect("ClubServlet");
    }
}

