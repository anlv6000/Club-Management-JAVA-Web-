/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.club;

import DAO.ClubDAO;
import DAO.dao;
import Model.Club;
import Model.Event;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quandxnunxi28
 */
@WebServlet(name = "ClubListUser", urlPatterns = {"/ClubListUser"})
public class ClubListUser extends HttpServlet {

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
        String category = request.getParameter("category");
        if (category == null || category.trim().isEmpty()) {
            category = "all"; // Giá trị mặc định nếu không có category
        }

        dao dao = new dao();
        dao daos = new dao();
        String kaka = "all";
        List<Club> listClubs; // Khai báo listClubs trước
        if (category.equalsIgnoreCase("all")) {
            listClubs = dao.getAllClubs(); // Gán danh sách tất cả câu lạc bộ
        } else {
            listClubs = dao.getClubWithCategory(category); // Gán danh sách theo category
        }

        List<Club> listClub = dao.getCategory();
        List<Event> listEvent = daos.gettop3EventPublic();
        request.setAttribute("listEvent", listEvent);
        request.setAttribute("listClub", listClub);
        request.setAttribute("listClubs", listClubs);
        request.getRequestDispatcher("/WEB-INF/club/Register_Member1.jsp").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
