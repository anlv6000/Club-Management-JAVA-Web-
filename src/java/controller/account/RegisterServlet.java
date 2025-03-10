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
import Services.SignUp_Validation;
import DAO.dao;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Quan
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        String fullname = request.getParameter("fullname");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");
        SignUp_Validation SignupEr = new SignUp_Validation();
        request.setAttribute("email", email);
                request.setAttribute("user", user);
                        request.setAttribute("pass", pass);
                                request.setAttribute("repass", re_pass);
                                request.setAttribute("fullname", fullname);

HttpSession session = request.getSession();

    // Validate username
   boolean b = false ;
       
        dao dao = new dao();
        boolean a= dao.checkAccountExist(user);
        boolean c=dao.checkemailExist(email);
        
        if(fullname == null || fullname.trim().isEmpty()|| !fullname.matches("[a-zA-Z0-9_ ]+") || fullname.length()>=30)  {
            String erf ="Invalid fullname. Only letters, digits, and underscores are allowed, or too long" ; 
        b=true;
           request.setAttribute("erf", erf);
         request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
       if(user ==null || user.trim().isEmpty() || !user.matches("[a-zA-Z0-9_]+") || fullname.length()>=30){
        String  eru=  "Invalid username. Only letters, digits, and underscores are allowed, or too long" ; 
        b=true;
           request.setAttribute("eru", eru);
         request.getRequestDispatcher("Register.jsp").forward(request, response);
         
       }
         if (pass==null || pass.length() < 8 || !pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*") || !pass.matches(".*[!@#$%^&*].*")) {
           String erp="Password must be at least 8 characters long and contain uppercase, lowercase, digits, and special characters.";
                  b=true;

           request.setAttribute("erp", erp);
           request.getRequestDispatcher("/WEB-INF/auth/Register.jsp").forward(request, response);
            
      } 
         if (!pass.equals(re_pass)){
           String err= "Passwords do not match.";
                   b=true;

                       request.setAttribute("err", err);
           request.getRequestDispatcher("/WEB-INF/auth/Register.jsp").forward(request, response);

          
       }
         if(!email.contains("@")){
             String erre= "email not form!";
                   b=true;

                       request.setAttribute("erre", erre);
           request.getRequestDispatcher("/WEB-INF/auth/Register.jsp").forward(request, response);
         }
        if(a == true || c ==true ){
            request.setAttribute("mess", "Username or Email or fullname is already taken.");
            request.setAttribute("user", user); // Keep username filled in
            
                   request.getRequestDispatcher("/WEB-INF/auth/Register.jsp").forward(request, response);

        }else if(a == false && b ==false && c==false){
           String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
           session.setAttribute("hashedPassword",hashedPassword);
            session.setAttribute("email", email);
                session.setAttribute("user", user);
                session.setAttribute("fullname", fullname);
             String otp = OTPggSignUp.generateOTP(6);
            boolean isSent = OTPggSignUp.sendOTP(email, otp);
            if(isSent){
                session.setAttribute("otpgg", otp);
                session.setMaxInactiveInterval(1800);
                request.getRequestDispatcher("/WEB-INF/account/OTPggInput1.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
