<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--header-->
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Club Manager</title>
        <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
            />
        <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/img/kaiadmin/favicon.ico"
            type="image/x-icon"
            />
        <style>
            .team-person img {
                width: 100%; /* Kích thước theo chiều ngang của phần tử cha */
                height: 200px; /* Đặt chiều cao cố định */
                object-fit: cover; /* Giúp ảnh không bị méo, giữ tỉ lệ khung hình */
            }
        </style>
        <!-- Fonts and icons -->
        <script src="${pageContext.request.contextPath}/assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {families: ["Public Sans:300,400,500,600,700"]},
                custom: {
                    families: [
                        "Font Awesome 5 Solid",
                        "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
                        "simple-line-icons",
                    ],
                    urls: ["assets/css/fonts.min.css"],
                },
                active: function () {
                    sessionStorage.fonts = true;
                },
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/kaiadmin.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/toast.css" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css" />

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tiny-slider/2.9.4/tiny-slider.css" />

        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico.png">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- simplebar -->
        <link href="${pageContext.request.contextPath}/assets/css/simplebar.css" rel="stylesheet" type="text/css" />
        <!-- Select2 -->
        <link href="${pageContext.request.contextPath}/assets/css/select2.min.css" rel="stylesheet" />
        <!-- Date picker -->
        <link rel="stylesheet" href="../assets/css/flatpickr.min.css">
        <link href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="${pageContext.request.contextPath}/assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
    </head>

    <body>

        <div class="wrapper">
            <!-- Sidebar -->

            <!-- End Sidebar -->
            <div class="main-panel">
                <div class="header-bar d-flex justify-content-between border-bottom">
                    <div class="d-flex align-items-center">
                    </div>
                    <!-- header menu -->
                    <ul class="list-unstyled mb-0">
                        

                        <li class="list-inline-item mb-0 ms-1">
                            <div class="dropdown dropdown-primary">
                                <button type="button" class="btn btn-pills btn-soft-primary dropdown-toggle p-0" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${member.imageURL}" class="avatar avatar-ex-small rounded-circle" alt=""></button>
                                <div class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3" style="min-width: 200px;">
                                    <a class="dropdown-item d-flex align-items-center text-dark" href="https://shreethemes.in/doctris/layouts/admin/profile.html">
                                        <img src="${member.imageURL}" class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                        <div class="flex-1 ms-2">
                                            <span class="d-block mb-1">${member.userName}</span>
                                            <small class="text-muted">${setting.userType}</small>
                                        </div>
                                    </a>

                            </div>
                        </li>
                    </ul>
                </div>

                <div class="container">
                    <div class="d-md-flex justify-content-between">
                        <h5 class="mb-0">Member Detail & Settings</h5>
                        <nav aria-label="breadcrumb" class="d-inline-block mt-4 mt-sm-0">
                            <ul class="breadcrumb bg-transparent rounded mb-0 p-0">
                                
                                <li class="breadcrumb-item"><a href="ClubMember">Member</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Detail</li>
                            </ul>
                        </nav>
                    </div>
                    <div class="card bg-white rounded shadow overflow-hidden mt-4 border-0">
                        <div class="p-5 bg-primary bg-gradient"  style="background-color: #000000b3 !important;"></div>
                        <!--header name card-->
                        <div class="avatar-profile d-flex margin-nagative mt-n5 position-relative ps-3">
                            <img src="${member.imageURL}" class="rounded-circle shadow-md avatar avatar-medium" alt="">
                            <div class="mt-4 ms-3 pt-3">
                                <h5 class="mt-3 mb-1">${member.fullname}</h5>
                                <p class="text-muted mb-0">${member.role}</p>
                            </div>
                        </div>
                        <!--end header name card-->

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="card border-0 rounded-0 p-4">
                                    <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded shadow overflow-hidden bg-light"
                                        id="pills-tab" role="tablist">


                                        <li class="nav-item">
                                            <a class="nav-link rounded-0 shadow-none  border-0" id="experience-tab" data-bs-toggle="pill"  style="background-color: #000000b3 !important;"
                                               href="#pills-experience" role="tab" aria-controls="pills-experience"
                                               aria-selected="false">
                                                <div class="text-center pt-1 pb-1">
                                                    <h4 class="title fw-normal mb-0" style="color: #ffffff !important; transition: color 0.3s ease;">Post List</h4>
                                                </div>
                                            </a><!--end nav link-->
                                        </li><!--end nav item-->

                                        <li class="nav-item">
                                            <a class="nav-link rounded-0 shadow-none  border-0" id="review-tab" data-bs-toggle="pill"  style="background-color: #000000b3 !important;"
                                               href="#pills-review" role="tab" aria-controls="pills-review"
                                               aria-selected="false">
                                                <div class="text-center pt-1 pb-1">
                                                    <h4 class="title fw-normal mb-0" style="color: #ffffff !important; transition: color 0.3s ease;">Task List</h4>
                                                </div>
                                            </a><!--end nav link-->
                                        </li><!--end nav item-->

                                        <li class="nav-item">
                                            <a class="nav-link rounded-0 shadow-none  border-0" id="timetable-tab" data-bs-toggle="pill"  style="background-color: #000000b3 !important;"
                                               href="#pills-timetable" role="tab" aria-controls="pills-timetable"
                                               aria-selected="false">
                                                <div class="text-center pt-1 pb-1">
                                                    <h4 class="title fw-normal mb-0" style="color: #ffffff !important; transition: color 0.3s ease;">Contact</h4>
                                                </div>
                                            </a><!--end nav link-->
                                        </li><!--end nav item-->

                                        <c:if test="${userRole eq 'ClubLeader'}">
                                            <li class="nav-item">
                                                <a class="nav-link rounded-0 shadow-none border-0" id="settings-tab" data-bs-toggle="pill"  
                                                   style="background-color: #000000b3 !important;" href="#pills-settings" role="tab" 
                                                   aria-controls="pills-settings" aria-selected="false">
                                                    <div class="text-center pt-1 pb-1">
                                                        <h4 class="title fw-normal mb-0" style="color: #ffffff !important; transition: color 0.3s ease;">Update</h4>
                                                    </div>
                                                </a><!--end nav link-->
                                            </li><!--end nav item-->
                                        </c:if>
                                    </ul><!--end nav pills-->

                                    <div class="tab-content mt-2" id="pills-tabContent">


                                        <!-- ✅ Tab Posts (Hiển thị dạng slide) -->
                                        <div class="tab-pane fade show active" id="pills-experience" role="tabpanel"
                                             aria-labelledby="overview-tab">                                            <div class="row justify-content-center">
                                                <div class="col-lg-8 text-center">
                                                    <div class="client-review-slider">
                                                        <c:forEach var="post" items="${posts}">
                                                            <div class="tiny-slide text-center">
                                                                <!-- Nội dung bài viết -->
                                                                <p class="text-muted h6 fw-normal fst-italic">
                                                                    "${post.description}"
                                                                </p>

                                                                <!-- Hình ảnh đại diện -->
                                                                <img src="${post.image_url}"
                                                                     class="img-fluid avatar avatar-small rounded-circle mx-auto shadow my-3"
                                                                     alt="Post Image">

                                                                <!-- Tiêu đề & Ngày đăng -->
                                                                <h6 class="text-primary">
                                                                    ${post.title}
                                                                    <span class="badge ${post.status eq 'Published' ? 'bg-success' : 'bg-secondary'} ms-2">
                                                                        ${post.status}
                                                                    </span>
                                                                </h6>

                                                                <!-- Ngày đăng -->
                                                                <p class="text-muted">
                                                                    <i class="ri-calendar-line"></i> Posted on: ${post.post_at}
                                                                </p>
                                                            </div><!-- end tiny-slide -->
                                                        </c:forEach>
                                                    </div><!--end client-review-slider-->
                                                </div><!--end col-->
                                                <h6 class="mb-0">Other Member: </h6>
                                                <div class="row row-cols-md-2 row-cols-lg-5">
                                                    <c:forEach var="member" items="${randomMembers}">
                                                        <div class="col mt-4">
                                                            <div class="card team border-0 rounded shadow overflow-hidden">
                                                                <div class="team-person position-relative overflow-hidden">
                                                                    <img src="${member.imageURL}"class="team-person" alt="Profile Picture">                                                          </div>
                                                                <div class="card-body">
                                                                    <!-- Tên thành viên -->
                                                                    <a href="MemberOfficialDetail?userID=${member.userID}" class="title text-dark h5 d-block mb-1">
                                                                        ${member.fullname}
                                                                    </a>

                                                                    <!-- Gender & Role -->
                                                                    <div class="d-flex align-items-center mt-2">
                                                                        <!-- Gender sát trái -->

                                                                        <!-- Role tự dài ra -->
                                                                        <span class="badge bg-secondary flex-grow-1">
                                                                            ${member.role}
                                                                        </span>
                                                                    </div>

                                                                    <ul class="list-unstyled mt-3 mb-0">
                                                                        <!-- Status -->
                                                                        <li class="d-flex align-items-center">
                                                                            <i class="ri-check-double-line text-success"></i>
                                                                            <small class="text-muted ms-2">${member.status}</small>
                                                                        </li>



                                                                        <!-- Email -->
                                                                        <li class="d-flex align-items-center mt-2">
                                                                            <i class="ri-mail-line text-primary"></i>
                                                                            <a href="mailto:${member.email}" class="text-muted ms-2">${member.email}</a>
                                                                        </li>
                                                                    </ul>

                                                                    <!-- Nút Xem Chi Tiết -->
                                                                    <a href="MemberOfficialDetail?userID=${member.userID}" class="btn btn-outline-primary btn-sm mt-3">
                                                                        <i class="ri-user-line"></i> View Detail
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div><!--end col-->
                                                    </c:forEach>
                                                </div><!--end row-->
                                            </div><!--end row-->
                                        </div><!--end tab-pane-->

                                        <!-- ✅ Tab Tasks (Hiển thị dạng danh sách như bài viết trước đây) -->
                                        <div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="review-tab">
                                            <h5 class="mb-3 fw-bold">Task List</h5>

                                            <div id="task-list-container"> <!-- ✅ Bọc toàn bộ danh sách task -->
                                                <c:forEach var="task" items="${tasks}">
                                                    <div class="task p-3 mb-3 border rounded shadow-sm bg-light"> <!-- ✅ Thêm class task -->
                                                        <!-- Task Title -->
                                                        <h4 class="text-primary fw-bold mb-2">${task.title}</h4>

                                                        <!-- Deadline & Status -->
                                                        <p class="text-muted mb-3">
                                                            <i class="ri-calendar-line"></i> <strong>Deadline:</strong> ${task.deadline}
                                                            <span class="badge ${task.status eq 'Completed' ? 'bg-success' : 'bg-warning'} ms-3">
                                                                ${task.status}
                                                            </span>
                                                        </p>

                                                        <!-- Assigner & Assignee Info -->
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <p class="text-secondary fw-bold">Assigned by:</p>
                                                                <p class="mb-1"><strong>Name:</strong> ${task.assignerName}</p>
                                                                <p class="mb-1"><strong>Email:</strong> ${task.assignerEmail}</p>

                                                            </div>
                                                            <div class="col-md-6">
                                                                <p class="text-secondary fw-bold">Assigned to:</p>
                                                                <p class="mb-1"><strong>Name:</strong> ${task.assigneeName}</p>
                                                                <p class="mb-1"><strong>Email:</strong> ${task.assigneeEmail}</p>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>

                                            <div id="task-pagination-container" class="mt-4"></div> <!-- ✅ Đặt pagination đúng chỗ -->
                                        </div> <!--end tab-pane-->

                                        <div class="tab-pane fade" id="pills-timetable" role="tabpanel"
                                             aria-labelledby="timetable-tab">
                                            <div class="row">
                                                <div class="col-lg-1 col-md-12">

                                                </div><!--end col-->
                                                <div class="col-lg-4 col-md-6 mt-4 mt-lg-0 pt-2 pt-lg-0">
                                                    <div class="card border-0 text-center features feature-primary">
                                                        <div class="icon text-center mx-auto rounded-md">
                                                            <i class="uil uil-phone h3 mb-0"></i>
                                                        </div>

                                                        <div class="card-body p-0 mt-4">
                                                            <h5 class="title fw-bold">Phone</h5>
                                                            <a href="tel:+152534-468-854" class="link">${member.phone}</a>
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                                <div class="col-lg-2 col-md-12">

                                                </div><!--end col-->

                                                <div class="col-lg-4 col-md-6 mt-4 mt-lg-0 pt-2 pt-lg-0">
                                                    <div class="card border-0 text-center features feature-primary">
                                                        <div class="icon text-center mx-auto rounded-md">
                                                            <i class="uil uil-envelope h3 mb-0"></i>
                                                        </div>

                                                        <div class="card-body p-0 mt-4">
                                                            <h5 class="title fw-bold">Email</h5>
                                                            <a href="mailto:contact@example.com"
                                                               class="link">${member.email}</a>
                                                        </div>
                                                    </div>
                                                </div><!--end col-->
                                            </div><!--end row-->
                                        </div><!--end teb pane-->
                                        <c:if test="${userRole eq 'ClubLeader'}">
                                            <div class="tab-pane fade" id="pills-settings" role="tabpanel" aria-labelledby="settings-tab">
                                                <h5 class="mb-3">Update Member Information</h5>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="card shadow-sm">
                                                            <div class="card-header bg-primary text-white text-center">
                                                                <h6 class="mb-0">Personal Information</h6>
                                                            </div>

                                                            <div class="card-body">
                                                                <div class="text-center mb-4">
                                                                    <img src="${member.imageURL}" 
                                                                         class="avatar avatar-md-md rounded-pill shadow" alt="User Avatar">
                                                                </div>

                                                                <div class="row">
                                                                    <!-- Full Name -->
                                                                    <div class="col-md-6">
                                                                        <div class="mb-3">
                                                                            <label class="form-label">Full Name</label>
                                                                            <input type="text" class="form-control" value="${member.fullname}" readonly>
                                                                        </div>
                                                                    </div>


                                                                    <!-- Email -->
                                                                    <div class="col-md-6">
                                                                        <div class="mb-3">
                                                                            <label class="form-label">Email</label>
                                                                            <input type="email" class="form-control" value="${member.email}" readonly>
                                                                        </div>
                                                                    </div>

                                                                    <!-- Phone Number -->
                                                                    <div class="col-md-6">
                                                                        <div class="mb-3">
                                                                            <label class="form-label">Phone No.</label>
                                                                            <input type="text" class="form-control" value="${member.phone}" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div> <!-- End Card -->
                                                    </div> <!-- End col-lg-6 -->

                                                    <!-- Form Update Role & Status -->
                                                    <div class="col-lg-6">
                                                        <div class="card shadow-sm">
                                                            <div class="card-header bg-secondary text-white text-center">
                                                                <h6 class="mb-0">Update Role & Status</h6>
                                                            </div>

                                                            <div class="card-body">
                                                                <form method="post" action="MemberOfficialDetail">
                                                                    <input type="hidden" name="userID" value="${member.userID}">
                                                                    <!-- Role Filter -->
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Role</label>
                                                                        <select id="roleFilter" class="form-select" name="role">
                                                                            <option value="">Select role</option>
                                                                            <option value="ClubMember" ${member.role == 'ClubMember' ? 'selected' : ''}>Member</option>
                                                                            <option value="ClubLeader" ${member.role == 'ClubLeader' ? 'selected' : ''}>Leader</option>

                                                                        </select>
                                                                    </div>

                                                                    <!-- Status Filter -->
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Status</label>
                                                                        <select id="statusFilter" class="form-select" name="status">
                                                                            <option value="">Select status</option>
                                                                            <option value="Active" ${member.status == 'Active' ? 'selected' : ''}>Active</option>
                                                                            <option value="Inactive" ${member.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="mb-3">
                                                                        <label class="form-label">Description:</label>
                                                                        <input type="text" class="form-control" name="description" value="${member.description}" />
                                                                    </div>



                                                                    <!-- Submit Button -->
                                                                    <div class="text-center">
                                                                        <button type="submit" class="btn btn-success px-4">Save Changes</button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div> <!-- End Card -->
                                                    </div> <!-- End col-lg-6 -->
                                                </div> <!-- End row -->
                                            </div> <!-- End tab-pane -->
                                        </c:if>
                                    </div><!--end col-->
                                </div><!--end row-->
                            </div>
                        </div>
                    </div>

                </div>
            </div>




            <!--JS to seach ajax-->
            <script>
                let currentPage = 1;
                const pageSize = 10;

                function searchByName(param) {
                    currentPage = 1;
                    loadSearchPage(currentPage, param.value);
                }

                function loadPage(page) {
                    currentPage = page;
                    $.ajax({
                        url: "/SWP/paginateclub",
                        type: "get",
                        data: {
                            page: currentPage,
                            pageSize: pageSize
                        },
                        success: function (data) {
                            document.getElementById("clubResults").innerHTML = data.results;
                            document.getElementById("paginationControls").innerHTML = data.pagination;
                        },
                        error: function () {
                            document.getElementById("clubResults").innerHTML = '<p>An error has occurred while loading the clubs.</p>';
                        }
                    });
                }

                function loadSearchPage(page, txtSearch) {
                    currentPage = page;
                    $.ajax({
                        url: "/SWP/searchclub",
                        type: "get",
                        data: {
                            txt: txtSearch,
                            page: currentPage,
                            pageSize: pageSize
                        },
                        success: function (data) {
                            document.getElementById("clubResults").innerHTML = data.results;
                            document.getElementById("paginationControls").innerHTML = data.pagination;
                        },
                        error: function () {
                            document.getElementById("clubResults").innerHTML = '<p>An error has occurred while searching for clubs.</p>';
                        }
                    });
                }

                function previousPage(totalPages, isSearch = false, txtSearch = '') {
                    if (currentPage > 1) {
                        if (isSearch) {
                            loadSearchPage(currentPage - 1, txtSearch);
                        } else {
                            loadPage(currentPage - 1);
                        }
                }
                }

                function nextPage(totalPages, isSearch = false, txtSearch = '') {
                    if (currentPage < totalPages) {
                        if (isSearch) {
                            loadSearchPage(currentPage + 1, txtSearch);
                        } else {
                            loadPage(currentPage + 1);
                        }
                }
                }

                $(document).ready(function () {
                    loadPage(1);
                });
            </script>

            <!--   Core JS Files   -->
            <!-- javascript -->
            <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
            <!-- simplebar -->
            <script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
            <!-- SLIDER -->
            <script src="${pageContext.request.contextPath}/assets/js/tiny-slider.js"></script>
            <script src="${pageContext.request.contextPath}/assets/js/tiny-slider-init.js"></script>
            <!-- Icons -->
            <script src="${pageContext.request.contextPath}/assets/js/feather.min.js"></script>
            <!-- Main Js -->
            <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

            <!-- jQuery Scrollbar -->
            <script src="${pageContext.request.contextPath}/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
            <!-- Kaiadmin JS -->
            <script src="${pageContext.request.contextPath}/assets/js/kaiadmin.min.js"></script>
            <!-- Toast Notification -->
            <div class="toast-container position-fixed top-0 end-0 p-3">
                <div id="toastMessage" class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="d-flex">
                        <div class="toast-body">
                            <!-- Nội dung thông báo sẽ thay đổi động -->
                            <span id="toastText"></span>
                            <div class="progress mt-2" style="height: 3px;">
                                <div id="toastProgress" class="progress-bar bg-light" role="progressbar" style="width: 100%; transition: width 3s linear;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--JS for toast notification-->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var message = "${sessionScope.message}";
                    var messageType = "${sessionScope.messageType}"; // "success" hoặc "error"

                    if (message) {
                        showToast(message, messageType);

                        // Xóa session message sau khi hiển thị
                        fetch("clearMessageSession", {method: "POST"});
                    }
                });

                function showToast(message, type = 'success') {
                    var toastElement = document.getElementById('toastMessage');
                    var progressBar = document.getElementById('toastProgress');
                    var toastText = document.getElementById('toastText');

                    // Cập nhật nội dung
                    toastText.innerText = message;

                    // Đổi màu nền theo loại thông báo
                    if (type === 'error') {
                        toastElement.classList.remove('bg-success');
                        toastElement.classList.add('bg-danger');
                    } else {
                        toastElement.classList.remove('bg-danger');
                        toastElement.classList.add('bg-success');
                    }

                    // Reset thanh tiến trình trước khi hiển thị
                    progressBar.style.width = '100%';

                    // Hiển thị toast
                    var toast = new bootstrap.Toast(toastElement);
                    toast.show();

                    // Thanh tiến trình giảm dần
                    setTimeout(() => {
                        progressBar.style.width = '0%';
                    }, 10); // Delay 10ms để hiệu ứng transition chạy mượt hơn

                    // Đóng toast sau 3 giây
                    setTimeout(() => {
                        toast.hide();
                    }, 3000);
                }
            </script>

            <!--preview image-->
            <script>
                document.getElementById('clubLogo').addEventListener('change', function (event) {
                    var file = event.target.files[0]; // Lấy file được chọn
                    var preview = document.getElementById('previewImage');

                    if (file) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            preview.src = e.target.result; // Hiển thị ảnh
                            preview.style.display = "block"; // Hiện ảnh xem trước
                        };
                        reader.readAsDataURL(file);
                    } else {
                        preview.style.display = "none"; // Ẩn nếu không có file
                    }
                });
            </script>

            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    document.querySelectorAll(".editable-badge").forEach(badge => {
                        badge.addEventListener("click", function () {
                            let field = this.getAttribute("data-field");
                            let id = this.getAttribute("data-id");
                            let currentValue = this.innerText.trim();

                            // Tạo dropdown
                            let select = document.createElement("select");
                            select.classList.add("form-select", "form-select-sm");
                            select.setAttribute("data-id", id);
                            select.setAttribute("data-field", field);

                            // Tạo options
                            let options = (field === "isPublic") ? ["Yes", "No"] : ["Active", "Inactive"];
                            options.forEach(option => {
                                let opt = document.createElement("option");
                                opt.value = option;
                                opt.text = option;
                                if (option === currentValue)
                                    opt.selected = true;
                                select.appendChild(opt);
                            });

                            // Thay badge bằng select
                            this.replaceWith(select);
                            select.focus();

                            // Khi chọn, gửi request cập nhật qua DAO
                            select.addEventListener("change", function () {
                                let newValue = this.value;
                                updateClubStatus(id, field, newValue);
                            });

                            // Khi blur, đổi lại thành badge nếu không chọn
                            select.addEventListener("blur", function () {
                                resetBadge(select, currentValue);
                            });
                        });
                    });
                });

                function updateClubStatus(id, field, value) {
                    fetch("/updateClubStatus", {
                        method: "POST",
                        headers: {"Content-Type": "application/json"},
                        body: JSON.stringify({id: id, field: field, value: value})
                    })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    resetBadge(document.querySelector(`[data-id='${id}'][data-field='${field}']`), value);
                                } else {
                                    alert("Update failed!");
                                }
                            })
                            .catch(error => {
                                alert("Error updating status!");
                            });
                }

                function resetBadge(select, value) {
                    let badge = document.createElement("span");
                    badge.classList.add("badge", value === "Yes" || value === "Active" ? "badge-success" : "badge-danger", "editable-badge");
                    badge.innerText = value;
                    badge.setAttribute("data-id", select.getAttribute("data-id"));
                    badge.setAttribute("data-field", select.getAttribute("data-field"));

                    // Gán lại sự kiện click
                    badge.addEventListener("click", function () {
                        this.replaceWith(select);
                        select.focus();
                    });

                    select.replaceWith(badge);
                }
            </script>

            <!--paging-->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const itemsPerPage = 3; // Số lượng task hiển thị trên mỗi trang
                    const tasks = document.querySelectorAll(".task");
                    const totalPages = Math.ceil(tasks.length / itemsPerPage);
                    let currentPage = 1;

                    function showPage(page) {
                        tasks.forEach((task, index) => {
                            task.style.display = (index >= (page - 1) * itemsPerPage && index < page * itemsPerPage) ? "block" : "none";
                        });
                    }

                    function createPagination() {
                        if (totalPages <= 1)
                            return; // Không tạo phân trang nếu chỉ có 1 trang

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

                        document.querySelector("#task-pagination-container").appendChild(pagination);
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


    </body>
</html>

