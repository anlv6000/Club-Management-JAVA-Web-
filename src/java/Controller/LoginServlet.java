/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;



import DAO.dao;
import Model.Account;
import Model.GoogleAccount;
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
/**
 *
 * @author Doan Quan
 */
@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
    Account a = dao.login(user, pass);

    if (a == null) {
        request.setAttribute("messerr", "Incorrect user or pass");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        if (BCrypt.checkpw(pass, a.getPassword()) || dao.checkLogin2(user, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("txtUsername", user);
            session.setAttribute("acc", a);
            session.setMaxInactiveInterval(1800);
            request.getRequestDispatcher("Public_ClubServlet").forward(request, response);
        } else {
            request.setAttribute("mess", "Incorrect user or pass");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    // Xử lý đăng nhập bằng Google
    String code = request.getParameter("code");
    String error = request.getParameter("error");

    if (error != null) {
        request.setAttribute("messerr", "Login with Google was canceled.");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    if (code != null) {
        try {
            // Lấy access token từ Google
            String accessToken = GoogleLogin.getToken(code);
            System.out.println(accessToken);
            // Lấy thông tin người dùng từ Google
          // Lấy thông tin người dùng từ Google
            GoogleAccount googleAccount = GoogleLogin.getUserInfo(accessToken);
String email= googleAccount.getEmail();
            if (googleAccount != null) {
                // Kiểm tra xem tài khoản Google đã tồn tại chưa
                if (!dao.isGoogleAccountExist(googleAccount.getId())) {
                    // Nếu chưa tồn tại, lưu thông tin tài khoản Google vào database
                    dao.saveGoogleAccount(googleAccount,email);
                }
                // Thiết lập session và chuyển hướng
                HttpSession session = request.getSession();
                session.setAttribute("googleAccount", googleAccount);
                request.getRequestDispatcher("Public_ClubServlet").forward(request, response);
            } else {
                request.setAttribute("messerr", "Unable to retrieve Google account information.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("messerr", "An error occurred during Google login.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
