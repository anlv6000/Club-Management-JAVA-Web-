<%@page import="java.util.List"%>
<%@page import="Model.Club"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/Club.css">
<!DOCTYPE html>
<html>
    <head>
    <header>
        <div class="header-logo">
            <img src="https://th.bing.com/th/id/OIP.HEjDxqON9OjorjiDpDhDPQHaFj?rs=1&pid=ImgDetMain" alt="alt" />
            <h1>FPT Club Manager</h1>
        </div>

        <c:if test="${sessionScope.acc != null}">
            <div>${sessionScope.txtUsername}</div>
        </c:if>

        <div id="logo-container">
            <!-- Default logo -->
            <img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo">
        </div>
    </header>
    <title>Club List</title>
    <style>

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #333;
            color: white;
        }

        header img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
        }

        .header-logo {
            display: flex;
            align-items: center;
            gap: 10px; /* Thêm ?i?u ch?nh gap ?? logo không b? dính vào tiêu ?? */
        }

        .header-logo img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
            object-fit: cover;
        }

        .header-logo h1 {
            margin: 0;
            font-size: 20px;
            line-height: 50px; /* ??m b?o c?n ch?nh v?i chi?u cao c?a hình ?nh */

        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .create-btn {
            float: right;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h2>Club List</h2>

    <form action="ClubServlet" method="get">
        <input type="text" name="search" placeholder="Search club..." />

        <select name="category">
            <option value="">All Categories</option>
            <% 
                List<String> categories = (List<String>) request.getAttribute("categories");
                if (categories != null) {
                    for (String cat : categories) { 
            %>
            <option value="<%= cat %>"><%= cat %></option>
            <% 
                    }
                } 
            %>
        </select>

        <button type="submit">Filter</button>
    </form>


    <button class="create-btn" onclick="window.location.href = 'createClub.jsp'">Create Club</button>


    <table>
        <tr>
            <th>Image</th>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Public</th>
            <th>Action</th>
        </tr>
        <c:forEach var="club" items="${clubs}">
            <tr>
                <td><img src="${club.imgURL}" width="50"></td>
                <td>${club.clubID}</td>
                <td>${club.clubName}</td>
                <td>${club.category}</td>
                <td>${club.isPublic ? 'Yes' : 'No'}</td>
                <td>
                    <a href="DetailClubServlet?clubID=${club.clubID}">Detail</a>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty clubs}">
            <tr>
                <td colspan="6">No clubs found</td>
            </tr>
        </c:if>

    </table>


</body>
</html>
