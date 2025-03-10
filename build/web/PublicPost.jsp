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



            /* General style for the dropdown */
            select {
                background-color: #ffffff; /* White background */
                color: #000000; /* Black text */
                border: 1px solid #ccc; /* Subtle border */
                padding: 10px;
                border-radius: 4px;
                appearance: none; /* Removes default styling in some browsers */
            }

            /* Style for the dropdown when focused (on click) */
            select:focus {
                background-color: #ffffff; /* Keep the white background when clicked */
                color: #000000; /* Maintain black text */
                outline: none; /* Remove the outline for cleaner look */
            }

            /* Style for the options within the dropdown */
            option {
                background-color: transparent; /* Transparent background */
                color: #000000; /* Black text */
            }

            /* Remove hover effect for the dropdown */
            select:hover {
                background-color: transparent; /* Transparent background when hovered */
                color: inherit; /* Text color remains unchanged */
                cursor: default; /* Normal cursor */
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
                                    <h1><span class="colored">Public</span> Post</h1>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Breadcrumb row -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="home1">Home</a></li>

                                <li>Blog Details</li>
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

                                        <form class="ngu" action="PublicPost" method="get">
                                            <input type="text" name="keyword" placeholder="Nhập từ khóa...">
                                            <select name="category">    
                                                <option value="">Tất cả thể loại</option>
                                                <option value="Nghệ thuật">Nghệ thuật</option>
                                                <option value="Âm nhạc">Âm nhạc</option>
                                                <option value="Ngôn ngữ">Ngôn ngữ</option>
                                                <option value="Kinh doanh">Kinh doanh</option>
                                                <option value="Tình nguyện">Tình nguyện</option>
                                                <option value="Công nghệ">Công nghệ</option>

                                            </select>
                                            <select name="club">
                                                <option value="">Tất cả câu lạc bộ</option>
                                                <option value="FPTU Vovinam Club">FPTU Vovinam Club</option>
                                                <option value="CNTT Club">CNTT Club</option>
                                                <option value="Trượt ván Club">Trượt ván Club</option>
                                                <option value="Bóng chuyền Club">Bóng chuyền Club</option>
                                                <option value="Muay Thai Club">Muay Thai Club</option>
                                                <option value="Hackathon Club">Hackathon Club</option>
                                                <option value="CLB Văn hóa nghệ thuật">CLB Văn hóa nghệ thuật</option>
                                                <option value="CLB Melody">CLB Melody</option>
                                                <option value="CLB Tiếng Trung">CLB Tiếng Trung</option>
                                                <option value="CLB Business">CLB Business</option>
                                                <option value="CLB Tình nguyện – vì cộng đồng iGo">CLB Tình nguyện – vì cộng đồng iGo</option>
                                                <option value="CLB Kỹ sư phần mềm Nhật Bản JS">CLB Kỹ sư phần mềm Nhật Bản JS</option>
                                                <option value="CLB Guitar">CLB Guitar</option>
                                            </select>
                                            <button type="submit">Tìm kiếm</button>
                                        </form>

                                    <c:forEach var="o" items="${postList}">                                                    
                                        <div class="recent-news blog-lg m-b40">
                                            <div class="action-box blog-lg">
                                                <img src="${o.image_url}" alt="">
                                            </div>
                                            <div class="info-bx">
                                                <ul class="media-post">
                                                    <li><a href="#"><i class="fa fa-calendar"></i>${o.post_at}</a></li>
                                                    <li><a href="#"><i class="fa fa-user"></i></a></li>
                                                </ul>
                                                <h5 class="post-title"><a href="PublicDetailPost?postid=${o.post_id}">${o.title}</a></h5>
                                                <p>${o.description}</p>
                                                <div class="post-extra">
                                                    <a href="#" class="btn-link">READ MORE</a>
                                                    <a href="#" class="comments-bx"><i class="fa fa-comments-o"></i>78 Comment</a>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>



                                    <!-- Pagination start -->
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                                            <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                            <li class="active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- Pagination END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                <div class="col-md-5 col-lg-4 col-xl-4 sticky-top">
                                    <aside class="side-bar sticky-top">
                                        <div class="widget">
                                            <h6 class="widget-title">Search</h6>
                                            <div class="search-bx style-1">
                                                <form role="search" method="post">
                                                    <div class="input-group">
                                                        <input name="text" class="form-control" placeholder="Enter your keywords..." type="text">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="fa fa-search text-primary"></button>
                                                        </span> 
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="widget recent-posts-entry">
                                            <h6 class="widget-title">Recent Posts</h6>
                                            <div class="widget-post-bx">
                                                <div class="widget-post clearfix">
                                                    <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic1.jpg" width="200" height="143" alt=""> </div>
                                                    <div class="ttr-post-info">
                                                        <div class="ttr-post-header">
                                                            <h6 class="post-title"><a href="blog-details.html">This Story Behind Education Will Haunt You Forever.</a></h6>
                                                        </div>
                                                        <ul class="media-post">
                                                            <li><a href="#"><i class="fa fa-calendar"></i>Oct 23 2019</a></li>
                                                            <li><a href="#"><i class="fa fa-comments-o"></i>15 Comment</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="widget-post clearfix">
                                                    <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic2.jpg" width="200" height="160" alt=""> </div>
                                                    <div class="ttr-post-info">
                                                        <div class="ttr-post-header">
                                                            <h6 class="post-title"><a href="blog-details.html">What Will Education Be Like In The Next 50 Years?</a></h6>
                                                        </div>
                                                        <ul class="media-post">
                                                            <li><a href="#"><i class="fa fa-calendar"></i>May 14 2019</a></li>
                                                            <li><a href="#"><i class="fa fa-comments-o"></i>23 Comment</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="widget-post clearfix">
                                                    <div class="ttr-post-media"> <img src="assets/images/blog/recent-blog/pic3.jpg" width="200" height="160" alt=""> </div>
                                                    <div class="ttr-post-info">
                                                        <div class="ttr-post-header">
                                                            <h6 class="post-title"><a href="blog-details.html">Eliminate Your Fears And Doubts About Education.</a></h6>
                                                        </div>
                                                        <ul class="media-post">
                                                            <li><a href="#"><i class="fa fa-calendar"></i>June 12 2019</a></li>
                                                            <li><a href="#"><i class="fa fa-comments-o"></i>27 Comment</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="widget widget-newslatter">
                                            <h6 class="widget-title">Newsletter</h6>
                                            <div class="news-box">
                                                <p>Enter your e-mail and subscribe to our newsletter.</p>
                                                <form class="subscription-form" action="http://educhamp.themetrades.com/demo/assets/script/mailchamp.php" method="post">
                                                    <div class="ajax-message"></div>
                                                    <div class="input-group">
                                                        <input name="dzEmail" required="required" type="email" class="form-control" placeholder="Your Email Address"/>
                                                        <button name="submit" value="Submit" type="submit" class="btn black radius-no">
                                                            <i class="fa fa-paper-plane-o"></i>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="widget widget_gallery gallery-grid-4">
                                            <h6 class="widget-title">Our Gallery</h6>
                                            <ul>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic2.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic1.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic5.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic7.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic8.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic9.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic3.jpg" alt=""></a></div></li>
                                                <li><div><a href="#"><img src="assets/images/gallery/pic4.jpg" alt=""></a></div></li>
                                            </ul>
                                        </div>
                                        <div class="widget widget_tag_cloud">
                                            <h6 class="widget-title">Tags</h6>
                                            <div class="tagcloud"> 
                                                <a href="#">Design</a> 
                                                <a href="#">User interface</a> 
                                                <a href="#">SEO</a> 
                                                <a href="#">WordPress</a> 
                                                <a href="#">Development</a> 
                                            </div>
                                        </div>
                                    </aside>
                                </div>
                                <!-- Side bar END -->
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <br>
                <br>

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

</html>