package Controller;

import DAO.ClubDAO;
import Model.Club;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ClubServlet")
public class ClubServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String category = request.getParameter("category");
        
        ClubDAO dao = new ClubDAO();
        List<Club> clubs;
        List<String> categories = dao.getAllCategories();
        
        if (category != null && !category.isEmpty()) {
            clubs = dao.getClubsByCategory(category);
        } else if (search != null && !search.trim().isEmpty()) {
            clubs = dao.searchClubs(search);
        } else {
            clubs = dao.getClubs();
        }

        request.setAttribute("categories", categories);
        request.setAttribute("clubs", clubs);
        request.getRequestDispatcher("clubs.jsp").forward(request, response);
    }
}


