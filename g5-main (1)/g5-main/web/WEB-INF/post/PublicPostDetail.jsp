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
            .fh5co-cta .overlay, .fh5co-page-title .overlay {
  background: rgba(0, 0, 0, 0.7);
  left: 0;
  right: 0;
  bottom: 0px;
  top: 0;
  position: absolute;
  z-index: 1;
}

.comment-respond {
    display: flex;
    justify-content: center; /* Căn giữa form theo chiều ngang */
    width: 100%;
}
.comment-form {
    width: 60%; /* Điều chỉnh độ rộng form, có thể thay đổi tùy ý */
    max-width: 600px; /* Giới hạn độ rộng tối đa */
}
.comment-respond {
    display: grid;
    place-items: center; /* Căn giữa theo chiều ngang */
    width: 100%;
}
.comment-form {
    width: 60%;
    max-width: 600px;
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
            /* Định dạng phần chứa thể loại và người tạo */
.post-meta {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 18px; /* Tăng kích thước chữ */
    font-weight: bold; /* Làm đậm chữ */
    margin-top: 15px;
    padding: 10px;
    border-radius: 5px;
    background: #f9f9f9; /* Màu nền nhẹ nhàng */
}

/* Border cho ảnh */
.action-box img {
    text-align: center;
    width: 83%;
    border: 5px solid #ddd; /* Đường viền màu nhẹ */
    border-radius: 10px;
    padding: 5px;
}

/* Căn chỉnh khoảng cách */
.post-meta div {
    display: flex;
    align-items: center;
    gap: 5px;
}

.post-meta div span {
    font-weight: normal;
    color: #555;
}
.action-box blog-lg{
    display: flex;
    
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
                                    <h1><span class="colored">Post</span> Detail</h1>
                                </div>
                            </div>
                        </div>
                    </div>                    <!-- Breadcrumb row -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="home1">Home</a></li>
                                <li><a href="${pageContext.request.contextPath}/PublicPost">Public Post</a></li>
                                <li>Post Details</li>
                            </ul>
                        </div>
                    </div>
                    <!-- Breadcrumb row END -->
                    <div class="content-block">
                        <div class="section-area section-sp1">
                            <div class="container">
                                <div class="row">
                                    <!-- Left part start -->
                                    <div class="col-lg-12 col-xl-12">
                                        <!-- blog start -->
                                        <div class="recent-news blog-lg">
                                            <div class="nanio"><div class="action-box blog-lg">
    <!-- Ảnh -->
    <div>
        <img src="${p.image_url}" alt="imgage">
    </div>

    <!-- Nội dung -->
    <div class="info-bx">
        <ul class="media-post">
            <li><a href="#"><i class="fa fa-calendar"></i>${p.post_at}</a></li>
            <li><a href="#"><i class="fa fa-comments-o"></i>10 Comment</a></li>
        </ul>
        <h5 style="color: #004085" class="post-title">${p.title}</h5>
        <div class="okaka">
            <p>${p.description}</p>
        </div>

        <!-- Phần meta -->
        
            <h3>Thể loại: <span>${pp.namecreated}</span></h3>
            <h3>Người tạo: <span>${pp.nameclub}</span></h3>
        
    </div>
</div>
                                            
  

                                            <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
                                           
                                            
                                            <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
                                        </div>
                                    </div>
                                    <!-- blog END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                
                             
                                
                                
                                
                                
                                
                                
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