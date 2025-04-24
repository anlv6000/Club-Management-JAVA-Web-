/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import Constant.OTPggSignUp;
import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;

import DAO.dao;
import Model.User;
import Model.GoogleAccount;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import net.sf.json.JSONObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import service.Login.LoginService;

/**
 *
 * @author Doan Quan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private LoginService authService;

    public LoginServlet() {
        this.authService = new LoginService();
    }

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
            throws ServletException, IOException, SQLException {

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
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        HttpSession session = request.getSession();
        try {
            User a = authService.authenticateUser(user, pass);
            request.setAttribute("users", user);
            request.setAttribute("pass", pass);
            session.setAttribute("a", a);
            if (a == null) {
                request.setAttribute("messerr", "Incorrect user or pass");
                request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                return;
            }

            if (!a.getStatus().equalsIgnoreCase("Active")) {
                request.setAttribute("mess", "Currently your account is Inactive so you can't Login");
                request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                return;
            }

             
            if (!authService.isUserSessionActive(session, a)) {
                if (authService.sendOTP(a, session)) {
                   
                    request.getRequestDispatcher("/WEB-INF/account/OTPggInput2.jsp").forward(request, response);
                } else {
                    request.setAttribute("mess", "Failed to send OTP. Please try again.");
                    request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                }
            } else {
                authService.setupUserSession(session, a);
                dao dao = new dao();
                List<User> u = dao.listUser();
                boolean isUserExist = u.stream().anyMatch(users -> users.getId() == a.getId());
                if (isUserExist) {
                    session.setAttribute("ex", "ex");
                }
                List<User> uk = dao.listuer();
                boolean isUserExistt = uk.stream().anyMatch(userss -> userss.getId() == a.getId());
                if (isUserExistt) {
                    session.setAttribute("ek", "ek");
                }
                request.getRequestDispatcher("home1").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mess", "An error occurred. Please try again later.");
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
        }
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
