/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

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

import service.RegisterByGoogle.OTPService;
import service.RegisterByGoogle.OTPVerificationService;

@WebServlet(name = "SendOTP", urlPatterns = {"/SendOTP"})
public class SendOTP extends HttpServlet {

    private OTPService otpService;
    private OTPVerificationService otpVerificationService;

    public SendOTP() {
        this.otpService = new OTPService();
        this.otpVerificationService = new OTPVerificationService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {

            return;
        }

        try {
            boolean otpSent = otpService.processOTPRequest(code, session);
            if (otpSent) {
                request.getRequestDispatcher("/WEB-INF/account/OTPggInput.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Account already exists");
                request.getRequestDispatcher("/WEB-INF/auth/Register.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendOTP.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String otpInput = request.getParameter("OTP");

        if (email == null || otpInput == null || email.isEmpty() || otpInput.isEmpty()) {
            request.setAttribute("erOTP", "Invalid input. Please enter all required fields.");
            request.getRequestDispatcher("/WEB-INF/account/OTPggInput.jsp").forward(request, response);
            return;
        }

        try {
            boolean isVerified = otpVerificationService.verifyOTP(email, otpInput, session);
            if (isVerified) {
                request.setAttribute("SUCC", otpVerificationService.success());
                request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
            } else {
                request.setAttribute("erOTP", "Invalid OTP. Please try again.");
                request.getRequestDispatcher("/WEB-INF/account/OTPggInput.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendOTP.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public String getServletInfo() {
        return "Handles OTP processing and verification.";
    }
}
