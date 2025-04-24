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
        <title>Event Registration Detail</title>
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
            /* Card chính */
/* Định dạng chung */
 /* Định dạng chung */
body {
    font-family: 'Arial', sans-serif;
    background-color: #f8f8f8;
    color: #333;
}

.form-container {
    background-color: #ffffff;
    padding: 24px;
    margin: 20px auto;
    border: 1px solid #ccc;
    border-radius: 12px;
    max-width: 800px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.avatar-wrapper {
    text-align: center;
    margin-bottom: 20px;
}

.avatar-img {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid #6e9ee0;
}

.form-group {
    display: flex;
    flex-direction: column;
    margin-bottom: 16px;
}

.form-group label {
    font-weight: bold;
    margin-bottom: 6px;
    color: #555;
}

.form-group span,
.form-select {
    font-size: 16px;
    color: #333;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #fafafa;
}

.form-select {
    cursor: pointer;
}

 .btn-group {
    display: flex;
    justify-content: flex-start; /* Đẩy các nút ra hai phía */
    gap: 16px; /* Khoảng cách giữa các nút */
}

.btn-primary, .btn-secondary {
    padding: 12px 24px;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.2s ease;
    border: none;
}

.btn-primary {
    background-color: #007bff;
    color: #fff;
}

.btn-secondary {
    background-color: #ccc;
    color: #333;
}

.btn-primary:hover {
    background-color: #0056b3;
}

.btn-secondary:hover {
    background-color: #bbb;
}


/* Responsive cho mobile */
@media (max-width: 768px) {
    .form-container {
        width: 100%;
    }

    .btn-group {
        flex-direction: column;
    }

    .btn {
        width: 100%;
        margin-bottom: 8px;
    }
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
                        <h1><span class="colored">View and Update Event Registrations</span></h1>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${not empty param.message}">
            <div id="alertMessage" class="alert alert-success" role="alert">
                ${param.message}
            </div>
        </c:if>

        <script>
            setTimeout(function () {
                var alert = document.getElementById('alertMessage');
                if (alert) {
                    alert.style.transition = 'opacity 0.5s ease';
                    alert.style.opacity = '0';
                    setTimeout(() => {
                        alert.style.display = 'none';
                    }, 500);
                }
            }, 3000);
        </script>

        <div id="best-deal">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                <h2>Registration Details</h2>
            </div>
        </div>

        <div class="registration-info">
            <c:if test="${empty eventRegistration}">
                <p class="text-center">No registration found!</p>
            </c:if>
            <c:if test="${not empty eventRegistration}">
                <form method="post" action="EventRegistrationDetail" class="form-container">
                    <input type="hidden" name="userID" value="${eventRegistration.userId}" />
                    <input type="hidden" name="ID" value="${eventRegistration.id}" />
                    <input type="hidden" name="eventID" value="${eventRegistration.eventId}" />

                    <div class="avatar-wrapper">
                        <img src="${eventRegistration.imageURL}" alt="Avatar" class="avatar-img">
                    </div>

                    <div class="form-group">
                        <label>Name:</label>
                        <span>${eventRegistration.fullName}</span>
                    </div>

                    <div class="form-group">
                        <label>Email:</label>
                        <span>${eventRegistration.email}</span>
                    </div>

                    <div class="form-group">
                        <label>Phone:</label>
                        <span>${eventRegistration.phone}</span>
                    </div>

                    <div class="form-group">
                        <label>Note:</label>
                        <span>${eventRegistration.note}</span>
                    </div>

                    <div class="form-group">
                        <label>Status:</label>
                        <select name="status" class="form-select">
                            <c:forEach var="status" items="${statuses}">
                                <option value="${status}" ${eventRegistration.status == status ? 'selected' : ''}>
                                    ${status}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                        <button type="button" class="btn btn-secondary" onclick="window.location.href='EventRegistrationServlet'">Cancel</button>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</div>

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
                function submitSearchForm() {
                    document.forms[0].submit();
                }
        </script>

    </body>
</html>

