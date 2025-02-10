<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm tài khoản mới</title>
    <style>
        body {
<<<<<<< HEAD
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #6a11cb, #2575fc);
        margin: 0;
        padding: 0;
    }
=======
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input, select {
<<<<<<< HEAD
            width: 95%;
=======
            width: 100%;
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            font-size: 16px;
            color: #fff;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #218838;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
        .eye-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
        }
        .password-wrapper {
            position: relative;
        }
    </style>
</head>
<body>
    <div class="container">
<<<<<<< HEAD
        <h2>ADD NEW ACCOUNT</h2>
         <form action="AddAccount" method="post" enctype="multipart/form-data">
=======
        <h2>Thêm tài khoản mới</h2>
        <form action="AddAccount" method="post">
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty mess}">
                <div class="error-message">${mess}</div>
            </c:if>
            <div class="form-group">
<<<<<<< HEAD
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Nhập tên tài khoản" required>
            </div>
=======
                <label for="username">Tên tài khoản:</label>
                <input type="text" id="username" name="username" placeholder="Nhập tên tài khoản" required>
            </div>
            <div class="form-group password-wrapper">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required>
                <span class="eye-icon" onclick="togglePassword()">👁️</span>
            </div>
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Nhập email" required>
            </div>
            <div class="form-group">
                <label for="usertype">Role:</label>
                <select id="usertype" name="usertype">
                    <option value="Admin">Admin</option>
                    <option value="ClubLeader">Club Leader</option>
                    <option value="ClubMember">Club Member</option>
                    <option value="WebUser">Web User</option>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status">
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>
            <div class="form-group">
<<<<<<< HEAD
                <label for="profileImage">Avarta:</label>
        <input type="file" id="profileImage" name="profileImage" accept="image/*" required>
            </div>
            <button type="submit" class="btn">ADD ACCOUNT</button>
        </form>
    </div>

   
</body>
</html>
=======
                <label for="imageURL">Ảnh đại diện:</label>
                <input type="text" id="imageURL" name="imageURL">
            </div>
            <button type="submit" class="btn">Thêm tài khoản</button>
        </form>
    </div>

    <script>
        function togglePassword() {
            var passwordField = document.getElementById("password");
            var type = passwordField.type === "password" ? "text" : "password";
            passwordField.type = type;
        }
    </script>
</body>
</html>
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
