/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.DBContext;
import java.util.ArrayList;
import java.util.List;
import Model.Task1;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class TaskDAO extends database.DBContext{
    
    public List<Task1> getTask(){
        List<Task1> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";
        
            try (Connection conn = getConnection(); 
             PreparedStatement stm = conn.prepareStatement(sql); 
             ResultSet rs = stm.executeQuery()) {
            
            while (rs.next()) {
                Task1 task = new Task1();
                task.setTaskID(rs.getInt("taskID"));
                task.setEventID(rs.getInt("eventID"));
                task.setClubID(rs.getInt("clubID"));
                task.setTitle(rs.getString("title"));
                task.setAssignerID(rs.getInt("assignerID"));
                task.setAssigneeID(rs.getInt("assigneeID"));
                task.setDeadline(rs.getDate("deadline").toLocalDate());
                task.setStatus(rs.getString("status"));
                task.setDescription(rs.getString("description"));
                task.setPriority(rs.getString("priority"));
                
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }                
        return tasks;
    }
    
    public Task1 getTaskByID(int taskID){
        Task1 task = null;
        String sql = "SELECT * FROM task WHERE taskID = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stm = conn.prepareStatement(sql)) {
            
            stm.setInt(1, taskID);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                task = new Task1();
                task.setTaskID(rs.getInt("taskID"));
                task.setEventID(rs.getInt("eventID"));
                task.setClubID(rs.getInt("clubID"));
                task.setTitle(rs.getString("title"));
                task.setAssignerID(rs.getInt("assignerID"));
                task.setAssigneeID(rs.getInt("assigneeID"));
                task.setDeadline(rs.getDate("deadline").toLocalDate());
                task.setStatus(rs.getString("status"));
                task.setDescription(rs.getString("description"));
                task.setPriority(rs.getString("priority"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }
    
     public void addTask(Task1 task) {
        String sql = "INSERT INTO task (clubID, title, deadline, status, description, priority) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getClubID());
            stmt.setString(2, task.getTitle());
            stmt.setDate(3, java.sql.Date.valueOf(task.getDeadline()));
            stmt.setString(4, task.getStatus());
            stmt.setString(5, task.getDescription());
            stmt.setString(6, task.getPriority());            
            System.out.println("Added Completed");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void addTask1(String eventID, String clubID, String title,  String deadline, String status, String description, String priority) {
        String sql = "INSERT INTO task (eventID, clubID, title, deadline, status, description, priority ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            stmt.setString(2, clubID);
            stmt.setString(3, title);        
            stmt.setDate(4, java.sql.Date.valueOf(deadline));
            stmt.setString(5, status);
            stmt.setString(6, description);
            stmt.setString(7, priority);            
            System.out.println("Added Completed");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
      public void updateTask(Integer taskID, String title, String deadline, String status, String description, String priority) {
        String sql = "UPDATE task SET title=?, deadline=?, status=?, description=?, priority=? WHERE taskID=?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement stm = conn.prepareStatement(sql)) {
            
            stm.setString(1, title);
            stm.setString(2, deadline);
            stm.setString(3, status);
            stm.setString(4, description);
            stm.setString(5, priority);
            stm.setInt(6, taskID);
            System.out.println("Update completed");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
      
    public List<Task1> searchTaskByTitle(String title) {
    List<Task1> tasks = new ArrayList<>();
    String sql = "SELECT * FROM task WHERE title LIKE ?";

    try (Connection conn = getConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setString(1, "%" + title + "%"); // Tìm kiếm tương đối (chứa từ khóa)
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            Task1 task = new Task1();
            task.setTaskID(rs.getInt("taskID"));
            task.setEventID(rs.getInt("eventID"));
            task.setClubID(rs.getInt("clubID"));
            task.setTitle(rs.getString("title"));
            task.setAssignerID(rs.getInt("assignerID"));
            task.setAssigneeID(rs.getInt("assigneeID"));
            task.setDeadline(rs.getDate("deadline").toLocalDate());
            task.setStatus(rs.getString("status"));
            task.setDescription(rs.getString("description"));
            task.setPriority(rs.getString("priority"));

            tasks.add(task);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tasks;
}


public void deleteTask(int taskID) {
    String sql = "DELETE FROM task WHERE taskID = ?";

    try (Connection conn = getConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {
        
        stm.setInt(1, taskID);
        stm.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public List<String> getDistinctStatuses() {
    List<String> statuses = new ArrayList<>();
    String sql = "SELECT DISTINCT status FROM task";

    try (Connection conn = getConnection();
         PreparedStatement stm = conn.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            statuses.add(rs.getString("status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return statuses;
}

public List<String> getDistinctPriorities() {
    List<String> priorities = new ArrayList<>();
    String sql = "SELECT DISTINCT priority FROM task";

    try (Connection conn = getConnection();
         PreparedStatement stm = conn.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            priorities.add(rs.getString("priority"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return priorities;
}
public List<Task1> getFilteredTasks(String status, String priority) {
    List<Task1> tasks = new ArrayList<>();
    String sql = "SELECT * FROM task WHERE 1=1"; // Base query

    if (status != null && !status.isEmpty()) {
        sql += " AND status = ?";
    }
    if (priority != null && !priority.isEmpty()) {
        sql += " AND priority = ?";
    }

    try (Connection conn = getConnection();
         PreparedStatement stm = conn.prepareStatement(sql)) {

        int paramIndex = 1;
        if (status != null && !status.isEmpty()) {
            stm.setString(paramIndex++, status);
        }
        if (priority != null && !priority.isEmpty()) {
            stm.setString(paramIndex++, priority);
        }

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            Task1 task = new Task1();
            task.setTaskID(rs.getInt("taskID"));
            task.setEventID(rs.getInt("eventID"));
            task.setClubID(rs.getInt("clubID"));
            task.setTitle(rs.getString("title"));
            task.setAssignerID(rs.getInt("assignerID"));
            task.setAssigneeID(rs.getInt("assigneeID"));
            task.setDeadline(rs.getDate("deadline").toLocalDate());
            task.setStatus(rs.getString("status"));
            task.setDescription(rs.getString("description"));
            task.setPriority(rs.getString("priority"));

            tasks.add(task);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tasks;
}


}
