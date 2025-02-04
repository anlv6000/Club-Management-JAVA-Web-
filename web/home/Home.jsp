<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FPT Club Manager</title>
        <link href="css/Home.css" rel="stylesheet" type="text/css" />
        <link rel="icon" href="https://example.com/favicon.ico" type="image/x-icon">
        <style>
            /* CSS của bạn đã được chỉnh sửa */
            h2 {
                text-align: center; /* Điều chỉnh align-items thành text-align để căn giữa h2 */

            }

            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
            }

            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-wrap: wrap; /* Thêm điều chỉnh flex-wrap để logo không bị đẩy ra ngoài màn hình */
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

            nav {
                margin-left: 30px;
                display: flex;
                gap: 15px;
            }

            nav a {
                color: gray;
                text-decoration: none;
                padding: 5px 10px;
                border: 1px solid white;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            nav a:hover {
                background-color: white;
                color: #333;
                text-decoration: underline; /* Thêm hiệu ứng gạch chân khi hover */
            }

            .body-content {
                padding: 20px;
                display: flex;
                flex-wrap: wrap;
                justify-content: space-around;
                text-align: center;
            }

            .image-item {
                width: 35%;
                margin: 50px;
            }

            .body-content img {
                width: 100%;
                max-width: 300px;
                height: auto;
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
                bottom: 0;
                width: 100%;
                font-size: 14px;
            }

            .image-grid {
                display: grid;
                grid-template-columns: repeat(2, 1fr); /* 2 columns */
                gap: 20px;
                padding: 20px;
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

            .header-logo {
                display: flex;
                align-items: center;
                gap: 10px; /* Thêm điều chỉnh gap để logo không bị dính vào tiêu đề */
            }

            .header-logo img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                cursor: pointer;
                object-fit: cover;
            }

            .header-logo h1 {
                margin: 0;
                font-size: 20px;
            }

            h2{
                text-align: center;
            }
            p{
                text-align: center;
            }
        </style>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const items = document.querySelectorAll('.image-item');

                items.forEach((item, index) => {
                    item.style.float = (index % 2 === 0) ? 'left' : 'right';
                    if (index % 2 === 0) {
                        item.style.clear = 'left';
                    } else {
                        item.style.clear = 'right';
                    }
                });
            });
        </script>

    </head>

    <body>
        <header>
            <div class="header-logo">
                <img src="https://th.bing.com/th/id/OIP.HEjDxqON9OjorjiDpDhDPQHaFj?rs=1&pid=ImgDetMain" alt="alt" />
                <h1>FPT Club Manager</h1>
            </div>

            <c:if test="${sessionScope.acc != null}">

               
                    
                
                <div>hello ${sessionScope.txtUsername}</div>
                
            </c:if>
                 <c:if test="${sessionScope.googleAccount != null}">
               
                    
                
                <div>hello ${sessionScope.name}</div>
                
            </c:if>
               



            <div id="logo-container">
                <!-- Default logo -->
                <img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo">
            </div>
        </header>

        <nav>
            <a href="#">Home</a>
            <c:if test="${sessionScope.acc == null && sessionScope.accc == null}">
                <a href="Login.jsp">Sign in</a>
                <a href="Register.jsp">Sign up</a>
            </c:if>
            <c:if test="${sessionScope.acc != null ||sessionScope.accc != null }">

                
                <a href="Login.jsp">Log out</a>
                <c:if test="${sessionScope.acc.role == 'Admin' || sessionScope.accc.role == 'Admin'}">
                     <a href="Login.jsp">Clubs List</a>
                                          <a href="Login.jsp">Users List</a>

                </c:if>

               

            </c:if>
            <a href="${pageContext.request.contextPath}/settings">Settings</a>
            <a href="ManageAccount" class="btn btn-primary">Manage Account</a>
        </nav>
        <h2>Welcome to FPT Club!</h2>
        <p>Explore our club activities and join us today!</p>
        <div class="body-content">

            <form id="autoSubmitForm" action="Public_ClubServlet" >

            <form id="autoSubmitForm" action="Public_ClubServlet" method="POST">

                <!-- Display data -->
                <c:if test="${not empty listP}">
                    <c:forEach var="o" items="${listP}">
                        <div class="image-item">
                            <img src="${o.imgURL}" alt="vov">
                            <div>${o.clubName}</div>
                        </div>
                    </c:forEach>
                </c:if>
            </form>
        </div>




        <footer>
            <p>&copy; 2025 FPT Club Manager. All Rights Reserved.</p>
            <div>
                <a href="#">Privacy Policy</a> | 
                <a href="#">Terms of Service</a> | 
                <a href="#">Contact Us</a>
            </div>
            <div>
                <p>Follow us on:</p>
                <a href="#"><img src="https://example.com/facebook-icon.png" alt="Facebook"></a>
                <a href="#"><img src="https://example.com/twitter-icon.png" alt="Twitter"></a>
                <a href="#"><img src="https://example.com/instagram-icon.png" alt="Instagram"></a>
            </div>
        </footer>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
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
                logo.addEventListener("click", function () {
                    if (isLoggedIn) {
                        alert("Welcome back, User!");
                    } else {
                        const option = confirm("You are not logged in. Would you like to login or register?");
                        if (option) {
                            window.location.href = "#"; // Link to login/register page
                        }
                    }
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
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
