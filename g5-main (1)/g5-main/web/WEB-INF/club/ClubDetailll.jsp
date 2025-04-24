<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="Estate-Free HTML5 Bootstrap" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/OIT" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/OIT.jpg" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>FPT Club Manager</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">
        <link rel="stylesheet" href="css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="css/icomoon.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <!-- Flexslider  -->
        <link rel="stylesheet" href="css/flexslider.css">
        <!-- Theme style  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            .row{
                display: flex;
                flex-wrap: nowrap;
                flex-direction: row;
            }

            .btn-container {
                text-align: center;
                font-family: fantasy;
                color: green;
                padding: 20px 30px 40px 20px;
            }
            .btn-container button {
                text-align: center;
                color: green;
                padding: 20px 30px 40px 20px;
                font-size: 18px;
                cursor: pointer;
            }
            #fh5co-header .header-inner{
                padding-bottom: 0px;
                margin-top: 30px;
            }

            /* Style for the modal background */
            /* Style for the modal background */
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                align-items: center;
                justify-content: center;
            }

            /* Style for the modal content */
            .modal-content {
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                width: 44%;
                max-width: 600px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            /* Close button style */
            .close {
                position: absolute;
                top: 10px;
                right: 15px;
                font-size: 20px;
                cursor: pointer;
            }

            /* Image container styling */
            .image-container {
                text-align: center;
                margin-bottom: 15px;
                width: 100%;
                display: flex;
                justify-content: center;
            }

            .image-container img {
                max-width: 48%;
                height: auto;
                border-radius: 8px;
                border: 2px solid #ddd;
                padding: 5px;
            }

            /* Form layout: two-column design */
            .form-container {
                display: flex;
                gap: 20px;
                width: 100%;
            }

            .form-column {
                flex: 1;
                display: flex;
                flex-direction: column;
            }

            label {
                font-weight: bold;
                margin-top: 10px;
            }

            input, textarea {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            textarea {
                resize: vertical;
                min-height: 80px;
            }

            /* Button styling */
            button {
                width: 100%;
                padding: 10px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 15px;
            }

            button:hover {
                background-color: #0056b3;
            }
            .obito {
                border: 2px solid #ddd; /* Viền xám nhạt */
                padding: 5px; /* Tạo khoảng cách giữa viền và nội dung bên trong */
                background-color: #f9f9f9; /* Màu nền rất nhạt để dễ nhận biết */
                border-radius: 8px; /* Bo góc nhẹ */
                text-align: center; /* Căn giữa nội dung */

            }

            .obito img {
                display: block;
                margin: 0 auto; /* Căn giữa hình ảnh */
            }

            .action-box {
                display: block;
                margin-top: 10px; /* Tạo khoảng cách giữa hình và nút */
            }
            .widget-post-bx{
                border: 2px solid #ddd; /* Viền xám nhạt */
                padding: 5px; /* Tạo khoảng cách giữa viền và nội dung bên trong */
                background-color: #f9f9f9; /* Màu nền rất nhạt để dễ nhận biết */
                border-radius: 8px; /* Bo góc nhẹ */
            }
            .naruto{
                border: 2px solid #ddd; /* Viền xám nhạt */
                padding: 5px; /* Tạo khoảng cách giữa viền và nội dung bên trong */
                background-color: #f9f9f9; /* Màu nền rất nhạt để dễ nhận biết */
                border-radius: 8px; /* Bo góc nhẹ */
            }
            .fh5co-cta .overlay, .fh5co-page-title .overlay {
                background: rgba(0, 0, 0, 0.7);
                left: 0;
                right: 0;
                bottom: 70px;
                top: 0;
                position: absolute;
                z-index: 1;
            }
            .fh5co-page-title {
                padding: 9em 0 0em 0 !important;
            }
            .join-btn {
    background-color: #ff5722;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 30px;
    font-size: 16px;
    cursor: pointer;
    transition: 0.3s;
}
.join-btn:hover {
    background-color: #e64a19;
}

.modal-content {
    border-radius: 15px;
    padding: 20px;
    box-shadow: 0 5px 20px rgba(0,0,0,0.3);
}

.post-title a:hover {
    color: #e91e63;
    text-decoration: underline;
}
.club-info-box {
    background-color: #fff8f5;
    padding: 25px;
    border-radius: 12px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.1);
    color: #5a3e36;
    font-family: 'Poppins', sans-serif;
    margin-top: 20px;
    transition: 0.3s;
}

.club-info-box h3 {
    color: #A52A2A;
    font-weight: bold;
    margin-bottom: 10px;
}

.club-info-box h5 {
    color: #A52A2A;
    margin-bottom: 15px;
    font-style: italic;
}

.schedule-text {
    background-color: #fff0e6;
    padding: 15px;
    border-left: 4px solid #A52A2A;
    border-radius: 8px;
    font-weight: 500;
    color: #3e2723;
    transition: all 0.3s ease;
}

.schedule-text:hover {
    background-color: #ffe3d9;
    transform: scale(1.02);
}

.animated-text {
    font-size: 16px;
    animation: fadeIn 1s ease-in-out;
}

.contact-box {
    background-color: #fef1ed;
    padding: 10px 20px;
    border-radius: 10px;
    margin-top: 10px;
    display: inline-block;
    transition: background-color 0.3s;
}

.contact-box a {
    color: #A52A2A;
    font-size: 16px;
    text-decoration: none;
}

.contact-box a:hover {
    color: #e64a19;
    background-color: #ffe4dc;
    border-radius: 8px;
    padding: 5px 10px;
}

.divider {
    margin: 25px 0;
    border-top: 2px dashed #A52A2A;
    width: 100%;
}

@keyframes fadeIn {
    0% {opacity: 0;}
    100% {opacity: 1;}
}
.widget-post {
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 15px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.widget-post:hover {
    transform: scale(1.02);
    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
}
.post-title a {
    transition: color 0.3s ease;
    font-weight: bold;
    display: inline-block;
}

.post-title a:hover {
    color: #007bff;
}

.fade-in {
    animation: fadeInUp 0.6s ease forwards;
    opacity: 0;
}

@keyframes fadeInUp {
    from {
        transform: translateY(20px);
        opacity: 0;
    }
    to {
        transform: translateY(0);
        opacity: 1;
    }
}
.post-image {
    width: 100%;
    aspect-ratio: 4 / 3;     /* Tỷ lệ 4:3, bạn có thể chỉnh 16/9, 1/1 tùy ý */
    object-fit: cover;
    border-radius: 8px;
}
.share-section {
    text-align: center;
    margin-top: 30px;
}

.share-title {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: 15px;
    color: #333;
}

.contact-social-bx {
    padding: 0;
    list-style: none;
    display: flex;
    justify-content: center;
    gap: 15px;
}

.btn-social {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 10px 18px;
    border: 2px solid #007bff;
    border-radius: 30px;
    color: #007bff;
    text-decoration: none;
    font-weight: 500;
    transition: all 0.3s ease;
}

.btn-social i {
    font-size: 18px;
}

.btn-social:hover {
    background-color: #007bff;
    color: #fff;
    transform: translateY(-2px);
    box-shadow: 0 8px 15px rgba(0, 123, 255, 0.2);
}

.btn-social.fb {
    border-color: #3b5998;
    color: #3b5998;
}

.btn-social.fb:hover {
    background-color: #3b5998;
    color: #fff;
}
.contact-social-bx li a{
	width: 100px;
	
}

        </style>
    </head>
    <body id="bg">
        <div class="page-wraper">




            <jsp:include page="header.jsp"></jsp:include>
                <!-- Header Top ==== -->

                <!-- header END ==== -->
                <!-- Content -->
                <div class="page-content bg-white">
                    <!-- inner page banner -->
                    <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
                        <div class="overlay"></div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12 animate-box">
                                    <h1><span class="colored">Club</span> Detail</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Breadcrumb row -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="home1">Home</a></li>

                                <li>Club Detail</li>
                            </ul>
                        </div>
                    </div>
                    <!-- Breadcrumb row END -->
                    <div class="content-block">
                        <div class="section-area section-sp1">
                            <div class="container">
                                <div class="row">
                                    <!-- Left part start -->
                                    <div class="col-lg-8 col-xl-8">
                                        <!-- blog start -->
                                        <div class="recent-news blog-lg">
                                            <div class="obito"><img src="${c.imgURL}" alt="Club Image" style="width: 65%; height: auto;">
                                            <c:if test="${sessionScope.acc.role == 'ClubMember'|| sessionScope.accc.role == 'ClubMember'|| sessionScope.acc.role == 'WebUser' || sessionScope.accc.role == 'WebUser'}">
                                                <div class="action-box blog-lg" style="position: relative; display: inline-block;">

                                                    <button id="openModal" class="join-btn">Join Club Now</button>
                                                </div></c:if></div>

                                            <div class="info-bx">
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-calendar"></i>${c.createDate}</a></li>
                                                <li><a href="#"><i class="fa fa-comments-o"></i>10 Comment</a></li>
                                            </ul>
                                            <h3 class="post-title"><a href="#">${c.clubName}</a></h3>
                                            <h6 >(${c.category})</h6>   


                                            <p>${c.description}</p>
                                            <br>
                                            <div class="club-info-box">
    <h3><i class="fa fa-calendar-check-o" aria-hidden="true"></i> Club Activity Schedule for 1 Week</h3>
    <h5><i class="fa fa-clock-o"></i> (Lịch trình hoạt động của CLB trong 1 tuần)</h5>

    <p class="animated-text">📌 The club's main activities are:</p>
    <h4 class="schedule-text">${c.schedule}</h4>

    <hr class="divider">

    <h3><i class="fa fa-envelope-open" aria-hidden="true"></i> Contact Information</h3>
    <p class="animated-text">📧 If you have any questions, feel free to email us for more details.</p>

    <div class="contact-box">
        <a href="mailto:${c.contactClub}?subject=Join%20the%20Club&body=Hello,%20I%20am%20interested%20in%20joining%20your%20club.">
            <i class="fa fa-paper-plane"></i> <span>${c.contactClub}</span>
        </a>
    </div>
</div>





                                                        <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
                                                        <div class="share-section">
    <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
    <h6 class="share-title">📢 CHIA SẺ BÀI VIẾT</h6>
    <ul class="list-inline contact-social-bx">
        <li>
            <a href="https://www.facebook.com/sharer/sharer.php?u=${c.imgURL}" 
               target="_blank" class="btn-social fb">
                <i class="fa fa-facebook"></i> Facebook
            </a>
        </li>
        <!-- Bạn có thể thêm các mạng xã hội khác ở đây -->
    </ul>
</div>

                                                        
                                                        </div>
                                                        </div>
                                                       
                                                        <!-- blog END -->
                                                        </div>
                                                        <!-- Left part END -->
                                                        <!-- Side bar start -->
                                                        <div class="col-lg-4 col-xl-4">
                                                            <aside  class="side-bar sticky-top">



                                                                <div id="myModal" class="modal">
                                                                    <div class="modal-content">
                                                                        <span class="close">&times;</span>
                                                                        <h2>Join Our Club</h2>

                                                                        <!-- Hình ảnh ĐƯỢC ĐẶT TRƯỚC -->
                                                                        <div class="image-container">
                                                                            <img src="${c.imgURL}" alt="Club Image">
                                                                        </div>

                                                                        <form id="clubForm" action="submitClubRegister" method="post">
                                                                            <h3 class="post-title"><a href="#">${c.clubName}</a></h3>
                                                                            <h6>(${c.category})</h6>

                                                                            <div class="form-container">
                                                                                <div class="form-column">
                                                                                    <label for="name">Name:</label>
                                                                                    <c:if test="${sessionScope.acc !=null && sessionScope.accc ==null}"><input type="text" id="name" name="name" value="${sessionScope.acc.userName}" readonly required>
                                                                                        <input type="hidden" name="userID" value="${sessionScope.acc.id}">
                                                                                        <input type="hidden" name="clubID" value="${c.clubID}"></c:if>
                                                                                    <c:if test="${sessionScope.acc ==null && sessionScope.accc !=null}">
                                                                                        <input type="text" id="name" name="name" value="${sessionScope.accc.name}" readonly required>
                                                                                        <input type="hidden" name="userID" value="${sessionScope.accc.id}">
                                                                                        <input type="hidden" name="clubID" value="${c.clubID}">
                                                                                    </c:if>


                                                                                    <label for="email">Email:</label>
                                                                                    <input type="email" id="email" name="email" value="${sessionScope.email}" readonly required>
                                                                                </div>

                                                                                <div class="form-column">
                                                                                    <label for="phone">Phone:</label>
                                                                                    <input type="text" id="phone" name="phone" required>
                                                                                    <div  style="color: red; "class="error-message" id="phoneError"></div>

                                                                                    <label for="note">Lý do tham gia:</label>
                                                                                    <textarea id="note" name="note" ></textarea>
                                                                                </div>
                                                                            </div>

                                                                            <button type="submit">Submit</button>
                                                                        </form>
                                                                    </div>
                                                                </div>

                                                                <script>
                                                                    document.addEventListener("DOMContentLoaded", function () {
                                                                        const modal = document.getElementById("myModal");
                                                                        const openModalBtn = document.getElementById("openModal");
                                                                        const closeBtn = document.querySelector(".close");

                                                                        // Kiểm tra xem nút có tồn tại không (tránh lỗi nếu điều kiện không đúng)
                                                                        if (openModalBtn) {
                                                                            openModalBtn.addEventListener("click", function () {
                                                                                modal.style.display = "block";
                                                                            });
                                                                        }

                                                                        // Đóng pop-up khi nhấn vào nút đóng (X)
                                                                        if (closeBtn) {
                                                                            closeBtn.onclick = function () {
                                                                                modal.style.display = "none";
                                                                            };
                                                                        }

                                                                        // Đóng pop-up khi nhấn ra ngoài vùng pop-up
                                                                        window.onclick = function (event) {
                                                                            if (event.target === modal) {
                                                                                modal.style.display = "none";
                                                                            }
                                                                        };

                                                                        // Khi load trang, đảm bảo pop-up luôn ẩn
                                                                        modal.style.display = "none";
                                                                    });
                                                                </script>



                                                                <script>
                                                                    document.addEventListener("DOMContentLoaded", function () {
                                                                        const modal = document.getElementById("myModal");
                                                                        const openModalBtn = document.getElementById("openModal");
                                                                        const closeBtn = document.querySelector(".close");

                                                                        // Kiểm tra xem nút có tồn tại không (tránh lỗi nếu điều kiện không đúng)
                                                                        if (openModalBtn) {
                                                                            openModalBtn.addEventListener("click", function () {
                                                                                modal.style.display = "block";
                                                                            });
                                                                        }

                                                                        closeBtn.onclick = function () {
                                                                            modal.style.display = "none";
                                                                        };

                                                                        window.onclick = function (event) {
                                                                            if (event.target === modal) {
                                                                                modal.style.display = "none";
                                                                            }
                                                                        };
                                                                    });
                                                                </script>



                                                                <div class="widget recent-posts-entry">
    <h6 class="widget-title">📰 Recent Posts</h6>
    <div class="widget-post-bx">
        <c:if test="${kaka!=null}">
            <div class="status-message">${kaka}</div>
        </c:if>
        <c:forEach var="o" items="${listPost}">
            <div class="widget-post clearfix post-entry fade-in">
                <div class="ttr-post-media">
                    <img src="${o.image_url}" class="post-image" alt="Post Image">
                </div>
                <div class="ttr-post-info">
                    <div class="ttr-post-header">
                        <h6 class="post-title">
                            <a href="PublicDetailPost?postid=${o.post_id}">${o.title}</a>
                        </h6>
                    </div>
                    <ul class="media-post">
                        <li><a href="#"><i class="fa fa-calendar"></i> ${o.post_at}</a></li>
                        <li><a href="#"><i class="fa fa-comments-o"></i> 15 Comments</a></li>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="widget recent-posts-entry">
    <h6 class="widget-title">📅 Event List</h6>
    <c:if test="${kakaka!=null}">
        <div class="status-message">${kakaka}</div>
    </c:if>
    <c:forEach var="o" items="${eventList}">
        <div class="widget-post-bx">
            <div class="widget-post clearfix post-entry fade-in">
                <div class="ttr-post-media">
                    <img src="${o.eventImageURL}" class="post-image" alt="Event Image">
                </div>
                <div class="ttr-post-info">
                    <div class="ttr-post-header">
                        <h6 class="post-title">
                            <a href="DetailEventbyID?eventID=${o.id}&clubid=${o.clubId}">${o.eventName}</a>
                        </h6>
                    </div>
                    <ul class="media-post">
                        <li><a href="#"><i class="fa fa-calendar"></i> ${o.createdBy}</a></li>
                        <li><a href="#"><i class="fa fa-comments-o"></i> 15 Comments</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


                                                            </aside>
                                                        </div>
                                                        <!-- Side bar END -->
                                                        </div>
                                                        </div>
                                                        </div>
                                                        </div>
                                                        </div>
                                                        <!-- Content END-->
                                                        <!-- Footer ==== -->

                                                        <script>
                                                            // Hiển thị và đóng pop-up
                                                            document.addEventListener("DOMContentLoaded", function () {
                                                                const modal = document.getElementById("myModal");
                                                                const closeBtn = document.querySelector(".close");



                                                                closeBtn.onclick = function () {
                                                                    modal.style.display = "none";
                                                                };

                                                                window.onclick = function (event) {
                                                                    if (event.target === modal) {
                                                                        modal.style.display = "none";
                                                                    }
                                                                };
                                                            });

                                                            // Kiểm tra số điện thoại
                                                            document.getElementById("clubForm").addEventListener("submit", function (event) {
                                                                const phoneInput = document.getElementById("phone");
                                                                const phoneError = document.getElementById("phoneError");
                                                                const phoneRegex = /^(0[3-9])+([0-9]{8})$/;

                                                                if (!phoneRegex.test(phoneInput.value)) {
                                                                    event.preventDefault(); // Ngăn form gửi đi
                                                                    phoneError.textContent = "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng (VD: 0987654321).";
                                                                } else {
                                                                    phoneError.textContent = "";
                                                                }
                                                            });
                                                        </script>
                                                        <jsp:include page="footer.jsp"></jsp:include>
                                                        <!-- Footer END ==== -->
                                                        <!-- scroll top button -->
                                                        <button class="back-to-top fa fa-chevron-up" ></button>
                                                        </div>
                                                        <!-- External JavaScripts -->
                                                        <script src="assets/js/jquery.min.js"></script>
                                                        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
                                                        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
                                                        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
                                                        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
                                                        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
                                                        <script src="assets/vendors/counter/waypoints-min.js"></script>
                                                        <script src="assets/vendors/counter/counterup.min.js"></script>
                                                        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
                                                        <script src="assets/vendors/masonry/masonry.js"></script>
                                                        <script src="assets/vendors/masonry/filter.js"></script>
                                                        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
                                                        <script src="assets/js/functions.js"></script>
                                                        <script src="assets/js/contact.js"></script>
                                                        <script src='assets/vendors/switcher/switcher.js'></script>
                                                        </body>

                                                        </html>