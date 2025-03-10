
import DAO.TaskDAO;
import Model.Task1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/CreateTaskServlet")
public class CreateTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lấy dữ liệu từ form
        String eventID = request.getParameter("eventID");
        String clubID = request.getParameter("clubID");         
        String title = request.getParameter("title");
        String deadlineStr = request.getParameter("deadline");
        LocalDate deadline = LocalDate.parse(deadlineStr);
        deadlineStr = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String status = request.getParameter("status");        
        String priority = request.getParameter("priority");
        String description = request.getParameter("description");

        // Tạo Task mới
       
       TaskDAO dao = new TaskDAO();
       dao.addTask1(eventID, clubID, title, deadlineStr, status, description, priority);

        // Chuyển hướng về trang danh sách task
        response.sendRedirect("taskList");
    }
}