/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.member;

import DAO.dao;
import Model.User;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import Model.UserClub;
import DAO.ClubMemberDAO;
import java.util.ArrayList;
import service.clubmember.ClubMemberService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ClubMember", urlPatterns = {"/ClubMember"})
public class ClubMember extends HttpServlet {

    private ClubMemberService ClubMemberService;

    @Override
    public void init() {
        ClubMemberService = new ClubMemberService();
    }

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
        // Lấy session nếu tồn tại
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String userRole = (String) session.getAttribute("role");

        try {
            // Lấy danh sách câu lạc bộ
            List<UserClub> clubs = ClubMemberService.getClubsByUserId(userId);
            request.setAttribute("clubs", clubs);

            // Lấy clubId từ request thay vì cố định
            String clubIdParam = request.getParameter("club");
            int clubId;

            if (clubIdParam != null && !clubIdParam.isEmpty()) {
                clubId = Integer.parseInt(clubIdParam); // Lấy clubId từ dropdown
            } else {
                clubId = ClubMemberService.getClubIdByUserId(userId); // Giá trị mặc định
            }

            // Cập nhật tên câu lạc bộ
            String clubName = ClubMemberService.getClubNameById(clubId);
            request.setAttribute("clubName", clubName);

            // Lấy danh sách vai trò và trạng thái
            List<String> roles = ClubMemberService.getRoles();
            List<String> statuses = ClubMemberService.getStatuses();
            request.setAttribute("roles", roles);
            request.setAttribute("statuses", statuses);

            // Lấy tham số tìm kiếm từ request
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String keyword = request.getParameter("keyword");

            if ("All".equals(role)) {
                role = null;
            }
            if ("All".equals(status)) {
                status = null;
            }

            // Lấy danh sách thành viên chính thức theo clubId từ dropdown
            List<UserClub> officialMembers;
            if (role != null || status != null || keyword != null) {
                officialMembers = ClubMemberService.searchMembers(role, status, keyword, clubId);
            } else {
                officialMembers = ClubMemberService.getOfficialMembers(clubId);
            }

            // Phân trang danh sách thành viên
            int page = 1;
            int recordsPerPage = 4;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int start = (page - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, officialMembers.size());
            List<UserClub> pageAccounts = officialMembers.subList(start, end);

            request.setAttribute("officialMembers", pageAccounts);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", (int) Math.ceil((double) officialMembers.size() / recordsPerPage));

            // Lấy danh sách thành viên đang chờ duyệt
            List<UserClub> pendingMembers = ClubMemberService.getMemberRegistrations(clubId);
            int pendingPage = 1;
            int pendingRecordsPerPage = 4;
            if (request.getParameter("pendingPage") != null) {
                pendingPage = Integer.parseInt(request.getParameter("pendingPage"));
            }

            int pendingStart = (pendingPage - 1) * pendingRecordsPerPage;
            int pendingEnd = Math.min(pendingStart + pendingRecordsPerPage, pendingMembers.size());
            List<UserClub> pendingPageAccounts = pendingMembers.subList(pendingStart, pendingEnd);

            request.setAttribute("pendingMembers", pendingPageAccounts);
            request.setAttribute("pendingCurrentPage", pendingPage);
            request.setAttribute("pendingTotalPages", (int) Math.ceil((double) pendingMembers.size() / pendingRecordsPerPage));

            request.setAttribute("userRole", userRole);
            request.getRequestDispatcher("/WEB-INF/member/memberList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("accessDenied.jsp");
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
