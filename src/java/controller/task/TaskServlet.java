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
import DAO.*;
import jakarta.servlet.annotation.WebServlet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 *
 * @author admin
 */

@WebServlet("/taskList")
public class TaskServlet extends HttpServlet {
   
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

    String searchKeyword = request.getParameter("search"); 
    String selectedStatus = request.getParameter("status");
    String selectedPriority = request.getParameter("priority");

    TaskDAO taskDao = new TaskDAO();
    List<Task1> taskList;
    
    if ((selectedStatus != null && !selectedStatus.isEmpty()) || 
        (selectedPriority != null && !selectedPriority.isEmpty())) {
        taskList = taskDao.getFilteredTasks(selectedStatus, selectedPriority);
    } else {
        taskList = taskDao.getTask();
    }

    // Lấy danh sách status và priority từ database để hiển thị dropdown
    List<String> statusList = taskDao.getDistinctStatuses();
    List<String> priorityList = taskDao.getDistinctPriorities();

    request.setAttribute("taskList", taskList);
    request.setAttribute("statusList", statusList);
    request.setAttribute("priorityList", priorityList);
    request.setAttribute("selectedStatus", selectedStatus);
    request.setAttribute("selectedPriority", selectedPriority);

    request.getRequestDispatcher("task.jsp").forward(request, response);
}




protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    TaskDAO taskDao = new TaskDAO();
    
    String action = request.getParameter("action");
    
    if ("delete".equals(action)) {
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        taskDao.deleteTask(taskID);
        response.sendRedirect("taskList");
    } else if ("edit".equals(action)) {
        String taskID = request.getParameter("taskID");
        String eventID = request.getParameter("eventID");
        String clubID = request.getParameter("clubID");
        String title = request.getParameter("title");
        String deadlineStr = request.getParameter("deadline");
//        LocalDate deadline = LocalDate.parse(deadlineStr);
//        deadlineStr = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");

        
        taskDao.updateTask(Integer.SIZE, title, deadlineStr, status, description, priority);
        response.sendRedirect("taskList");
    }
    }
}

