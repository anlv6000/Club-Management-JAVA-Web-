/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Project;

import DAO.projectDao;
import Model.Club;
import Model.Project;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author User
 */
@WebServlet(name="ListProjectServlet", urlPatterns={"/ListProjectServlet"})
public class ListProjectServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ListProjectServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListProjectServlet at " + request.getContextPath () + "</h1>");
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
        String clubId = request.getParameter("clubId");
        String status = request.getParameter("status");
        String keyword = request.getParameter("keyword");
        String pageRaw = request.getParameter("page");
        String sort = request.getParameter("sort");
        
         HttpSession session = request.getSession();
         String username = (String) session.getAttribute("txtUsername");
        // Xử lý phân trang
        int page = 1;
        if (pageRaw != null && !pageRaw.isEmpty()) {
            try {
                page = Integer.parseInt(pageRaw);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        int offset = (page - 1) * PAGE_SIZE;

        // Gọi DAO để lấy danh sách dự án
        projectDao dao = new projectDao();
        
         int userID = dao.getUserIDByUsername(username);
        List<Integer> clubIDs = dao.getClubIDsByUserID2(userID);
        List<Club> clubList = dao.getClubsByClubIDs(clubIDs);
        
        
        
        
        List<Project> projectList = dao.getFilteredProjects(clubId,clubIDs, status, keyword, offset, PAGE_SIZE, sort);
        int totalCount = dao.countFilteredProjects(clubId, status, keyword);
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        // Lấy danh sách Club cho dropdown
       

        // Truyền dữ liệu về JSP
        request.setAttribute("sort", sort);
        request.setAttribute("ProjectList", projectList);
        request.setAttribute("clubList", clubList);
        request.setAttribute("selectedClubId", clubId);
        request.setAttribute("selectedStatus", status);
        request.setAttribute("keyword", keyword);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Forward đến JSP
        request.getRequestDispatcher("/WEB-INF/project/ProjectList.jsp").forward(request, response);
    }
    

   
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
