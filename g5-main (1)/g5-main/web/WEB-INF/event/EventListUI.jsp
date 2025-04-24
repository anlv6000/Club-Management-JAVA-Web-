
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Estate &mdash; Free HTML5 Bootstrap Website Template by FreeHTML5.co</title>
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


        <!-- Modernizr JS -->
        <script src="js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="js/respond.min.js"></script>
        <![endif]-->
        <style>
            .fh5co-property {
                display: flex;
                flex-direction: column;
                height: 100%;
            }

            .fh5co-property img {
                width: 400px; /* Adjust the width as needed */
                height: 350px; /* Adjust the height as needed */
                object-fit: cover; /* Ensure the image covers the area */
                display: block;
            }

            .fh5co-property-innter {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
            }

            .fh5co-property-innter h3 {
                flex-grow: 1;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 3; /* Number of lines to show */
                -webkit-box-orient: vertical;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination li{
                text-align: center;
            }

            .pagination li.active{
                text-align: center;
            }
            .toast {
                position: fixed;
                top: 20px; /* Đưa thông báo lên trên */
                right: 20px; /* Giữ nó ở góc phải */
                background: rgba(0, 128, 0, 0.8);
                color: white;
                margin-top: 200px ;
                padding: 12px 20px;
                border-radius: 5px;
                font-size: 14px;
                display: none;
                opacity: 1;
                transition: opacity 0.5s ease-in-out, transform 0.5s ease-in-out;
                z-index: 1000;
                transform: translateY(-20px); /* Ban đầu ẩn đi một chút */
            }


            .toast.error {
                background: rgba(255, 0, 0, 0.8);
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
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${not empty sessionScope.notification}">
            <div id="toast" class="toast ${sessionScope.notificationType}">
                ${sessionScope.notification}
            </div>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var toast = document.getElementById("toast");
                    if (toast) {
                        toast.style.display = "block"; // Hiển thị thông báo
                        setTimeout(function () {
                            toast.style.opacity = "0"; // Làm mờ dần
                            setTimeout(function () {
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
            <header id="fh5co-header" role="banner">
                <div class="container">
                    <div class="row">
                        <div class="header-inner">







                        </div>
                    </div>
                </div>
            </header>

            <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 animate-box">
                            <h1><span class="colored">Event</span>List</h1>
                        </div>
                    </div>
                </div>
            </div>
            
                <div id="best-deal">

                    <div class="container">
                    

                        <div class="row">
                            <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">

                                <h2>Manage Your Event List Effectively</h2>

                                <p>Track and manage event details with ease and efficiency.</p>
                            </div>


                            <!-- New Finance button -->



                            <form action="EventListUser">
                                <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">


                                    <select name="clubName"   onchange="this.form.submit()">

                                    <c:forEach var="o" items="${listClub}">
                                        <option value="${o.clubName}" ${o.clubName == param.clubName ? 'selected' : ''}>
                                            ${o.clubName}
                                        </option>
                                    </c:forEach>
                                    <option value="all">Get All Club</option>

                                </select>

                            </div>
                            <c:forEach var="k" items="${listEvent}"> 
                                <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">

                                    <div class="fh5co-property">

                                        <figure>
                                            <img src="${k.eventImageURL}" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                            <a href="DetailEventbyID?eventID=${k.id}" class="tag">Detail Club</a>
                                        </figure>



                                        <div class="fh5co-property-innter">

                                            <h3><a href="DetailEventbyID?eventID=${k.id}">${k.eventName}</a></h3>
                                            <h3>${k.description}</h3>


                                        </div>
                                        <p class="fh5co-property-specification">
                                            <span><strong>Start time:</strong> ${k.eventDate}</span>  
                                        </p>

                                    </div>
                                </div>
                            </c:forEach>
                            <c:forEach var="p" items="${listEventss}"> 
                                <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">

                                    <div class="fh5co-property">

                                        <figure>
                                            <img src="${p.eventImageURL}" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                            <a  href="DetailEventbyID?eventID=${p.id}"  class="tag">Detail Club</a>
                                        </figure>



                                        <div class="fh5co-property-innter">

                                            <h3><a href="DetailEventbyID?eventID=${p.id}">${p.eventName}</a></h3>
                                            <h3>${p.description}</h3>


                                        </div>
                                        <p class="fh5co-property-specification">
                                           <span><strong>Start time:</strong> ${p.eventDate}</span>  
                                        </p>
                                    </div>
                                </div>
                            </c:forEach>

                        </form>

                        
                    </div>
                </div>
            </div>





            <div class="fh5co-cta" style="background-image: url(images/slide_4.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="col-md-12 text-center animate-box">
                        <h3>We Try To Update The Site Everyday</h3>
                        <p><a href="#" class="btn btn-primary btn-outline with-arrow">Get started now! <i class="icon-arrow-right"></i></a></p>
                    </div>

                </div>
            </div>

            <script>

                document.addEventListener("DOMContentLoaded", function () {
                    const itemsPerPage = 6; // Số lượng câu lạc bộ hiển thị trên mỗi trang
                    const clubs = document.querySelectorAll(".col-md-4.item-block");
                    const totalPages = Math.ceil(clubs.length / itemsPerPage);
                    let currentPage = 1;

                    function showPage(page) {
                        clubs.forEach((club, index) => {
                            club.style.display = (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) ? "block" : "none";
                        });
                    }

                    function createPagination() {
                        if (totalPages <= 1)
                            return; // Nếu chỉ có một trang thì không cần phân trang

                        const pagination = document.createElement("ul");
                        pagination.className = "pagination justify-content-center mt-4";

                        for (let i = 1; i <= totalPages; i++) {
                            const li = document.createElement("li");
                            li.className = "page-item";
                            const a = document.createElement("a");
                            a.className = "page-link";
                            a.href = "#";
                            a.textContent = i;

                            a.addEventListener("click", function (e) {
                                e.preventDefault();
                                currentPage = i;
                                showPage(currentPage);
                                updateActivePage();
                            });

                            li.appendChild(a);
                            pagination.appendChild(li);
                        }

                        // Chèn pagination vào sau form
                        document.querySelector("form").appendChild(pagination);
                    }

                    function updateActivePage() {
                        document.querySelectorAll(".pagination .page-item").forEach((item, index) => {
                            item.classList.toggle("active", index + 1 === currentPage);
                        });
                    }

                    showPage(currentPage);
                    createPagination();
                    updateActivePage();
                });



            </script>


        </div>
        <jsp:include page="footer.jsp"></jsp:include>
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

    </body>
</html>

