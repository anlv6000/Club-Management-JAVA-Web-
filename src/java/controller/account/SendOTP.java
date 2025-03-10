/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.account;

import Constant.OTPggSignUp;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Quan
 */
@WebServlet(name="SendOTP", urlPatterns={"/SendOTP"})
public class SendOTP extends HttpServlet {
   
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
         String code = request.getParameter("code");
      
dao dao = new dao();
String errEmailExist = "";
ggRegister gg = new ggRegister();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
                String email = acc.getEmail();
                String name = acc.getName();
                  HttpSession session = request.getSession();
       
        try {
            if (dao.isGoogleAccountExist(acc.getId())|| dao.checkemailExist(email)) {
                session = request.getSession();
                GoogleAccount a = dao.loginggg(email);
                session.setAttribute("accc", a);
                
                session.setAttribute("name", a.getName());
                session.setAttribute("googleAccount", a);
                request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request, response);
            }//<else 1>
            else {
                String otp = OTPggSignUp.generateOTP(6);
                // Gửi OTP qua email
                boolean isSent = OTPggSignUp.sendOTP(email, otp);
                 
                if (isSent) {
                  session.setAttribute("email", email);
                    session.setAttribute("account", acc);
                    session.setAttribute("otpgg", otp);
                    session.setAttribute("code", code);
                    response.getWriter().write("OTP has been sent to " + email);
                    
                    request.getRequestDispatcher("/WEB-INF/account/OTPggInput.jsp").forward(request, response);
                } else {
                    response.getWriter().write("Failed to send OTP!");
                }
                // Nếu chưa tồn tại, lưu thông tin tài khoản Google vào database
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendOTP.class.getName()).log(Level.SEVERE, null, ex);
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
        HttpSession session = request.getSession();
          dao dao = new dao(); 
        String email=  request.getParameter("email");
        GoogleAccount acc = (GoogleAccount) session.getAttribute("account");
        String otp = request.getParameter("OTP");
              String otpgg=  request.getParameter("otpGG");
              
if(otp.equals(otpgg) ){
     dao.saveGoogleAccount(acc, email);
         GoogleAccount a = dao.loginggg(email);
          request.setAttribute("SUCC", "Register successfully");
           session.setAttribute("googleAccount", acc);
            session.setAttribute("name", acc.getName());
            session.setAttribute("accc", a);
        request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
     }else{
    request.setAttribute("otpgg", otpgg);
        request.setAttribute("erOTP", "ErrOTP");
        request.getRequestDispatcher("/WEB-INF/account/OTPggInput.jsp").forward(request, response);
    }
    
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
