<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT University Club - Sign Up</title>
    <link href="css/Register.css" rel="stylesheet" type="text/css"/>
    <style> .google-signup button {
            background-color: #f5f5f5;
            color: #333;
            border: 1px solid #ccc;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: underline;
        }</style>
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
            <h2>Login</h2>
            <form action="LoginServlet" method="post"> 
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="user" type="text" id="username" placeholder="Enter your username" required>
                </div>

                <div class="form-group password-toggle">
                    <label for="password">Password</label>
                    <input name="pass" type="password" id="password" placeholder="Enter your password" required>
                    <span onclick="togglePassword('password')"></span>
                </div>

                <c:if test="${messerr !=null}" > ${messerr} </c:if>
                

                
 <div class="google-signup">
                    <button type="button">Forgot Password or Username</button>
                </div>
                <button type="submit" class="btn">Sign in</button>

                <div class="divider">OR</div>
<c:if test="${mess != null}"> ${mess}</c:if>
                <div class="google-signup">
                <c:if test="${sessionScope.acc == null}">
                    <a href="https://accounts.google.com/o/oauth2/auth?
scope=email profile openid&
client_id=359282860225-atna0216v504grriqa6g819uvcgfnli6.apps.googleusercontent.com&
redirect_uri=http://localhost:8080/club_manaaaa/LoginServlet&
response_type=code&
access_type=offline" target="_blank">Sign in with Google</a>
                   
   
</c:if>
                    
                        <a href="../../../AppData/Local/Temp/FPT University Club - Sign Up.url"></a>
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
        document.addEventListener("DOMContentLoaded", function() {
        const logoContainer = document.getElementById("logo-container");

        // Create navigation container
        const nav = document.createElement("nav");
        nav.innerHTML = `
            <a href="#">Home</a>
            <a href="PublicClub.jsp">Public Club</a>
            <a href="Login.jsp">Login</a>
            <a href="Register.jsp">Register</a>
        `;

        // Append navigation to logo container
        logoContainer.appendChild(nav);

        // Add style to align navigation inside logo-container
        const style = document.createElement("style");
        style.innerHTML = `
            #logo-container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            #logo-container nav {
                margin-top: 10px;
                display: flex;
                gap: 15px;
            }

            #logo-container nav a {
                text-decoration: none;
                color: #000;
                font-weight: bold;
            }

            #logo-container nav a:hover {
                text-decoration: underline;
            }
        `;
        document.head.appendChild(style);
    });
    </script>
</body>
</html>
