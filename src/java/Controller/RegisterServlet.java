/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");
        
    // Validate username
    if(user ==null || user.trim().isEmpty() || !user.matches("[a-zA-Z0-9_]+")){
        request.setAttribute("messuser", "Invalid username. Only letters, digits, and underscores are allowed.");
        request.getRequestDispatcher("Register.jsp").forward(request, response);
        return;
    }
        //Validate password
        if (pass==null || pass.length() < 8 || !pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*") || !pass.matches(".*[!@#$%^&*].*")) {
            request.setAttribute("messpass", "Password must be at least 8 characters long and contain uppercase, lowercase, digits, and special characters.");
            request.setAttribute("user", user); // Keep username filled in
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }
        if (!pass.equals(re_pass)){
            request.setAttribute("messverify", "Passwords do not match.");
            request.setAttribute("user", user); // Keep username filled in
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }
        
        dao dao = new dao();
        boolean a= dao.checkAccountExist(user);
        if(a == true){
            request.setAttribute("mess", "Username is already taken.");
            request.setAttribute("user", user); // Keep username filled in
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }else{
            String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
            dao.addAcount(user, hashedPassword);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(1800);
            response.sendRedirect("Login.jsp");
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
