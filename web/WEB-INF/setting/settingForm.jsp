<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.dao" %>
<%@ page import="Model.Setting" %>
<%@ page import="java.sql.SQLException" %>
<%
    // Lấy id từ URL
    String idStr = request.getParameter("id");
    int id = 0;
    Setting setting = new Setting(); // Khởi tạo Setting mặc định cho trường hợp thêm mới

    if (idStr != null && !idStr.trim().isEmpty()) {
        id = Integer.parseInt(idStr);
        
        // Lấy thông tin chi tiết của thiết lập từ cơ sở dữ liệu cho trường hợp chỉnh sửa
        dao dao = new dao();
        try {
            setting = dao.getSettingById(id); // Giả sử bạn có method getSettingById trong lớp dao
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("SQL Error: " + e.getMessage());
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><%= id > 0 ? "Edit Setting" : "New Setting" %></title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                background-color: #f4f4f9;
            }
            .form-container {
                width: 80%;
                max-width: 600px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .form-group {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                margin-bottom: 15px;
                width: 100%;
            }
            .form-group-horizontal {
                display: flex;
                justify-content: space-between;
                width: 100%;
                align-items: flex-start;
            }
            .form-group label {
                margin-bottom: 5px;
                width: 100%;
            }
            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 8px;
                border: 2px solid #f4f4f9;
                border-radius: 4px;
            }
            .form-group-half {
                width: 48%;
            }
            .form-container button {
                padding: 8px 16px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .form-container button:hover {
                background-color: #45a049;
            }
            input:focus,
            select:focus,
            textarea:focus {
                border-color: #4CAF50;
                outline: none;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            }
            .back-btn {
                margin-top: 10px;
                background-color: #333;
            }
            .back-btn:hover {
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1><%= id > 0 ? "Edit Setting" : "New Setting" %></h1>
            <form action="<%= id > 0 ? "updateSetting" : "newSetting" %>" method="post">
                <input type="hidden" id="id" name="id" value="<%= setting.getId() %>">
                <div class="form-group-horizontal">
                    <div class="form-group form-group-half">
                        <label for="name">Name*</label>
                        <input type="text" id="name" name="name" value="<%= setting.getName() != null ? setting.getName() : "" %>" required>
                    </div>
                    <div class="form-group form-group-half">
                        <label for="type">Type</label>
                        <select id="type" name="type" required>
                            <option value="String" <%= "String".equals(setting.getType()) ? "selected" : "" %>>String</option>
                            <option value="Integer" <%= "Integer".equals(setting.getType()) ? "selected" : "" %>>Integer</option>
                            <option value="Boolean" <%= "Boolean".equals(setting.getType()) ? "selected" : "" %>>Boolean</option>
                            <option value="Date" <%= "Date".equals(setting.getType()) ? "selected" : "" %>>Date</option>
                            <option value="Text" <%= "Text".equals(setting.getType()) ? "selected" : "" %>>Text</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="value">Value</label>
                    <input type="text" id="value" name="value" value="<%= setting.getValue() != null ? setting.getValue() : "" %>" required>
                </div>
                <div class="form-group">
                    <label for="priority">Priority</label>
                    <input type="number" id="priority" name="priority" value="<%= setting.getPriority() %>" required>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <div class="radio-group" style="display: flex; align-items: center;">
                        <label for="active" style="margin-right: 10px;">
                            <input type="radio" id="active" name="status" value="Active" <%= "Active".equals(setting.getStatus()) ? "checked" : "" %>> Active
                        </label>
                        <label for="inactive">
                            <input type="radio" id="inactive" name="status" value="Inactive" <%= "Inactive".equals(setting.getStatus()) ? "checked" : "" %>> Inactive
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userType">User Type</label>
                    <select id="userType" name="userType" required>
                        <option value="Admin" <%= "Admin".equals(setting.getUserType()) ? "selected" : "" %>>Admin</option>
                        <option value="ClubLeader" <%= "ClubLeader".equals(setting.getUserType()) ? "selected" : "" %>>Club Leader</option>
                        <option value="ClubMember" <%= "ClubMember".equals(setting.getUserType()) ? "selected" : "" %>>Club Member</option>
                        <option value="WebUser" <%= "WebUser".equals(setting.getUserType()) ? "selected" : "" %>>Web User</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" required><%= setting.getDescription() != null ? setting.getDescription() : "" %></textarea>
                </div>
                <div class="form-group" style="align-items: center;">
                    <button type="submit"><%= id > 0 ? "Update" : "Submit" %></button>
                    <br>
                    <button type="button" class="back-btn" onclick="window.location.href='settings'">Back to List</button>
                </div>
            </form>
        </div>
    </body>
</html>
