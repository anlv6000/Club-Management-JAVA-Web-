/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerPost;

import DAO.ClubDAO;
import DAO.postHT;
import Model.Club;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name="BlogListMemberServlet", urlPatterns={"/BlogListMemberServlet"})
public class BlogListMemberServlet extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogListMemberServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogListMemberServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       int PAGE_SIZE = 5;
       // lay thong tin tu trang jsp 
        String clubId = request.getParameter("clubId");
        String status = request.getParameter("status");
        String keyword = request.getParameter("keyword");
        String pageRaw = request.getParameter("page");
        String sortParam = request.getParameter("sort"); 
        String sort;
        if (sortParam == null || (!sortParam.equals("title_asc") && !sortParam.equals("title_desc") && !sortParam.equals("post_at"))) {
            sort = "post_at"; // Mặc định nếu sort không hợp lệ hoặc không được truyền
        } else {
            sort = sortParam;
        }
        //
         
        ClubDAO dao = new ClubDAO();
        postHT postdao = new postHT();
        
         List<Club> clublist = dao.getAllClubs();
          // su ly phan trang 
        int page = 1;
        if (pageRaw != null && !pageRaw.isEmpty()) {
            try {
                page = Integer.parseInt(pageRaw);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        int offset = (page - 1) * PAGE_SIZE;
        
         
          // Lấy danh sách sự kiện theo trang
        List<Post> PostList = postdao.getFilteredPosts(clubId, status, keyword, offset, PAGE_SIZE, sort);
        int totalCount = postdao.countFilteredPosts(clubId, status, keyword);

        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);
       
        
        request.setAttribute("selectedClubId", clubId);
        request.setAttribute("selectedStatus", status);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sort", sort);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("clubList", clublist);
        request.setAttribute("e", PostList);
        request.getRequestDispatcher("WEB-INF/post/BlogListMember.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
