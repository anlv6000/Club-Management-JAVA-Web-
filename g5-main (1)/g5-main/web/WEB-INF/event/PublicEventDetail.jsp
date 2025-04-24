<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${event.eventName}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }
            .container {
                max-width: 900px;
            }
            .breadcrumb {
                background: none;
                padding: 0;
            }
            .content img {
                width: 100%;
                height: auto;
                border-radius: 10px;
                margin-bottom: 20px;
            }
            .sidebar {
                background: white;
                padding: 20px;
                border-radius: 10px;
            }
            .sidebar h5 {
                font-weight: bold;
                margin-bottom: 15px;
            }
            .recent-post {
                display: flex;
                margin-bottom: 15px;
                align-items: center;
            }
            .recent-post img {
                width: 60px;
                height: 60px;
                object-fit: cover;
                border-radius: 5px;
                margin-right: 10px;
            }
            .status-box {
                display: inline-block;
                padding: 5px 10px;
                border: 2px solid #007bff; /* Viền xanh */
                border-radius: 5px;
                background-color: rgba(0, 123, 255, 0.1); /* Làm sáng lên */
                font-weight: bold;
                color: #007bff; /* Chữ màu xanh */
            }
            .fw-bold {
                font-weight: bold;
            }
            .content-wrapper {
                background: white; /* Nền trắng */
                border-radius: 10px; /* Bo góc */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
                border: 1px solid #ddd; /* Viền mỏng */
                padding: 20px; /* Khoảng cách bên trong */
                margin-top: 20px; /* Khoảng cách với breadcrumb */
            }
            .fh5co-page-title {
                padding: 9em 0 0em 0 !important;
            }
            .obito {
                border: 2px solid #ddd; /* Viền xám nhạt */
                padding: 5px; /* Tạo khoảng cách giữa viền và nội dung bên trong */
                background-color: #f9f9f9; /* Màu nền rất nhạt để dễ nhận biết */
                border-radius: 8px; /* Bo góc nhẹ */
                text-align: center; /* Căn giữa nội dung */

            }

            .obito img {
                display: block;
                margin: 0 auto; /* Căn giữa hình ảnh */
            }
            .action-box {
                display: block;
                margin-top: 10px; /* Tạo khoảng cách giữa hình và nút */
            }
            button:hover {
                background-color: #0056b3;
            }
            .fh5co-cta .overlay, .fh5co-page-title .overlay {
                background: rgba(0, 0, 0, 0.7);
                left: 0;
                right: 0;
                bottom: 72px;
                top: 0;
                position: absolute;
                z-index: 1;
            }
            textarea {
                resize: vertical;
                min-height: 80px;
            }
            /* Button styling */
            button {
                width: 100%;
                padding: 10px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 15px;
            }
            label {
                font-weight: bold;
                margin-top: 10px;
            }
            input, textarea {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            textarea {
                resize: vertical;
                min-height: 80px;
            }
            .form-container {
                display: flex;
                gap: 20px;
                width: 100%;
            }
            .form-column {
                flex: 1;
                display: flex;
                flex-direction: column;
            }
            .image-container {
                text-align: center;
                margin-bottom: 15px;
                width: 100%;
                display: flex;
                justify-content: center;
            }
            .image-container img {
                max-width: 53%;
                height: auto;
                border-radius: 8px;
                border: 2px solid #ddd;
                padding: 5px;
            }
            .modal-content {
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                width: 105%;
                max-width: 600px;
                position: absolute;
                top: 32%;
                left: 50%;
                transform: translate(-50%, -50%);
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 167px;
            }
            /* Close button style */
            .close {
                position: absolute;
                top: 10px;
                right: 15px;
                font-size: 20px;
                cursor: pointer;
            }
            .separator {
                margin: 0 10px;
                font-size: 20px;
                color: #777;
            }
            .note-title {
                font-size: 20px;
                font-weight: bold;
                color: #d9534f;
                margin-top: 15px;
            }
            .event-note {
                font-size: 16px;
                color: #666;
            }
            .kathuy{
                display: flex;
                align-items: center;
                text-align: center;
           }
            #nunu{
                margin-left: 20px;
                margin-right: 20px;
            }
            #kanu{
                 margin-left: 20px;
                margin-right: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="home1" class="btn btn-primary btn-sm">Home</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="PublicEvent" class="btn btn-secondary btn-sm">Public Event</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">Event Detail</li>
                </ol>
            </nav>
            <div class="content-wrapper p-4">
                <div class="row">

                    <div class="col-lg-8">
                        <h1 class="fw-bold">${event.eventName}</h1>
                        <p class="status-box">Status : ${event.status ? 'Active' : 'Inactive'}</p>
                        <p class="fw-bold">Type : ${event.type ? 'Online' : 'Offline'}</p>
                        <p class="text-muted mb-1">
                            <strong>Creator:</strong> ${event.userCreat} &nbsp; | &nbsp; 
                            <strong>Date:</strong> ${event.eventDate}
                        </p>
                        <p class="text-muted">
                            <strong>Time:</strong> ${event.eventDate} &rarr; ${event.endEventDate}
                        </p>
                        <p class="text-muted">
                            <strong>Participants:</strong> ${event.participantcount} người tham gia
                        </p>
                        <img src="${event.eventImageURL != null ? event.eventImageURL: 'images/default.jpg'}" 
                             class="img-fluid rounded" alt="Blog Image">

                        <div class="mt-4 p-3 border rounded bg-light">
                            <h5 class="fw-bold mb-3">Description</h5>
                            <p>${event.description}</p>
                        </div>
                    </div>

                    <!-- Sidebar -->
                    <div class="col-lg-4">
                        <div class="sidebar">


                            <h5>Recent Event</h5>
                            <c:forEach var="o" items="${list}">
                                <div class="recent-post">
                                    <img src="${o.eventImageURL  != null ? o.eventImageURL: 'images/default.jpg'}" alt="Post Image">
                                    <div>

                                        <a href="DetailEventPublic?event_id=${o.id}" class="text-dark text-decoration-none">
                                            ${o.eventName}
                                        </a>
                                        <br>
                                        <small class="text-muted">${o.eventDate} | ${o.getUserCreat()}</small>
                                    </div>
                                </div>
                            </c:forEach>

                            <h5>CLub</h5>
                            <div class="tag-cloud">
                                <a href="${pageContext.request.contextPath}/ViewPublicClubDetail?clubName=${event.nameCLub}" 
                                   class="btn btn-outline-primary btn-sm">
                                    ${event.nameCLub}
                                </a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- Button -->
        <button id="openModal" class="btn btn-primary" style="margin: 40px auto; display: block;width: fit-content; text-align: center; align-content: center;">Join Event Now</button>

        <!-- Modal Bootstrap -->
        <div class="modal fade" id="joinEventModal" tabindex="-1" aria-labelledby="joinEventModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content p-4">
                    <div class="modal-header">
                        <h5 class="modal-title" id="joinEventModalLabel">Join Event: ${event.eventName}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <img src="${event.eventImageURL}" alt="Event Image" class="img-fluid rounded border p-2">
                            </div>
                            <div class="col-md-12">
                                <form action="submiteventRegistration_2" method="post">
                                    <input type="hidden" name="eventID" value="${event.id}">
                                    <input type="hidden" name="clubID" value="${event.clubId}">
                                    <div class="kathuy"><div class="mb-4" id="nunu">
                                            <label for="name" class="form-label">Name:</label>
                                            <c:if test="${sessionScope.acc != null || sessionScope.accc != null}"><input type="text" id="name" name="name"
                                                   value="<c:choose><c:when test='${sessionScope.acc != null || sessionScope.accc == null}'>${sessionScope.txtUsername}</c:when><c:when test='${sessionScope.acc == null || sessionScope.accc != null}'>${sessionScope.name}</c:when><c:otherwise></c:otherwise></c:choose>"
                                                   class="form-control" required readonly=""></c:if> 
                                            <c:if test="${sessionScope.acc ==null && sessionScope.accc ==null}"><input type="text" id="name" name="name" required=""></c:if>
                                                   </div>
                                                   <c:if test="${sessionScope.acc != null || sessionScope.accc != null}"><input type="hidden" name="userId" value="${sessionScope.userId}"></c:if>
                                                 
                                                           <div class="mb-4" >
                                                       <label for="phone" class="form-label">Phone:</label>
                                                       <input type="text" id="phone" name="phone" class="form-control" required>
                                                       <div class="text-danger small" id="phoneError"></div>
                                                   </div></div>
                                                           <div class="kathuy"><div class="mb-4" id="kanu">
                                                    <label for="email" class="form-label">Email:</label>
                                                               <c:if test="${sessionScope.email !=null}"><input type="email" id="email" name="email"
                                                           value="${sessionScope.email != null ? sessionScope.email : ''}"
                                                               class="form-control" required readonly=""></c:if>  
                                                               <c:if test="${sessionScope.email == null}"><input type="email" id="email" name="email" required=""></c:if>
                            </div>
                                        <div class="mb-4">
                                            <label for="note" class="form-label">Lý do tham gia:</label>
                                            <textarea id="note" name="note" class="form-control" rows="3"></textarea>
                                        </div></div>
                                    <button type="submit" class="btn btn-success w-100 mt-2">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const openModalBtn = document.getElementById("openModal");
                const joinModal = new bootstrap.Modal(document.getElementById("joinEventModal"));

                openModalBtn.addEventListener("click", function () {
                    joinModal.show();
                });
                // Validate phone
                const form = document.querySelector("form[action='submiteventRegistration_2']");
                const phoneInput = document.getElementById("phone");
                const phoneError = document.getElementById("phoneError");
                form.addEventListener("submit", function (event) {
                    const phoneRegex = /^(0[3-9])+([0-9]{8})$/;
                    if (!phoneRegex.test(phoneInput.value.trim())) {
                        event.preventDefault();
                        phoneError.textContent = "Số điện thoại không hợp lệ. Ví dụ: 0987654321.";
                    } else {
                        phoneError.textContent = "";
                    }
                });
            });
        </script>
        <script>
            // Hiển thị và đóng pop-up
            document.addEventListener("DOMContentLoaded", function () {
                const clubForm = document.querySelector("form[action='submitEventRegister']");
                if (!clubForm)
                    return; // Kiểm tra xem form có tồn tại không
                clubForm.addEventListener("submit", function (event) {
                    const phoneInput = document.getElementById("phone");
                    const phoneError = document.getElementById("phoneError");
                    const phoneRegex = /^(0[3-9])+([0-9]{8})$/; // Định dạng hợp lệ cho số điện thoại VN
                    if (!phoneRegex.test(phoneInput.value.trim())) {
                        event.preventDefault(); // Ngăn form gửi đi
                        phoneError.textContent = "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng (VD: 0987654321).";
                        phoneError.style.color = "red"; // Hiển thị lỗi bằng màu đỏ
                    } else {
                        phoneError.textContent = ""; // Xóa thông báo lỗi nếu hợp lệ
                    }
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const commentForm = document.getElementById("commentform");
                const commentList = document.querySelector("ol.comment-list") || document.createElement("ol");
                if (!commentList.classList.contains("comment-list")) {
                    commentList.classList.add("comment-list");
                    document.body.appendChild(commentList);
                }
                commentForm.addEventListener("submit", function (event) {
                    event.preventDefault(); // Ngăn chặn load lại trang khi gửi form
// Lấy giá trị từ form
                    let author = document.getElementById("author").value.trim();
                    let email = document.getElementById("email").value.trim();
                    let commentText = document.getElementById("comment").value.trim();
                    if (author === "" || email === "" || commentText === "") {
                        alert("Vui lòng điền đầy đủ thông tin!");
                        return;
                    }
// Tạo phần tử bình luận mới
                    let newComment = document.createElement("li");
                    newComment.classList.add("comment");
// Tạo phần tử thẻ <a> chứa ngày giờ
                    let timeElement = document.createElement("a");
                    timeElement.href = "#";
                    timeElement.textContent = new Date().toLocaleString(); // Hiển thị ngày giờ
                    newComment.innerHTML = `
<div class="comment-body">
<div class="comment-author vcard">
<img class="avatar photo" src="assets/images/testimonials/pic1.jpg" alt="">
<cite class="fn">${author}</cite> <span class="says">says:</span>
</div>
<div class="comment-meta">
</div>
<p>${commentText}</p>
<div class="reply">
<a href="#" class="comment-reply-link">Reply</a>
</div>
</div>
`
// Chèn phần tử thời gian vào phần meta
                    newComment.querySelector(".comment-meta").appendChild(timeElement);
// Thêm bình luận vào danh sách (ở trên cùng)
                    commentList.prepend(newComment);
// Xóa nội dung trong form
                    commentForm.reset();
// Cuộn lên để hiển thị bình luận mới nhất
                    newComment.scrollIntoView({behavior: "smooth", block: "start"});
                });
            });
        </script>
        <!-- Update Post Modal -->
        <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateModalLabel">UpdatePost</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="UpdatePostServlet" method="post">
                            <input type="hidden" name="post_id" value="${e.post_id}">
                            <div class="mb-3">
                                <label for="title" class="form-label">TiTle of Post</label>
                                <input type="text" class="form-control" id="title" name="title" value="${e.title}" required>
                            </div>
                            <div class="mb-3">
                                <label for="image_url" class="form-label">Image</label>
                                <input type="text" class="form-control" id="image_url" name="image_url" value="${e.image_url}">
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Content</label>
                                <textarea class="form-control" id="description" name="description" rows="3" required>${e.description}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status">
                                    <option value="Draft" ${e.status == 'Draft' ? 'selected' : ''}>Draft</option>
                                    <option value="Published" ${e.status == 'Published' ? 'selected' : ''}>Published</option>
                                    <option value="Unpublished" ${e.status == 'Unpublished' ? 'selected' : ''}>Unpublished</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Change</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
