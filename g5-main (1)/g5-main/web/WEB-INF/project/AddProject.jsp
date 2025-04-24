<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm Project Mới</title>

        <!-- Import thư viện TailwindCSS và Font Awesome -->
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <!-- CSS Tùy Chỉnh -->
        <style>
            body {
                background-color: #f3f4f6; /* Màu nền nhạt */
                font-family: 'Arial', sans-serif;
            }
            .form-container {
                max-width: 600px;
                margin: 50px auto;
                background: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            .form-container h1 {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 20px;
                color: #2d3748;
            }
            .form-container .form-group {
                margin-bottom: 15px;
            }
            .form-container .form-group label {
                display: block;
                font-size: 14px;
                font-weight: bold;
                color: #4a5568;
                margin-bottom: 5px;
            }
            .form-container .form-group input,
            .form-container .form-group textarea,
            .form-container .form-group select {
                width: 100%;
                padding: 10px;
                border: 1px solid #e2e8f0;
                border-radius: 4px;
                outline: none;
                font-size: 14px;
            }
            .form-container .form-group input:focus,
            .form-container .form-group textarea:focus,
            .form-container .form-group select:focus {
                border-color: #3182ce;
                box-shadow: 0 0 0 2px rgba(66, 153, 225, 0.5);
            }
            .form-container button {
                display: inline-block;
                width: 100%;
                padding: 10px;
                font-size: 16px;
                font-weight: bold;
                color: white;
                background-color: #3182ce;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .form-container button:hover {
                background-color: #2b6cb0;
            }
        </style>
    </head>
    <body>
        <script>
            window.onload = function () {
                // Lấy thông báo từ session
                let successMessage = '<%= (String) request.getSession().getAttribute("successMessage") %>';
                let errorMessage = '<%= (String) request.getSession().getAttribute("errorMessage") %>';

                // Chỉ hiển thị alert khi có thông báo hợp lệ
                if (successMessage && successMessage !== "null") {
                    alert(successMessage); // Hiển thị thông báo thành công
            <% request.getSession().removeAttribute("successMessage"); %> // Xóa thông báo khỏi session
                    window.location.href = "<%= request.getContextPath() %>/ListProject"; // Chuyển hướng đến danh sách dự án
                }

                if (errorMessage && errorMessage !== "null") {
                    alert(errorMessage); // Hiển thị thông báo lỗi
            <% request.getSession().removeAttribute("errorMessage"); %> // Xóa thông báo khỏi session
                }
            };
        </script>

        <!-- Container Form -->
        <div class="form-container">
            <h1>Thêm Dự Án</h1>
            <form action="AddProject" method="post">
                <div class="form-group">
                    <label for="name">Project Name</label>
                    <input type="text" id="name" name="Name" placeholder="Enter project name" value="<%= request.getAttribute("Name") != null ? request.getAttribute("Name") : " " %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("nameError") : " " %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="code">Project Code</label>
                    <input type="text" id="code" name="Code" placeholder="Enter project code" value="<%= request.getAttribute("Code") != null ? request.getAttribute("Code") : " " %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("codeError") : " " %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="leader_id">Select Leader</label>
                    <select id="leader_id" name="User_Id" >
                        <c:forEach var="member" items="${clubMembers}">
                            <option value="${member.id}">${member.userName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${empty clubMembers}">
                        <div style="color: red;">Không tìm thấy thành viên nào trong câu lạc bộ.</div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="from_date">Start Date</label>
                    <input type="date" id="from_date" name="From_Date" value="<%= request.getAttribute("From_Date") != null ? request.getAttribute("From_Date") : " " %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("fromDateError") : " " %>
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("dateError") : " " %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="to_date">End Date</label>
                    <input type="date" id="to_date" name="To_Date" value="<%= request.getAttribute("To_Date") != null ? request.getAttribute("To_Date") : " " %>" >
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("toDateError") : " " %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <div class="radio-group" style="display: flex; align-items: center;">
                        <label for="active" style="margin-right: 10px;">
                            <input type="radio" id="active" name="Status" value="Active" <%= "Active".equals(request.getAttribute("Status")) ? "checked" : " " %>> Active
                        </label>
                        <label for="inactive">
                            <input type="radio" id="inactive" name="Status" value="Inactive" <%= "Inactive".equals(request.getAttribute("Status")) ? "checked" : " " %>> Inactive
                        </label>
                    </div>
                    <div style="color: red;">
                        <%= request.getAttribute("errors") != null ? ((Map<String, String>) request.getAttribute("errors")).get("statusError") : " " %>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="Description" placeholder="Enter project description" rows="4"><%= request.getAttribute("Description") != null ? request.getAttribute("Description") : " " %></textarea>
                </div>
                <button type="submit">Add Project</button>
            </form>
        </div>
    </body>
</html>