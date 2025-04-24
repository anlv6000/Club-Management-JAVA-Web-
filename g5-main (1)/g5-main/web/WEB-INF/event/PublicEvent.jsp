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
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

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
        <link rel="stylesheet" href="assets/vendors/bootstrap-select/bootstrap-select.min.css">
        <style>
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

            /* CSS cho modal (hộp thoại) */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }
            .modal-content {
                background-color: white;
                margin: 10% auto;
                padding: 20px;
                border-radius: 10px;
                width: 40%;
                text-align: center;
                position: relative;
            }
            .close {
                position: absolute;
                top: 10px;
                right: 15px;
                font-size: 20px;
                cursor: pointer;
            }
            .row{
                display: flex;
                flex-wrap: nowrap;
                flex-direction: row;
            }
            .tuanngu{
                display: flex;
                flex-wrap: wrap;
            }
            ul{
                margin: 76 19px 0 -50 ;
            }

            .fh5co-page-title {
                padding: 9em 0 0em 0 !important;
            }

            .btn.dropdown-toggle.btn-default {
                background-color: white; /* Ensure white background */
                color: black; /* Black text */
                border: 1px solid #ccc; /* Subtle border for structure */
                padding: 10px 15px; /* Add comfortable spacing */
                border-radius: 4px; /* Slightly rounded edges */
                font-family: "Varela Round", sans-serif; /* Font consistency */
                font-size: 14px; /* Readable text size */
                cursor: pointer; /* Pointer for better usability */
            }

            /* Completely remove hover effects */
            .btn.dropdown-toggle.btn-default:hover {
                background-color: white; /* Keep white background on hover */
                color: black; /* Maintain black text */
                border: 1px solid #ccc; /* Consistent border */
                cursor: pointer; /* Keep pointer style */
            }

            /* Remove any active or focus effects */
            .btn.dropdown-toggle.btn-default:active,
            .btn.dropdown-toggle.btn-default:focus {
                background-color: white; /* Keep white background */
                color: black; /* Maintain black text */
                outline: none; /* Remove focus outline */
                border: 1px solid #ccc; /* Retain border */
            }
            .fh5co-cta .overlay, .fh5co-page-title .overlay {
                background: rgba(0, 0, 0, 0.7);
                left: 0;
                right: 0;
                bottom: 113px;
                top: 0;
                position: absolute;
                z-index: 1;
            }

            filter-option.pull-left a{
                background-color: white;
                color:black;
            }


            #fh5co-header .header-inner{
                padding-left: 0px;
                padding-right:  178px;
                padding-bottom:  0 px;
                padding-top: 53px;
                margin-left: -100px;
                margin-right: 20px;
            }
            .sticky-top {
                top: -29px;
            }

            /* CSS cho phần tìm kiếm */
            .search-form {
                padding: 15px;
                background-color: #f8f9fa; /* Màu nền nhạt */
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng nhẹ */
            }

            .search-row {
                display: flex;
                flex-wrap: wrap;
                gap: 10px; /* Khoảng cách giữa các phần tử */
                align-items: center;
            }

            .search-row input.form-control {
                flex: 1;
                min-width: 200px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }

            .search-row select {
                flex: 1;
                min-width: 150px;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
                background-color: white;
                cursor: pointer;
                position: relative;
                z-index: 1040 !important;
            }

            .search-row button {
                padding: 8px 15px;
                background-color: #28a745; /* Màu xanh lá */
                color: white;
                border: none;
                border-radius: 4px;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .search-row button:hover {
                background-color: #218838; /* Màu xanh đậm hơn khi hover */
            }

            /* Đảm bảo phần tìm kiếm không bị tràn */
            .side-bar .widget {
                margin-bottom: 20px;
                overflow: visible !important; /* Đảm bảo dropdown không bị cắt */
            }

            /* Định dạng lại phần sticky-top để không bị lệch */
            .sticky-top {
                top: 0;
                padding-top: 20px;
                z-index: 1000 !important; /* Thấp hơn z-index của dropdown */
                overflow: visible !important;
            }

            /* Đảm bảo dropdown menu hiển thị rõ ràng */
            .bootstrap-select .dropdown-menu {
                background-color: white !important;
                color: black !important;
                opacity: 1 !important;
                z-index: 1050 !important; /* Cao hơn sticky-top */
                -webkit-transform: translateZ(0);
                transform: translateZ(0);
                will-change: transform;
            }

            .bootstrap-select .dropdown-item {
                background-color: white !important;
                color: black !important;
                opacity: 1 !important;
            }

            .bootstrap-select .dropdown-item:hover {
                background-color: #f0f0f0 !important;
                color: black !important;
            }

            /* Đảm bảo overlay không che khuất dropdown */
            .fh5co-page-title .overlay {
                z-index: 1 !important;
            }
        </style>
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>



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
                                    <h1><span class="colored">Public</span> Event</h1>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Breadcrumb row -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="home1">Home</a></li>

                                <li>Event </li>
                            </ul>
                        </div>
                    </div>
                    <!-- Breadcrumb row END -->

                    <div class="content-block">
                        <div class="section-area section-sp1">
                            <div class="container">
                                <div class="row">
                                    <!-- Left part start -->

                                    <div class="col-md-7 col-lg-8 col-xl-8">
                                    <c:forEach var="o" items="${e}">
                                        <div class="recent-news blog-lg m-b40">
                                            <div class="action-box blog-lg">
                                                <img src="${o.eventImageURL}" alt="">
                                            </div>
                                            <div class="info-bx">
                                                <ul class="media-post">
                                                    <li>
                                                        <a href="#">
                                                            <i class="fa fa-calendar"></i>
                                                            Event Date: ${o.eventDate} 
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <i class="fa fa-calendar"></i>
                                                            End Date: ${o.endEventDate}
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <i class="fa fa-globe"></i>
                                                            Type: <c:choose>
                                                                <c:when test="${o.status == '1'}">Online</c:when>
                                                                <c:otherwise>Offline</c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <i class="fa fa-users"></i>
                                                            Participants: ${o.participantcount}
                                                        </a>
                                                    </li>
                                                </ul>
                                                <h5 class="post-title">
                                                    <a href="DetailEventPublic?event_id=${o.id}">${o.eventName}</a>
                                                </h5>
                                                <p>${o.description}</p>

                                                <!-- Nút Join Event -->

                                            </div>
                                        </div>
                                    </c:forEach>

                                    <!-- Pagination start -->
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                                            <!-- Trang trước -->
                                            <c:if test="${currentPage > 1}">
                                                <li class="previous">
                                                    <c:url var="prevUrl" value="${not empty keyword || not empty type || not empty selectedClub ? 'SearchEventPublic' : 'PublicEvent'}">
                                                        <c:param name="page" value="${currentPage - 1}" />
                                                        <c:if test="${not empty keyword}">
                                                            <c:param name="keyword" value="${keyword}" />
                                                        </c:if>
                                                        <c:if test="${not empty type}">
                                                            <c:param name="type" value="${type}" />
                                                        </c:if>
                                                        <c:if test="${not empty selectedClub}">
                                                            <c:param name="club" value="${selectedClub}" />
                                                        </c:if>
                                                    </c:url>
                                                    <a href="${prevUrl}"><i class="ti-arrow-left"></i> Prev</a>
                                                </li>
                                            </c:if>

                                            <!-- Các trang -->
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <c:url var="pageUrl" value="${not empty keyword || not empty type || not empty selectedClub ? 'SearchEventPublic' : 'PublicEvent'}">
                                                    <c:param name="page" value="${i}" />
                                                    <c:if test="${not empty keyword}">
                                                        <c:param name="keyword" value="${keyword}" />
                                                    </c:if>
                                                    <c:if test="${not empty type}">
                                                        <c:param name="type" value="${type}" />
                                                    </c:if>
                                                    <c:if test="${not empty selectedClub}">
                                                        <c:param name="club" value="${selectedClub}" />
                                                    </c:if>
                                                </c:url>
                                                <li class="${i == currentPage ? 'active' : ''}">
                                                    <a href="${pageUrl}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <!-- Trang tiếp theo -->
                                            <c:if test="${currentPage < totalPages}">
                                                <li class="next">
                                                    <c:url var="nextUrl" value="${not empty keyword || not empty type || not empty selectedClub ? 'SearchEventPublic' : 'PublicEvent'}">
                                                        <c:param name="page" value="${currentPage + 1}" />
                                                        <c:if test="${not empty keyword}">
                                                            <c:param name="keyword" value="${keyword}" />
                                                        </c:if>
                                                        <c:if test="${not empty type}">
                                                            <c:param name="type" value="${type}" />
                                                        </c:if>
                                                        <c:if test="${not empty selectedClub}">
                                                            <c:param name="club" value="${selectedClub}" />
                                                        </c:if>
                                                    </c:url>
                                                    <a href="${nextUrl}">Next <i class="ti-arrow-right"></i></a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                    <!-- Pagination END -->
                                    <!-- Pagination END -->
                                </div>

                                <!-- Side bar start -->
                                <div class="col-md-5 col-lg-4 col-xl-4 sticky-top">
                                    <aside class="side-bar sticky-top">
                                        <div class="widget">
                                            <div class="search-bx style-1">
                                                <form class="search-form" action="SearchEventPublic" method="get">
                                                    <div class="search-row">
                                                        <input name="keyword" class="form-control" placeholder="Enter your keywords..." type="text" value="${keyword}">
                                                        <select name="type" class="selectpicker">
                                                            <option value="" ${empty type ? 'selected' : ''}>Tất cả kiểu</option>
                                                            <option value="0" ${type == '0' ? 'selected' : ''}>Offline</option>
                                                            <option value="1" ${type == '1' ? 'selected' : ''}>Online</option>
                                                        </select>
                                                        <select name="club" class="selectpicker">
                                                            <option value="" ${empty selectedClub ? 'selected' : ''}>Tất cả câu lạc bộ</option>
                                                            <c:forEach var="o" items="${club}">
                                                                <option value="${o.clubName}" ${o.clubName == selectedClub ? 'selected' : ''}>${o.clubName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <button type="submit">Tìm kiếm</button>
                                                    </div>
                                                </form>
                                            </div>
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
                $(document).ready(function () {
                    $('.selectpicker').selectpicker();
                });
            </script>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const commentForm = document.getElementById("commentform");
                    const commentList = document.querySelector("ol.comment-list") || document.createElement("ol");

                    if (!commentList.classList.contains("comment-list")) {
                        commentList.classList.add("comment-list");
                        document.body.appendChild(commentList);
                    }

                    commentForm.addEventListener("submit", function (event) {
                        event.preventDefault(); // Ngăn chặn load lại trang khi gửi form

                        // Lấy giá trị từ form
                        let author = document.getElementById("author").value.trim();
                        let email = document.getElementById("email").value.trim();
                        let commentText = document.getElementById("comment").value.trim();

                        if (author === "" || email === "" || commentText === "") {
                            alert("Vui lòng điền đầy đủ thông tin!");
                            return;
                        }

                        // Tạo phần tử bình luận mới
                        let newComment = document.createElement("li");
                        newComment.classList.add("comment");

                        // Tạo phần tử thẻ <a> chứa ngày giờ
                        let timeElement = document.createElement("a");
                        timeElement.href = "#";
                        timeElement.textContent = new Date().toLocaleString(); // Hiển thị ngày giờ

                        newComment.innerHTML = `
                        <div class="comment-body">
                            <div class="comment-author vcard">
                                <img class="avatar photo" src="assets/images/testimonials/pic1.jpg" alt="">
                                <cite class="fn"></cite> <span class="says">says:</span>
                            </div>
                            <div class="comment-meta">
                            </div>
                            <p></p>
                            <div class="reply">
                                <a href="#" class="comment-reply-link">Reply</a>
                            </div>
                        </div>
                    `

                        // Chèn phần tử thời gian vào phần meta
                        newComment.querySelector(".comment-meta").appendChild(timeElement);

                        // Thêm bình luận vào danh sách (ở trên cùng)
                        commentList.prepend(newComment);

                        // Xóa nội dung trong form
                        commentForm.reset();

                        // Cuộn lên để hiển thị bình luận mới nhất
                        newComment.scrollIntoView({behavior: "smooth", block: "start"});
                    });
                });
            </script>
            <jsp:include page="footer.jsp" />
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