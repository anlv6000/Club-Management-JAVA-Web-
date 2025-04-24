/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.account;

import Constant.OTPggSignUp;
import DAO.dao;
import Model.GoogleAccount;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.protocol.HTTP;
import service.RegisterByGoogle.GoogleAccountService;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name="welcome", urlPatterns={"/welcome"})
public class welcome extends HttpServlet {
    private final GoogleAccountService googleAccountService = new GoogleAccountService();
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
        HttpSession session = request.getSession();
        
        
//        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
//         if (session == null) {
//        // Chuyển hướng về Login.jsp với thông báo lỗi
//        request.setAttribute("erqqq", "Session của bạn đã hết hạn, vui lòng đăng nhập lại.");
//        request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
//        return; // Dừng xử lý tiếp
//    }
            String code = request.getParameter("code");
        
        GoogleLogin gg = new GoogleLogin();

        // Lấy Access Token từ Google
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
        if (acc == null) {
            request.setAttribute("erqqq", "Lỗi khi lấy thông tin tài khoản Google!");
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
            return;
        } 
         dao dao = new dao();
         GoogleAccount at = dao.loginggg(acc.getEmail());
             List<GoogleAccount> u = dao.listUsert();
                boolean isUserExist = u.stream().anyMatch(users -> users.getId() == at.getId() );
                if (isUserExist) {
                    session.setAttribute("ex", "ex");
                }
                List<GoogleAccount> uk = dao.listuerr();
                boolean isUserExistt = uk.stream().anyMatch(userss -> userss.getId() == at.getId());
                if (isUserExistt) {
                    session.setAttribute("ek", "ek");
                }
        
        
        // Kiểm tra tài khoản trong Database
        GoogleAccount existingAccount = googleAccountService.loginOrRegisterGoogleAccount(acc.getId(), acc.getEmail(), acc.getName(),  request,  response );
        if (existingAccount != null) {
            session.setAttribute("accc", existingAccount);
            session.setAttribute("email", existingAccount.getEmail());
            session.setAttribute("name", existingAccount.getName());
            session.setAttribute("googleAccount", existingAccount);
            session.setMaxInactiveInterval(30000); // Session timeout 30s
           
          u = dao.listUsert();
                isUserExist = u.stream().anyMatch(users -> users.getId() == at.getId() );
                if (isUserExist) {
                    session.setAttribute("ex", "ex");
                }
                 uk = dao.listuerr();
                 isUserExistt = uk.stream().anyMatch(userss -> userss.getId() == at.getId());
                if (isUserExistt) {
                    session.setAttribute("ek", "ek");
                }
            request.getRequestDispatcher("home1").forward(request, response);
        } else {
            request.setAttribute("erqqq", "Account not exist, Sign up now!!");
            request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
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
            Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
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
