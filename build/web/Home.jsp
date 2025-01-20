<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT Club Manager</title>
    <link href="css/Home.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS của bạn đã được chỉnh sửa */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
<<<<<<< HEAD

=======
>>>>>>> d2eed3358e4482e50d65286431d6555c71efc463

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #333;
            color: white;
        }

<<<<<<< HEAD
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #333;
            color: white;
        }

        header img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
        }

=======
        header img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            cursor: pointer;
        }

>>>>>>> d2eed3358e4482e50d65286431d6555c71efc463
        nav {
            display: flex;
            gap: 15px;
        }

        nav a {
            color: black;
            text-decoration: none;
            padding: 5px 10px;
            border: 1px solid black;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        nav a:hover {
            background-color: black;
            color: #333;
            text-decoration: underline; /* Thêm hiệu ứng gạch chân khi hover */
        }

        .body-content {
            padding: 20px;
            text-align: center;
            display: flex; /* Thêm display: flex để chia thành 2 cột */
            justify-content: space-around; /* Giúp chia đều khoảng cách giữa các cột */
        }

        .column {
            flex: 1;
            padding: 20px;
        }

        .body-content img {
            width: 300px;
            height: 200px;
            margin: 10px;
            border-radius: 10px;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .body-content img:hover {
            transform: scale(1.1);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: white;
        }

        .image-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* 2 columns */
            gap: 20px;
            padding: 20px;
        }

        .image-item {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .image-item img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }

        .image-item div {
            margin-top: 10px;
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }
    </style>
</head>
<body>
    <header>
        <div>
<<<<<<< HEAD

            <img <img src="https://inkythuatso.com/uploads/thumbnails/800/2021/11/logo-mu-inkythuatso-3-01-05-15-53-03.jpg" alt="alt"/>

            <img src="https://aothethaothietke.com/wp-content/uploads/2022/05/Logo-cau-lac-bo-bong-da-Ngoai-hang-Anh-%E2%80%93-Manchester-United.jpg.web" alt="alt"/>

=======
            <img src="https://aothethaothietke.com/wp-content/uploads/2022/05/Logo-cau-lac-bo-bong-da-Ngoai-hang-Anh-%E2%80%93-Manchester-United.jpg.web" alt="alt"/>
>>>>>>> d2eed3358e4482e50d65286431d6555c71efc463
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

<<<<<<< HEAD

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

            <form id="autoSubmitForm" action="Public_ClubServlet" method="POST">
                <!-- Display data -->
                <c:if test="${not empty listP}">
                    <c:forEach var="o" items="${listP}">
                        <div class="image-item">
                            <img src="${o.imgURL}" alt="vov">
                            <div>${o.clubName}</div>
                            <div>${o.description}</div>
                        </div>
                    </c:forEach>
                </c:if>
            </form>
        </div>


=======
            <form id="autoSubmitForm" action="Public_ClubServlet" method="POST">
                <!-- Display data -->
                <c:if test="${not empty listP}">
                    <c:forEach var="o" items="${listP}">
                        <div class="image-item">
                            <img src="${o.imgURL}" alt="vov">
                            <div>${o.clubName}</div>
                            <div>${o.description}</div>
                        </div>
                    </c:forEach>
                </c:if>
            </form>
        </div>

>>>>>>> d2eed3358e4482e50d65286431d6555c71efc463
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
