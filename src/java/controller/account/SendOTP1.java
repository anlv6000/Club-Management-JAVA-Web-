/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import DAO.dao;
import Model.GoogleAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name = "SendOTP1", urlPatterns = {"/SendOTP1"})
public class SendOTP1 extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendOTP1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendOTP1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();

        String otp = request.getParameter("OTP");
        String otpgg = request.getParameter("otpGG");
        
        String user = (String) session.getAttribute("user");
        String fullname = (String) session.getAttribute("fullname");
        String email = (String) session.getAttribute("email");
String hashedPassword= (String) session.getAttribute("hashedPassword");
        if (otp.equals(otpgg)) {

            request.setAttribute("SUCC", "Register successfully");

 
  dao.addAcount(user, hashedPassword, email,fullname);
   session.setMaxInactiveInterval(1800);
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
        } else {
            request.setAttribute("otpgg", otpgg);
            request.setAttribute("erOTP", "ErrOTP");
            request.getRequestDispatcher("/WEB-INF/account/OTPggInput1.jsp").forward(request, response);
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
