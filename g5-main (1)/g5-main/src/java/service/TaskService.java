/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.TaskDAO;
import Model.Event;
import Model.Task1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TaskService {

    private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAO();
    }

    public List<Task1> searchTask(String keyword, int page, int pageSize, int userID) {

        return taskDAO.searchTaskByTitleOrEventName(keyword, page, pageSize, userID);
    }

    public List<Task1> getTask(int limit, int offset,int userID) {

        return taskDAO.getTaskNew(limit, offset, userID);
    }
    
    public void deleteTask(int taskID) {
    taskDAO.deleteTask(taskID);
}
    public void addTask(String eventID, String clubID, String title, String deadline, String status,
            String description, String priority, int assignerID, int assigneeID) throws IllegalArgumentException {
        List<String> errors = new ArrayList<>();

        if (!title.matches("^.{5,100}$")) {
            errors.add("Title must be between 5 and 100 characters long.");
        }

        LocalDate today = LocalDate.now();
        LocalDate deadlineDate;
        try {
            deadlineDate = LocalDate.parse(deadline);
            if (!deadlineDate.isAfter(today)) {
                errors.add("Deadline must be greater than current date.");
            }
        } catch (DateTimeParseException e) {
            errors.add("Deadline date is not valid.");
        }
 
        if (assignerID == assigneeID) {
            errors.add("The assigner and assignee cannot be the same.");
        }
        if (description.length() > 5000) {
            errors.add("Description cannot exceed 5000 characters.");
        }
        
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }

        // Gọi DAO để thêm task nếu dữ liệu hợp lệ
        taskDAO.addTask1(eventID, clubID, title, deadline, status, description, priority, assignerID, assigneeID);
    }
    
     public Task1 getTaskByID(int taskID){
       return taskDAO.getTaskByID(taskID);
    }
    
        public void updateTask(int taskID, String title, String deadline, String status, String description, 
                          String assignerID, String assigneeID) {
            List<String> errors = new ArrayList<>();

        if (!title.matches("^[\\p{L} ]{5,50}$")) {
            errors.add("Title must contain letters (can have accents) and be between 5 and 50 characters long.");
        }

        LocalDate today = LocalDate.now();
        LocalDate deadlineDate;
        try {
            deadlineDate = LocalDate.parse(deadline);
            if (!deadlineDate.isAfter(today)) {
                errors.add("Deadline must be greater than current date.");
            }
        } catch (DateTimeParseException e) {
            errors.add("Deadline date is not valid.");
        }
 
        if (assignerID == assigneeID) {
            errors.add("The assigner and assignee cannot be the same.");
        }
        if (description.length() > 5000) {
            errors.add("Description cannot exceed 5000 characters.");
        }
        
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }

        // Gọi DAO để thêm task nếu dữ liệu hợp lệ
        taskDAO.updateTask(taskID, title, deadline, status, description, assignerID, assigneeID);
    }
    
        public List<Event> getEventsOfJoinedClub(int userID) {
        
        return taskDAO.getEventsOfJoinedClub(userID);
    }
}


