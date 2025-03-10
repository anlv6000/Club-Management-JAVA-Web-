<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="Club FPT University" />

        <!-- OG -->
        <meta property="og:title" content="Club FPT University" />
        <meta property="og:description" content="Club FPT University" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Club FPT University</title>

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

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(https://daihoc.fpt.edu.vn/en/wp-content/uploads/2024/09/DHFPT6-1.webp);">
                    <a href="index.html"><img src="assets/images/logo-white-2.png" alt=""></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Sign Up <span>Now</span></h2>
                            <p>Login Your Account <a href="Login.jsp">Click here</a></p>
                        </div>	
                        <form class="contact-bx" action="RegisterServlet">
                            <div class="row placeani">
                                 <c:if test="${erf != null}"><div style="color: red">${erf}</div></c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                        <c:if test="${fullname == null}" ><label>Fullname</label></c:if>
                                            <input name="fullname" type="text" class="form-control" value="${fullname}" required="">
                                        </div>
                                    </div>
                                </div>
                                        <c:if test="${eru != null}"><div style="color: red">${eru}</div></c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <c:if test="${user == null}" ><label>Username</label></c:if>
                                            <input name="user" type="text" required="" class="form-control" value="${user}" >
                                        </div>
                                    </div>
                                </div>
                                        <c:if test="${erre != null}"><div style="color: red">${erre}</div></c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <c:if test="${email == null}" ><label>Your Email Address</label></c:if>
                                            <input name="email" type="email" required="" class="form-control" value="${email}">
                                        </div>
                                    </div>
                                </div>
                                         <c:if test="${erp != null}"><div style="color: red">${erp}</div></c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <c:if test="${pass == null}" ><label>Your Password</label></c:if>
                                            <input name="pass" type="password" id="password" class="form-control" required="" value="${pass}">
                                            <span onclick="togglePassword('password')"></span>
                                        </div>
                                    </div>
                                </div>
                                             <c:if test="${err != null}"><div style="color: red">${err}</div></c:if>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <c:if test="${repass == null}" ><label>Verify Password</label></c:if>
                                            <input name="re_pass" type="password"  id="verification" class="form-control" required="" value="${repass}">
                                            <span onclick="togglePassword('verification')"></span>

                                        </div>
                                    </div>
                                </div>
                                             <c:if test="${mess != null}"><div style="color: red">${mess}</div></c:if>
                                <div class="col-lg-12 m-b30">
                                    <button name="submit" type="submit" value="Submit" class="btn button-md">Sign Up</button>
                                </div>
                                           
                                <div class="col-lg-12">
                                    <h6>Sign Up with Social media</h6>
                                    <div class="d-flex">
                                        <a class="btn flex-fill m-l5 google-plus" href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/club_manaaaa/SendOTP&response_type=code&client_id=359282860225-atna0216v504grriqa6g819uvcgfnli6.apps.googleusercontent.com&approval_prompt=force"target="_blank" ><i class="fa fa-google-plus"></i>Google Plus</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                                
                                <script>
            function togglePassword(id) {
                const input = document.getElementById(id);
                if (input.type === 'password') {
                    input.type = 'text';
                } else {
                    input.type = 'password';
                }
            }
        </script>
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
