/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.task;



import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Task1; // Nếu package là "Model"
import Model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import service.TaskService;


/**
 *
 * @author admin
 */

@WebServlet("/taskList")
public class TaskServlet extends HttpServlet {
    private TaskService taskService = new TaskService();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();
    User acc = (User) session.getAttribute("acc");
    int userID = acc.getId();
    
    String searchKeyword = request.getParameter("search"); 
    String selectedStatus = request.getParameter("status");
    String selectedPriority = request.getParameter("priority");
    int page = 1;
    int limit = 10; // Số task trên mỗi trang

    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }
    int offset = (page - 1) * limit;


    List<Task1> taskList;
    int totalTasks;

    if (searchKeyword != null && !searchKeyword.isEmpty()) {
        taskList = taskService.searchTask(searchKeyword, limit, offset, userID);
        totalTasks = taskService.searchTask(searchKeyword, Integer.MAX_VALUE, 0, userID).size();
       
    } else {
        taskList = taskService.getTask(limit, offset, userID);
        totalTasks = taskService.getTask(Integer.MAX_VALUE, 0, userID).size();
    }

    int totalPages = (int) Math.ceil((double) totalTasks / limit);
    request.setAttribute("searchKeyword", searchKeyword);
    request.setAttribute("taskList", taskList);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);

    request.getRequestDispatcher("/WEB-INF/task/task.jsp").forward(request, response);
}





protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    
    String action = request.getParameter("action");
    
    
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        taskService.deleteTask(taskID);
         request.getSession().setAttribute("deleteMessage","Task Deleted successfully!");
        response.sendRedirect("taskList");
 
}
}

