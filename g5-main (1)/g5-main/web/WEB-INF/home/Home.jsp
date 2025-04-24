<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->


    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Welcome to FPT Club!</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <!-- 
              //////////////////////////////////////////////////////
      
              FREE HTML5 TEMPLATE 
              DESIGNED & DEVELOPED by FreeHTML5.co
                      
              Website: 		http://freehtml5.co/
              Email: 			info@freehtml5.co
              Twitter: 		http://twitter.com/fh5co
              Facebook: 		https://www.facebook.com/fh5co
      
              //////////////////////////////////////////////////////
        -->

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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
        <!-- Modernizr JS -->
        <script src="js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="js/respond.min.js"></script>
        <![endif]-->
        <style>
            #toast {
    position: fixed;
    top: 20px;
    right: 20px;
    width: 400px;
    padding: 20px;
    font-size: 20px;
    font-weight: bold;
    background-color: #28a745; /* Màu xanh lá */
    color: white;
    border-radius: 8px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
    display: none;
    opacity: 1;
    transition: opacity 0.5s ease-in-out;
    text-align: center;
    z-index: 9999;
}

/* Thêm hiệu ứng rung nhẹ khi xuất hiện */
@keyframes shake {
    0%, 100% { transform: translateX(0); }
    25% { transform: translateX(-5px); }
    50% { transform: translateX(5px); }
    75% { transform: translateX(-5px); }
}

.toast {
    animation: shake 0.5s ease-in-out;
}
             #floatingButton {
                position: fixed;
                bottom: 10px;
                right: 10px;
                z-index: 1000;
                width: 50px;
                height: 50px;
                background-color: #f1eeee; /* Trắng trong suốt */
                color: black; /* Màu chữ */
                border: none;
                border-radius: 50%;
                cursor: pointer;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 24px; /* Đảm bảo chữ vừa vặn trong nút */
            }

            #floatingButton:hover {
                background-color: rgba(255, 255, 255, 0.7); /* Màu trắng trong suốt đậm hơn khi hover */
            }
            /* Căn giữa carousel */
.swiper-container {
    width: 60%;  /* Điều chỉnh độ rộng */
    max-width: 800px; /* Giới hạn kích thước tối đa */
    margin: auto;  /* Căn giữa */
    padding: 20px 0; /* Khoảng cách trên dưới */
}

/* Slide gọn hơn */
.swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
}

/* Card CLB */
.fh5co-property {
    background: white;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 15px;
    max-width: 300px; /* Giới hạn chiều rộng card */
    text-align: center;
}

/* Hình ảnh gọn hơn */
.fh5co-property img {
    width: 100%;
    height: 200px; /* Giới hạn chiều cao ảnh */
    object-fit: cover;
    border-radius: 8px;
}

/* Tiêu đề */
.fh5co-property h3 {
    font-size: 18px;
    margin-top: 10px;
    color: #333;
}

/* Nút Detail */
.fh5co-property .tag {
    display: inline-block;
    padding: 5px 12px;
    background: #ff5e57;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    font-size: 14px;
    margin-top: 8px;
}

/* Chỉnh nút điều hướng */
.swiper-button-next,
.swiper-button-prev {
    color: #ff5e57; /* Đổi màu nút */
}

.swiper-pagination-bullet-active {
    background: #ff5e57; /* Đổi màu chấm tròn đang active */
}


        </style>
        <jsp:include page="header.jsp"></jsp:include>
    

  
        </head>
        <body>
<c:if test="${not empty sessionScope.notification}">
    <div id="toast" class="toast ${sessionScope.notificationType}">
        ${sessionScope.notification}
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var toast = document.getElementById("toast");
            if (toast) {
                toast.style.display = "block"; // Hiển thị thông báo
                setTimeout(function() {
                    toast.style.opacity = "0"; // Làm mờ dần
                    setTimeout(function() {
                        toast.style.display = "none"; // Ẩn hoàn toàn
                    }, 500);
                }, 10000);
            }
        });
    </script>
    <!-- Xóa thông báo khỏi session sau khi hiển thị -->
    <c:remove var="notification" scope="session" />
    <c:remove var="notificationType" scope="session" />
</c:if>

            <div id="fh5co-page">

                <aside id="fh5co-hero" clsas="js-fullheight">
                    <div class="flexslider js-fullheight">
                        <ul class="slides">
                            <li style="background-image: url(images/FPT1.jpg);">
                                <div class="container">
                                    <div class="col-md-12 text-center js-fullheight fh5co-property-brief slider-text">
                                        <div class="fh5co-property-brief-inner">
                                            <div class="fh5co-box">
                                                <h3><a href="#">FPT green space</a></h3>
                                                <div class="price-status">
                                                    <p>A green, clean, beautiful university is an ideal learning environment, where students and lecturers can immerse themselves in a fresh, natural space.</p>
                                                </div>

                                                <div>${success}</div>
                                            <c:if test="${kaka!=null}"><div>${kaka}</div></c:if>
                                                <p class="fh5co-property-specification">
                                                    <span><strong>VOV</strong></span><span><strong>Technology</strong></span><span><strong>Music</strong></span><span><strong>traditional musical instruments</strong></span>
                                                </p>




                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li style="background-image: url(images/fpt2.jpg);">
                                <div class="container">
                                     
                                    <div class="col-md-12 text-center js-fullheight fh5co-property-brief slider-text">
                                        <div class="fh5co-property-brief-inner">
                                            <div class="fh5co-box">

                                                <h3><a href="#">FPT green space</a></h3>
                                                <div class="price-status">

                                                </div>
                                                <p>A green, clean, beautiful university is an ideal learning environment, where students and lecturers can immerse themselves in a fresh, natural space.</p>

                                                <p class="fh5co-property-specification">
                                                    <span><strong>VOV</strong></span>  <span><strong>Technology</strong></span>  <span><strong>Music</strong> </span>  <span><strong>traditional musical instruments</strong></span>
                                                </p>




                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </li>
                            <li style="background-image: url(images/fpt3.jpg);">
                                <div class="container">
                                    <div class="col-md-12 text-center js-fullheight fh5co-property-brief slider-text">
                                        <div class="fh5co-property-brief-inner">
                                            <div class="fh5co-box">

                                                <h3><a href="#">FPT green space</a></h3>
                                                <div class="price-status">

                                                </div>
                                                <p>A green, clean, beautiful university is an ideal learning environment, where students and lecturers can immerse themselves in a fresh, natural space.</p>

                                                <p class="fh5co-property-specification">
                                                    <span><strong>VOV</strong></span>  <span><strong>Technology</strong></span>  <span><strong>Music</strong> </span>  <span><strong>traditional musical instruments</strong></span>
                                                </p>




                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>


                        </ul>
                    </div>
                </aside>
                                                
                <div id="best-deal">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                                <p>Welcome to FPT Club!</p>
                                <p>Explore our club activities and join us today!</p>
                                <h2>Lots of clubs for you to explore</h2>
                                <br>
                                
                            </div>
                            <form id="autoSubmitForm" action="home1" >
                            <c:if test="${not empty listP}">
                                <c:forEach var="o" items="${listP}" varStatus="status">
                                    <%-- Mở hàng mới khi index % 3 == 0 --%>
                                    <c:if test="${status.index % 3 == 0}">
                                        <div class="row">
                                        </c:if>

                                        <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                            <div class="fh5co-property">
                                                <figure>
                                                    <img src="${o.imgURL}" alt="img" class="img-responsive">
                                                    <a href="${pageContext.request.contextPath}/ViewPublicClubDetail?clubName=${o.clubName}" class="tag">Detail</a>
<!--                                                      <a href="ClubDetailById?clubid=${o.clubID}" class="tag">Detail</a>-->
                                                </figure>
                                                <div class="fh5co-property-innter">
                                                    <h3><a>${o.clubName}</a></h3>
                                                    <div class="price-status"></div>

                                                </div>
                                                <p class="fh5co-property-specification">
                                                    <span><strong>${o.category}</strong> Category</span>
                                                    <span><strong>${o.schedule}</strong> Schedule</span>
                                                </p>
                                            </div>
                                        </div>

                                        <%-- Đóng hàng khi index % 3 == 2 hoặc là phần tử cuối cùng --%>
                                        <c:if test="${status.index % 3 == 2 or status.last}"  >
                                        </div> <%-- Đóng div.row --%>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </form>




                    </div>
                </div>
            </div>




            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <c:forEach var="o" items="${listall}">
                        <div class="swiper-slide">
                            <div class="fh5co-property">
                                <figure>
                                    <img src="${o.imgURL}" alt="img" class="img-responsive">
                                    <a href="ClubDetailById?clubid=${o.clubID}" class="tag">Detail</a>
                                </figure>
                                <div class="fh5co-property-innter">
                                    <h3><a>${o.clubName}</a></h3>
                                    <p><strong>${o.category}</strong> Category</p>
                                    <p><strong>${o.schedule}</strong> Schedule</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Pagination (dấu chấm) -->
                <div class="swiper-pagination"></div>

                <!-- Nút điều hướng -->
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var swiper = new Swiper(".swiper-container", {
                        loop: true, // Lặp vô hạn
                        autoplay: {
                            delay: 3000, // Thời gian chuyển đổi (3s)
                            disableOnInteraction: false, // Giữ autoplay ngay cả khi người dùng tương tác
                        },
                        pagination: {
                            el: ".swiper-pagination",
                            clickable: true,
                        },
                        navigation: {
                            nextEl: ".swiper-button-next",
                            prevEl: ".swiper-button-prev",
                        },
                        effect: "fade", // Hiệu ứng mượt hơn
                    });
                });</script>


            <div id="fh5co-agents">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3 text-center fh5co-heading white animate-box" data-animate-effect="fadeIn">
                            <h2>Các bài post tiêu biểu</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                        <form id="autoSubmitForm" action="home1" >
                            <c:if test="${not empty postList}">
                                <c:forEach var="o" items="${postList}">
                                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">

                                        <div class="fh5co-agent">
                                            <figure>
                                                <img src="${o.image_url}" alt="Free Website Template by FreeHTML5.co">
                                            </figure>
                                            <h3>${o.title}</h3>

                                            <p><a href="#" class="btn btn-primary btn-outline">Detail Post</a></p>
                                        </div>

                                    </div>
                                </c:forEach></c:if>
                            </form>
                        </div>
                    </div>
                </div>



                <div id="fh5co-blog">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6 col-md-offset-3 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                                <h2>Latest <em>from</em> Blog</h2>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <form id="autoSubmitForm" action="home1" >

                            <c:if test="${not empty postList}">
                                <c:forEach var="o" items="${postList}">
                                    <div class="col-md-4 animate-box" data-animate-effect="fadeIn">
                                        <a class="fh5co-entry" href="#">
                                            <figure>
                                                <img src="${o.image_url}" alt="Free Website Template, Free HTML5 Bootstrap Template" class="img-responsive">
                                            </figure>
                                            <div class="fh5co-copy">
                                                <h3>${o.title}</h3>
                                                <span class="fh5co-date">June 8, 2016</span>

                                            </div>
                                        </a>
                                    </div>
                                </c:forEach></c:if>

                            </form>

                            <div class="col-md-12 text-center animate-box" data-animate-effect="fadeIn">
                                <p><a href="#" class="btn btn-primary btn-outline with-arrow">View More Posts <i class="icon-arrow-right"></i></a></p>
                            </div>
                        </div>
                    </div>	
                </div>

                <div class="fh5co-cta" style="background-image: url(images/slide_4.jpg);">
                    <div class="overlay"></div>
                    <div class="container">
                        <div class="col-md-12 text-center animate-box" data-animate-effect="fadeIn">
                            <h3>We Try To Update The Site Everyday</h3>
                            <p><a href="#" class="btn btn-primary btn-outline with-arrow">Get started now! <i class="icon-arrow-right"></i></a></p>
                        </div>
                    </div>
                </div>


            <jsp:include page="footer.jsp"></jsp:include>


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
        <script src="js/jquery.flexslider-min.js"></script>

        <!-- MAIN JS -->
        <script src="js/main.js"></script>
        <script>
                function scrollToTop() {
                    window.scrollTo({top: 0, behavior: 'smooth'});
                }

        </script>

        <button id="floatingButton" onclick="scrollToTop()">>.<</button>

        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    </body>
</html>

