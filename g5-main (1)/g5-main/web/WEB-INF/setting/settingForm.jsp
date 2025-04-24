<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.dao" %>
<%@ page import="Model.Setting" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Map" %>

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
                <input type="hidden" id="id" name="id" value="<%= request.getAttribute("id") != null ? request.getAttribute("id") : (id > 0 ? setting.getId() : "") %>">

                <div class="form-group-horizontal">
                    <div class="form-group form-group-half">
                        <label for="name">Name*</label>
                        <input type="text" id="name" name="name" value="<%= (request.getAttribute("name") != null && !"null".equals(request.getAttribute("name").toString())) ? request.getAttribute("name") : (id > 0 && setting.getName() != null ? setting.getName() : "") %>" >
                        <div style="color: red;">
                            <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("nameError") : "" %>
                        </div>
                    </div>
                    <div class="form-group form-group-half">
                        <label for="type">Type</label>
                        <select id="type" name="type">
                            <option value="String" <%= "String".equals((request.getAttribute("type") != null && !"null".equals(request.getAttribute("type").toString())) ? request.getAttribute("type") : (id > 0 && setting.getType() != null ? setting.getType() : "")) ? "selected" : "" %>>String</option>
                            <option value="Integer" <%= "Integer".equals((request.getAttribute("type") != null && !"null".equals(request.getAttribute("type").toString())) ? request.getAttribute("type") : (id > 0 && setting.getType() != null ? setting.getType() : "")) ? "selected" : "" %>>Integer</option>
                            <option value="Boolean" <%= "Boolean".equals((request.getAttribute("type") != null && !"null".equals(request.getAttribute("type").toString())) ? request.getAttribute("type") : (id > 0 && setting.getType() != null ? setting.getType() : "")) ? "selected" : "" %>>Boolean</option>
                            <option value="Date" <%= "Date".equals((request.getAttribute("type") != null && !"null".equals(request.getAttribute("type").toString())) ? request.getAttribute("type") : (id > 0 && setting.getType() != null ? setting.getType() : "")) ? "selected" : "" %>>Date</option>
                            <option value="Text" <%= "Text".equals((request.getAttribute("type") != null && !"null".equals(request.getAttribute("type").toString())) ? request.getAttribute("type") : (id > 0 && setting.getType() != null ? setting.getType() : "")) ? "selected" : "" %>>Text</option>
                        </select>
                        <!-- Loại bỏ kiểm tra lỗi cho trường Type -->
                    </div>
                </div>

                <div class="form-group">
                    <label for="value">Value</label>
                    <input type="text" id="value" name="value" value="<%= (request.getAttribute("value") != null && !"null".equals(request.getAttribute("value").toString())) ? request.getAttribute("value") : (id > 0 && setting.getValue() != null ? setting.getValue() : "") %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("valueError") : "" %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="priority">Priority</label>
                    <input type="number" id="priority" name="priority" value="<%= request.getAttribute("priority") != null 
                        ? request.getAttribute("priority") 
                        : (id > 0 && setting.getPriority() != 0 ? setting.getPriority() : "") %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null 
                            ? ((Map<String, String>) request.getAttribute("errors")).get("priorityError") 
                            : "" %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <div class="radio-group" style="display: flex; align-items: center;">
                        <label for="active" style="margin-right: 10px;">
                            <input type="radio" id="active" name="status" value="Active" <%= "Active".equals((request.getAttribute("status") != null && !"null".equals(request.getAttribute("status").toString())) ? request.getAttribute("status") : (id > 0 && setting.getStatus() != null ? setting.getStatus() : "")) ? "checked" : "" %>> Active
                        </label>
                        <label for="inactive">
                            <input type="radio" id="inactive" name="status" value="Inactive" <%= "Inactive".equals((request.getAttribute("status") != null && !"null".equals(request.getAttribute("status").toString())) ? request.getAttribute("status") : (id > 0 && setting.getStatus() != null ? setting.getStatus() : "")) ? "checked" : "" %>> Inactive
                        </label>
                    </div>
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("statusError") : "" %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="roleId">Role</label>
                    <select id="roleId" name="roleId">
                        <option value="1" <%= "1".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Web User</option>
                        <option value="2" <%= "2".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Club Member</option>
                        <option value="3" <%= "3".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Club Leader</option>
                        <option value="4" <%= "4".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Event Leader</option>
                        <option value="5" <%= "5".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Project Leader</option>
                        <option value="6" <%= "6".equals(String.valueOf(request.getAttribute("roleId"))) ? "selected" : "" %>>Admin</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description">
                        <%= (request.getAttribute("description") != null && !"null".equals(request.getAttribute("description").toString())) ? request.getAttribute("description") : (id > 0 && setting.getDescription() != null ? setting.getDescription() : "") %>
                    </textarea>
                </div>
                <div class="form-group" style="align-items: center;">
                    <button type="submit"><%= id > 0 ? "Update" : "Submit" %></button>
                    <br>
                    <button type="button" class="back-btn" onclick="window.location.href = 'settings'">Back to List</button>
                </div>
            </form>
        </div>
    </body>
</html>
