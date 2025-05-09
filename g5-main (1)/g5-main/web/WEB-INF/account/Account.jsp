<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="currentPage" value="${currentPage}" />
<c:set var="totalPages" value="${totalPages}" />
<%@ include file="/WEB-INF/home/sidebar.jsp" %>


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
        <link href="css/manager.css" rel="stylesheet" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <style>
            body {
                position: relative;
                background-image: url('https://daihoc.fpt.edu.vn/en/wp-content/uploads/2024/09/DHFPT6-1.webp');
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

            .container {
                margin-left: 250px; /* Điều chỉnh khoảng cách theo ý muốn */
                margin-top: 40px;
                background: #ffffff; /* Nền trắng */
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

            /* Điều chỉnh input và select cho form */
            input, select {
                width: 100%;
                padding: 10px;
                margin-bottom: 12px;
                border: 1px solid #d1d5db;
                border-radius: 8px;
                font-size: 14px;
                background-color: #f9fafb;
            }

            /* Nút Add Account */
            .btn {
                background-color: #4c6ef5;
                color: #fff;
                border: none;
                padding: 12px 16px;
                border-radius: 8px;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.2s ease-in-out;
            }

            .btn:hover {
                background-color: #3b5bdb;
            }

            /* Success Message */
            .success-message {
                color: #2dca73;
                background-color: #e6fffa;
                padding: 10px;
                border: 1px solid #2dca73;
                border-radius: 8px;
                margin-bottom: 12px;
            }

            /* Error Message */
            .error-message {
                color: #ff4d4f;
                font-size: 12px;
                margin-top: 4px;
            }

            /* Tùy chỉnh cho màn hình nhỏ hơn */



        </style>
    </head>
    <body>
        <div class="container">
            <div id="successMessage" class="alert alert-success" style="display: none;">
                Tài khoản đã được thêm thành công!
            </div>
           <c:if test="${not empty sessionScope.successMessage}">
    <div class="alert alert-success" id="alertMessage">
        ${sessionScope.successMessage}
    </div>
    <c:remove var="successMessage" scope="session"/>
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
            <div class="table-wrapper">
                <div class="table-title">
                    <h2>Manage <b>Account</b></h2>
                    <!-- Nút Add New Account -->
                    <a href="#" id="openAddAccountModal" class="btn btn-success">Add New Account</a>

                    <div id="addAccountModal" class="modal">
                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <div id="addAccountContent">
                                <iframe id="addAccountFrame" src="AddAccount.jsp" style="width: 100%; height: 500px; border: none;"></iframe>
                            </div>
                        </div>


                    </div>
                </div>
                <div class="form-container">
                    <form method="get" action="ManageAccount">
                        <div class="row search-container">
                            <div class="col-md-3">
                                <select id="roleDropdown" name="role" class="form-select" onchange="submitSearchForm()">
                                    <option value="All">All role</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role}" ${param.role eq role ? 'selected' : ''}>${role}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <select id="statusDropdown" name="status" class="form-select" onchange="submitSearchForm()">
                                    <option value="All">All status</option>
                                    <c:forEach var="status" items="${statuses}">
                                        <option value="${status}" ${param.status eq status ? 'selected' : ''}>${status}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input type="text" id="keyword" name="keyword" class="form-control" placeholder="Enter name or email">
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

                <table class="table table-striped">
                    <thead>
                        <tr>
                    <thead>
                        <tr>
                            <th scope="col">Avatar</th>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">FullName</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col">Status</th>

                            <th scope="col">Action</th>
                        </tr>
                    </thead>



                    <tbody>
                        <c:forEach items="${accountlist}" var="o">
                            <tr>
                                <td>
                                    <img src="${not empty o.imageURL ? o.imageURL : 'https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg'}" alt="Avatar" />
                                </td>

                                <td>${o.id}</td>
                                <td>${o.userName}</td>
                                <td>${o.fullname}</td>
                                <td>${o.email}</td>
                                <td>${o.role}</td>
                                <td class="${o.status == 'Active' ? 'status-active' : 'status-inactive'}">${o.status}</td>

                                <td>

                                    <a href="DisplayAccount?accountId=${o.id}" class="edit">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Phân trang -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="ManageAccount?page=${currentPage - 1}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="ManageAccount?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="ManageAccount?page=${currentPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div>

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
            function submitSearchForm() {
                document.forms[0].submit();
            }
        </script>


    </body>
</html>

