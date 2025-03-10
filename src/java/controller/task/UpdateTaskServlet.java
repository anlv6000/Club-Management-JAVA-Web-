import DAO.TaskDAO;
import Model.Task1;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@WebServlet("/updateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String taskIDStr = request.getParameter("taskID");
             int taskID = Integer.parseInt(taskIDStr);
            String clubID = request.getParameter("clubID");
            String eventID = request.getParameter("eventID");
            String title = request.getParameter("title");
            String deadline = request.getParameter("deadline");
            String status = request.getParameter("status");
            String priority = request.getParameter("priority");
            String description = request.getParameter("description");
            
            TaskDAO dao = new TaskDAO();
            dao.updateTask(taskID, title, deadline, status, description, priority);
            
            response.sendRedirect("taskList"); // Chuyển hướng sau khi cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Chuyển hướng nếu có lỗi
        }
    }
}

