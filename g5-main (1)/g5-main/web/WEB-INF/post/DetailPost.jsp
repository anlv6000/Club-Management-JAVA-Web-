<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${e.title} - Blog Detail</title>
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
            .content-wrapper {
                background: white; /* Nền trắng */
                border-radius: 10px; /* Bo góc */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
                border: 1px solid #ddd; /* Viền mỏng */
                padding: 20px; /* Khoảng cách bên trong */
                margin-top: 20px; /* Khoảng cách với breadcrumb */
            }
        </style>
    </head>
    <body>

        <div class="container mt-4">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home1">Home</a></li>
                    <li class="breadcrumb-item"><a href="BlogListMember">Post</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Post Detail</li>
                </ol>
            </nav>
            <div class="content-wrapper p-4">
                <div class="row">
                    <!-- Main Content -->
                    <div class="col-lg-8">
                        <h1 class="fw-bold">${e.title}</h1>
                        <p class="text-muted">Creater : ${e.namecreated} | Time: ${e.post_at}</p>

                        <img src="${e.image_url != null ? e.image_url : 'images/default.jpg'}" 
                             class="img-fluid rounded" alt="Blog Image">

                        <div class="content mt-4">
                            <p>${e.description}</p>
                        </div>
                    </div>

                    <!-- Sidebar -->
                    <div class="col-lg-4">
                        <div class="sidebar">
                            <!-- Buttons: Update & Delete -->
                            <div class="mb-3">
                                <button class="btn btn-primary w-100 mb-2" data-bs-toggle="modal" data-bs-target="#updateModal">Update</button>
                                <a href="DeletePostServlet?post_id=${e.post_id}" class="btn btn-danger w-100" onclick="return confirm('Bạn có chắc muốn xóa bài viết này không?');">Delete</a>
                            </div>

                            <h5>Recent Posts</h5>
                            <c:forEach var="p" items="${list}">
                                <div class="recent-post">
                                    <img src="${p.image_url != null ? p.image_url : 'images/default.jpg'}" alt="Post Image">
                                    <div>
                                        <a href="DetailPost?post_id=${p.post_id}" class="text-dark text-decoration-none">
                                            ${p.title}
                                        </a>
                                        <br>
                                        <small class="text-muted">${p.post_at} | ${p.namecreated}</small>
                                    </div>
                                </div>
                            </c:forEach>

                            <h5>CLub</h5>
                            <div class="tag-cloud">
                                <a href="ClubDetailById?clubid=${o.clubID}">${e.nameclub}</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Update Post Modal -->
        <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateModalLabel">UpdatePost</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="UpdatePostServlet" method="post" enctype="multipart/form-data"> <!-- THÊM enctype -->
                            <input type="hidden" name="post_id" value="${e.post_id}">

                            <div class="mb-3">
                                <label for="title" class="form-label">TiTle of Post</label>
                                <input type="text" class="form-control" id="title" name="title" value="${e.title}" required>
                            </div>

                            <div class="mb-3">
                                <label for="image" class="form-label">Image</label>
                                <input type="file" class="form-control" id="image" name="image"> <!-- ĐỔI name thành image -->
                                <p>Current image: <img src="${e.image_url}" alt="Image" width="100"></p>
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

                            <div class="mb-3">

                                <input type="hidden" class="form-control" id="clubId" name="clubId" value="${e.club_id}" required>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Change</button>
                        </form>


                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
