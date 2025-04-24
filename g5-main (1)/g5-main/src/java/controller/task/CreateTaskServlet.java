
import DAO.TaskDAO;
import DAO.ClubDAO;
import DAO.dao;
import Model.Task1;
import Model.User;
import Model.Event;
import java.util.List;
import Model.Club;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ClubService;
import service.TaskService;

@WebServlet("/CreateTaskServlet")
public class CreateTaskServlet extends HttpServlet {

    private TaskService taskService = new TaskService();
    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get User ID of logged account
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("acc");
        int userID = acc.getId();

        //Lấy club
        ClubDAO clubDao = new ClubDAO();
        List<Club> clubs = clubService.getJoinedClubs(userID);
        //
        List<User> users = clubService.getUsersJoinedClubOfUser(userID);
        
        // Lấy danh sách events từ database
        List<Event> events = taskService.getEventsOfJoinedClub(userID);
        request.setAttribute("users", users);
        request.setAttribute("clubs", clubs);
        request.setAttribute("events", events);

        //Gửi tới createTask.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/task/createTask.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String eventID = request.getParameter("eventID");
        String clubID = request.getParameter("clubID");
        String title = request.getParameter("title");
        String deadlineStr = request.getParameter("deadline");
        String status = request.getParameter("status");
        String priority = request.getParameter("priority");
        String description = request.getParameter("description");
        int assignerID = Integer.parseInt(request.getParameter("assigner"));
        int assigneeID = Integer.parseInt(request.getParameter("assignee"));
         
         
        
        
        


        try {
           
            taskService.addTask(eventID, clubID, title, deadlineStr, status, description, priority, assignerID, assigneeID);
            request.getSession().setAttribute("taskSuccess", "Task created successfully!");
            response.sendRedirect("taskList");
        } catch (IllegalArgumentException e) {
            request.setAttribute("errors", List.of(e.getMessage().split("; ")));
            request.getRequestDispatcher("/WEB-INF/task/createTask.jsp").forward(request, response);
        }
    }
    
}
