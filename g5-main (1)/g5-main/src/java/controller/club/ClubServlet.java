package controller.club;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;

import Model.Club;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import service.ClubService;

@WebServlet("/ClubServlet")
public class ClubServlet extends HttpServlet {
    ClubService service = new ClubService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String search = request.getParameter("search");
    String category = request.getParameter("category");
    String status = request.getParameter("status");
    
    int pageSize = 5;
    int page = 1;
    String pageStr = request.getParameter("page");
    if (pageStr != null && !pageStr.isEmpty()) {
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }
    }


    List<Club> allClubs = service.filterClub(search, category, status);
    List<String> categories = service.getCategories();
    List<User> users = service.getUser();
    
    int totalClubs = allClubs.size();
    int totalPages = (int) Math.ceil((double) totalClubs / pageSize);
    int startIndex = (page - 1) * pageSize;
    int endIndex = Math.min(startIndex + pageSize, totalClubs);
    List<Club> clubsForPage = allClubs.subList(startIndex, endIndex);
    

    
    request.setAttribute("users", users);
    request.setAttribute("status", status);
    request.setAttribute("search", search);
    request.setAttribute("categories", categories);
    request.setAttribute("clubs", clubsForPage);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("category", category);
        // Có thể có lỗi được truyền từ servlet addClub
    String error = (String) request.getAttribute("error");
    if (error != null) {
        request.setAttribute("error", error);
    }
    request.getRequestDispatcher("/WEB-INF/club/clubs.jsp").forward(request, response);
}
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response); // Gọi lại doGet để dùng chung logic
}
}