<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT University Club - Sign Up</title>
    <link href="css/Register.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container">
        <!-- Left Section -->
        <div class="left-section">
            <h1>FPT University Club</h1>
            <img src="https://via.placeholder.com/400" alt="Club Banner">
            <p>Join us to connect, learn, and grow with fellow students!</p>
        </div>

        <!-- Right Section -->
        <div class="right-section">
            <h2>Sign Up</h2>
            
                <form action="RegisterServlet" >
                    <c:if test="${eru != null}">${eru}</c:if>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="user" type="text" id="username" placeholder="Enter your username" >
                </div>
               
<c:if test="${erp != null}">${erp}</c:if>
                <div class="form-group password-toggle">
                    <label for="password">Password</label>
                    <input name="pass" type="password" id="password" placeholder="Enter new password" >
                    <span onclick="togglePassword('password')"></span>
                </div>
                <c:if test="${err != null}">${err}</c:if>
                <div class="form-group password-toggle">
                    <label for="verification">Verification</label>
                    <input name="re_pass" type="password" id="verification" placeholder="Confirm password" >
                    <span onclick="togglePassword('verification')"></span>
                </div>
                <c:if test="${mess != null}">${mess}</c:if>

                <button type="submit" class="btn">Sign Up</button>

                <div class="divider">OR</div>

                <div class="google-signup">
                    <button><a href="GmailSignup.jsp"> Sign Up With Google</a></button>
                    
                </div>
            </form>
        </div>
    </div>

    <script>
        function togglePassword(id) {
            const input = document.getElementById(id);
            if (input.type === 'password') {
                input.type = 'text';
            } else {
                input.type = 'password';
            }
        }
    </script>
</body>
</html>
