<%-- 
    Document   : task
    Created on : Mar 3, 2025, 2:11:10 AM
    Author     : admin
--%>
<jsp:include page="header.jsp"></jsp:include>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="Model.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">


<%
    List<Task> taskList = (List<Task>) request.getAttribute("taskList");
    String selectedStatus = (String) request.getAttribute("selectedStatus");
    String searchKeyword = (String) request.getAttribute("searchKeyword");
    if (searchKeyword == null) searchKeyword = "";
    
%>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
}

.content {
    padding: 130px 50px 50px 50px;
}

h2 {
    color: #333;
}

/* Search Form */
form[action="taskList"] input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    width: 250px;
}

form[action="taskList"] button {
    padding: 10px 15px;
    border: none;
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

form[action="taskList"] button:hover {
    background-color: #0056b3;
}

/* Task Table */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    background: white;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border: none; /* Xóa viền bảng */
}

table th, table td {
    padding: 12px;
    text-align: center;
    border: none; /* Xóa viền giữa các ô */
}

table th {
    background-color: #0000ff;
    color: white;
}

table tr:hover {
    background-color: #f1f1f1;
}
table td{
    color:#000;
    font-size: 18px;
}
/* Buttons */
button {
    padding: 8px 12px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

button[onclick="openPopup()"] {
    background-color: #28a745;
    color: white;
}

button[onclick="openPopup()"]:hover {
    background-color: #218838;
}



.create-btn{
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid black;
    color:#333;
}

/* Popup */
.popup {
    display:none;
}

.popup h2 {
    text-align: center;
    color: #333;
}

.popup button {
    width: 100%;
    margin-top: 10px;
}

.popup-main {
    display: flex;
    justify-content: space-between;
}

.col_1, .col_2 {
    width: 48%;
}

.popup input, .popup select, .popup textarea {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
}
/* Định dạng bảng với màu xen kẽ */
table tr:nth-child(odd) {
    background-color: #f2f2f2; /* Màu xám nhạt */
}

table tr:nth-child(even) {
    background-color: #ffffff; /* Màu trắng */
}
.pagination {
                margin-top: 20px;
                text-align: center;
            }
            .pagination a, .pagination .current{
                margin: 0 5px;
                padding: 5px 10px;
                border: 1px solid #333;
                text-decoration: none;
                color: #333;
            }
            .pagination .current {
                background-color: #333;
                color: white;
            }
            button[type="submit"] {
    height: 49.21px; 
    padding: 0 15px;
    align-items: center;
    justify-content: center;
}
</style>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">;
    
</head>

<body>
    
      <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 animate-box">
                            <h1><span class="colored">Task</span> List</h1>
                        </div>
                    </div>
                </div>
            </div>
    
        
    <div class="content" style="padding-top: 130px; padding-bottom: 50px; margin-left: 50px;">
        <h2>Task List</h2>
        
<form action="taskList" method="GET">
    <!-- Ô tìm kiếm -->
    <input type="text" name="search" placeholder="Enter task title or event name" value="${searchKeyword}">

    
<button type="submit"> <i class="fa fa-search" ></i></button>
  
</form>


    

<form action="CreateTaskServlet" method="get">
    <button type="submit" class="create-btn">Create Task</button>
</form>
  
        <!-- Task Table -->
        <table border="1" >
            <tr >
                <th>ID</th>
                <th>Title</th>
                <th>Event</th>
                <th>Deadline</th>
                <th>Status</th>
                <th>Assignee</th>
                <th>Action</th>
            </tr>
            
            <c:forEach var="task" items="${taskList}">
                <tr style="">
                    <td>${task.taskID}</td>
                    <td>${task.title}</td>
                     <td>${task.eventName}</td>
                    <td>${task.deadline}</td>
                    <td>${task.status}</td>
                    <td>${task.assigneeID} - ${task.assigneeName}</td>

                    
                    <td style="display:flex; justify-content: center;">
                         <c:if test="${sessionScope.acc.id == task.assignerID}">
                <form action="taskList" method="post">
                    <input type="hidden" name="taskID" value="${task.taskID}">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit" onclick="return confirm('Are you sure?')"
                            style="background: none; border: none; cursor: pointer;">
                        <i class="fa-solid fa-trash" style="color:red; font-size: 18px;" title="Delete"></i>
                    </button>
                </form>
            </c:if>


                        <form action="updateTaskServlet" method="get" style="display:inline;">
                            <input type="hidden" name="taskID" value="${task.taskID}">
                            
                                <button type="submit" style="background: none; border: none; cursor: pointer;">
                                    <i class="fa-solid fa-pen-to-square" style="color: #0044e0; font-size: 18px;" title="Edit"></i>
                            </button>
                        </form>

                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${not empty sessionScope.taskSuccess}">
                <div id="taskSuccess"
                     style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                     background: green; color: white; padding: 10px 20px;
                     border-radius: 5px; z-index: 1000;">
                    ${sessionScope.clubMessage}
                </div>
                <c:remove var="taskSuccess" scope="session"/>
            </c:if>


 
<c:if test="${not empty sessionScope.successMessageTask}">
            <div id="success-messageTask"
                 style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                 background: green; color: white; padding: 10px 20px;
                 border-radius: 5px; z-index: 1000;">
                ${sessionScope.successMessageTask}
            </div>
            <c:remove var="successMessageTask" scope="session"/>
        </c:if>
        
        <script>
            setTimeout(() => {
                document.getElementById("success-messageTask").style.display = "none";
            }, 3000);

        </script>

<!-- JavaScript -->
<script>
    
</script>
<% int currentPage = (int) request.getAttribute("currentPage");
   int totalPages = (int) request.getAttribute("totalPages");
   
%>

<div class="pagination">
    <!-- Trang đầu -->
    <c:if test="${currentPage > 2}">
        <a href="taskList?page=1&search=${searchKeyword}">1</a>
    </c:if>

    <!-- Trang trước -->
    <c:if test="${currentPage > 1}">
        <a href="taskList?page=${currentPage - 1}&search=${searchKeyword}">${currentPage - 1}</a>
    </c:if>

    <!-- Trang hiện tại -->
    <span class="current">${currentPage}</span>

    <!-- Trang tiếp -->
    <c:if test="${currentPage < totalPages - 1}">
        <a href="taskList?page=${currentPage + 1}&search=${searchKeyword}">${currentPage + 1}</a>
    </c:if>

    <!-- Trang cuối -->
    <c:if test="${currentPage < totalPages}">
        <a href="taskList?page=${totalPages}&search=${searchKeyword}">${totalPages}</a>
    </c:if>
</div>


    
        
        

    </div>
           

        <jsp:include page="footer.jsp" />




        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></script>

        <!-- MAIN JS -->
        <script src="js/main.js"></script>
</body>
<meta charset="UTF-8">
        <title>Finance Detail</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <!-- Facebook and Twitter integration -->
        <meta property="og:title" content=""/>
        <meta property="og:image" content=""/>
        <meta property="og:url" content=""/>
        <meta property="og:site_name" content=""/>
        <meta property="og:description" content=""/>
        <meta name="twitter:title" content="" />
        <meta name="twitter:image" content="" />
        <meta name="twitter:url" content="" />
        <meta name="twitter:card" content="" />

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico">
        <link href='https://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'> -->

        <!-- Animate.css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="css/icomoon.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <!-- Flexslider  -->
        <link rel="stylesheet" href="css/flexslider.css">
        <!-- Theme style  -->
        <link rel="stylesheet" href="css/style.css">

        <!-- Modernizr JS -->
        <script src="js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="js/respond.min.js"></script>
        <![endif]-->
</html>