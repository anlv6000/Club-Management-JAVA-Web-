/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.club;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import DAO.ClubDAO;
import Model.Club;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import service.ClubService;

/**
 *
 * @author admin
 */
@WebServlet("/UpdateClubServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,//2MB
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class UpdateClubServlet extends HttpServlet {
ClubService clubService = new ClubService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clubID = Integer.parseInt(request.getParameter("clubID"));
        ClubDAO dao = new ClubDAO();
        Club club = clubService.getClubByID(clubID);

        request.setAttribute("club", club);
        request.getRequestDispatcher("/WEB-INF/club/detailClub.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clubID;
        try {
            clubID = Integer.parseInt(request.getParameter("clubID"));
        } catch (NumberFormatException e) {
            request.setAttribute("errors", List.of("ID câu lạc bộ không hợp lệ."));
            request.getRequestDispatcher("updateClub.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String isPublic = request.getParameter("isPublic");
        
        int leaderID = Integer.parseInt(request.getParameter("leaderID"));
        String contact = request.getParameter("contactClub");
        String schedule = request.getParameter("schedule");
        String imgURL = request.getParameter("imgURL");
        String oldImgURL = request.getParameter("oldImgURL");

        // Xử lý ảnh
        Part sourcePath = request.getPart("imgFile");
        if (sourcePath != null && sourcePath.getSize() > 0) {
            String uploadDir = getServletContext().getRealPath("/") + "images/ClubIMG";
            String fileName = Paths.get(sourcePath.getSubmittedFileName()).getFileName().toString();

            // Kiểm tra thư mục, nếu chưa có thì tạo mới
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Lưu ảnh vào thư mục server
            File destFile = new File(uploadFolder, fileName);
            try {
                Files.copy(sourcePath.getInputStream(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imgURL = "images/ClubIMG/" + fileName;
            } catch (Exception e) {
                request.setAttribute("errors", List.of("Lỗi khi tải ảnh lên: " + e.getMessage()));
                request.getRequestDispatcher("/WEB-INF/club/detailClub.jsp").forward(request, response);
                return;
            }
        }else{ 
            imgURL = oldImgURL;
        }

//        try {
//            clubService.updateClub(clubID, name, category, description, isPublic, imgURL, status, leader, contact, schedule);
//            request.getSession().setAttribute("successMessage", "Cập nhật câu lạc bộ thành công!");
//            response.sendRedirect("DetailClubServlet?clubID=" + clubID);
//        } catch (IllegalArgumentException e) {
//            request.setAttribute("errors", List.of(e.getMessage().split("; ")));
//            request.getRequestDispatcher("/WEB-INF/club/detailClub.jsp").forward(request, response);
//        }

        clubService.updateClub2(clubID, name, category, description, isPublic, imgURL, leaderID, contact, schedule);
        request.getSession().setAttribute("successMessage", "Cập nhật câu lạc bộ thành công!");
        response.sendRedirect("DetailClubServlet?clubID=" + clubID);
}
}
    
