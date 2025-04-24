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

    .info-container {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    .info-item {
        background: #f8f9fa;
        padding: 12px 15px;
        border-left: 4px solid #007bff;
        border-radius: 6px;
        font-size: 16px;
        color: #333;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .info-item b {
        color: #007bff;
    }

    .btn-custom {
        display: block;
        width: fit-content;
        margin: 30px auto;
        padding: 12px 25px;
        background-color: #007bff;
        color: white;
        border-radius: 6px;
        text-decoration: none;
        font-weight: bold;
        font-size: 16px;
        transition: all 0.3s ease;
        text-align: center;
    }

    .btn-custom:hover {
        background-color: #0056b3;
        transform: scale(1.05);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
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

            <div class="container" style="max-width: 500px">
                <h2>Project Detail</h2>
                <div class="info-container">
                    <div class="info-item"><b>Club ID:</b> <span>${project.clubID}</span></div>
                    <div class="info-item"><b>Name:</b> <span>${project.name}</span></div>
                    <div class="info-item"><b>LeaderID:</b> <span>${project.leader_ID}</span></div>

                    <div class="info-item"><b>Form Date:</b> <span>${project.fromDate}</span>
                        <b>End Date:</b> <span>${project.toDate}</span></div>
                    <div class="info-item"><b>Status:</b> <span>${project.status ? "Active" : "Inactive"}
                        </span>
                        <b>Code:</b> <span>${project.code}</span></div>
                    <div class="info-item" style="display: flex; flex-direction: column; align-items: flex-start;">
                        <b>Description:</b> 
                        <span>${project.description}</span>
                    </div>

                </div>

                <a href="ListProject" class="btn-custom">Back to List</a>


            </div>


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