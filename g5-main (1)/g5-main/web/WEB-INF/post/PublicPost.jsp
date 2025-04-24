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
            .pagination {
    list-style: none;
    display: flex;
    justify-content: center; /* Căn giữa phân trang */
    padding: 0;
}

.page-item {
    margin: 0 5px; /* Khoảng cách giữa các số trang */
}

.page-link {
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
    color: #333; /* Màu chữ */
}

.page-item.active .page-link {
    background-color: #007bff; /* Màu nền trang hiện tại */
    color: white;
}

.previous.disabled,
.next.disabled {
    pointer-events: none; /* Vô hiệu hóa nút Prev/Next khi ở trang đầu/cuối */
    opacity: 0.5;
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

           
            filter-option.pull-left a{
                background-color: white;
                color:black;
            }
            
            .ngu a{
                background-color: white;
            }
            #fh5co-header .header-inner{
                padding-left: 0px;
                padding-right:  178px;
                padding-bottom:  0 px;
                padding-top: 53px;
                margin-left: -100px;
                margin-right: 20px;
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
                                                    
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>



                                    <!-- Pagination start -->
                                    <div class="pagination-bx rounded-sm gray clearfix">
                                        <ul class="pagination">
                           
                                            <li class="active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                          
                                        </ul>
                                    </div>
                                    <!-- Pagination END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                <div class="col-md-5 col-lg-4 col-xl-4 sticky-top">
                                    <aside class="side-bar sticky-top">
                                        <div class="widget">
                                           
                                            <div class="search-bx style-1">
                                                
                                                    
                                                        
                                                        
                                                    
                                                    <br><!-- comment -->
                                                    <form class="ngu" action="PublicPost" method="get">
                                            <input name="keyword" class="form-control" placeholder="Enter your keywords..." type="text">
                                            <br>
                                            <select name="category">    
                                                <option value="">Tất cả thể loại</option>
                                            <c:forEach var="o" items="${listC}" ><option value="${o.category}">${o.category}</option></c:forEach> 
                                               

                                            </select>
                                            <br><!-- comment -->
                                            <select name="club">
                                                <option value="">Tất cả câu lạc bộ</option>
                                            <c:forEach var="o" items="${listClub}"> <option value="${o.clubName}">${o.clubName}</option></c:forEach>  
                                                
                                            </select>
                                            <button type="submit">Tìm kiếm</button>
                                        </form>
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
        <script>
 document.addEventListener("DOMContentLoaded", function () {
    const posts = document.querySelectorAll(".recent-news"); // Lấy tất cả bài viết
    const itemsPerPage = 4; // Số bài viết trên mỗi trang
    const totalPages = Math.ceil(posts.length / itemsPerPage); // Tổng số trang
    let currentPage = 1; // Trang hiện tại

    // Kiểm tra pagination container có tồn tại không
    const paginationContainer = document.querySelector(".pagination");
    if (!paginationContainer || totalPages < 1) {
        console.warn("Không có phần tử pagination hoặc không cần phân trang.");
        return;
    }

    // Tạo pagination HTML
    function generatePagination() {
        let paginationHTML = `
            <li class="previous ${currentPage == 1 ? "disabled" : ""}">
                <a href="#" id="prevBtn"><i class="ti-arrow-left"></i> Prev</a>
            </li>
        `;

        for (let i = 1; i <= totalPages; i++) {
            paginationHTML += `
                <li class="page-item ${i == currentPage ? "active" : ""}">
                    <a href="#" class="page-link">${i}</a>
                </li>
            `;
        }

        paginationHTML += `
            <li class="next ${currentPage == totalPages ? "disabled" : ""}">
                <a href="#" id="nextBtn">Next <i class="ti-arrow-right"></i></a>
            </li>
        `;

        paginationContainer.innerHTML = paginationHTML;
    }

    // Hàm hiển thị bài viết theo trang
    function showPage(page) {
        posts.forEach((post, index) => {
            post.style.display = (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) ? "block" : "none";
        });
    }

    // Hàm cập nhật phân trang
    function updatePagination() {
        generatePagination();

        document.querySelectorAll(".page-link").forEach((link, index) => {
            link.addEventListener("click", function (e) {
                e.preventDefault();
                currentPage = index + 1;
                showPage(currentPage);
                updatePagination();
            });
        });

        document.getElementById("prevBtn").addEventListener("click", function (e) {
            e.preventDefault();
            if (currentPage > 1) {
                currentPage--;
                showPage(currentPage);
                updatePagination();
            }
        });

        document.getElementById("nextBtn").addEventListener("click", function (e) {
            e.preventDefault();
            if (currentPage < totalPages) {
                currentPage++;
                showPage(currentPage);
                updatePagination();
            }
        });
    }

    // Khởi tạo lần đầu
    showPage(currentPage);
    updatePagination();
});
</script>
    </body>

</html>