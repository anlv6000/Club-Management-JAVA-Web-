<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.TaskDAO, Model.Task" %>
<%
    int taskID = Integer.parseInt(request.getParameter("taskID"));
    TaskDAO taskDAO = new TaskDAO();
    Task task = taskDAO.getTaskByID(taskID);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            width: 100%;
        }
        .container {
            width: 50%;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            margin: auto;
            text-align: left;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .form-group label {
            width: 30%;
            font-weight: bold;
        }
        .form-group input, .form-group textarea, .form-group select {
            width: 70%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
            width: 100%;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Task</h2>
        <form action="updateTaskServlet" method="post">
            <div class="form-group">
                <label>Task ID</label>
                <input type="text" name="taskID" value="<%= task.getTaskID() %>" readonly>
            </div>
            
            
            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" value="<%= task.getTitle() %>" required>
            </div>
            
            <div class="form-group">
                <label>Club ID:</label>
                <input type="text" name="clubID" value="<%= task.getClubID()%>" readonly>
            </div>
            
            <div class="form-group">
                <label>Event ID:</label>
                <input type="text" name="eventID" value="<%= task.getEventID() %>" readonly>
            </div>
            
            <div class="form-group">
                <label>Deadline:</label>
                <input type="date" name="deadline" value="<%= task.getDeadline() %>" required>
            </div>
            
            <div class="form-group">
                <label>Status:</label>
                <select name="status" required>
                    <option value="Pending" <%= task.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                    <option value="To Do" <%= task.getStatus().equals("To Do") ? "selected" : "" %>>To Do</option>
                    <option value="Doing" <%= task.getStatus().equals("Doing") ? "selected" : "" %>>Doing</option>
                    <option value="Closed" <%= task.getStatus().equals("Closed") ? "selected" : "" %>>Closed</option>
                    <option value="Cancelled" <%= task.getStatus().equals("Cancelled") ? "selected" : "" %>>Cancelled</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Priority:</label>
                <select name="priority" required>
                    <option value="Low" <%= task.getPriority().equals("Low") ? "selected" : "" %>>Low</option>
                    <option value="Medium" <%= task.getPriority().equals("Medium") ? "selected" : "" %>>Medium</option>
                    <option value="High" <%= task.getPriority().equals("High") ? "selected" : "" %>>High</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required><%= task.getDescription() %></textarea>
            </div>
            
            <button type="submit">Update Task</button>
        </form>
    </div>
</body>
</html>
