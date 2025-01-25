<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT Club Manager</title>
    <-<!-- comment --><link href="css/Home.css" rel="stylesheet" type="text/css"/>
    
</head>
<body>
    <header>
        <div>

            <img <img src="https://inkythuatso.com/uploads/thumbnails/800/2021/11/logo-mu-inkythuatso-3-01-05-15-53-03.jpg" alt="alt"/>

            <img src="https://aothethaothietke.com/wp-content/uploads/2022/05/Logo-cau-lac-bo-bong-da-Ngoai-hang-Anh-%E2%80%93-Manchester-United.jpg.web" alt="alt"/>

            <img src="https://aothethaothietke.com/wp-content/uploads/2022/05/Logo-cau-lac-bo-bong-da-Ngoai-hang-Anh-%E2%80%93-Manchester-United.jpg.web" alt="alt"/>

            <h1>FPT Club Manager</h1>
        </div>
        
        <c:if test="${sessionScope.acc != null}">
            <div>hello ${sessionScope.txtUsername}</div>
        </c:if>

        <div id="logo-container">
            <!-- Default logo -->
            <img src="https://media.discordapp.net/attachments/1326181143841869847/1328762115007451279/image.png?ex=6787e1a7&is=67869027&hm=982177fd4bb7b909b36866ac49ca606b0ccc15ffa87e3ade47020082b388cd13&=&format=webp&quality=lossless" alt="Logo">
        </div>
    </header>

    <div>
        <a href="#">Home</a>
        <a href="PublicClub.jsp">Public Club</a>
        <c:if test="${sessionScope.acc == null}">
            <a href="Login.jsp">Sign in</a>
            <a href="Register.jsp">Sign up</a>
        </c:if>
        <c:if test="${sessionScope.acc != null}">
            <a href="Login.jsp">Log out</a>
        </c:if>
    </div>

    <div class="body-content">
        <div class="column">
            <h2>Welcome to FPT Club!</h2>
            <p>Explore our club activities and join us today!</p>



   <form id="autoSubmitForm" action="Public_ClubServlet" >
    <!-- Hiển thị dữ liệu -->
    <c:if test="${not empty listP}">
        <c:forEach var="o" items="${listP}">
            <div class="image-grid">
                <div class="image-item">
                    
                    <img src="${o.imgURL}" alt="vov">
                    <div>${o.clubName}</div>
                    <div>${o.description}</div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</form>

            



           


        <div class="column">
            <!-- Add additional content for the right column here -->
        </div>
    </div>

    <footer>
        <p>&copy; 2025 FPT Club Manager. All Rights Reserved.</p>
    </footer>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const logo = document.getElementById("logo");

            // Simulate logged-in state for demonstration
            let isLoggedIn = false;
            let userAvatar = "user-avatar.png"; // Change to actual uploaded avatar

            // Update logo based on login state
            if (isLoggedIn) {
                logo.src = userAvatar;
                logo.alt = "User Avatar";
            } else {
                logo.src = "default-user.png";
                logo.alt = "Default Logo";
            }

            // Add click event to the logo
            logo.addEventListener("click", function() {
                if (isLoggedIn) {
                    alert("Welcome back, User!");
                } else {
                    const options = confirm("You are not logged in. Would you like to login or register?");
                    if (options) {
                        window.location.href = "#"; // Link to login/register page
                    }
                }
            });
        });

        document.addEventListener("DOMContentLoaded", function() {
            const form = document.getElementById("autoSubmitForm");
            if (!sessionStorage.getItem("formSubmitted")) { 
                // Chỉ submit nếu chưa được gửi
                sessionStorage.setItem("formSubmitted", "true");
                form.submit();
            }
        });
    </script>
</body>
</html>
