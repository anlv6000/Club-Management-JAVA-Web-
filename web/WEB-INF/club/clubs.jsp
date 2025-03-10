<%@page import="java.util.List"%>
<%@page import="Model.Club"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<%@ include file="/WEB-INF/home/sidebar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <!--    <header>
                <div class="header-logo">
                    <img src="https://th.bing.com/th/id/OIP.HEjDxqON9OjorjiDpDhDPQHaFj?rs=1&pid=ImgDetMain" alt="alt" />
                    <h1>FPT Club Manager</h1>
                </div>
        
         <c:if test="${sessionScope.acc != null}">
            <div>${sessionScope.txtUsername}</div>
        </c:if>

        <div id="logo-container">
             Default logo 
            <img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo">
        </div>
    </header>-->
        <title>Club List</title>

        <style>

            body {
                position: relative;
                background-image: url('https://daihoc.fpt.edu.vn/en/wp-content/uploads/2024/09/DHFPT6-1.webp');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
            }

            body::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(255, 255, 255, 0.5); /* 50% transparency layer */
                z-index: -1; /* Place it behind any content */
            }
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
            .create-btn {
                float: right;
                margin-bottom: 10px;
                padding: 10px;
                border-radius: 5px;
            }

            .custom_row th{
                background-color: #486380;
                color: #f2f2f2;
            }

            .custome_column td{
                background-color: #F4F3F8;

            }
            .popup {
                display: none; /* Ẩn popup ban đầu */
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .popup-content {
                background: white;
                padding: 20px;
                border: 2px solid #333;
                border-radius: 10px;
                width: 400px;
                box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
                text-align: center;
            }
            .popup input, textarea {
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }
            .popup select{
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .popup button {
                color: #28a745;
                border: none;
                padding: 10px;
                width: 100%;
                cursor: pointer;
                margin-top: 10px;
                border-radius: 5px;
            }
            .popup h2{
            display: block;
            }

            .popup button:hover, .back-btn:hover {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 10px;
                width: 100%;
                cursor: pointer;
                margin-top: 10px;
                border-radius: 5px;
            }
            .material-icons{
                color:#0044e0;
            }
            .main_content{
                margin-left: 250px;
                width: calc(100% - 250px);
                padding: 20px;
                box-sizing: border-box;
            }
            .pagination {
                margin-top: 20px;
                text-align: center;
            }
            .pagination a {
                margin: 0 5px;
                padding: 5px 10px;
                border: 1px solid #333;
                text-decoration: none;
                color: #333;
            }
            .pagination a.active {
                background-color: #333;
                color: white;
            }
            
            .col_1, .col_2 {
    width: 48%;
    padding: 10px;
}   
.popup_main{
    display: flex;
}

        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <div>
                <h2>Admin</h2>

                <div class="menu-section">
                    <h4> Home </h4>
                    <a href="${pageContext.request.contextPath}/home1">
                        <i class="fa-solid fa-house"></i> Dashboard
                    </a>
                    <h3>Manager</h3>
                    <a href="${pageContext.request.contextPath}/ClubServlet">
                        <i class="fa-solid fa-users"></i> Club
                    </a>
                    <a href="${pageContext.request.contextPath}/settings">
                        <i class="fa-solid fa-cog"></i> Settings
                    </a>
                    <a href="ManageAccount">
                        <i class="fa-solid fa-user"></i> Manage Account
                    </a>
                    <a href="${pageContext.request.contextPath}/listEvent">
                        <i class="fa-solid fa-calendar"></i> Event
                    </a>
                </div>
            </div>
        </div>

        <div class ="main_content">
            <h2>Club List</h2>

            <form action="ClubServlet" method="get">
                <input type="text" name="search" placeholder="Search club..." 
                       value="${requestScope.search != null ? requestScope.search : ''}" />

                <select name="category">
                    <option value="">All Categories</option>
                    <% 
                        List<String> categories = (List<String>) request.getAttribute("categories");
                        if (categories != null) {
                            for (String cat : categories) { 
                    %>
                    <option value="<%= cat %>" <%= (cat.equals(request.getParameter("category")) ? "selected" : "") %>><%= cat %></option>
                    <% 
                            }
                        } 
                    %>
                </select>

                <button type="submit">Filter</button>
            </form>

            <!-- <button class="create-btn"><a href="home">Back to Home</a></button> -->
            <!-- Nút Create Club -->
            <button class="create-btn" onclick="openPopup()" style="cursor: pointer;">Create Club</button>


            <table>
                <tr class ="custom_row">
                    <th>Image</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Public</th>
                    <th>Club Leader</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="club" items="${clubs}">
                    <tr class ="custome_column">
                        <td><img src="${club.imgURL}" width="50"></td>
                        <td>${club.clubID}</td>
                        <td>${club.clubName}</td>
                        <td>${club.category}</td>
                        <td>${club.isPublic}</td>
                        <td>${club.clubleader}</td>
                        <td>
                            <a href="DetailClubServlet?clubID=${club.clubID}">
                                <i class="material-icons" data-toggle="tooltip" title="Edit" >&#xE254;</i>
                            </a>
                            <form action="DeleteClubServlet" method="post" style="display:inline;">
                                <input type="hidden" name="clubID" value="${club.clubID}">
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this club?');"style="color: red; background: none; border: none; cursor: pointer;">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                </button>
                            </form>


                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty clubs}">
                    <tr>
                        <td colspan="6">No clubs found</td>
                    </tr>
                </c:if>

            </table>
            
            <div id="popup" class="popup">
                <div class="popup-content">
                    <h2>Create New Club</h2>
                    <form action="CreateClubServlet" method="post">
                        <div class ="popup_main">
                        <div class ="col_1">
                        <label>Name:</label> 
                        <input type="text" name="name" required><br>

                        <label>Category:</label> 
                        <input type="text" name="category" required><br>

                        <label>Description:</label> 
                        <textarea name="description" required></textarea><br>

                        <label>Image URL:</label> 
                        <input type="text" name="imgURL" required><br>

                        <label>Public:</label> 
                        <select name="isPublic">
                            <option value="Yes">Yes</option>
                            <option value="No">No</option>
                        </select>
                        <br>
                        </div>
                        <div class ="col_2">
                        <label>Club Leader</label>
                        <input type ="text" name = "imgURL" required><br>
                        </div>
                        </div>
                        <button type="submit">Create</button>
                    </form>


                    <button class="back-btn" onclick="closePopup()">Cancel</button>
                </div>
            </div>

            <script>
                // Hàm mở popup
                function openPopup() {
                    document.getElementById('popup').style.display = 'flex';
                }

                // Hàm đóng popup
                function closePopup() {
                    document.getElementById('popup').style.display = 'none';
                }
            </script>

            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="ClubServlet?page=${currentPage - 1}&search=${search}&category=${category}">Previous</a>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="ClubServlet?page=${i}&search=${search}&category=${category}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="ClubServlet?page=${currentPage + 1}&search=${search}&category=${category}">Next</a>
                </c:if>
            </div>
        </div>
    </body>
</html>
