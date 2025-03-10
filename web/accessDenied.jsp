<%-- 
    Document   : accessDenied
    Created on : Feb 24, 2025, 10:21:22 AM
    Author     : ADMIN88
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Access Denied</title>
        <style>
            body {
                text-align: center;
                margin-top: 100px;
                font-family: Arial, sans-serif;
            }
        </style>
    </head>
    <body>
        <h1>Access Denied</h1>
        <p>Your access to this page has been restricted.</p>
        <a href="${pageContext.request.contextPath}/home1">
            <i class="fa-solid fa-house"></i> Home
        </a>
    </body>
</html>

