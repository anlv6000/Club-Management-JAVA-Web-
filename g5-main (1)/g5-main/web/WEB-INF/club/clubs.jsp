<%@page import="java.util.List"%>
<%@page import="Model.Club"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

<!-- JS của Flatpickr -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<!-- Thêm vào trong scroll bar cho option -->
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<%@ include file="/WEB-INF/home/sidebar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <!--    <header>
                <div class="header-logo">
                    <img src="https://th.bing.com/th/id/OIP.HEjDxqON9OjorjiDpDhDPQHaFj?rs=1&pid=ImgDetMain" alt="alt" />
                    <h1>FPT Club Manager</h1>
                </div>
        
        <c:if test="${sessionScope.acc != null}">
           <div>${sessionScope.txtUsername}</div>
        </c:if>

        <div id="logo-container">
             Default logo 
            <img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo">
        </div>
    </header>-->
        <title>Club List</title>

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
            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px;
                background-color: #333;
                color: white;
            }

            header img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                cursor: pointer;
            }

            .header-logo {
                display: flex;
                align-items: center;
                gap: 10px; /* Thêm ?i?u ch?nh gap ?? logo không b? dính vào tiêu ?? */
            }

            .header-logo img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                cursor: pointer;
                object-fit: cover;
            }

            .header-logo h1 {
                margin: 0;
                font-size: 20px;
                line-height: 50px; /* ??m b?o c?n ch?nh v?i chi?u cao c?a hình ?nh */


            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: center;
            }
            .create-btn {
                float: right;
                margin-bottom: 10px;
                padding: 10px;
                border-radius: 5px;
            }

            .custom_row th{
                background-color: #486380;
                color: #f2f2f2;
            }

            .custome_column td{
                background-color: #F4F3F8;

            }
            .popup {
                display: none; /* Ẩn popup ban đầu */
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .popup-content {
                background: white;
                padding: 20px;
                border: 2px solid #333;
                border-radius: 10px;
                width: 600px;
                box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
                text-align: center;
                margin-top: 100px;
            }
            .popup input, textarea {
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }
            .popup select{
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .popup button {
                color: #28a745;
                border: none;
                padding: 10px;
                width: 100%;
                cursor: pointer;
                margin-top: 10px;
                border-radius: 5px;
            }
            .popup h2{
                display: block;
            }

            .popup button:hover, .back-btn:hover {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 10px;
                width: 100%;
                cursor: pointer;
                margin-top: 10px;
                border-radius: 5px;
            }
            .material-icons{
                color:#0044e0;
            }
            .main_content{
                margin-left: 250px;
                width: calc(100% - 250px);
                padding: 20px;
                box-sizing: border-box;
            }
            .pagination {
                margin-top: 20px;
                text-align: center;
            }
            .pagination a, .pagination .current{
                margin: 0 5px;
                padding: 5px 10px;
                border: 1px solid #333;
                text-decoration: none;
                color: #333;
            }
            .pagination .current {
                background-color: #333;
                color: white;
            }

            .col_2 {
                width: 48.5%;
                padding-left: 10px;
            }
            .col_3 {
                width: 48.5%;
                padding-left: 10px;
            }
            .col_1{
                width: 48.5%;
                padding-right: 10px;
            }
            .popup_main{
                display: flex;
            }


            .popup label{
                display:grid;
                justify-content: left;
            }
            .choose1_2{
                display: flex;
                align-items: center; /* Căn giữa theo chiều dọc */
                justify-content: center;
                margin-bottom: 20px;

            }
            .radio-option{
                display:flex;
                margin-right: 15px;

            }
            .radio-option label{
                display: inline-flex;
                align-items: center; /* Căn giữa nội dung theo chiều dọc */
                width: 60px; /* Bạn có thể điều chỉnh giá trị này để căn chỉnh tốt hơn */
                height: 30px; /* Bạn có thể thay đổi chiều cao nếu cần */
            }
            .status.active {
                color: #27ae60;
                font-weight: bold;
            }

            .status.deactive {
                color: #e74c3c;
                font-weight: bold;
            }
            .select2-results__options {
                max-height: 180px !important;
                overflow-y: auto !important;
            }
            .select2-container .select2-selection--single {
                margin: 10px 0;
                height: 38.96px;
            }

            .select2-container--default .select2-selection--single .select2-selection__rendered  {
                line-height: 38px; /* canh giữa nội dung */
            }
            .select2-container--default .select2-selection--single .select2-selection__arrow {
                height: 56px;
            }
        </style>
    </head>
    <%List<String> categories = (List<String>) request.getAttribute("categories");
  
    %>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <div>
                <h2>Admin</h2>

                <div class="menu-section">
                    <h4> Home </h4>
                    <a href="${pageContext.request.contextPath}/Public_ClubServlet">
                        <i class="fa-solid fa-house"></i> Dashboard
                    </a>
                    <h3>Manager</h3>
                    <a href="${pageContext.request.contextPath}/ClubServlet">
                        <i class="fa-solid fa-users"></i> Club
                    </a>
                    <a href="${pageContext.request.contextPath}/settings">
                        <i class="fa-solid fa-cog"></i> Settings
                    </a>
                    <a href="ManageAccount">
                        <i class="fa-solid fa-user"></i> Manage Account
                    </a>
                    <a href="${pageContext.request.contextPath}/listEvent">
                        <i class="fa-solid fa-calendar"></i> Event
                    </a>
                </div>
            </div>
        </div>

        <div class ="main_content">

            <h2>Club List</h2>

            <form id="searchForm" action="ClubServlet" method="get">
                <input type="text" name="search" placeholder="Search club..." 
                       value="${search}" 
                       />

                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
            </form>

            <script>
                let timeout = null;
                document.getElementById("searchInput").addEventListener("input", function () {
                    timeout = setTimeout(() => {
                        document.getElementById("searchForm").submit();
                    }, 1000);
                });

                window.onload = function () {
                    document.getElementById("searchInput").focus();
                    searchInput.focus();
                    searchInput.setSelectionRange(searchInput.value.length, searchInput.value.length);
                };
            </script>
            <button class="create-btn" onclick="openPopup()" style="cursor: pointer;">Create Club</button>

            <form id="filterForm" action="ClubServlet" method="get">
                <table>
                    <tr class="custom_row">
                        <th>Image</th>
                        <th>ID</th>
                        <th>Name</th>

                        <!-- Filter Category -->
                        <th>
                            <select name ="category"
                                    style="background-color: #486380; border: none; color: #f2f2f2;
                                    margin-top: 10px; font-size: 16px; font-family: Arial, sans-serif; font-weight: bold;"
                                    onchange="document.getElementById('filterForm').submit()">
                                <option value ="">Category</option>
                                <c:forEach var ="cat" items ="${categories}">

                                    <option value = "${cat}" ${cat == param.category ? 'selected' :''}>${cat}</option>
                                </c:forEach>
                            </select>
                        </th>

                        <th>Club Leader</th>

                        <!-- Filter Status -->
                        <th>
                            <select name ="status" 
                                    style="background-color: #486380; border: none; color: #f2f2f2;
                                    margin-top: 10px; font-size: 16px; font-family: Arial, sans-serif; font-weight: bold;"
                                    onchange="document.getElementById('filterForm').submit()">
                                <option value ="" style ="font-size: 16px; font-family: Arial, sans-serif;">Status</option>
                                <option value ="active" ${param.status  == 'active' ? 'selected' : ''}>Active</option>
                                <option value ="deactive" ${param.status == 'deactive' ? 'selected' : ''}>Deactive</option>
                            </select>
                        </th>

                        <th>Action</th>
                    </tr>

                    <c:forEach var="club" items="${clubs}">
                        <tr class="custome_column">
                            <td><img src="${club.imgURL}" width="50"></td>
                            <td>${club.clubID}</td>
                            <td>${club.clubName}</td>
                            <td>${club.category}</td>
                            <td><span style="font-weight: bold">${club.fullName}</span><br>
                                ${club.clubLeader}

                            </td>
                            <td class="status ${club.status == 'active' ? 'active' : 'deactive'}">
                                ${club.status == 'active' ? 'Active' : 'Deactive'}
                            </td>
                            <td>
                                <!-- Edit button -->
                                <a href="DetailClubServlet?clubID=${club.clubID}" title="Edit">
                                    <i class="material-icons">&#xE254;</i>
                                </a>

                                <!--                                    Deactive button-->
                                <button type="button" onclick="deactivateClub('${club.clubID}', '${club.status}')"
                                        style="color: ${club.status == 'deactive' ?'#27ae60' :'red'};
                                        background: none; border: none; cursor: pointer;">
                                    <i class="fa fa-power-off" style="font-size: 20px;" aria-hidden="true" title="${club.status == 'deactive'?'Activate':'Deactivate'}"></i>
                                </button>

                                <!--                                    Delete button -->
                                <button type="button" onclick="deleteClub('${club.clubID}')"
                                        style="color: red; background: none; border: none; cursor: pointer;">
                                    <i class="fa fa-trash" style="font-size: 20px;" aria-hidden="true" title="Delete"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty clubs}">
                        <tr>
                            <td colspan="7">No clubs found</td>
                        </tr>
                    </c:if>
                </table>
            </form>

            <!--            Popup create club-->
            <div id="popup" class="popup">
                <div class="popup-content">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" style="color: red">${error}</div>
                    </c:if>
                    <h2>Create New Club</h2>


                    <form action="CreateClubServlet" method="post" enctype ="multipart/form-data" style="margin-bottom: 100px">
                        <label>Name*</label> 
                        <input type="text" name="name" value ="${param.name}"style="width: 401.58px; display: block;" required placeholder="Enter Club name">


                        <div class ="popup_main">

                            <div class ="col_1">


                                <label>Category*</label> 
                                <select name ="category">
                                    <c:forEach var ="cat" items ="${categories}">

                                        <option value = "${cat}" ${cat == param.category ? 'selected' :''}>${cat}</option>
                                    </c:forEach>
                                </select>
                                <label>Gmail Infor</label>
                                <input type ="email" name ="contactClub" value ="${param.contactClub}" placeholder="Enter club Email" required> <br>

                                <div class="choose1_2">
                                    <div class="radio-option">
                                        <input type="radio" name="isPublic" value="Yes" id="isPublicYes"

                                               ${empty param.isPublic || param.isPublic == 'Yes' ? 'checked' : ''}>
                                        <label for="isPublicYes" style="color: #27ae60">Public</label>
                                    </div>
                                    <div class="radio-option">
                                        <input type="radio" name="isPublic" value="No" id="isPublicNo"
                                               ${param.isPublic == 'No' ? 'checked' : ''}>
                                        <label for="isPublicNo" style="#e74c3c">Private</label>
                                    </div>
                                </div>



                            </div>

                            <div class ="col_2">
                                <label>Club Leader*</label>

                                <select name="leader" class="dropdown-scroll" style="width: 190px" >
                                    <c:forEach var="user" items="${users}">
                                        <option value="${user.id}" ${user == param.leader ? 'selected' : ''}>
                                            ${user.fullname} - ${user.userName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <script>
                                    $(document).ready(function () {
                                        $('.dropdown-scroll').select2({
                                            dropdownCssClass: "custom-scroll"
                                        });
                                    });
                                </script>                
                                <label>Schedule</label>  
                                <select id="day">
                                    <option value="Monday">Monday</option>
                                    <option value="Tuesday">Tuesday</option>
                                    <option value="Wednesday">Wednesday</option>
                                    <option value="Thursday">Thursday</option>
                                    <option value="Friday">Friday</option>
                                    <option value="Saturday">Saturday</option>
                                    <option value="Sunday">Sunday</option>
                                </select>
                                <div style="display: flex; width: 100%; gap:10px">
                                    <input id="startTime" type="text" placeholder="From">
                                    <input id="endTime" type="text" placeholder="To">
                                </div>
                                <button type="button" onclick="addSchedule()">Add Schedule</button>

                                <input type="text" value ="${param.schedule}"name="schedule" id="schedule" >



                                <script>
                                    let schedules = [];

                                    function addSchedule() {
                                        let day = document.getElementById("day").value;
                                        let startTime = document.getElementById("startTime").value;
                                        let endTime = document.getElementById("endTime").value;

                                        // Kiểm tra đã có ngày này trong lịch chưa
                                        if (schedules.some(s => s.startsWith(day + ":"))) {
                                            alert("Ngày này đã được chọn. Vui lòng chọn ngày khác.");
                                            return;
                                        }

                                        // Kiểm tra định dạng và logic thời gian
                                        if (startTime && endTime) {
                                            const [startHour, startMin] = startTime.split(":").map(Number);
                                            const [endHour, endMin] = endTime.split(":").map(Number);

                                            const start = startHour * 60 + startMin;
                                            const end = endHour * 60 + endMin;

                                            if (start >= end) {
                                                alert("Start time phải nhỏ hơn End time.");
                                                return;
                                            }

                                            let scheduleItem = day + ": " + startTime + " - " + endTime;
                                            schedules.push(scheduleItem);

                                            // Cập nhật ô input schedule
                                            document.getElementById("schedule").value = schedules.join("; ");
                                        }
                                    }

                                    flatpickr("#startTime", {
                                        enableTime: true,
                                        noCalendar: true,
                                        dateFormat: "H:i",
                                        time_24hr: true
                                    });

                                    flatpickr("#endTime", {
                                        enableTime: true,
                                        noCalendar: true,
                                        dateFormat: "H:i",
                                        time_24hr: true
                                    });
                                </script>

                            </div> 
                            <div class ="col_3">
                                <div id="imageFileInput">
                                    <label>Upload Image:</label>
                                    <input type="file" name="imgFile" accept="image/*" id="fileInput">
                                    <br>
                                    <img id="previewImage" style="max-width: 100%;max-height: 205px; display: none; margin-top: 10px;">
                                </div>

                                <script>
                                    document.getElementById("fileInput").addEventListener("change", function (event) {
                                        const file = event.target.files[0];
                                        if (file) {
                                            const reader = new FileReader();
                                            reader.onload = function (e) {
                                                const img = document.getElementById("previewImage");
                                                img.src = e.target.result;
                                                img.style.display = "block";
                                            };
                                            reader.readAsDataURL(file);
                                        }
                                    });
                                </script>                     


                            </div>
                        </div>
                        <label>Description</label> 
                        <textarea name="description" value ="${param.description}" placeholder="Enter your club description" style="height: 100px;"></textarea><br>
                        <button class="back-btn" onclick="closePopup()">Cancel</button>
                        <button type="submit" onclick="sessionStorage.setItem('fromCreate', 'true')">Create</button>

                    </form>

                </div>
            </div>



            <c:if test="${not empty sessionScope.clubMessage}">
                <div id="success-message"
                     style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                     background: green; color: white; padding: 10px 20px;
                     border-radius: 5px; z-index: 1000;">
                    ${sessionScope.clubMessage}
                </div>
                <c:remove var="clubMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.deleteMessage}">
                <div id="success-message"
                     style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                     background: red; color: white; padding: 10px 20px;
                     border-radius: 5px; z-index: 1000;">
                    ${sessionScope.deleteMessage}
                </div>
                <c:remove var="deleteMessage" scope="session"/>
            </c:if>

            <c:if test="${not empty sessionScope.deactiveMessage}">
                <div id="success-message"c
                     style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                     background: red; color: white; padding: 10px 20px;
                     border-radius: 5px; z-index: 1000;">
                    ${sessionScope.deactiveMessage}
                </div>
                <c:remove var="deactiveMessage" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.activeMessage}">
                <div id="success-message"
                     style="position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
                     background: green; color: white; padding: 10px 20px;
                     border-radius: 5px; z-index: 1000;">
                    ${sessionScope.activeMessage}
                </div>
                <c:remove var="activeMessage" scope="session"/>
            </c:if>


            <script>
                // Hàm mở popup
                function openPopup() {
                    document.getElementById('popup').style.display = 'flex';
                }



                window.onload = function () {
                    var error = "<c:out value='${empty error ? "" : error}' />";
                    var fromCreate = sessionStorage.getItem('fromCreate');

                    if (error !== "" && fromCreate === 'true') {
                        document.getElementById("popup").style.display = "flex";
                    }

                    // Sau khi xử lý xong thì xóa cờ để không hiện lại khi reload nữa
                    sessionStorage.removeItem('fromCreate');
                };


                function closePopup() {
                    popupClosed = true;
                    document.getElementById('popup').style.display = 'none';
                    document.getElementById('category').value = "";
                    document.getElementById('status').value = "";

                }
                setTimeout(() => {
                    document.getElementById("success-message").style.display = "none";
                }, 3000);

                function deactivateClub(clubID, status) {
                    if (status === 'deactive') {
                        if (confirm("This club is currently deactivated. Do you want to activate it?")) {
                            let form = document.createElement('form');
                            form.method = 'post';
                            form.action = 'ActivateClubServlet'; // Servlet dùng để kích hoạt
                            let input = document.createElement('input');
                            input.type = 'hidden';
                            input.name = 'clubID';
                            input.value = clubID;
                            form.appendChild(input);
                            document.body.appendChild(form);
                            form.submit();
                        }
                    } else if (confirm("Are you sure you want to deactivate this club?")) {
                        let form = document.createElement('form');
                        form.method = 'post';
                        form.action = 'DeactiveClubServlet';
                        let input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'clubID';
                        input.value = clubID;
                        form.appendChild(input);
                        document.body.appendChild(form);
                        form.submit();
                    }
                }

                function deleteClub(clubID) {
                    if (confirm("Are you sure you want to delete this club?")) {
                        let form = document.createElement('form');
                        form.method = 'post';
                        form.action = 'DeleteClubServlet';
                        let input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'clubID';
                        input.value = clubID;
                        form.appendChild(input);
                        document.body.appendChild(form);
                        form.submit();
                    }
                }

                
            </script>

            <div class="pagination">
                <!-- Trang đầu-->
                <c:if test="${currentPage > 2}">
                    <a href="ClubServlet?page=1&search=${search}&category=${category}&status=${status}">1</a>
                </c:if>
                <!--                Trang trước-->
                <c:if test="${currentPage > 1}">
                    <a href="ClubServlet?page=${currentPage - 1}&search=${search}&category=${category}&status=${status}">${currentPage -1}</a>
                </c:if>
                <!--                    Trang hiện tại-->
                <span class="current">${currentPage}</span>
                <!--                Trang tiếp-->
                <c:if test="${currentPage < totalPages -1}">
                    <a href="ClubServlet?page=${currentPage + 1}&search=${search}&category=${category}&status=${status}">${currentPage + 1}</a>
                </c:if>
                <!--Trang cuối-->
                <c:if test="${currentPage < totalPages}">
                    <a href="ClubServlet?page=${totalPages}&search=${search}&category=${category}&status=${status}">${totalPages}</a>
                </c:if>
            </div>
        </div>
</html>
