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


<style>
    .form-container {
        width: 50%;
        margin: auto;
        margin-top: 130px;
        display: flex;
        flex-direction: column;
        gap: 20px;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        border: 1px solid #ccc;
        padding: 30px;
        border-radius: 10px;
        background-color: #f9f9f9;
    }
    .form-group {
        display: flex;
        flex-direction: column;
        gap: 8px;
    }
    .row {

    }
    .row .form-group {
        flex: 1;
    }
    input, select, textarea {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }
    textarea {
        resize: vertical;
    }
    .submit-btn {
        padding: 12px;
        background-color: #007BFF;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    label{
        color: #000;
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
                        <h1><span class="colored">Create task</span></h1>
                    </div>
                </div>
            </div>
        </div>

        <!-- Kiểm tra nếu user chưa đăng nhập -->
        <c:if test="${empty sessionScope.acc}">
            <p style="color: red; text-align: center;">User is not logged in! Please log in first.</p>
        </c:if>

        <div class="form-container">
            <%-- Hiển thị lỗi nếu có --%>
            <% if (request.getAttribute("errors") != null) { %>
            <ul style="color: red;">
                <% for (String error : (List<String>) request.getAttribute("errors")) { %>
                <li><%= error %></li>
                    <% } %>
            </ul>
            <% } %>

            <form action="CreateTaskServlet" method="post">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" value="${param.title}" required placeholder="Enter task Title">
                </div>

                <div class="form-group">
                    <label for="clubID">Club</label>
                    <select id="clubID" name="clubID" required>
                        <c:forEach var="club" items ="${requestScope.clubs}">
                            <option value ="${club.clubID}" ${param.clubID == club.clubID ? 'selected' : ''}>
                                ${club.clubName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="eventID">Event</label>
                    <select id="eventID" name="eventID" required>
                        <c:forEach var ="event"  items = "${requestScope.events}">
                            <option value = "${event.id}" ${param.eventID == event.id ? 'selected' : ''}>
                                ${event.eventName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row" style="display: flex; gap: 20px;">
                    <div class="form-group">
                        <label>Assigner</label>
                        <!-- Hiển thị tên đầy đủ -->
                        <input type="text" name="assignerFullName" value="${sessionScope.acc.fullname}" readonly>

                        <!-- Hoặc nếu cần ẩn ID mà vẫn gửi lên server -->
                        <input type="hidden" name="assigner" value="${sessionScope.acc.id}">
                    </div>
                    <div class="form-group">
                        <label >Assignee</label>

                        <select name = "assignee">
                            <c:forEach var="user" items="${users}">
                                <option value ="${user.id}">${user.fullname}</option>z

                             
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row" style="display: flex; gap: 20px;">
                    <div class="form-group">
                        <label for="deadline">Deadline</label>
                        <input type="date" id="deadline" name="deadline" value="${param.deadline}" required>
                    </div>
                    <div class="form-group">
                        <label for="status">Status</label>
                        <select id="status" name="status" required>
                            <option value="Pending" ${param.status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="In Progress" ${param.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
                            <option value="Completed" ${param.status == 'Completed' ? 'selected' : ''}>Completed</option>
                            <option value="To Do" ${param.status == 'To Do' ? 'selected' : ''}>To Do</option>
                            <option value="Cancelled" ${param.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="4" >${param.description}</textarea>
                </div>

                <button type="submit" class="submit-btn">Create Task</button>
            </form>
        </div>




        <!-- CSS -->
        <style>
        </style>

        <!-- JavaScript -->
        <script>
            // Lấy ngày hôm nay theo định dạng YYYY-MM-DD
            let today = new Date();

            // Định dạng YYYY-MM-DD (chỉnh để không bị lệch múi giờ)
            let offset = today.getTimezoneOffset() * 60000; // Lấy chênh lệch múi giờ tính bằng milliseconds
            let localDate = new Date(today.getTime() - offset).toISOString().split("T")[0];

            // Đặt min cho input deadline
            document.getElementById("deadline").setAttribute("min", localDate);
        </script>






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