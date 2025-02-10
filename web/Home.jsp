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
            h2 {
                text-align: center;
                font-size: 2em;
                color: #333;
                margin-bottom: 20px;
            }

            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
                display: flex;
                flex-direction: column;
                min-height: 100vh;

                background-image: linear-gradient(45deg, #eee 50%, transparent 50%);
                background-size: 20px 20px;
                background-color: #fff;
            }

            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-wrap: wrap;
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
                margin-top: 20px; /* Thêm khoảng cách trên thanh menu */
                display: flex;
                gap: 15px;
                justify-content: center; /* Căn giữa thanh menu */
            }

            nav a {
                color: white;
                text-decoration: none;
                padding: 10px 20px;
                background-color: #444;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            nav a:hover {
                background-color: #555;
                text-decoration: underline;
            }

            .body-content {
                padding: 20px;
                display: flex;
                flex-wrap: wrap;
                justify-content: space-around;
                text-align: center;
                flex-grow: 1;
                margin: 0 auto; /* Cân giữa phần nội dung chính */
                max-width: 1200px; /* Giới hạn độ rộng tối đa của phần nội dung chính */
            }







            .image-item {
                width: calc(40% - 100px); /* Đảm bảo khoảng cách đều giữa hai cột */
                margin: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s, box-shadow 0.3s;
            }

            .image-item:hover {
                transform: translateY(-10px);
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            }

            .body-content img {
                width: 100%;
                max-width: 100%;
                height: auto;
                border-radius: 10px 10px 0 0;
            }

            .body-content .image-item div {
                padding: 10px;
                font-size: 18px;
                font-weight: bold;
                color: #333;
            }

            footer {
                text-align: center;
                padding: 20px;
                background-color: #333;
                color: white;
                bottom: 0;
                width: 100%;
                font-size: 14px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            footer div {
                margin: 5px 0;
            }

            footer a {
                color: white;
                margin: 0 10px;
                text-decoration: none;
            }

            footer a:hover {
                text-decoration: underline;
            }

            .header-logo {
                display: flex;
                align-items: center;
                gap: 10px;
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
                color: #f4f4f9;
            }

            p {
                text-align: center;
                color: #666;
                font-size: 1.2em;
                margin-bottom: 20px;
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




                <div>Hello ${sessionScope.txtUsername}</div>

            </c:if>

            <c:if test="${sessionScope.googleAccount != null}">



                <div>Hello ${sessionScope.name}</div>

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
            <c:if test="${sessionScope.acc != null}">

<a href="${pageContext.request.contextPath}/ClubServlet">Club</a>
                <a href="Login.jsp">Log out</a>

              <c:if test="${sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin'}">
                    <a href="${pageContext.request.contextPath}/ClubServlet">Club</a>
                    <a href="${pageContext.request.contextPath}/settings">Settings</a>
                    <a href="ManageAccount" class="btn btn-primary">Manage Account</a>
                    <a href="${pageContext.request.contextPath}/listEvent">Event</a>

                </c:if>




            </c:if>
            <c:if test="${ sessionScope.accc != null }">
                <a href="Login.jsp">Log out</a>

                <c:if test="${sessionScope.accc.role == 'Admin'}">
                    <a href="${pageContext.request.contextPath}/ClubServlet">Club</a>
                    <a href="${pageContext.request.contextPath}/settings">Settings</a>
                    <a href="ManageAccount" class="btn btn-primary">Manage Account</a>
                    <a href="${pageContext.request.contextPath}/listEvent">Event</a>

                </c:if>
            </c:if>

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
                                <div>${o.clubName}
                                    <a href="Public_ClubServlet?clubName=${o.clubName}">=></a>                                    
                                </div>                              
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
                <a href="#"><img src="" alt="Facebook"></a>
                <a href="#"><img src="" alt="Twitter"></a>
                <a href="#"><img src="" alt="Instagram"></a>
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
