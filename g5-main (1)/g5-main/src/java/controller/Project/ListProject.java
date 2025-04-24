/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Project;

import DAO.projectDao;
import Model.Club;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Project;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name="ListProject", urlPatterns={"/ListProject"})
public class ListProject extends HttpServlet {
   
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
            out.println("<title>Servlet ListProject</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListProject at " + request.getContextPath () + "</h1>");
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
        
         HttpSession session = request.getSession();
            String username = (String) session.getAttribute("txtUsername");
       int page = 1;
       int pageSize = 5;  
        String pageParam = request.getParameter("page");
          if (pageParam != null) {
        try {
            page = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            page = 1; // nếu bị lỗi thì trở về trang 1
        }
    }   
            
            
       projectDao dao = new projectDao();
       int userID = dao.getUserIDByUsername(username);
       List<Integer> clubIDs = dao.getClubIDsByUserID2(userID);
        List<Club> clubList = dao.getClubsByClubIDs(clubIDs);
       
       List<Project> ProjectList = dao.getProjectsByPageAndClubIDs(page, pageSize, clubIDs);
       
        int totalEvents = dao.getTotalProjectsByClubIDs(clubIDs);  // Cần viết thêm phương thức này trong DAO
        int totalPages = (int) Math.ceil((double) totalEvents / pageSize);
       
       request.setAttribute("currentPage", page);
       request.setAttribute("totalPages", totalPages);
       request.setAttribute("clubList",  clubList);
       request.setAttribute("ProjectList",  ProjectList);
//        request.getRequestDispatcher("/WEB-INF/project/Project.jsp").forward(request, response);
         request.getRequestDispatcher("/WEB-INF/project/ProjectList.jsp").forward(request, response);
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
