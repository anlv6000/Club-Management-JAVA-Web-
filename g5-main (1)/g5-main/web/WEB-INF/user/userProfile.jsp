<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Profile</title>
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
        <style>
            .profile-container1 {
                width: 70%; /* Chiều rộng chỉ chiếm 80% của vùng cha */
                margin-left: 15%; /* Thêm khoảng trống 10% ở bên trái */
                margin-right: 15%; /* Thêm khoảng trống 10% ở bên phải */
            }
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.5);
            }
            .modal-content {
                background-color: #fff;
                margin: 15% auto;
                padding: 20px;
                border-radius: 8px;
                width: 50%;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover, .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }
            .btn-primary {
                background-color: #3498db; /* Màu nền */
                border-color: #2980b9;    /* Màu viền */
                color: #fff;             /* Màu chữ */
                font-size: 16px;         /* Kích thước chữ */
                padding: 10px 30px;      /* Padding */
                border-radius: 5px;      /* Độ cong viền */
                margin-right: 15%;
            }

            .btn-secondary {
                background-color: #95a5a6; /* Màu nền */
                border-color: #7f8c8d;     /* Màu viền */
                color: #fff;              /* Màu chữ */
                font-size: 16px;          /* Kích thước chữ */
                padding: 10px 30px;       /* Padding */
                border-radius: 5px;       /* Độ cong viền */
                margin-right: 20%;
            }

            .btn-third {
                background-color: #95a5a6; /* Màu nền */
                border-color: #7f8c8d;     /* Màu viền */
                color: #fff;              /* Màu chữ */
                font-size: 16px;          /* Kích thước chữ */
                padding: 10px 30px;       /* Padding */
                border-radius: 5px;       /* Độ cong viền */
            }
            .success-popup {
                position: fixed;
                bottom: 20px;
                right: 20px;
                background: #e7f9e7;
                color: #2e7d32;
                padding: 10px 20px;
                border-radius: 5px;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
                font-size: 14px;
                z-index: 1000;
                animation: fadeInOut 1.5s ease-in-out forwards;
            }

            /* Animation for fade-in and fade-out */
            @keyframes fadeInOut {
                0% {
                    opacity: 0;
                    transform: translateY(20px);
                }
                10% {
                    opacity: 1;
                    transform: translateY(0);
                }
                90% {
                    opacity: 1;
                    transform: translateY(0);
                }
                100% {
                    opacity: 0;
                    transform: translateY(20px);
                }
            }

        </style>
        <jsp:include page="header.jsp"></jsp:include>

        </head>
        <body>
            <script>
                function previewAvatar(event) {
                    const reader = new FileReader();
                    reader.onload = function () {
                        const avatarPreview = document.getElementById('avatar-preview');
                        avatarPreview.src = reader.result; // Set the uploaded image as the avatar preview
                    }
                    reader.readAsDataURL(event.target.files[0]); // Read the file
                }
                function openPhoneNameModal() {
                    document.getElementById("phoneNameModal").style.display = "block";
                }

                function closePhoneNameModal() {
                    document.getElementById("phoneNameModal").style.display = "none";
                }

                function openEmailModal() {
                    document.getElementById("emailModal").style.display = "block";
                }

                function closeEmailModal() {
                    document.getElementById("emailModal").style.display = "none";
                }

                function openPasswordModal() {
                    document.getElementById("passwordModal").style.display = "block";
                }

                function closePasswordModal() {
                    document.getElementById("passwordModal").style.display = "none";
                }

// Đóng modal khi click ra ngoài
                window.onclick = function (event) {
                    if (event.target == document.getElementById("fullnameEmailPhoneModal")) {
                        closeFullnameEmailPhoneModal();
                    }
                    if (event.target == document.getElementById("passwordModal")) {
                        closePasswordModal();
                    }
                }
                window.onload = function () {
                    // Check if modal needs to be reopened
                    const modalStatus = document.getElementById("passwordModal").getAttribute("style");
                    if (modalStatus === "display: block;") {
                        document.getElementById("passwordModal").style.display = "block";
                    }
                };
                window.onload = function () {
                    // Check if modal needs to be reopened
                    const modalStatus = document.getElementById("emailModal").getAttribute("style");
                    if (modalStatus === "display: block;") {
                        document.getElementById("emailModal").style.display = "block";
                    }
                };

                window.onload = function () {
                    const successMessage = '<c:out value="${SuccessMessage}" />';
                    const successPopup = document.getElementById("successPopup");

                    if (successMessage && successMessage.trim() !== "") {
                        // Hiển thị popup
                        successPopup.style.display = "block";
                        successPopup.innerHTML = `<p><span style="color: green;">Thành công:</span> ${SuccessMessage}</p>`;

                        // Tự động ẩn sau 1 giây
                        setTimeout(() => {
                            successPopup.style.display = "none";
                        }, 10000);
                    }
                };


        </script>
        <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 animate-box">
                        <h1><span class="colored">User</span> Profile</h1>
                    </div>
                </div>
            </div>
        </div>
        <br>

        <div class="profile-container1">
            <div class="text-center mb-4">
                <h2>Thông tin tài khoản</h2>

            </div>

            <form action="${pageContext.request.contextPath}/userProfile?action=updateProfileImage" method="post" enctype="multipart/form-data">
                <div class="row mb-3 text-center">
                    <!-- Nhãn Avatar -->
                    <div class="col-12">
                        <label for="profileImage" class="form-label ">Avatar:</label>
                    </div>
                    <div class="col-12 mt-3 border-radius" style="
                         width: 150px; /* Set the circle's size */
                         height: 150px;
                         border-radius: 50%;
                         overflow: hidden;
                         display: inline-block;">
                        <img 
                            src="<c:choose>
                                <c:when test='${not empty userProfiles[0].imageURL}'>
                                    ${userProfiles[0].imageURL}
                                </c:when>
                                <c:otherwise>
                                    https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg
                                </c:otherwise>
                            </c:choose>" 
                            alt="Avatar" 
                            style="width: 100%; height: auto; object-fit: cover;">
                    </div>
                    <!-- Trường tải lên ảnh -->
                    <div class="col-12">
                        <input type="file" id="profileImage" name="profileImage" accept="image/*" class="form-control" style="margin: 10px auto; max-width: 300px;">
                        <c:if test="${not empty imageError}">
                            <div class="error-message" style="color: red; margin-top: 10px;">${imageError}</div>
                        </c:if>
                    </div>

                    <!-- Hiển thị Avatar -->

                </div>

                <!-- Nút Cập nhật -->
                <div class="row text-center mt-4">
                    <div class="col-12">
                        <button type="submit" class="btn btn-success">Cập nhật ảnh đại diện</button>
                    </div>
                </div>
            </form>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="fullname" class="form-label">Họ và tên:</label>
                    <input type="text" class="form-control" id="fullname" value="${userProfiles[0].fullname}" readonly>
                </div>
                <div class="col-md-6">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" value="${userProfiles[0].email}" readonly>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="phone" class="form-label">Số điện thoại:</label>
                    <input type="text" class="form-control" id="phone" value="${userProfiles[0].phone}" readonly>
                </div>
                <div class="col-md-6">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" value="${pass}" readonly>
                </div>

            </div>
            <div class="row mb-3">
                <div class="col-md-12">
                    <label for="clubNames" class="form-label">Danh sách các câu lạc bộ:</label>
                    <ul class="list-group">
                        <c:forEach var="userProfile" items="${userProfiles}">
                            <li class="list-group-item">${userProfile.clubName}</li>
                            </c:forEach>
                    </ul>
                </div>
            </div>

            <div id="phoneNameModal" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closePhoneNameModal()">&times;</span>
                    <h1>Cập nhật Số điện thoại và Họ tên</h1>
                    <form action="${pageContext.request.contextPath}/userProfile?action=updatePhoneName" method="post">
                        <!-- Fullname Field -->
                        <div class="form-group">
                            <label for="fullname">Họ và tên:</label>
                            <input type="text" id="fullname" name="fullname" class="form-control" value="${userProfiles[0].fullname}">
                        </div>

                        <!-- Phone Field -->
                        <div class="form-group">
                            <label for="phone">Số điện thoại:</label>
                            <input type="text" id="phone" name="phone" class="form-control" value="${userProfiles[0].phone}">
                        </div>

                        <!-- Submit Button -->
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                        </div>
                    </form>
                </div>
            </div>


            <div id="emailModal" class="modal" style="<c:if test='${openEmailModal}'>display: block;</c:if>">
                    <div class="modal-content">
                        <span class="close" onclick="closeEmailModal()">&times;</span>
                        <h1>Cập nhật Email</h1>
                        <form action="${pageContext.request.contextPath}/userProfile?action=updateEmail" method="post">
                        <!-- Email Field -->
                        <div class="form-group">
                            <label for="email">Email mới:</label>
                            <input type="email" id="email" name="email" class="form-control" value="${userProfiles[0].email}" required>
                        </div>

                        <!-- Hiển Thị Thông Báo Lỗi -->
                        <c:if test="${not empty errorMessage}">
                            <div class="error-message" style="color: red; margin-top: 5px;">${errorMessage}</div>
                        </c:if>

                        <!-- Submit Button -->
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary">Cập nhật Email</button>
                        </div>
                    </form>
                </div>
            </div>

            <div id="passwordModal" class="modal" style="<c:if test='${openPasswordModal}'>display: block;</c:if>">
                    <div class="modal-content">
                        <span class="close" onclick="closePasswordModal()">&times;</span>
                        <h1>Cập nhật Password</h1>
                        <form action="${pageContext.request.contextPath}/userProfile?action=updatePassword" method="post">
                        <!-- New Password Field -->
                        <div class="form-group">
                            <label for="password">New Password*</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                            <c:if test="${not empty errorMessage}">
                                <div class="error-message" style="color: red; margin-top: 5px;">${errorMessage}</div>
                            </c:if>
                        </div>

                        <!-- Confirm Password Field -->
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password*</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                            <c:if test="${not empty confirmPasswordError}">
                                <div class="error-message" style="color: red; margin-top: 5px;">${confirmPasswordError}</div>
                            </c:if>
                        </div>

                        <!-- Submit Button -->
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                        </div>
                    </form>
                </div>
            </div>




            <div id="successPopup" class="success-popup" style="display: none;">
                <p><span style="color: green;">Thành công:</span> Cập nhật thông tin thành công!</p>
            </div>


            <br>
            <!-- Button to open Phone and Name Modal -->
            <button onclick="openPhoneNameModal()" class="btn btn-primary">Cập nhật Họ tên và Số điện thoại</button>

            <!-- Button to open Email Modal -->
            <button onclick="openEmailModal()" class="btn btn-secondary">Cập nhật Email</button>

            <button onclick="openPasswordModal()" class="btn btn-third">Đổi Mật Khẩu</button>           


        </div>

        <br>
        <div id="fh5co-testimonial">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <h2>Happy Clients</h2>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                        <blockquote>
                            <p>&ldquo; She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Jason Davidson</cite></span><i class="icon twitter-color icon-twitter pull-right"></i></p>
                        </blockquote>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                        <blockquote>
                            <p>&ldquo; Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. She had a last view back on the skyline of her hometown Bookmarksgrove, the headline of. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Kyle Smith</cite></span><i class="icon googleplus-color icon-google-plus pull-right"></i></p>
                        </blockquote>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                        <blockquote>
                            <p>&ldquo; The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She had a last view back on the skyline of her hometown Bookmarksgrove. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Rick Cook</cite></span><i class="icon facebook-color icon-facebook pull-right"></i></p>
                        </blockquote>
                    </div>
                </div>
            </div>
        </div>

        <div class="fh5co-cta" style="background-image: url(images/slide_4.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="col-md-12 text-center animate-box">
                    <h3>We Try To Update The Site Everyday</h3>

                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></
            
            <!-- MAIN JS -->
            <script src="js/main.js"></script>
            <jsp:include page="footer.jsp" />
    </body>
</html>
