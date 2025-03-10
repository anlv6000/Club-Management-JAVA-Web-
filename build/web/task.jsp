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

<%
    List<Task> taskList = (List<Task>) request.getAttribute("taskList");
    String selectedStatus = (String) request.getAttribute("selectedStatus");
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
}

table th, table td {
    padding: 12px;
    text-align: center;
    border-bottom: 1px solid #ddd;
}

table th {
    background-color: #007bff;
    color: white;
}

table tr:hover {
    background-color: #f1f1f1;
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

button[type="submit"] {
    background-color: #dc3545;
    color: white;
}

button[type="submit"]:hover {
    background-color: #c82333;
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
</style>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        
        
        <!-- Search Form -->
<form action="taskList" method="GET">
    <label for="status">Enter task name</label>
    <select name="status" id="status" onchange="this.form.submit()">
        <option value="">Priority</option>
        <c:forEach var="status" items="${statusList}">
            <option value="${status}" ${status == selectedStatus ? 'selected' : ''}>${status}</option>
        </c:forEach>
    </select>

    <label for="priority">Choose Status</label>
    <select name="priority" id="priority" onchange="this.form.submit()">
        <option value="">Choose priority</option>
        <c:forEach var="priority" items="${priorityList}">
            <option value="${priority}" ${priority == selectedPriority ? 'selected' : ''}>${priority}</option>
        </c:forEach>
    </select>

    <input type="text" name="search" placeholder="Enter task title" value="${searchKeyword}">
    <button type="submit">Search</button>
</form>

           <button onclick="openPopup()">Create Task</button>
  
        <!-- Task Table -->
        <table border="1" >
            <tr style="background: #007bff; color: #ffffff;">
                <th>TaskID</th>
                <th>ClubID</th>
                <th>Title</th>
                <th>Deadline</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            
            <c:forEach var="task" items="${taskList}">
                <tr style="">
                    <td>${task.taskID}</td>
                    <td>${task.clubID}</td>
                    <td>${task.title}</td>
                    <td>${task.deadline}</td>
                    <td>${task.status}</td>
                    <td>${task.priority}</td>
                    <td>${task.description}</td>
                        <td>
                <form action="taskList" method="post">
    <input type="hidden" name="taskID" value="${task.taskID}">
    <input type="hidden" name="action" value="delete">
    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
</form>


                <form action="editTask.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="taskID" value="${task.taskID}">
                    <button type="submit" style="background: #0044e0">Edit</button>
                </form>
                    <button type="submit" style="background-color: #ffff66; color: #000">Get task</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        
<!-- Popup Form -->
<div id="taskPopup" class="popup">
    <div class="popup-content">
        <h2>Create New Task</h2>
        <form action="CreateTaskServlet" method="post">
            <div class="popup-main" >
                <div class="col_1" style="display: flex; flex-direction: column;">
                    <label>Title*</label>
                    <input type="text" name="title" required placeholder="Task title">
                    <label>ClubID*</label>
                    <input type="text" name="clubID" required placeholder="Club ID existed">
                    <label>EventID</label>
                    <input type="text" name="eventID" required placeholder="Event ID existed">
                    
                </div>
                
                <div class="col_2">
                    <label>Priority*</label>
                    <select name="priority" style = "height: 45.23px;"required>
                        <option value="Low">Low</option>
                        <option value="Medium">Medium</option>
                        <option value="High">High</option>
                    </select>
                    
                    <label>Deadline*</label>
                    <input type="date" name="deadline" style="height:45.23px"required>

                    <label>Status*</label>
                    <select name="status" style="height:45.23px"required>
                        <option value="Pending">Pending</option>
                        <option value="To Do">To Do</option>
                        <option value="Doing">Doing</option>
                        <option value="Closed">Closed</option>
                        <option value="Cancelled">Cancelled</option>
                    </select>
                    
                    
                </div>
                
            </div>
            <label>Description</label>
                    <textarea name="description" placeholder="Enter task description" style="height: 150px;"></textarea>
            <button type="submit">Create</button>
        </form>
        <button class="back-btn" onclick="closePopup()">Cancel</button>
    </div>
</div>



<!-- CSS -->
<style>
    .popup { display: none; position: fixed; top: 20%; left: 30%; width: 40%; background: white; padding: 20px; box-shadow: 0px 0px 10px gray; }
    .popup-main { display: flex; justify-content: space-between; }
    .col_1, .col_2 { width: 45%; }
</style>

<!-- JavaScript -->
<script>
    function openPopup() { document.getElementById("taskPopup").style.display = "block"; }
    function closePopup() { document.getElementById("taskPopup").style.display = "none"; }
</script>


        
        

    </div>
           
           <div class="fh5co-cta" style="background-image: url(images/slide_4.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="col-md-12 text-center animate-box">
                    <h3>We Try To Update The Site Everyday</h3>
                    <p><a href="#" class="btn btn-primary btn-outline with-arrow">Get started now! <i class="icon-arrow-right"></i></a></p>
                </div>
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