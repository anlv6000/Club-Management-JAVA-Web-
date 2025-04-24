/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerPost;

import DAO.ClubDAO;
import DAO.dao;
import DAO.postHT;
import Model.Club;
import Model.Event;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Post;

/**
 *
 * @author User
 */
@WebServlet(name = "PostList", urlPatterns = {"/postList"})
public class PostList extends HttpServlet {

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
            out.println("<title>Servlet PostList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostList at " + request.getContextPath() + "</h1>");
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
        ClubDAO dao = new ClubDAO();
        postHT postdao = new postHT();
//    List<Post> PostList = postdao.getAllPosts();
        List<Club> clublist = dao.getAllClubs();
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) {
            System.out.println("Message received: " + successMessage);
        }

        int page = 1;
        int pageSize = 6;

        // Kiểm tra nếu người dùng chọn trang khác
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy danh sách sự kiện theo trang
        List<Post> PostList = postdao.getPostByPage(page, pageSize);

        // Đếm tổng số sự kiện để tính số trang
        int totalEvents = postdao.getTotalPosts();  // Cần viết thêm phương thức này trong DAO
        int totalPages = (int) Math.ceil((double) totalEvents / pageSize);
        
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("clubList", PostList);
        request.setAttribute("e", PostList);
        request.getRequestDispatcher("WEB-INF/post/PostList.jsp").forward(request, response);

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
        ClubDAO dao = new ClubDAO();
        postHT postdao = new postHT();
//    List<Post> PostList = postdao.getAllPosts();
        List<Club> clublist = dao.getAllClubs();
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) {
            System.out.println("Message received: " + successMessage);
        }

        int page = 1;
        int pageSize = 6;

        // Kiểm tra nếu người dùng chọn trang khác
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Lấy danh sách sự kiện theo trang
        List<Post> PostList = postdao.getPostByPage(page, pageSize);

        // Đếm tổng số sự kiện để tính số trang
        int totalEvents = postdao.getTotalPosts();  // Cần viết thêm phương thức này trong DAO
        int totalPages = (int) Math.ceil((double) totalEvents / pageSize);
        
        request.setAttribute("action", "postList"); 
        
        
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("clubList", PostList);
        request.setAttribute("e", PostList);
        request.getRequestDispatcher("WEB-INF/post/PostList.jsp").forward(request, response);

    
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
