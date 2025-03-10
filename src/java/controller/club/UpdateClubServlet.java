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

/**
 *
 * @author admin
 */
@WebServlet("/UpdateClubServlet")
public class UpdateClubServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubID = Integer.parseInt(request.getParameter("clubID"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String imgURL = request.getParameter("imgURL");
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        ClubDAO dao = new ClubDAO();
        dao.updateClub(clubID, name, category, description, isPublic, imgURL);

        response.sendRedirect("ClubServlet");
    }


}

