<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Event List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                background-color: #f4f4f9;
                flex-direction: column;
                position: relative; /* Để định vị biểu tượng "x" */
            }
            .close-icon {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 24px;
                color: #ff0000;
                cursor: pointer;
                text-decoration: none;
            }
            .close-icon:hover {
                color: #cc0000;
            }
            .container {
                position: relative; /* Để biểu tượng "X" có thể bám vào */
                width: 90%;
                max-width: 1000px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                text-align: center;
                border: 2px solid #007bff;
            }
            .search-container {
                margin-bottom: 20px;
                display: flex;
                justify-content: center;
                gap: 10px;
            }
            .search-box {
                padding: 8px;
                width: 300px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .search-btn {
                padding: 8px 15px;
                border: none;
                background-color: #007bff;
                color: white;
                border-radius: 4px;
                cursor: pointer;
                font-weight: bold;
            }
            .search-btn:hover {
                background-color: #0056b3;
            }
            .event-table {
                width: 100%;
                border-collapse: collapse;
                background: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .event-table th, .event-table td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }
            .event-table th {
                background-color: #007bff;
                color: white;
            }
            .action-links a, .delete-btn {
                margin: 0 5px;
                color: #007bff;
                text-decoration: none;
                cursor: pointer;
            }
            .delete-btn {
                color: red;
                border: none;
                background: none;
                font-size: 16px;
            }
            .delete-btn:hover {
                color: darkred;
            }
            .home-btn {
                display: inline-block;
                margin-top: 15px;
                padding: 10px 15px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                font-weight: bold;
            }
            .home-btn:hover {
                background-color: #218838;
            }
        </style>

        <script>
            function filterByType() {
                let selectedType = document.getElementById("eventTypeSelect").value;
                if (selectedType !== "") {
                    window.location.href = "searchType?type=" + encodeURIComponent(selectedType);
                } else {
                    alert("Vui lòng chọn loại sự kiện!");
                }
            }
            function confirmDelete(eventId) {
                let confirmAction = confirm("Bạn có chắc chắn muốn xóa sự kiện này?");
                if (confirmAction) {
                    window.location.href = "deleteEvent?id=" + eventId;
                }
            }
        </script>
    </head>
    <body>

        <div class="container">
            <!-- Biểu tượng "X" đóng trang -->
            <a href="home1" class="close-icon">
                <i class="fas fa-times"></i>
            </a>

            <h1><a href="listEvent" style="text-decoration: none; color: inherit;">Event List</a></h1>
            <form class="search-container" action="searchEvent" method="get">
                <input type="text" name="searchQuery" class="search-box" placeholder="Search by event name...">
                <button type="submit" class="search-btn">Tìm kiếm</button>
            </form>
            <div class="search-container">
                <!-- Dropdown chọn loại sự kiện -->
                <select id="eventTypeSelect" class="search-box" onchange="filterByType()">
                    <option value="">-- Chọn loại sự kiện --</option>
                    <option value="true">Online</option>
                    <option value="false">Offline</option>
                </select>
            </div>

            <table class="event-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Event Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${e}" var="o">
                        <tr>
                            <td>${o.getId()}</td>
                            <td>${o.eventName}</td>
                            <td><img src="${o.eventImageURL}" alt="Event Image" width="50"></td>
                            <td>${o.description}</td>
                            <td class="action-links">
                                <a href="editEvent?id=${o.getId()}">Edit</a> 
                                <a href="eventDetail?id=${o.getId()}">Details</a>
                                <button class="delete-btn" onclick="confirmDelete(${o.getId()})">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="AddEvent.jsp" class="home-btn">Add Event</a>
        </div>

</html>