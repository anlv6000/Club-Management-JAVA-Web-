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
import Model.TokenFogotPass;
import Model.*;

import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import static org.apache.coyote.http11.Constants.a;

/**
 *
 * @author HP
 */
@WebServlet(name="requestPassword", urlPatterns={"/requestPassword"})
public class requestPassword extends HttpServlet {
   private String emailErrorMessage = "";
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
                request.getRequestDispatcher("/WEB-INF/auth/requestPassword.jsp").forward(request, response);

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
        dao dao  = new dao();
        String email = request.getParameter("email");
        //email co ton tai trong db
        User a = (User) dao.getUserByEmail(email);
        if(a == null) {
            request.setAttribute("mess", "email khong ton tai");
                request.getRequestDispatcher("/WEB-INF/auth/requestPassword.jsp").forward(request, response);
            return;
        }
        resetService service = new resetService();
        String token = service.generateToken();
        String linkReset = "http://localhost:8080/club_manaaaa/resetPassword?token="+token;
        
        TokenFogotPass newTokenForget = new TokenFogotPass(a.getId() , false, token, service.expireDateTime());
        //send link to this email
        dao daoToken = new dao();
        boolean isInsert = daoToken.insertTokenForget(newTokenForget);
        if(!isInsert) {
            request.setAttribute("mess", "have error in server"+newTokenForget);
                request.getRequestDispatcher("/WEB-INF/auth/requestPassword.jsp").forward(request, response);
            return;
        }
        boolean isSend = sendEmail(email, linkReset, a.getUserName());
        if (!isSend) {
    request.setAttribute("mess", "Cannot send request. Check error logs for details."+ emailErrorMessage);
     
                request.getRequestDispatcher("/WEB-INF/auth/requestPassword.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mess", "send request success");
                request.getRequestDispatcher("/WEB-INF/auth/requestPassword.jsp").forward(request, response);
    
    }
    
    public boolean sendEmail(String to, String link, String name) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Sử dụng TLS 1.2
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Tin tưởng vào server Gmail

    String from = "xuanquanyd@gmail.com";
    String password = "skgt waor gqiv jpkr";  // Mật khẩu ứng dụng

    Authenticator auth = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    };

    Session session = Session.getInstance(props, auth);
    try {
        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/html; charset=UTF-8");
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("Reset Password");

        String content = "<h1>Hello " + name + "</h1>" +
                         "<p>Click the link to reset your password:</p>" +
                         "<p><a href=\"" + link + "\">Click here</a></p>";
        msg.setContent(content, "text/html; charset=UTF-8");

        Transport.send(msg);
        System.out.println(" Send successfully");
        return true;
    } catch (Exception e) {
        System.out.println(" Send error: " + e.getMessage());
        e.printStackTrace();
        return false;
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
