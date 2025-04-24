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
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
    }

    h2 {
        color: #333;
    }

    form {
        max-width: 500px;
        margin: 20px auto;
        padding: 20px;
        background: #f8f9fa;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
        display: block;
        margin: 10px 0 5px;
        font-weight: bold;
    }

    input, select, textarea {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    textarea {
        resize: vertical;
        min-height: 100px;
    }

    button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        margin-top: 10px;
    }

    button:hover {
        background-color: #0056b3;
    }

    a {
        display: block;
        text-align: center;
        margin-top: 10px;
        color: #007bff;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
    .status-container {
        display: flex;
        gap: 20px; /* Khoảng cách giữa hai ô chọn */
    }

    .status-option {
        display: flex;
        flex-direction: column; /* Đặt label trên radio */
        align-items: center; /* Căn giữa theo chiều dọc */
    }

    .status-option label {
        font-weight: bold;
        margin-bottom: 5px; /* Tạo khoảng cách giữa label và radio */
    }

    .status-option input {
        width: 20px;
        height: 20px;
        cursor: pointer; /* Tăng UX */
    }
    .content input, textarea{
        border-left: 4px solid #007bff;
        color: #333;
        font-size: 16px;
        border-radius: 6px;
    }
    .content label{
            color: #007bff;
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
                        <h1><span class="colored">Project</span> ${project.name}</h1>
                    </div>
                </div>
            </div>
        </div>


        <div class="content" >


            <form action="UpdateProjectServlet" method="post">
                <h2>Update Project</h2>
                <input type="hidden" name="ProjectID" value="${project.projectID}">


                <label for="name">Name:</label>
                <input type="text" id="name" name="Name" value="${project.name}" required>

                <div style="display: flex; width: 100%; gap:10%">
                    <div style="flex: 1">
                        <label for="code">Code:</label>
                        <input type="text" id="code" name="Code" value="${project.code}" required style="width: 100%;">
                    </div>
                    <div style="flex: 1">
                        <label for="leader">Leader:</label>
                        <input type="text" id="leader" name="Leader" value="${project.leader_ID}" style="width: 100%;">
                    </div>
                </div>





                <div style="display: flex; width: 100%; gap:10%">
                    <div>
                        <label for="fromDate">Start Date:</label>
                        <input type="date" id="fromDate" name="FromDate" value="${project.fromDate}" required>
                    </div>
                    <div>
                        <label for="toDate">End Date:</label>
                        <input type="date" id="toDate" name="ToDate" value="${project.toDate}" required>
                    </div>
                </div>
                <div class="status-container">
                    <div class="status-option">
                        <label for="active" style="color:#27ae60">Active</label>
                        <input type="radio" id="active" name="Status" value="true" ${project.status ? 'checked' : ''}>
                    </div>
                    <div class="status-option">
                        <label for="inactive" style="color:#e74c3c">Inactive</label>
                        <input type="radio" id="inactive" name="Status" value="false" ${!project.status ? 'checked' : ''}>
                    </div>
                </div>




                <label for="description">Description:</label>
                <textarea id="description" name="Description" required>${project.description}</textarea>



                <button type="submit" style="color:white">Update</button>
            </form>


        </div>




    </body>
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