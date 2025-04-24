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
                font-family: 'Poppins', sans-serif;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
                flex-direction: column;
                position: relative;
            }
            .container {
                width: 90%;
                max-width: 1100px;
                background: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                position: relative;
            }
            .search-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 10px;
                margin-bottom: 20px;
            }
            .search-box, select {
                padding: 10px;
                width: 300px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
            }
            .search-btn, .add-btn {
                padding: 10px 15px;
                border: none;
                background-color: #007bff;
                color: white;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                text-decoration: none;
            }
            .search-btn:hover, .add-btn:hover {
                background-color: #0056b3;
            }
            .event-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                border-radius: 8px;
                overflow: hidden;
            }
            .event-table th, .event-table td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: center;
            }
            .event-table th {
                background-color: #007bff;
                color: white;
            }
            .event-table tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .event-table img {
                width: 60px;
                border-radius: 6px;
            }
            .status-active {
                font-weight: bold;
                color: green;
            }
            .status-inactive {
                font-weight: bold;
                color: red;
            }
            .pagination {
                margin-top: 20px;
                display: flex;
                justify-content: center;
                gap: 10px;
            }
            .pagination a {
                padding: 8px 12px;
                border: 1px solid #007bff;
                color: #007bff;
                text-decoration: none;
                border-radius: 5px;
            }
            .pagination a.active {
                background-color: #007bff;
                color: white;
            }
            .pagination a:hover {
                background-color: #0056b3;
                color: white;
            }
            .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                background: none;
                border: none;
                font-size: 24px;
                color: #ff0000;
                cursor: pointer;
            }

            .close-btn:hover {
                color: #cc0000;
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
          <button class="close-btn" onclick="closePage()">
    <i class="fas fa-times"></i>
</button>
            <h1><a href="listEvent">Event List</a></h1>
       
            <div class="search-container">
                 <a href="addEvent" class="add-btn">Add Event</a>
                <form action="searchEvent" method="get">
                    <input type="text" name="searchQuery" class="search-box" placeholder="Search by event name...">
                    <select id="eventTypeSelect" onchange="filterByType()">
                        <option value="">-- Chọn loại sự kiện --</option>
                        <option value="true">Online</option>
                        <option value="false">Offline</option>
                    </select>
                    <button type="submit" class="search-btn">Tìm kiếm</button>
                </form>
            </div>
            <table class="event-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Event Name</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th>Type</th>
                        <th>Group Name</th>
                        <th>UserBy</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${e}" var="o">
                        <tr>
                            <td>${o.getId()}</td>
                            <td>${o.eventName}</td>
                            <td><img src="${o.eventImageURL}" alt="Event Image"></td>
                            <td class="${o.status ? 'status-active' : 'status-inactive'}">${o.status ? "Active" : "Inactive"}</td>
                            <td>${o.getType() ? "Online" : "Offline"}</td>
                            <td>${o.getNameCLub()}</td>
                            <td>${o.getUserCreat()}</td>
                            <td>
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

            <div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="${servlet}?page=${currentPage - 1}${queryParams}">&laquo; Prev</a>
    </c:if>
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="${servlet}?page=${i}${queryParams}" class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="${servlet}?page=${currentPage + 1}${queryParams}">Next &raquo;</a>
    </c:if>
</div>
            
        </div>
    </body>
    <script>
        function closePage() {
    window.location.href = "home1";
}</script>
</html>
