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
        <style>
            .user-info{
                margin-top: -4px;
            }
            #fh5co-header{
                padding-bottom: 20px;
            }
            #fh5co-header .header-inner{
                padding-bottom: 0px;
                margin-top: 50px;
            }
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
            .event-container {
                padding: 20px;
                max-width: 600px;
                margin: auto;

            }

            .event-title {
                font-size: 24px;
                font-weight: bold;
                color: #333;
                text-align: center;
            }

            .event-dates {
                font-size: 18px;
                color: #555;
                text-align: center;
                margin: 10px 0;
            }

            .event-date {
                font-weight: bold;
            }

            .separator {
                margin: 0 10px;
                font-size: 20px;
                color: #777;
            }

            .note-title {
                font-size: 20px;
                font-weight: bold;
                color: #d9534f;
                margin-top: 15px;
            }

            .event-note {
                font-size: 16px;
                color: #666;
            }
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
                width: 50%;
                max-width: 600px;
                position: absolute;
                top: 32%;
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
                max-width: 53%;
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
            .fh5co-cta .overlay, .fh5co-page-title .overlay {
                background: rgba(0, 0, 0, 0.7);
                left: 0;
                right: 0;
                bottom: 72px;
                top: 0;
                position: absolute;
                z-index: 1;
            }
            .fh5co-page-title {
                padding: 9em 0 0em 0 !important;
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
                                    <h1><span class="colored">Event</span> Detail</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Breadcrumb row -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="home1">Home</a></li>
                                <li>Event Detail</li>
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
                                            <div class="obito"><img src="${e.eventImageURL}" alt="Club Image" style="width: 100%; height: auto;">
                                            <c:if test="${sessionScope.acc.role != 'ClubLeader'|| sessionScope.accc.role != 'ClubLeader'|| sessionScope.acc.role != 'Admin' || sessionScope.accc.role != 'Admin'}"> <div class="action-box blog-lg" style="position: relative; display: inline-block;">

                                                    <button id="openModal" class="join-btn">Join Event Now</button>

                                                </div></c:if></div>


                                            <div class="info-bx">
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-calendar"></i>${e.eventDate}</a></li>
                                                <li><a href="#"><i class="fa fa-comments-o"></i>10 Comment</a></li>
                                            </ul>
                                            <h5 class="post-title"><a href="#">${e.eventName}</a></h5>
                                            <p>${e.description}</p>

                                            <div class="event-container">
                                                <h2 class="event-title">Event Duration</h2>
                                                <div class="event-dates">
                                                    <span class="event-date">${e.eventDate}</span>
                                                    <span class="separator">-</span>
                                                    <span class="event-date">${e.endEventDate}</span>
                                                </div > <h3  style="text-align:  center;"   class="note-title">Note: </h3>
                                                <div style="color:  red; text-align: center; ">vui lòng bỏ qua tin này nếu bạn là Club Leader</div>
                                                <p  style="text-align:  center;" class="event-note">You can only register for the event 2 days before the event takes place.</p>

                                            </div>





                                            <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
                                            <h6>SHARE</h6>
                                            <ul class="list-inline contact-social-bx">
                                                <li>
        <a href="https://www.facebook.com/sharer/sharer.php?u=${e.eventImageURL}" 
   target="_blank" class="btn outline radius-xl">
   <i class="fa fa-facebook"></i>
</a>
    </li>
                                                
                                            </ul>
                                            <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
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
                                                    <img src="${e.eventImageURL}" alt="Club Image">
                                                </div>

                                                <form action="submitEventRegister" method="post" >
                                                    <h3 class="post-title"><a href="#">${e.eventName}</a></h3>


                                                    <div class="form-container">
                                                        <div class="form-column">
                                                            <c:if test="${sessionScope.acc !=null && sessionScope.accc ==null}">
                                                                <div style="margin-top: -19px; padding-top:  20px; margin-bottom: 20px; padding-bottom: 20px;">
                                                                    <label for="name">Name:</label><br>

                                                                    <input  type="text" id="name" name="name" value="${sessionScope.acc.userName}" readonly required></div>
                                                                <input type="hidden" name="eventID" value="${eventid}">
                                                                <input type="hidden" name="clubID" value="${e.clubId}">
                                                                <input type="hidden" name="userID" value="${sessionScope.acc.id}">
                                                            </c:if>
                                                            <c:if test="${sessionScope.acc ==null && sessionScope.accc !=null}">
                                                                <input style="margin-bottom: 30px; padding-bottom:  30px ;" type="text" id="name" name="name" value="${sessionScope.name}" readonly required><br><br>
                                                                <input type="hidden" name="eventID" value="${eventid}">
                                                                <input type="hidden" name="clubID" value="${e.clubId}">
                                                                <input type="hidden" name="userID" value="${sessionScope.accc.id}">
                                                            </c:if>
                                                            <div style="margin-top: -58px; padding-top:  20px; margin-bottom: 20px; padding-bottom: 20px;"><!-- comment --><label for="email">Email:</label>
                                                                <input type="email" id="email" name="email" value="${sessionScope.email}" readonly required></div>

                                                        </div>
                                                        <div class="form-column">
                                                            <label for="phone">Phone:</label>
                                                            <input type="text" id="phone" name="phone" required>
                                                            <div class="error-message" id="phoneError"></div>

                                                            <label for="note">Lý do tham gia:</label>
                                                            <textarea id="note" name="note" ></textarea>
                                                        </div>
                                                    </div>
                                                    <button type="submit">Submit</button>
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
                                                                    // Hiển thị và đóng pop-up
                                                                   document.addEventListener("DOMContentLoaded", function () {
    const clubForm = document.querySelector("form[action='submitEventRegister']");
    if (!clubForm) return; // Kiểm tra xem form có tồn tại không

    clubForm.addEventListener("submit", function (event) {
        const phoneInput = document.getElementById("phone");
        const phoneError = document.getElementById("phoneError");
        const phoneRegex = /^(0[3-9])+([0-9]{8})$/; // Định dạng hợp lệ cho số điện thoại VN

        if (!phoneRegex.test(phoneInput.value.trim())) {
            event.preventDefault(); // Ngăn form gửi đi
            phoneError.textContent = "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng (VD: 0987654321).";
            phoneError.style.color = "red"; // Hiển thị lỗi bằng màu đỏ
        } else {
            phoneError.textContent = ""; // Xóa thông báo lỗi nếu hợp lệ
        }
    });
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
                                <cite class="fn">${author}</cite> <span class="says">says:</span>
                            </div>
                            <div class="comment-meta">
                            </div>
                            <p>${commentText}</p>
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

            <!-- Footer END ==== -->
            <!-- scroll top button -->
            <button class="back-to-top fa fa-chevron-up" ></button>







            <jsp:include page="footer.jsp"></jsp:include>





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