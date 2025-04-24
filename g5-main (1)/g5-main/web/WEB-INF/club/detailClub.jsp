<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="Model.Club"%>
<%@page import="DAO.ClubDAO"%>
<%@ include file="/WEB-INF/home/sidebar.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<!-- Flatpickr CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<!-- Flatpickr JS -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- Plugin chọn ngày trong tuần -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/plugins/weekdayPlugin.js"></script>

<% 
    String clubID = (String) request.getAttribute("clubID");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Club Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .form-container {
                width: 700px;
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                margin-bottom: 20px;
            }
            .form-row {
                display: flex;
                justify-content: space-between;
                margin-bottom: 15px;
            }
            .col_2_3 {
                width: 65%;
            }
            .col_1_3 {
                width: 30%;
                display: flex;
                justify-content: center;
            }
            .image-container img {
                width: 100%;
                width: 200px;
                height: 200px;
                object-fit: cover;
                border-radius: 10px;
                border: 1px solid #ddd;
            }
            label {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
            }
            input[type="text"], textarea {
                width: 100%;
                padding: 8px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            textarea {
                height: 100px;
            }
            .choose1_2 {
                display: flex;
                align-items: center;
                gap: 10px;
            }
            button {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #007bff;
                color: white;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
            }
            .category-leader {
                display:flex;
                gap: 15%;
            }
            .category-leader div {
                width: 50%;
            }
            input[type="radio"]{
                cursor: pointer;
            }
            input{
                width: 100%;
                padding: 8px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
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
        <c:if test="${not empty club}">
            <div class="form-container">
                <form action="UpdateClubServlet" method="Post" enctype="multipart/form-data">

                    <h2>Club Details</h2>

                    <div class="form-row">
                        <div class="col_2_3">
                            <div style="display: flex;">
                                <div>
                            <label>ClubID*</label>
                            <input type="text" style="background-color: #ccc"name="clubID" value="${club.clubID}" readonly>
                            </div>
                            <div style="margin-left: 70px; width: 191.82px">
                            <label>Name*</label>
                            <input type="text" name="name" value="${club.clubName}" required>
                            </div>
                            </div>
                            <div class="category-leader">
                                <div>
                                    <label>Category*</label>
                                    
                                    <select name="category" style="width: 191.82px; height: 38px;border-radius: 5px;margin-top: 10px; border-color: #ccc">
                                        <c:forEach var ="cat" items ="${categories}">
                                    <option value = "${club.category}" ${cat == club.category ? 'selected' :''}>${cat}</option>
                                </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label>Club Leader*</label>

                                    <select name="leaderID" class="dropdown-scroll" style="width: 100%">
                                        <c:forEach var="user" items="${users}">
                                            <option value="${user.id}" ${user.id == club.leaderID ? 'selected' : ''}>
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
                                </div>
                            </div>
                        </div>
                        <div class="col_1_3">
                            <div class="image-container">
                                <img src="${club.imgURL}" alt="Club Image">
                            </div>
                        </div>
                    </div>
                    <div class="form-row" style="display: flex;">
                        <div class="choose1_2">
                            <input type="radio" name="isPublic" value="Yes" ${club.isPublic eq 'Yes' ? 'checked' : ''}>
                            <label>Public</label>
                            <input type="radio" name="isPublic" value="No" ${club.isPublic eq 'No' ? 'checked' : ''}>
                            <label>Private</label>
                        </div>


                       

                    </div>
                    <div style="display: flex;">

                        <div class ="col_1_2"style="width: 455px;display: flex">
                            <div id="imageFileInput">
                                <!--                            Use old image when not upload new-->
                                <input type="hidden" name="oldImgURL" value="${club.imgURL}" />
                                <label>Upload Image:</label>
                                <input type="file" name="imgFile" accept="image/*" id="fileInput" >
                            </div>
                        

                        <div class = "col_2_2"style="margin-left: 30px; width:330px">
                            <label>Contact</label>
                            <input type="text" name="contactClub" value="${club.contactClub}" required>
                        </div>
                        </div>
                    </div>
                    <div id="schedule-container">
                        
                        <div style="display: flex;">
                            <div style="display: flex; max-width: 455px; width: 100%" class="col_2_3">
                            <div >
                                <select id="day" style="height: 33.86px; border: 1px solid #ccc;    border-radius: 5px;margin-top: 10px;width: 100px">
                                    <option value="Monday">Monday</option>
                                    <option value="Tuesday">Tuesday</option>
                                    <option value="Wednesday">Wednesday</option>
                                    <option value="Thursday">Thursday</option>
                                    <option value="Friday">Friday</option>
                                    <option value="Saturday">Saturday</option>
                                    <option value="Sunday">Sunday</option>
                                </select>
                            </div>
                            <div>
                                <input id="startTime" type="text" placeholder="From" style="margin-left: 18px;width: 100px">
                            </div>
                            <div>
                                <input id="endTime" type="text" placeholder="To" style="margin-left: 18px; width: 100px">
                            </div>
                                <div>
                                <button type="button" style="margin-left: 18px; width: 100px; height: 33.86px;font-size: 10px" onclick="addSchedule()">Add Schedule</button>
                            </div>
                                </div>
                            
                        </div>


                        

                        <input type="text" style="width: 455px;" value = "${club.schedule}"name="schedule" id="schedule" placeholder="Schedule Time of Club per week">

                        <script>
                            let schedules = [];
                            // Lấy dữ liệu có sẵn trong input khi trang vừa load
    window.onload = function() {
        const existing = document.getElementById("schedule").value;
        if (existing.trim() !== "") {
            schedules = existing.split(";").map(item => item.trim());
        }
    }
                            function addSchedule() {
                                let day = document.getElementById("day").value;
                                let startTime = document.getElementById("startTime").value;
                                let endTime = document.getElementById("endTime").value;

                                if (startTime && endTime) {
                                    let scheduleItem = day + ": " + startTime + " - " + endTime;
                                    schedules.push(scheduleItem);

                                    // Cập nhật ô input schedule
                                    document.getElementById("schedule").value = schedules.join("; ");
                                }
                            }

                            flatpickr("#startTime", {
                                enableTime: true,
                                noCalendar: true,
                                dateFormat: "H:i", // 24h format
                                time_24hr: true
                            });

                            flatpickr("#endTime", {
                                enableTime: true,
                                noCalendar: true,
                                dateFormat: "H:i",
                                time_24hr: true
                            });
                        </script>




                        <label>Description:</label>
                        <textarea name="description">${club.description}</textarea>

                        <button type="submit">Update</button>
                </form>
            </div>
        </c:if>
        <c:if test="${empty club}">
            <p>Club not found!</p>
        </c:if>
        <c:if test="${not empty sessionScope.successMessage}">
            <div id="successMessage" style="
                 background-color: #d4edda;
                 color: #155724;
                 padding: 10px;
                 border-radius: 5px;
                 text-align: center;
                 position: fixed;
                 top: 10px;
                 left: 50%;
                 transform: translateX(-50%);
                 box-shadow: 0 0 10px rgba(0,0,0,0.1);
                 ">
                ${sessionScope.successMessage}
            </div>

            <script>
                setTimeout(function () {
                    document.getElementById('successMessage').style.display = 'none';
                }, 3000); // Tự động ẩn sau 3 giây
            </script>

            <%-- Xóa thông báo sau khi hiển thị để tránh lặp lại khi tải lại trang --%>
            <c:remove var="successMessage" scope="session"/>
        </c:if>


        <script>
            function toggleImageInput() {
                const urlInput = document.getElementById('imageURLInput');
                const fileInput = document.getElementById('imageFileInput');
                if (document.getElementById('imageURL').checked) {
                    urlInput.style.display = 'block';
                    fileInput.style.display = 'none';
                } else {
                    urlInput.style.display = 'none';
                    fileInput.style.display = 'block';
                }
            }


        </script>
    </body>
</html>
