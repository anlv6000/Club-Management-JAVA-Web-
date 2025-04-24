import DAO.ClubDAO;
import DAO.TaskDAO;
import DAO.dao;
import Model.Club;
import Model.Event;
import Model.Task1;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import service.ClubService;
import service.TaskService;


@WebServlet("/updateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
 private TaskService taskService = new TaskService();
    private ClubService clubService = new ClubService();

    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy taskID từ request
    int taskID = Integer.parseInt(request.getParameter("taskID"));
     Task1 task = taskService.getTaskByID(taskID);
     //get User ID of logged account
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("acc");
        int userID = acc.getId();

        //Lấy club
        ClubDAO clubDao = new ClubDAO();
        List<Club> clubs = clubService.getJoinedClubs(userID);
        //
        List<User> users = clubService.getUsersJoinedClubOfUser(userID);
        
        List<Event> events = taskService.getEventsOfJoinedClub(userID);
        request.setAttribute("users", users);
        request.setAttribute("clubs", clubs);
        request.setAttribute("events", events);
    // Lấy thông tin task từ database
   
    
    // Gửi thông tin task tới trang editTask.jsp
    request.setAttribute("task", task);
    request.getRequestDispatcher("/WEB-INF/task/editTask.jsp").forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Lấy các tham số từ form
    String taskIDStr = request.getParameter("taskID");
    int taskID = Integer.parseInt(taskIDStr);
    String clubID = request.getParameter("clubID");
    String eventID = request.getParameter("eventID");
    String title = request.getParameter("title");
    String deadline = request.getParameter("deadline");
    String status = request.getParameter("status");
    String priority = request.getParameter("priority");
    String description = request.getParameter("description");
    String assigner = request.getParameter("assigner");  // Assigning user ID of the person assigning
    String assignee = request.getParameter("assignee");  // Assigning user ID of the person being assigned
    
    
    // Khởi tạo DAO để cập nhật task
    try {
    taskService.updateTask(taskID, title, deadline, status, description, assigner, assignee);
    request.getSession().setAttribute("successMessageTask", "Update Task Succes");
    response.sendRedirect("taskList");
    }catch (IllegalArgumentException e) {
            request.setAttribute("errors", List.of(e.getMessage().split("; ")));
            request.getRequestDispatcher("updateTaskServlet?taskID=" + taskID).forward(request, response);
        }
    // Sau khi cập nhật, chuyển hướng về trang task list

}

}

