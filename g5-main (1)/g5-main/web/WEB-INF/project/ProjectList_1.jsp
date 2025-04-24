<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="currentPage" value="${currentPage}" />
<c:set var="totalPages" value="${totalPages}" />
<jsp:include page="header.jsp"></jsp:include>


<!DOCTYPE html>
<html>
    
    <head>
        <script type="text/javascript">
            function openPopup() {
                window.open('popup.jsp', 'popUpWindow', 'height=400,width=600,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes');
            }
        </script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/manager.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <style>
            body {
                position: relative;
                
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
            }

            body::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(255, 255, 255, 0.5); /* 50% transparency layer */
                z-index: -1; /* Place it behind any content */
            }

            .content {
        margin-left: 150px;
        margin-right: 50px;
    margin-top: 200px;
    background: #ffffff;
    padding: 25px;
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            }

            .table-title {
                background: #ffffff; /* Nền trắng */
                color: #34495e; /* Màu chữ đậm */
                padding: 20px;
                border-radius: 8px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .table-title h2 {
                margin: 0;
                font-size: 24px;
                font-weight: 600;
            }

            .table-title a {
                padding: 12px 20px;
                background-color: #1abc9c;
                color: white;
                border-radius: 25px;
                text-decoration: none;
                font-weight: bold;
                transition: all 0.3s ease;
            }

            .table-title a:hover {
                background-color: #16a085;
                transform: scale(1.05);
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
            }


            .table {
                margin-top: 30px;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
            }

            .table th {
                background: #2c3e50;
                color: white;
                font-weight: 600;
                text-transform: uppercase;
            }

            .table tbody tr:hover {
                background-color: #ecf0f1;
            }

            .table td {
                vertical-align: middle;
                padding: 12px;
                font-size: 14px;
            }

            img {
                width: 50px;
                height: 50px;
                object-fit: cover;
                border-radius: 50%;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .status-active {
                color: #27ae60;
                font-weight: bold;
            }

            .status-inactive {
                color: #e74c3c;
                font-weight: bold;
            }

            .btn {
                transition: all 0.3s ease;
            }

            .btn:hover {
                transform: scale(1.1);
                background-color: #1abc9c;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            .search-container {
                display: flex;
                align-items: center;
                margin-top: 20px;
                gap: 20px;
                z-index: 1; /* Đặt z-index thấp để không che modal */
                position: relative; /* Đảm bảo search không nằm trên modal */
            }

            .search-container select,
            .search-container input {
                height: 35px;
                padding: 8px;
                font-size: 14px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            .search-container button {
                padding: 8px 15px;
                font-size: 14px;
                border-radius: 5px;
                background-color: #2980b9;
                color: white;
                border: none;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .search-container button:hover {
                background-color: #3498db;
                transform: scale(1.05);
            }

            .table-title a {
                text-decoration: none;
                color: #ecf0f1;
            }

            .table-title a:hover {
                background-color: #2ecc71;
                transform: scale(1.05);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            /* Đặt lại phần nền cho pop-up */
            .modal {
                display: none;
                position: fixed;
                z-index: 999;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0,0,0,0.5); /* Làm mờ nền phía sau */
                overflow: auto;
            }

            /* Nội dung của modal */
            .modal-content {
                background-color: #ffffff;
                margin: 5% auto;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.3); /* Thêm bóng để nổi bật */
                width: 90%; /* Giảm kích thước */
                max-width: 500px;
                position: relative;
                transition: all 0.3s ease-in-out;
            }

            /* Nút đóng */
            .close {
                position: absolute;
                top: 10px;
                right: 15px;
                color: #888;
                font-size: 24px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: #000;
            }
            .page-items .page-links .active{
                background-color: #118DF0; color: white 
            }

        </style>
    </head>
    <body>

        <div class="container" >
            <div id="successMessage" class="alert alert-success" style="display: none;">
                Project đã được thêm thành công!
            </div>
            <div class="content" style="">
            <div class="table-wrapper">
                <div class="table-title">
                    <h2>
                        <a href="ListProject" style="text-decoration: none; color: inherit;">
                            List <b>Project</b>
                        </a>
                    </h2>
                    <a href="AddProject.jsp"  class="btn btn-success">Add New Project</a>

                    <div id="addAccountModal" class="modal">
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <iframe id="addAccountFrame" src="AddProject.jsp" style="width: 100%; height: 500px; border: none;"></iframe>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Form lọc + tìm kiếm -->
            <form id="filterForm" method="get" action="ListProjectServlet">
                <div class="row search-container">

                    <!-- Lọc theo Club -->
                    <div class="col-md-3">
                        <select name="clubId" class="form-select" onchange="submitForm()">
                            <option value="">All Clubs</option>
                            <c:forEach var="club" items="${clubList}">
                                <option value="${club.clubID}" <c:if test="${club.clubID == selectedClubId}">selected</c:if>>${club.clubName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Lọc theo Status -->
                    <div class="col-md-3">
                        <select name="status" class="form-select" onchange="submitForm()">
                            <option value="">All Status</option>
                            <option value="1" <c:if test="${selectedStatus == '1'}">selected</c:if>>Active</option>
                            <option value="0" <c:if test="${selectedStatus == '0'}">selected</c:if>>Inactive</option>
                            </select>
                        </div>

                        <!-- Thanh tìm kiếm -->
                        <div class="col-md-3">
                            <div class="input-groups" style="display: flex;">
                                <input type="text" name="keyword" class="form-control" placeholder="Enter name or email" value="${keyword}">
                            <div class="input-group-appends" >
                                <button type="submit" class="btn btn-primary">
                                    <i class="fa fa-search" style="height: 17.453px"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <select name="sort" onchange="this.form.submit()">
                            <option value="">-- Sort --</option>
                            <option value="name_asc" ${sort == 'name_asc' ? 'selected' : ''}>Tên A-Z</option>
                            <option value="name_desc" ${sort == 'name_desc' ? 'selected' : ''}>Tên Z-A</option>
                        </select>
                    </div>
                    <!-- Ẩn page để giữ phân trang khi tìm kiếm -->
                    <input type="hidden" name="page" value="${currentPage}" />
                </div>
            </form>

            <!-- Bảng dữ liệu -->
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Code</th>
                        <th>Form Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Name Creater</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ProjectList}" var="o">
                        <tr>
                            <td>${o.projectID}</td>
                            <td>${o.name}</td>

                            <td>${o.code}</td>
                            <td>${o.fromDate}</td>
                            <td>${o.toDate}</td>
                            <td class="${o.status ? 'status-active' : 'status-inactive'}">
                                ${o.status ? 'Active' : 'Inactive'}
                            </td>
                            <td>${o.nameCreater}</td>
                            <td>
                                <a href="ProjectDetails?projectId=${o.projectID}" class="View">
                                    <i class="fa fa-eye" style="color: #0044e0; font-size: 18px;" title="View"></i>
                                </a>

                                <c:if test="${sessionScope.acc.id == o.leader_ID}">
                                    <a href="UpdateProjectServlet?ProjectID=${o.projectID}">
                                        <i class="material-icons" data-toggle="tooltip" style="margin-left: 5px"title="Update">&#xE254;</i>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Phân trang -->
            <form>
                <ul class="paginations justify-content-center" style="display: flex;list-style: none;;">
                    <c:if test="${currentPage > 1}">
                        <li class="page-items" ">
                            <c:url var="prevUrl" value="ListProjectServlet">
                                <c:param name="page" value="${currentPage - 1}" />
                                <c:if test="${not empty selectedClubId}">
                                    <c:param name="clubId" value="${selectedClubId}" />
                                </c:if>
                                <c:if test="${not empty selectedStatus}">
                                    <c:param name="status" value="${selectedStatus}" />
                                </c:if>
                                <c:if test="${not empty keyword}">
                                    <c:param name="keyword" value="${keyword}" />
                                </c:if>
                            </c:url>
                            <a class="page-links" href="${prevUrl}" style="border: 1px solid grey; padding: 10px">Previous</a>
                        </li>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
    <c:url var="pageUrl" value="ListProjectServlet">
        <c:param name="page" value="${i}" />
        <c:if test="${not empty selectedClubId}">
            <c:param name="clubId" value="${selectedClubId}" />
        </c:if>
        <c:if test="${not empty selectedStatus}">
            <c:param name="status" value="${selectedStatus}" />
        </c:if>
        <c:if test="${not empty keyword}">
            <c:param name="keyword" value="${keyword}" />
        </c:if>
    </c:url>
    <li class="page-items">
        <a class="page-links" href="${pageUrl}" 
            style="border: 1px solid grey; padding: 10px;
                   ${i == currentPage ? 'background-color: blue; color: white; border-color: blue;' : ''}">
            ${i}
        </a>
    </li>
</c:forEach>


                    <c:if test="${currentPage < totalPages}">
                        <li class="page-items">
                            <c:url var="nextUrl" value="ListProjectServlet">
                                <c:param name="page" value="${currentPage + 1}" />
                                <c:if test="${not empty selectedClubId}">
                                    <c:param name="clubId" value="${selectedClubId}" />
                                </c:if>
                                <c:if test="${not empty selectedStatus}">
                                    <c:param name="status" value="${selectedStatus}" />
                                </c:if>
                                <c:if test="${not empty keyword}">
                                    <c:param name="keyword" value="${keyword}" />
                                </c:if>
                            </c:url>
                            <a class="page-links" href="${nextUrl}" style="border: 1px solid grey; padding: 10px">Next</a>
                        </li>
                    </c:if>
                </ul>
            </form>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var modal = document.getElementById("addAccountModal");
                var btn = document.getElementById("openAddAccountModal");
                var span = document.getElementsByClassName("close")[0];
                var iframe = document.getElementById("addAccountFrame");

                // Khi click nút "Add New Account", mở modal
                btn.onclick = function () {
                    modal.style.display = "block";
                    document.body.classList.add("modal-open"); // Chặn cuộn trang
                };

                // Khi click nút đóng (x), đóng modal
                span.onclick = function () {
                    modal.style.display = "none";
                    document.body.classList.remove("modal-open");
                };

                // Đóng modal nếu click bên ngoài
                window.onclick = function (event) {
                    if (event.target === modal) {
                        modal.style.display = "none";
                        document.body.classList.remove("modal-open");
                    }
                };

                function reloadAccountList() {
                    $.ajax({
                        url: 'ManageAccount', // Gọi lại servlet để lấy danh sách mới
                        type: 'GET',
                        success: function (response) {
                            $('#accountTable').html($(response).find('#accountTable').html()); // Cập nhật danh sách mà không reload trang
                        }
                    });
                }


                function reloadAccountList() {
                    $.ajax({
                        url: 'ManageAccount', // Gọi lại servlet để lấy danh sách mới
                        type: 'GET',
                        success: function (response) {
                            $('#accountTable').html($(response).find('#accountTable').html()); // Cập nhật danh sách mà không reload trang
                        }
                    });
                }



                function submitSearchForm() {
                    document.querySelector("form").submit();
                }
            });
        </script>

        <script>
            function submitForm() {
                document.getElementById("filterForm").submit();
            }
        </script>

        <script>
            function submitSearchForm() {
                document.getElementById("searchForm").submit();
            }
        </script>


    </body>
  <jsp:include page="footer.jsp" />




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
    
    <meta charset="UTF-8">
    <title>Finance Detail</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
    <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
    <meta name="author" content="FreeHTML5.co" />

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
</html>