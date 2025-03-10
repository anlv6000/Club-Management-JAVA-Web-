<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
  

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm tài khoản mới</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style>
      html, body {
    margin: 0;
    padding: 0;
    background: none; /* Xóa màu nền */
     overflow: hidden;
}

.container {
    width: auto; /* Để bảng có kích thước tự nhiên */
    max-width: 500px;
    background-color: white;
    border-radius: 12px;
    box-shadow: none; /* Xóa hiệu ứng bóng */
}


            h2 {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                color: #4c6ef5;
                margin-bottom: 30px;
            }
            .form-group {
                margin-bottom: -5px;
                position: relative;
            }
            label {
                font-weight: 600;
                display: block;
                margin-bottom: 8px;
            }
           input, select {
    width: 100%;
    height: 32px; /* Giảm chiều cao */
    padding: 4px 8px; /* Giảm padding */
    border-radius: 6px; /* Bo góc nhẹ hơn */
    border: 1px solid #d1d5db;
    font-size: 14px; /* Giảm kích thước chữ */
    color: #333;
    background-color: #f3f4f6;
    box-sizing: border-box;
}
.btn {
    padding: 10px 16px; /* Giảm kích thước nút */
    font-size: 14px; /* Giảm kích thước chữ */
    border-radius: 6px;
    width: auto; /* Nút không chiếm hết chiều rộng */
    min-width: 100px; /* Đảm bảo không quá nhỏ */
}
h2 {
    font-size: 20px; /* Giảm kích thước tiêu đề */
    margin-bottom: 20px;
}
.form-group {
    margin-bottom: 4px; /* Giảm khoảng cách giữa các trường */
}
.error-message {
    font-size: 12px; /* Nhỏ hơn để tiết kiệm không gian */
    bottom: -15px;
    color: red; /* Đặt màu đỏ */
     margin-top: 2px; /* Giữ khoảng cách với input trên */
    margin-bottom: -20px; /* Thêm khoảng cách để tránh bị che nút */
}

        </style>
    </head>
    <body>
        <div class="container">
            <h2>New account</h2>
            <form action="AddAccount" method="post" enctype="multipart/form-data">
   <c:if test="${not empty sessionScope.successMessage}">
    <script>
        alert("${sessionScope.successMessage}");
        window.parent.reloadAccountList(); // Gọi hàm reload danh sách tài khoản mà không đóng pop-up
    </script>
    <c:remove var="successMessage" scope="session"/>
</c:if>


    <div class="grid grid-cols-2 gap-4"> <!-- Chia thành 2 cột -->
        <div class="form-group">
            <label for="username">Username: <span class="text-red-500">*</span><span class="text-red-500 ml-2">(Ít nhất 8 kí tự)</span></label>
            <input type="text" id="username" name="username" placeholder="Nhập tên tài khoản" value="${username}" required>
            <c:if test="${not empty usernameError}">
                <div class="error-message">${usernameError}</div>
            </c:if>
        </div>
              <div class="form-group">
            <label for="fullname">Fullname: <span class="text-red-500">*</span><span class="text-red-500 ml-2">(Ít nhất 8 kí tự)</span></label>
            <input type="text" id="fullname" name="fullname" placeholder="Nhập tên tài khoản" value="${username}" required>
            <c:if test="${not empty fullnameError}">
                <div class="error-message">${fullnameError}</div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">Email: <span class="text-red-500">*</span></label>
            <input type="email" id="email" name="email" placeholder="Nhập email" value="${email}" required>
            <c:if test="${not empty emailError}">
                <div class="error-message">${emailError}</div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="usertype">Role:</label>
            <select id="usertype" name="usertype">
                <option value="Admin" ${usertype == 'Admin' ? 'selected' : ''}>Admin</option>
                <option value="ClubLeader" ${usertype == 'ClubLeader' ? 'selected' : ''}>Club Leader</option>
                <option value="ClubMember" ${usertype == 'ClubMember' ? 'selected' : ''}>Club Member</option>
                <option value="WebUser" ${usertype == 'WebUser' ? 'selected' : ''}>Web User</option>
            </select>
            <c:if test="${not empty usertypeError}">
                <div class="error-message">${usertypeError}</div>
            </c:if>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="Active" ${status == 'Active' ? 'selected' : ''}>Active</option>
                <option value="Inactive" ${status == 'Inactive' ? 'selected' : ''}>Inactive</option>
            </select>
            <c:if test="${not empty statusError}">
                <div class="error-message">${statusError}</div>
            </c:if>
        </div>

        <div class="form-group col-span-2"> <!-- Ảnh chiếm cả 2 cột -->
            <label for="profileImage">Avarta:</label>
            <input type="file" id="profileImage" name="profileImage" accept="image/*" required>
            <c:if test="${not empty imageError}">
                <div class="error-message">${imageError}</div>
            </c:if>
        </div>
    </div>

        <div class="mt-4 flex justify-end">
        <button type="submit" class="btn bg-blue-500 text-white hover:bg-blue-600">Add Account</button>
    </div>

</form>

        </div>
            
    </body>
</html>
