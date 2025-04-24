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
        <title>Event Registration</title>
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
            /* Bảng chính */
            /* Bảng chính */
            .table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }

            .table th,
            .table td {
                padding: 14px;
                border-bottom: 1px solid #e0e0e0;
                text-align: center;
                font-size: 14px;
            }

            /* Header bảng */
            .table th {
                background-color: #3b5998; /* Nền đậm hơn */
                color: #ffffff; /* Chữ trắng cho dễ đọc */
                font-weight: bold;
            }


            /* Hover cho bảng */
            .table-hover tbody tr:hover {
                background-color: #f9f9f9;
                transition: background-color 0.2s ease;
            }

            /* Ảnh avatar */
            .avatar-img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                object-fit: cover;
            }

            /* Trạng thái */
            .status-active {
                color: #28a745;
                font-weight: 600;
            }

            .status-inactive {
                color: #dc3545;
                font-weight: 600;
            }

            /* Nút hành động */
            .btn-info {
                background-color: #6e9ee0;
                color: #fff;
                border: none;
                padding: 6px 12px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.2s ease;
            }

            .btn-info:hover {
                background-color: #5b8dbd;
            }

            /* Dòng cuối cùng của bảng không có border */
            .table tbody tr:last-child td {
                border-bottom: none;
            }


            .right-section .btn-primary {
                height: 53px; /* Tăng chiều cao của nút, tùy chỉnh giá trị này */
                line-height: 1.5; /* Đảm bảo văn bản căn giữa theo chiều dọc */
                padding: 5px 15px; /* Khoảng cách bên trong nút */
                border-radius: 5px; /* Bo góc nhẹ cho nút */
            }
            /* Tùy chỉnh giao diện của select */
            .form-select {
                border: 1px solid #6e9ee0;
                width:200px;
                border-radius: 5px;
                padding: 5px 40px 5px -20px; /* Điều chỉnh padding: trên/dưới - phải - trái */
                background-color: #fff;
                color: #333;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                appearance: none;
                background-image: url('https://cdn-icons-png.flaticon.com/512/60/60995.png');
                background-repeat: no-repeat;
                background-position: right 10px center; /* Giữ vị trí mũi tên bên phải */
                background-size: 15px; /* Giảm kích thước mũi tên */
                transition: border-color 0.3s, box-shadow 0.3s;
               

            }

            .form-select:focus {
                border-color: #FF69B4;
                box-shadow: 0 0 5px rgba(255, 105, 180, 0.5);
                outline: none;
            }

            .form-select option {
                padding: 5px;
            }

            /* Đảm bảo icon không ảnh hưởng đến chiều rộng của select */
            .form-select {
                padding-right: 80px; /* Tăng padding phải để tránh che mất nội dung */
            }
             /* Nút Search */
            .right-section .btn-primary {
                height: 40px; /* Khớp với chiều cao của input và dropdown */
                padding: 5px 15px;
                border: 1px solid #6e9ee0;
                background-color: #6e9ee0;
                color: #fff;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.2s ease;
                font-size: 14px;
            }

            .right-section .btn-primary:hover {
                background-color: #5b8dbd;
                border-color: #5b8dbd;
            }

            /* Input Search */
            .search-input {
                border: 1px solid #6e9ee0;
                border-radius: 5px;
                padding: 5px 15px;
                width: 250px;
                height: 40px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                background-color: #fff;
                color: #333;
                font-size: 14px;
                transition: border-color 0.3s, box-shadow 0.3s;
                outline: none;
            }

            .search-input:focus {
                border-color: #FF69B4;
                box-shadow: 0 0 5px rgba(255, 105, 180, 0.5);
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

                            <h1><span class="colored">Event Registration</span>  Management</h1>

                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${not empty requestScope.message}">
                <div id="alertMessage" class="alert alert-success" role="alert">
                    ${requestScope.message}
                </div>
            </c:if>

            <script>
                // Đóng thông báo sau 3 giây
                setTimeout(function () {
                    var alert = document.getElementById('alertMessage');
                    if (alert) {
                        alert.style.transition = 'opacity 0.5s ease';
                        alert.style.opacity = '0';
                        setTimeout(() => {
                            alert.style.display = 'none';
                        }, 500); // Chờ hiệu ứng mờ dần hoàn thành rồi mới ẩn
                    }
                }, 3000);
            </script>



            <div id="best-deal">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                            <h2>List of Event Registrations</h2>
                        </div>
                    </div>

                    <!-- Form tìm kiếm hoặc lọc -->
                    <div class="container">
                        <form method="get" action="EventRegistrationServlet">
                            <h2> ${eventName}</h2>



                            <div class="container-fluid fh5co-heading animate-box" data-animate-effect="fadeIn">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="left-section">
                                        <select id="roleDropdown" name="event" class="form-select" onchange="submitSearchForm()">
                                            <c:forEach var="event" items="${event}">
                                                <tr>
                                                <option value="${event.id}" ${param.event eq event.id ? 'selected' : ''}>${event.eventName}</option>
                                                </tr>
                                            </c:forEach>
                                        </select>
                                        <!-- Dropdown cho Role -->
                                        <select id="roleDropdown" name="role" class="form-select" onchange="submitSearchForm()">
                                            <option value="All">All</option>
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role}" ${param.role eq role ? 'selected' : ''}>${role}</option>
                                            </c:forEach>
                                        </select>

                                        <!-- Dropdown cho Status -->
                                        <select id="statusDropdown" name="status" class="form-select" onchange="submitSearchForm()">
                                            <option value="All">All</option>
                                            <c:forEach var="status" items="${statuses}">
                                                <option value="${status}" ${param.status eq status ? 'selected' : ''}>${status}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="right-section">
                                        <input type="text" id="keyword" name="keyword" class="search-input" placeholder="Enter name or email">
                                        <div class="input-group-append">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fa fa-search"></i> <!-- Thêm biểu tượng tìm kiếm -->
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </form>
                    </div>

                    <!-- Danh sách đăng ký -->
                    <div class="container">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Avatar</th>
                                    <th>Name</th>

                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty eventRegistration}">
                                    <tr>
                                        <td colspan="5" class="text-center">No registrations found!</td>
                                    </tr>
                                </c:if>
                                <c:forEach var="registration" items="${eventRegistration}">
                                    <tr>
                                        <td>
                                            <img src="${registration.imageURL}" alt="Avatar" width="50" height="50" style="border-radius: 50%;">
                                        </td>

                                        <td>${registration.fullName}</td>

                                        <td>${registration.status}</td>

                                        <td>
                                            <a href="EventRegistrationDetail?ID=${registration.id}" class="btn btn-info btn-sm">View</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- Phân trang -->
                    <div class="row">
                        <div class="col-md-12 text-center animate-box" data-animate-effect="fadeIn">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="EventRegistrationServlet?page=${currentPage - 1}">Previous</a>
                                        </li>
                                    </c:if>

                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="EventRegistrationServlet?page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="EventRegistrationServlet?page=${currentPage + 1}">Next</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
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

