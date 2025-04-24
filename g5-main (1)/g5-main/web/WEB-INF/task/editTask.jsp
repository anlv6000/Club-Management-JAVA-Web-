
<jsp:include page="header.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="DAO.TaskDAO, DAO.ClubDAO, Model.Task1, Model.Club, Model.Event" %>
<%@ page import="java.util.List"%>

<%
    Task1 task = (Task1) request.getAttribute("task");
    
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Task Detail</title>
        <style>
            .form-container {
                width: 50%;
                margin: auto;
                padding: 20px;
                border: 1px solid #333;
                border-radius: 5px;
                background: #fff;
                display: flex;
                flex-direction: column;
                gap: 15px;
            }
            .form-group {
                display: flex;
                flex-direction: column;
            }
            .form-row {
                display: flex;
                gap: 15px;
            }
            .form-row .form-group {
                flex: 1;
            }
            .form-group label {
                font-weight: bold;
                color: #333;
            }
            .form-group input, .form-group select, .form-group textarea {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            span{
                color: #0000cc;
            }
            .form-container h2{
                padding: 10px;
            }
            .form-group span{
                font-weight: bold;
                padding: 5px;
            }
       
        </style>
    </head>
    <body>
        <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 animate-box">
                        <h1><span class="colored">Task: </span> <%= task.getTitle() %></h1>
                    </div>
                </div>
            </div>
        </div>

        <%-- Hiển thị danh sách lỗi --%>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && !errors.isEmpty()) {
        %>
        <div class="error-messages" style="color: red;">
            <ul>
                <% for (String error : errors) { %>
                <li><%= error %></li>
                    <% } %>
            </ul>
        </div>
        <% } %>
        <div class="form-container">
            <h2>Task Detail</h2>

            <form action="updateTaskServlet" method="post">
                <input type="hidden" name="taskID" value="<%= task.getTaskID() %>">

                <div class="form-group">
                    <label>Title</label>
                    <input type="text" name="title" style="width:100%"value="<%= task.getTitle() %>" required>
                    
                </div>
                <div class="form-row">
                    <div class="form-group" style="width:50%">
                        <label>Club</label>
                        <input type="hidden" name="clubID" value="<%= task.getClubID() %>" readonly>
                        <input type="text" name="clubID" style="background-color: #cccccc" value="<%= task.getClubName() %>" readonly>
                       
                    </div>

                    <div class="form-group" style="width:50%">
                        <label>Event</label>
                        
                        <input type="hidden" name="eventID" value="<%= task.getEventID() %>" readonly>
                        <input type="text" name="clubID" style="background-color: #cccccc" value="<%= task.getEventName() %>" readonly>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>AssignerID</label>
                        <input type="hidden" name="assigner" value="<%= task.getAssignerID() %>" readonly>
                        <input type="text" name="assignerFullName" value="<%= task.getAssignerName() %>" readonly>
                    </div>
                    <div class="form-group">
                        <label>Assignee ID</label>
                        
                        <select name="assignee">
                            <c:forEach var="user" items="${users}">
                                <option value="${user.id}" <c:if test="${user.id == task.assigneeID}">selected</c:if>>
                                    ${user.fullname}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Deadline</label>
                        <input type="date" id="deadline" name="deadline" value="<%= task.getDeadline() %>" required>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select name="status" required>
                            <option value="Pending" <%= task.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                            <option value="To Do" <%= task.getStatus().equals("To Do") ? "selected" : "" %>>To Do</option>
                            <option value="Doing" <%= task.getStatus().equals("Doing") ? "selected" : "" %>>Doing</option>
                            <option value="Closed" <%= task.getStatus().equals("Closed") ? "selected" : "" %>>Closed</option>
                            <option value="Cancelled" <%= task.getStatus().equals("Cancelled") ? "selected" : "" %>>Cancelled</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description"  maxlength="500" minlength="5"><%= task.getDescription() %></textarea>
                </div>

                <c:if test="${sessionScope.acc.id == task.assignerID}">
                    <button type="submit">Update Task</button>
                </c:if>

            </form>
        </div>

        <script>
            setTimeout(() => {
                document.getElementById("success-messageTask").style.display = "none";
            }, 3000);

        </script>
        <jsp:include page="footer.jsp" />

        <script>
            // Lấy ngày hôm nay theo định dạng YYYY-MM-DD
            let today = new Date();

// Định dạng YYYY-MM-DD (chỉnh để không bị lệch múi giờ)
            let offset = today.getTimezoneOffset() * 60000; // Lấy chênh lệch múi giờ tính bằng milliseconds
            let localDate = new Date(today.getTime() - offset).toISOString().split("T")[0];

// Đặt min cho input deadline
            document.getElementById("deadline").setAttribute("min", localDate);
        </script>

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