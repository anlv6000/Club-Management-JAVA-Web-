<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<jsp:include page="header.jsp"></jsp:include>--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog Posts</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            /* CSS */
            .blog-card {
        border-radius: 10px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        transition: transform 0.3s;
        border: 1px solid #ddd;
        background-color: white;
    }

    .blog-card:hover {
        transform: translateY(-5px);
    }

    .blog-card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .post-container {
        border: 1px solid #ccc;
        border-radius: 10px;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    .row-cols-md-3 > * {
        padding: 10px;
    }

    .select-small {
        width: 150px;
        padding: 5px;
        font-size: 14px;
    }

    .pagination-container {
        margin-top: 20px;
        display: flex;
        justify-content: center;
    }

            .all_conten {
                margin-top: 20px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                background-color: #f9f9f9;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            }


        </style>
    </head>
     
    <body>
        
        <div class="container" >
           
            <div class="d-flex justify-content-between align-items-center mb-3">
                <a href="home1">
                    <h2><i class="fas fa-home"></i></h2>
                </a>
                        <c:if test="${not empty requestScope.successMessage}">
    <div id="successMessage" class="alert alert-success text-center" style="position: relative; width: 20%; margin: 20px auto;">
        ${requestScope.successMessage}
    </div>
  
</c:if>
                
                <div class="d-flex align-items-center gap-2">
                    <!-- Bộ lọc Status -->
                    <select class="form-select select-small" id="SelectByStatus">
                        <option value="all" ${param.status eq 'all' ? 'selected' : ''}>All</option>
                        <option value="Published" ${param.status eq 'Published' ? 'selected' : ''}>Published</option>
                        <option value="Unpublished" ${param.status eq 'Unpublished' ? 'selected' : ''}>Unpublished</option>
                        <option value="Draft" ${param.status eq 'Draft' ? 'selected' : ''}>Draft</option>
                    </select>

                    <!-- Thông báo thành công -->
                    
                 <form action="SearchPostByName" method="get" class="d-flex">
            <input type="text" name="name" class="form-control" placeholder="Nhập tên bài viết">
            <button type="submit" class="btn btn-outline-secondary">
                <i class="fas fa-search"></i>
            </button>
        </form>

                    <!-- Nút Thêm Bài Viết -->
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addPostModal">Thêm Bài Viết</button>
                    <div class="modal fade" id="addPostModal" tabindex="-1" aria-labelledby="addPostModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addPostModalLabel">Creat Post</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="AddPostServlet" method="post" enctype="multipart/form-data" id="addPostForm">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Images</label>
                                    <input type="text" class="form-control mb-2" name="image_url" placeholder="Nhập URL ảnh">
                                    <small class="text-danger d-none" id="imageError">Ảnh không hợp lệ! Chỉ chấp nhận .jpg, .jpeg, .png, .gif</small>
                                </div>
                                <div class="col-md-8">
                                    <div class="mb-3">
                                        <label>Title of Post *</label>
                                        <input type="text" class="form-control" name="title" required>
                                    </div>
                                    <div class="mb-3">
                                        <label>Status</label>
                                        <select class="form-control" name="status">
                                            <option value="Draft">Draft</option>
                                            <option value="Published">Published</option>
                                            <option value="Unpublished">Unpublished</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label>Description *</label>
                                        <textarea class="form-control" name="description" required></textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Exit</button>
                                <button type="submit" class="btn btn-primary" id="submitBtn">Created Post</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                </div>
            </div>

            <!-- Danh sách bài viết -->
           <div class="post-container">
    <h4 class="text-center mb-3">Danh Sách Bài Viết</h4>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="post" items="${e}">
            <div class="col">
                <div class="card blog-card">
                    <img src="${post.image_url != null ? post.image_url : 'https://via.placeholder.com/300'}" alt="Blog Image">
                    <div class="card-body">
                        <h5 class="card-title">${post.title}</h5>
                        <p class="text-muted">Creater: <strong>${post.nameCreated}</strong> - ${post.nameClub}</p>
                        <p class="text-muted">Post at: <strong>${post.post_at}</strong></p>
                        <p class="text-muted">Status: <strong>${post.status}</strong></p>
                        <p class="text-muted"><i class="fas fa-eye"></i> ${post.view}</p>
                        <a href="DetailPost?post_id=${post.post_id}" class="btn btn-primary">Read More</a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty e}">
            <div class="col text-center">
                <p class="text-muted">Không có bài viết nào.</p>
            </div>
        </c:if>
    </div>

    <!-- Phần phân trang -->
    <div class="pagination-container">
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="${action}?page=${currentPage - 1}<c:if test='${not empty status}'>&status=${status}</c:if>">Previous</a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="${action}?page=${i}<c:if test='${not empty status}'>&status=${status}</c:if>">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="${action}?page=${currentPage + 1}<c:if test='${not empty status}'>&status=${status}</c:if>">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>



    </div>
        <!-- JavaScript -->
        
        <script>
            document.getElementById("SelectByStatus").addEventListener("change", function () {
            const status = this.value;
            window.location.href = "SearchPostByStatus?status=" + status;
        });

            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const status = urlParams.get("status");
                if (status) {
                    document.getElementById("SelectByStatus").value = status;
                }
            };

            setTimeout(() => {
                const messageDiv = document.getElementById("successMessage");
                if (messageDiv) messageDiv.style.display = "none";
            }, 3000);
            
            
            
        </script>
        <script>
    document.getElementById("addPostForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Ngăn chặn gửi form ngay lập tức
        
        let isValid = true;
        
        // Lấy giá trị các input
        const imageUrl = document.querySelector("[name='image_url']").value.trim();
        const title = document.querySelector("[name='title']").value.trim();
        const description = document.querySelector("[name='description']").value.trim();
        
        // Kiểm tra URL ảnh hợp lệ
        const imageRegex = /\.(jpg|jpeg|png|gif)$/i;
        if (imageUrl === "" || !imageRegex.test(imageUrl)) {
            document.getElementById("imageError").classList.remove("d-none");
            isValid = false;
        } else {
            document.getElementById("imageError").classList.add("d-none");
        }

        // Kiểm tra độ dài Title
        if (title.length < 10) {
            alert("Title phải có ít nhất 10 ký tự.");
            isValid = false;
        }

        // Kiểm tra độ dài Description
        if (description.length < 10) {
            alert("Description phải có ít nhất 10 ký tự.");
            isValid = false;
        }

        // Nếu tất cả đều hợp lệ thì gửi form
        if (isValid) {
            this.submit();
        }
    });
</script>

       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
       
    </body>
 
</html>