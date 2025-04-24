<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Post</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            margin-top: 30px;
        }
        .card {
            border: none;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .form-label {
            font-weight: 500;
        }
        .editor-toolbar {
            background-color: #f1f1f1;
            padding: 10px;
            border: 1px solid #ddd;
            border-bottom: none;
            border-radius: 5px 5px 0 0;
        }
        .editor-toolbar button {
            background: none;
            border: none;
            margin-right: 10px;
            font-size: 16px;
            color: #333;
        }
        .editor-toolbar button:hover {
            color: #007bff;
        }
        #content {
            min-height: 200px;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 0 0 5px 5px;
        }
        .btn-submit {
            background-color: #007bff;
            border: none;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
        .alert {
            margin-top: 20px;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                Add New Post
            </div>
            <div class="card-body">
                <!-- Hiển thị thông báo nếu có -->
                <c:if test="${not empty message}">
                    <div class="alert alert-success" role="alert">
                        ${message}
                    </div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>

                <form action="AddNewPostServlet" method="post" enctype="multipart/form-data" onsubmit="saveContent()">
                    <!-- Title -->
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" 
                               value="${param.title != null ? param.title : ''}" 
                               placeholder="Enter post title" required>
                        <div class="error-message">${errors['titleError']}</div>
                    </div>

                    <!-- Select Club -->
                    <div class="mb-3">
                        <label for="clubId" class="form-label">Club</label>
                        <select class="form-select" id="clubId" name="clubId" required>
                            <option value="">-- Select Club --</option>
                            <c:forEach var="club" items="${clubList}">
                                <option value="${club.clubID}" 
                                        <c:if test="${param.clubId == club.clubID}">selected</c:if>>
                                    ${club.clubName}
                                </option>
                            </c:forEach>
                        </select>
                        <div class="error-message">${errors['clubError']}</div>
                    </div>

                    <!-- Status -->
                    <div class="mb-3">
                        <label for="status" class="form-label">Status</label>
                        <select class="form-select" id="status" name="status" required>
                            <option value="">-- Select Status --</option>
                            <option value="Published" <c:if test="${param.status == 'Published'}">selected</c:if>>Published</option>
                            <option value="Unpublished" <c:if test="${param.status == 'Unpublished'}">selected</c:if>>Unpublished</option>
                            <option value="Draft" <c:if test="${param.status == 'Draft'}">selected</c:if>>Draft</option>
                        </select>
                        <div class="error-message">${errors['statusError']}</div>
                    </div>

                    <!-- Image -->
                    <div class="mb-3">
                        <label for="image" class="form-label">Image</label>
                        <input type="file" class="form-control" id="image" name="image" accept="image/*">
                        <div class="error-message">${errors['imageError']}</div>
                    </div>

                    <!-- Content -->
                    <div class="mb-3">
                        <label for="content" class="form-label">Content</label>
                        <div class="editor-toolbar">
                            <button type="button" onclick="formatText('bold')"><i class="fas fa-bold"></i></button>
                            <button type="button" onclick="formatText('italic')"><i class="fas fa-italic"></i></button>
                            <button type="button" onclick="formatText('underline')"><i class="fas fa-underline"></i></button>
                            <button type="button" onclick="formatText('list')"><i class="fas fa-list-ul"></i></button>
                        </div>
                        <div contenteditable="true" id="content" class="form-control">
                            ${param.content != null ? param.content : ''}
                        </div>
                        <!-- Hidden input để lưu nội dung từ contenteditable -->
                        <input type="hidden" id="contentHidden" name="content">
                        <div class="error-message">${errors['contentError']}</div>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary btn-submit">Create Post</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- JavaScript for Editor -->
    <script>
        function formatText(command) {
            if (command === 'list') {
                document.execCommand('insertUnorderedList', false, null);
            } else {
                document.execCommand(command, false, null);
            }
            document.getElementById('content').focus();
        }

        function saveContent() {
            const content = document.getElementById('content').innerHTML;
            document.getElementById('contentHidden').value = content;
        }
    </script>
</body>
</html>