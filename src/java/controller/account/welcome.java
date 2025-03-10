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
import org.apache.http.protocol.HTTP;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name="welcome", urlPatterns={"/welcome"})
public class welcome extends HttpServlet {
   
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
//        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
//         if (session == null) {
//        // Chuyển hướng về Login.jsp với thông báo lỗi
//        request.setAttribute("erqqq", "Session của bạn đã hết hạn, vui lòng đăng nhập lại.");
//        request.getRequestDispatcher("/WEB-INF/auth/Login.jsp").forward(request, response);
//        return; // Dừng xử lý tiếp
//    }
            String code = request.getParameter("code");
        //neu nguoi dung huy uy quyen
        HttpSession session = request.getSession();
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount acc =  gg.getUserInfo(accessToken);
        dao dao = new dao();
                String email = acc.getEmail();
                String name = acc.getName();
                
                               if (dao.isGoogleAccountExist(acc.getId())|| dao.checkemailExist(email)) {
                    session = request.getSession();
                     GoogleAccount a = dao.loginggg(email);
                    session.setAttribute("accc", a);

                    session.setAttribute("name", a.getName());
                    session.setAttribute("googleAccount", a);
                    session.setMaxInactiveInterval(30000);
                                        request.getRequestDispatcher("home").forward(request, response);

                }else {
                    String erqqq= "Account not exist , Sign up now!!";
                    request.setAttribute("erqqq",erqqq);
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
