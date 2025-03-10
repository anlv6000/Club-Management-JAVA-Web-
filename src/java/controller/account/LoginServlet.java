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

/**
 *
 * @author Doan Quan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        dao dao = new dao();
        User a = (User) dao.login(user, pass);
        request.setAttribute("users", user);
        request.setAttribute("pass", pass);

        if (a == null) {
            request.setAttribute("messerr", "Incorrect user or pass");
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);

        } else {
            if (BCrypt.checkpw(pass, a.getPassword()) || dao.checkLogin2(user, pass)) {
                if (a.getStatus().equals("Active")) {
                    HttpSession session = request.getSession();

                    session.setAttribute("txtUsername", user);
                    session.setAttribute("acc", a);
                    session.setAttribute("userId", a.getId());

                    session.setAttribute("role", a.getRole()); // Lưu quyền của user
                    session.setMaxInactiveInterval(900);

                    request.getRequestDispatcher("Public_ClubServlet").forward(request, response);
                } else {
                    request.setAttribute("mess", "Curently your account is Inactive so you can't Login");
                    request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mess", "Incorrect user or pass");
                request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
            }
        }

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
        dao dao = new dao();
        User a = (User) dao.login(user, pass);
        request.setAttribute("users", user);
        request.setAttribute("pass", pass);

        if (a == null) {
            request.setAttribute("messerr", "Incorrect user or pass");
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);

        } else {
            try {
                if (BCrypt.checkpw(pass, a.getPassword()) || dao.checkLogin2(user, pass)) {
                    HttpSession session = request.getSession();
                    if (a.getStatus().equalsIgnoreCase("Active")) {
                        ArrayList<User> listuser = (ArrayList<User>) session.getAttribute("listuser");
                        if (listuser == null) {
                            listuser = new ArrayList<>();
                        }

                        boolean isUserExists = listuser.stream().anyMatch(u -> u.getUserName().equals(a.getUserName()));
                        if (!isUserExists) {
                            // Tạo OTP và gửi email
                            String otp = OTPggSignUp.generateOTP(6);
                            boolean isSent = OTPggSignUp.sendOTP(a.getEmail(), otp);
                            if (isSent) {
                                session.setAttribute("otpgg", otp);
                                session.setAttribute("email", a.getEmail());
                                session.setAttribute("acc", a);
                                session.setAttribute("txtUsername", a.getUserName());
                                listuser.add(a);
                                session.setAttribute("userId", a.getId());

                                session.setAttribute("listuser", listuser);
                                session.setAttribute("role", a.getRole());
                                session.setMaxInactiveInterval(3000);
                                request.getRequestDispatcher("/WEB-INF/account/OTPggInput2.jsp").forward(request, response);
                            }
                        } else {
                            // Nếu user đã tồn tại trong session, giữ nguyên thông tin đăng nhập
                            session.setAttribute("txtUsername", a.getUserName());
                            session.setAttribute("acc", a);
                            session.setAttribute("email", a.getEmail());
                            session.setAttribute("role", a.getRole());
                            session.setAttribute("listuser", listuser);
                            session.setAttribute("userId", a.getId());
                            session.setMaxInactiveInterval(3000);
                            request.getRequestDispatcher("home").forward(request, response);
                        }
                    } else {
                        request.setAttribute("mess", "Curently your account is Inactive so you can't Login");
                        request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Incorrect user or pass");
                    request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
