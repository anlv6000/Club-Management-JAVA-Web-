<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="currentPage" value="${currentPage}" />
<c:set var="totalPages" value="${totalPages}" />
<%@ include file="/WEB-INF/home/header.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
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
        <style>
            .left-section {
                display: flex;
                align-items: center;
            }

            /* Phần bên phải */
            .right-section {
                color: #6e9ee0;
            }

            /* Loại bỏ khoảng cách mặc định của các phần tử */
            .left-section label,
            .left-section select,
            .left-section input,
            .right-section a {
                margin: 0;
                padding: 0;
            }

            /* Khoảng cách giữa các phần tử trong left-section */
            .left-section label,
            .left-section select,
            .left-section input {
                margin-right: 5px;
            }

            /* Đảm bảo nút nằm sát lề phải */
           .right-section {
    color: #6e9ee0;
    margin-left: auto; /* Đẩy phần này sang bên phải */
    display: flex;
    align-items: center;
}

            .d-flex {
                display: flex;
            }
        </style>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

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

    </head>
    <body>


        <div id="fh5co-page">


            <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div style="display: flex; align-items: center; justify-content: space-between;">
                            <h1><span class="colored">Member</span> Club</h1>
                            <button type="button" class="btn btn-warning" onclick="window.location.href = 'PendingMemberServlet'">
                                Danh sách chờ
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="best-deal">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                            <h2>We are Offering the Best Real Estate Deals</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia.</p>
                        </div>
                    </div>



                    <div class="container">


                        <form method="get" action="ClubMember">
                            
                               
                                
                                    <div class="container-fluid fh5co-heading animate-box" data-animate-effect="fadeIn">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <!-- Phần bên trái -->
                                            <div class="left-section">
                                                <select id="roleDropdown" name="role" class="form-select" onchange="submitSearchForm()">
                                        <option value="">Select Roles</option>
                                        <option value="ClubLeader">Club Leader</option>
                                        <option value="ClubMember">Club Member</option>
                                    </select>
                                                <select id="statusDropdown" name="status" class="form-select" onchange="submitSearchForm()">
                                        <option value="">Select Status</option>
                                        <option value="Active">Active</option>
                                        <option value="Inactive">Inactive</option>
                                    </select>
                                            </div>
                                            <!-- Phần bên phải -->
                                            <div class="right-section">

                                               <input type="text" id="keyword" name="keyword" class="form-control" placeholder="Enter name or email">
                                        <button type="submit" class="btn btn-primary input-group-text">
                                            <i class="fa fa-search"></i>
                                        </button>

                                            </div>

                                        </div>
                                    </div>
                                    
                         
                        </form>
                    </div>

                        <div class="row">
                            <c:if test="${empty officialMembers}">
                                <p class="text-center w-100">Danh sách thành viên rỗng!</p>
                            </c:if>

                            <c:forEach var="member" items="${officialMembers}">
                                <div class="col-md-3">
                                    <div class="card mb-3 shadow-sm">
                                        <div class="card-body text-center">
                                            <img src="${member.imageURL}" alt="Avatar">
                                            <a href="MemberOfficialDetail?userID=${member.userID}" class="btn btn-link card-title">
                                                ${member.userName}
                                            </a>
                                            <p class="card-text"><strong>Phone:</strong> ${member.phone}</p>
                                            <p class="card-text"><strong>Role:</strong> ${member.role}</p>
                                            <p class="card-text"><strong>Status:</strong>
                                                <span class="${member.status eq 'Active' ? 'text-success' : 'text-danger'}">
                                                    ${member.status}
                                                </span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>


                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-12 text-center animate-box" data-animate-effect="fadeIn">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="ClubMember?page=${currentPage - 1}">Previous</a>
                                </li>
                            </c:if>

                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="ClubMember?page=${i}">${i}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="ClubMember?page=${currentPage + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>

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
                        <p>&ldquo; The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. S	he had a last view back on the skyline of her hometown Bookmarksgrove. &rdquo;</p>
                        <p><span class="fh5co-author"><cite>Rick Cook</cite></span><i class="icon facebook-color icon-facebook pull-right"></i></p>
                    </blockquote>
                </div>
            </div>
        </div>
    </div>


    <div id="fh5co-agents">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3 text-center fh5co-heading white animate-box" data-animate-effect="fadeIn">
                    <h2>Our Trusted Agents</h2>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                </div>
                <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">

                    <div class="fh5co-agent">
                        <figure>
                            <img src="images/testimonial_person2.jpg" alt="Free Website Template by FreeHTML5.co">
                        </figure>
                        <h3>John Doe</h3>
                        <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus. Debitis assumenda, possimus ducimus omnis.</p>
                        <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                    </div>

                </div>
                <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                    <div class="fh5co-agent">
                        <figure>
                            <img src="images/testimonial_person3.jpg" alt="Free Website Template by FreeHTML5.co">
                        </figure>
                        <h3>John Doe</h3>
                        <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus. Debitis assumenda, possimus ducimus omnis.</p>
                        <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                    </div>
                </div>
                <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                    <div class="fh5co-agent">
                        <figure>
                            <img src="images/testimonial_person4.jpg" alt="Free Website Template by FreeHTML5.co">
                        </figure>
                        <h3>John Doe</h3>
                        <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus. Debitis assumenda, possimus ducimus omnis.</p>
                        <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                    </div>
                </div>
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


    <footer id="fh5co-footer" role="contentinfo">

        <div class="container">
            <div class="col-md-3 col-sm-12 col-sm-push-0 col-xs-12 col-xs-push-0">
                <h3>About Us</h3>
                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                <p><a href="#" class="btn btn-primary btn-outline with-arrow btn-sm">I'm button <i class="icon-arrow-right"></i></a></p>
            </div>
            <div class="col-md-6 col-md-push-1 col-sm-12 col-sm-push-0 col-xs-12 col-xs-push-0">
                <h3>Our Services</h3>
                <ul class="float">
                    <li><a href="#">Web Design</a></li>
                    <li><a href="#">Branding &amp; Identity</a></li>
                    <li><a href="#">Free HTML5</a></li>
                    <li><a href="#">HandCrafted Templates</a></li>
                </ul>
                <ul class="float">
                    <li><a href="#">Free Bootstrap Template</a></li>
                    <li><a href="#">Free HTML5 Template</a></li>
                    <li><a href="#">Free HTML5 &amp; CSS3 Template</a></li>
                    <li><a href="#">HandCrafted Templates</a></li>
                </ul>

            </div>

            <div class="col-md-2 col-md-push-1 col-sm-12 col-sm-push-0 col-xs-12 col-xs-push-0">
                <h3>Follow Us</h3>
                <ul class="fh5co-social">
                    <li><a href="#"><i class="icon-twitter"></i></a></li>
                    <li><a href="#"><i class="icon-facebook"></i></a></li>
                    <li><a href="#"><i class="icon-google-plus"></i></a></li>
                    <li><a href="#"><i class="icon-instagram"></i></a></li>
                </ul>
            </div>


            <div class="col-md-12 fh5co-copyright text-center">
                <p>&copy; 2016 Free HTML5 template. All Rights Reserved. <span>Designed with <i class="icon-heart"></i> by <a href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a> Demo Images by <a href="http://unsplash.com/" target="_blank">Unsplash</a></span></p>	
            </div>

        </div>
    </footer>



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
                                        function submitSearchForm() {
                                            document.forms[0].submit();
                                        }
    </script>

</body>
</html>

