/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Club;
import Model.UserClub;
import Model.Post;
import Model.Setting;
import Model.Task;
import Model.User;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import service.clubmember.ClubMemberService;

/**
 *
 * @author pc
 */
@WebServlet(name = "MemberOfficialDetail", urlPatterns = {"/MemberOfficialDetail"})
public class MemberOfficialDetail extends HttpServlet {

    ClubMemberService ClubMemberService = new ClubMemberService();

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
        HttpSession session = request.getSession(false);
        String userIdParam = request.getParameter("userID");
        int userId = Integer.parseInt(userIdParam);

        String userRole = (String) session.getAttribute("role");

        // Lấy thông tin chi tiết thành viên theo ID
        UserClub member = ClubMemberService.getMemberById(userId);

        // Lấy 5 thành viên ngẫu nhiên từ CLB của thành viên này
//        int clubId = member.getClubId(); // Cần thêm `getClubId()` trong class User
        int clubid = 21;
        List<UserClub> randomMembers = ClubMemberService.getRandomMembers(clubid);

        // Gọi Service để lấy danh sách bài viết của user
        List<Post> posts = ClubMemberService.getPostsByUserId(userId);
        Post latestPost = ClubMemberService.getLatestPostByUser(userId);
        // Lấy danh sách task từ service
        List<Task> tasks = ClubMemberService.getUserTasksWithUsers(userId);

        UserClub clubmember = ClubMemberService.getClubMemberByUserId(clubid);

        request.setAttribute("latestPost", latestPost);
        // Đẩy danh sách bài viết sang JSP
        request.setAttribute("posts", posts);
        // Đẩy danh sách task lên JSP
        request.setAttribute("tasks", tasks);

        // Đẩy dữ liệu lên JSP
        request.setAttribute("member", member);
        request.setAttribute("userRole", userRole);
        request.setAttribute("randomMembers", randomMembers);
        request.getRequestDispatcher("/WEB-INF/member/memberDetails.jsp").forward(request, response);
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
        try {
            String id = request.getParameter("userID");
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String description = request.getParameter("description");
            // Lấy thông tin chi tiết thành viên theo ID
            // Cập nhật thông tin thành viên
            boolean updated = ClubMemberService.updateMemberRoleAndStatus(Integer.parseInt(id), role, status, description);

            if (updated) {
                response.sendRedirect("ClubMember?message=Update successful!");
            } else {
                // Nếu cập nhật không thành công, chuyển hướng đến trang lỗi hoặc thông báo lỗi
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update member");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberOfficialDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
