/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Event;
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
public class TaskDAO extends database.DBContext {
    
    public static void main(String[] args) {
        TaskDAO dao = new TaskDAO(); // Thay YourDAOClass bằng tên class chứa method getJoinedClubs
        List<Event> events = dao.getEventsOfJoinedClub(1); // Test với userID = 1

        if (events.isEmpty()) {
            System.out.println("User không tham gia CLB nào.");
        } else {
            System.out.println("Danh sách CLB đã tham gia:");
            for (Event eve : events) {
                System.out.println(eve); // Phụ thuộc vào phương thức toString của class Club
            }
        }
    }
    public List<Event> getEventsOfJoinedClub(int userID) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events "
                + "where clubID in "
                + "(SELECT ClubID FROM userclubs where userID = ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ) {
            ps.setInt(1, userID);
           ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getString("EventDate"),
                        rs.getInt("CreatedBy"), // Có thể null
                        rs.getString("CreatedDate"),
                        rs.getString("EventImageURL"),
                        rs.getBoolean("Status"), // Cột mới
                        rs.getBoolean("Type") // Cột mới
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sự kiện: " + e.getMessage());
        }
        return events;
    }
    
    public List<Task1> getTask() {
        List<Task1> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

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

    public Task1 getTaskByID(int taskID) {
        Task1 task = null;
        String sql = "SELECT t.*, e.eventName, "
                + "u.fullname AS assigneeName, "
                + "a.fullname AS assignerName, "
                + "c.clubName "
                + "FROM task t "
                + "JOIN events e ON t.eventID = e.eventID "
                + "JOIN users u ON t.assigneeID = u.userID "
                + "JOIN users a ON t.assignerID = a.userID "
                + // Thêm JOIN để lấy assignerName
                "JOIN clubs c ON t.clubID = c.clubID "
                + "WHERE t.taskID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, taskID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                task = new Task1();
                task.setTaskID(rs.getInt("taskID"));
                task.setEventID(rs.getInt("eventID"));
                task.setEventName(rs.getString("eventName")); // Lấy tên sự kiện
                task.setClubID(rs.getInt("clubID"));
                task.setClubName(rs.getString("clubName")); // Lấy tên câu lạc bộ
                task.setTitle(rs.getString("title"));
                task.setAssignerID(rs.getInt("assignerID"));
                task.setAssigneeID(rs.getInt("assigneeID"));
                task.setAssignerName(rs.getString("assignerName"));
                task.setAssigneeName(rs.getString("assigneeName")); // Lấy tên người được giao task
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

    public void addTask1(String eventID, String clubID, String title, String deadline, String status,
            String description, String priority, int assignerID, int assigneeID) {
        String sql = "INSERT INTO task (eventID, clubID, title, deadline, status, description, priority, assignerID, assigneeID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            stmt.setString(2, clubID);
            stmt.setString(3, title);
            stmt.setDate(4, java.sql.Date.valueOf(deadline));
            stmt.setString(5, status);
            stmt.setString(6, description);
            stmt.setString(7, priority);
            stmt.setInt(8, assignerID);
            stmt.setInt(9, assigneeID);

            System.out.println("Task added successfully!");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(int taskID, String title, String deadline, String status, String description,
            String assigner, String assignee) {
        // Kết nối với database
        String sql = "UPDATE task SET title = ?, deadline = ?, status = ?, description = ?, "
                + "assignerID = ?, assigneeID = ? WHERE taskID = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, title);
            statement.setString(2, deadline);
            statement.setString(3, status);
            statement.setString(4, description);
            statement.setString(5, assigner);  // Cập nhật assigner
            statement.setString(6, assignee); // Cập nhật assignee
            statement.setInt(7, taskID);  // Cập nhật taskID để xác định task cần sửa

            // Thực thi câu lệnh cập nhật
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task1> searchTaskByTitle(String title) {
        List<Task1> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE title LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

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

    public List<Task1> searchTaskByEventName(String eventName) {
        List<Task1> taskList = new ArrayList<>();
        String query = "SELECT * FROM Task WHERE eventName LIKE ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + eventName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task1 task = new Task1();
                task.setTaskID(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setStatus(rs.getString("status"));
                task.setEventName(rs.getString("eventName"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    public void deleteTask(int taskID) {
        String sql = "DELETE FROM task WHERE taskID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, taskID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDistinctStatuses() {
        List<String> statuses = new ArrayList<>();
        String sql = "SELECT DISTINCT status FROM task";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

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

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                priorities.add(rs.getString("priority"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorities;
    }

    public List<String> getDistinctEventID() {
        List<String> eventIDs = new ArrayList<>();
        String sql = "SELECT DISTINCT eventID FROM events";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                eventIDs.add(rs.getString("eventID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventIDs;
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

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

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

    public List<Task1> searchAndFilterTasks(String title, String status, String priority) {
        List<Task1> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE 1=1"; // Điều kiện mặc định luôn đúng

        if (title != null && !title.isEmpty()) {
            sql += " AND title LIKE ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND status = ?";
        }
        if (priority != null && !priority.isEmpty()) {
            sql += " AND priority = ?";
        }

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            int index = 1;
            if (title != null && !title.isEmpty()) {
                stm.setString(index++, "%" + title + "%");
            }
            if (status != null && !status.isEmpty()) {
                stm.setString(index++, status);
            }
            if (priority != null && !priority.isEmpty()) {
                stm.setString(index++, priority);
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

    public List<Task1> searchTaskByTitleOrEventName(String keyword, int limit, int offset, int userID) {
        List<Task1> taskList = new ArrayList<>();
        String sql = "SELECT t.*, e.eventName, u.fullname AS assigneeName FROM task t "
                + "JOIN events e ON t.eventID = e.eventID "
                + "JOIN users u ON t.assigneeID = u.userID "
                + "WHERE t.clubID IN "
                + "( SELECT clubID FROM userclubs WHERE userId = ?) "
                + "AND (t.title LIKE ? OR e.eventName LIKE ?) "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, userID);
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            stm.setInt(4, limit);
            stm.setInt(5, offset);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Task1 task = new Task1();
                task.setTaskID(rs.getInt("taskID"));
                task.setTitle(rs.getString("title"));
                task.setStatus(rs.getString("status"));
                task.setEventID(rs.getInt("eventID"));
                task.setEventName(rs.getString("eventName"));
                task.setDeadline(rs.getDate("deadline").toLocalDate());
                task.setAssigneeID(rs.getInt("assigneeID"));
                task.setAssigneeName(rs.getString("assigneeName"));

                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    //
    public List<Task1> getTaskNew(int limit, int offset, int userID) {
        List<Task1> tasks = new ArrayList<>();
        String sql = "SELECT t.*, e.eventName, u.fullname AS assigneeName "
                + "FROM task t "
                + "JOIN events e ON t.eventID = e.eventID "
                + "JOIN users u ON t.assigneeID = u.userID "
                + "Where t.clubID IN "
                + "(SELECT clubID FROM userclubs WHERE UserID = ?) "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, userID);
            stm.setInt(2, limit);
            stm.setInt(3, offset);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Task1 task = new Task1();
                task.setTaskID(rs.getInt("taskID"));
                task.setEventID(rs.getInt("eventID"));
                task.setEventName(rs.getString("eventName"));
                task.setClubID(rs.getInt("clubID"));
                task.setTitle(rs.getString("title"));
                task.setAssignerID(rs.getInt("assignerID"));
                task.setAssigneeID(rs.getInt("assigneeID"));
                task.setAssigneeName(rs.getString("assigneeName")); // Lấy fullname
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

    public boolean isTitleExists(String title) {
        String sql = "SELECT COUNT(*) FROM task WHERE title = ?";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, title);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số lượng > 0 thì tiêu đề đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTitleExistsForOtherTask(int taskID, String title) {
        String sql = "SELECT COUNT(*) FROM tasks WHERE title = ? AND task_id != ?";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, title);
            stm.setInt(2, taskID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số lượng > 0 thì tiêu đề đã tồn tại trong task khác
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
