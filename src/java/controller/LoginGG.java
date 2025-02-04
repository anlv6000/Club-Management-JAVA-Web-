/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
<<<<<<< HEAD:src/java/controller/LoginGG.java
import java.util.logging.Level;
import java.util.logging.Logger;
=======
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java
import net.sf.json.JSONException;

/**
 *
 * @author Doan Quangb ngfgfjgfjfggfggf
 */
@WebServlet(name = "LoginGG", urlPatterns = {"/LoginGG"})
public class LoginGG extends HttpServlet {

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
<<<<<<< HEAD:src/java/controller/LoginGG.java
            throws ServletException, IOException, SQLException {
=======
            throws ServletException, IOException {
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java
        response.setContentType("text/html;charset=UTF-8");
        // Xử lý đăng nhập bằng Google
        String code = request.getParameter("code");
        String error = request.getParameter("error");

        if (error != null) {
            request.setAttribute("messerr", "Login with Google was canceled.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }

        if (code != null) {
<<<<<<< HEAD:src/java/controller/LoginGG.java
            
=======
            try {
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java
                // Lấy access token từ Google
                String accessToken = GoogleLogin.getToken(code);

                // Lấy thông tin người dùng từ Google
                // Lấy thông tin người dùng từ Google
                GoogleAccount googleAccount = GoogleLogin.getUserInfo(accessToken);
                dao dao = new dao();
                String email = googleAccount.getEmail();
                String name = googleAccount.getName();
                if (dao.isGoogleAccountExist(googleAccount.getId())) {
                    request.setAttribute("erexisst", "tai khoan da ton tai");
                } else {
                    // Nếu chưa tồn tại, lưu thông tin tài khoản Google vào database
                    dao.saveGoogleAccount(googleAccount, email);
                    HttpSession session = request.getSession();

                    session.setAttribute("name", googleAccount.getName());
                    session.setAttribute("googleAccount", googleAccount);
                    request.getRequestDispatcher("Home.jsp").forward(request, response);
                }

                // Thiết lập session và chuyển hướng
<<<<<<< HEAD:src/java/controller/LoginGG.java
           
=======
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("messerr", "Network error. Please try again.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                request.setAttribute("messerr", "Session expired. Please log in again.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } catch (NullPointerException e) {
                e.printStackTrace();

                String accessToken = GoogleLogin.getToken(code);
                GoogleAccount googleAccount = GoogleLogin.getUserInfo(accessToken);
                request.setAttribute("messerr", "Unexpected error: Missing user information." + googleAccount);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("messerr", "An unexpected error occurred.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java

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
<<<<<<< HEAD:src/java/controller/LoginGG.java
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginGG.class.getName()).log(Level.SEVERE, null, ex);
        }
=======
        processRequest(request, response);
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java
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
<<<<<<< HEAD:src/java/controller/LoginGG.java
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginGG.class.getName()).log(Level.SEVERE, null, ex);
        }
=======
        processRequest(request, response);
>>>>>>> fd702484aa86f26afacfef749177bb05115a7d64:src/java/Controller/LoginGG.java
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
