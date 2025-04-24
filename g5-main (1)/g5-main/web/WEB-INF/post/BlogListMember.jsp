<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="currentPage" value="${currentPage}" />
<c:set var="totalPages" value="${totalPages}" />
<%--<%@ include file="/WEB-INF/home/sidebar.jsp" %>--%>


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
    margin: 40px auto; /* Căn giữa theo chiều ngang */
    width: 80%; /* Điều chỉnh chiều rộng để không quá lớn */
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
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1050; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                padding-top: 60px;

            }
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 40%; /* Thu nhỏ pop-up xuống 50% */
                height: 75%;
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
            body.modal-open {
                overflow: hidden; /* Chặn cuộn trang */
                pointer-events: none; /* Chặn bấm vào các phần tử khác */
            }

            .modal {
                pointer-events: auto; /* Nhưng vẫn cho phép tương tác với modal */
            }


        </style>
    </head>
    <body>

        <div class="container">
    <div id="successMessage" class="alert alert-success" style="display: none;">
        Project đã được thêm thành công!
    </div>

    <div class="table-wrapper">
        <div class="table-title">
            
<h2>
  <a href="home1" style="text-decoration: none; color: inherit;">
    <i class="fa fa-home"></i>
</a>
  <a href="BlogListMember" style="text-decoration: none; color: inherit;">
    List <b>Post</b>
  </a>
  
</h2>
            <a href="AddNewPostServlet" id="openAddAccountModal" class="btn btn-success">Add New Post</a>

            <div id="addAccountModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <iframe id="addAccountFrame" src="AddAccount.jsp" style="width: 100%; height: 500px; border: none;"></iframe>
                </div>
            </div>
        </div>
    </div>

    <!-- Form lọc + tìm kiếm -->
    <form id="filterForm" method="get" action="BlogListMemberServlet">
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
                    <option value="Published" <c:if test="${selectedStatus == 'Published'}">selected</c:if>>Published</option>
                    <option value="Unpublished" <c:if test="${selectedStatus == 'Unpublished'}">selected</c:if>>Unpublished</option>
                    <option value="Draft" <c:if test="${selectedStatus == 'Draft'}">selected</c:if>>Draft</option>
                </select>
            </div>

            <!-- Thanh tìm kiếm -->
            <div class="col-md-3">
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" placeholder="Enter name or email" value="${keyword}">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
                     <div class="col-md-1">
 <select name="sort" onchange="this.form.submit()">
        <option value="">-- Sort --</option>
        <option value="title_asc" ${sort == 'name_asc' ? 'selected' : ''}>Tên A-Z</option>
        <option value="title_desc" ${sort == 'name_desc' ? 'selected' : ''}>Tên Z-A</option>
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
                <th>Image</th>
                <th>ClubName</th>
                <th>Post at</th>
                <th>Status</th>
                <th>Views</th>
                <th>Name Creater</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${e}" var="o">
                <tr>
                    <td>${o.post_id}</td>
                    <td>${o.title}</td>
                    <td><img src="${o.image_url}" alt="Post Image"></td>
                    <td>${o.nameclub}</td>
                    <td>${o.post_at}</td>
                    <td>${o.status}</td>
                    <td>${o.view}</td>
                    <td>${o.namecreated}</td>
                    <td>
                    <a href="DetailPost?post_id=${o.post_id}" class="btn btn-primary">Read More</a>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

 <c:choose>
    <c:when test="${empty param.clubId and empty param.status and empty param.keyword and empty param.sort}">
        <c:set var="baseUrl" value="BlogListMember" />
    </c:when>
    <c:otherwise>
        <c:set var="baseUrl" value="BlogListMemberServlet" />
    </c:otherwise>
</c:choose>

<form>
    <ul class="paginations justify-content-center" style="display: flex; list-style: none;">
        <!-- Nút Previous -->
        <c:if test="${currentPage > 1}">
            <li class="page-items">
                <c:url var="prevUrl" value="${baseUrl}">
                    <c:param name="page" value="${currentPage - 1}" />
                    <c:if test="${not empty param.clubId}">
                        <c:param name="clubId" value="${param.clubId}" />
                    </c:if>
                    <c:if test="${not empty param.status}">
                        <c:param name="status" value="${param.status}" />
                    </c:if>
                    <c:if test="${not empty param.keyword}">
                        <c:param name="keyword" value="${param.keyword}" />
                    </c:if>
                    <c:if test="${not empty param.sort}">
                        <c:param name="sort" value="${param.sort}" />
                    </c:if>
                </c:url>
                <a class="page-links" href="${prevUrl}" style="border: 1px solid grey; padding: 10px">Previous</a>
            </li>
        </c:if>

        <!-- Các số trang -->
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:url var="pageUrl" value="${baseUrl}">
                <c:param name="page" value="${i}" />
                <c:if test="${not empty param.clubId}">
                    <c:param name="clubId" value="${param.clubId}" />
                </c:if>
                <c:if test="${not empty param.status}">
                    <c:param name="status" value="${param.status}" />
                </c:if>
                <c:if test="${not empty param.keyword}">
                    <c:param name="keyword" value="${param.keyword}" />
                </c:if>
                <c:if test="${not empty param.sort}">
                    <c:param name="sort" value="${param.sort}" />
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

        <!-- Nút Next -->
        <c:if test="${currentPage < totalPages}">
            <li class="page-items">
                <c:url var="nextUrl" value="${baseUrl}">
                    <c:param name="page" value="${currentPage + 1}" />
                    <c:if test="${not empty param.clubId}">
                        <c:param name="clubId" value="${param.clubId}" />
                    </c:if>
                    <c:if test="${not empty param.status}">
                        <c:param name="status" value="${param.status}" />
                    </c:if>
                    <c:if test="${not empty param.keyword}">
                        <c:param name="keyword" value="${param.keyword}" />
                    </c:if>
                    <c:if test="${not empty param.sort}">
                        <c:param name="sort" value="${param.sort}" />
                    </c:if>
                </c:url>
                <a class="page-links" href="${nextUrl}" style="border: 1px solid grey; padding: 10px">Next</a>
            </li>
        </c:if>
    </ul>
</form>
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
</html>
