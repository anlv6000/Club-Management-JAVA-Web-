<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
        }

        table tr:hover {
            background-color: #f8f9fa;
        }

        .table-wrapper {
            margin-top: 50px;
        }

        .table-title h2 {
            font-size: 28px;
            color: #333;
        }

        .table-title {
            margin-bottom: 20px;
        }

        table th, table td {
            text-align: center;
            vertical-align: middle;
        }

        .btn-success {
            margin-top: 10px;
        }

        .delete, .edit {
            font-size: 20px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .delete:hover {
            transform: scale(1.2);
        }

        .edit:hover {
            transform: scale(1.2);
        }

        .btn-primary {
            margin-top: 20px;
        }

        .form-container {
            margin-bottom: 20px;
        }

        .form-container .col-md-4 {
            margin-bottom: 15px;
        }

        .form-container .d-flex {
            justify-content: flex-start;
            align-items: center;
        }

        .form-container input,
        .form-container select,
        .form-container button {
            width: 100%;
        }

        .search-container {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            gap: 10px; /* Khoảng cách giữa các phần tử */
        }

        .search-container .col-md-3 {
            margin-bottom: 0;
            padding-right: 0;
        }

        .search-container .col-md-3 input {
            width: auto;
            margin-right: 0; /* Không có khoảng cách bên phải */
        }

        .search-container .col-md-3 button {
            width: auto;
            margin-left: 0; /* Không có khoảng cách bên trái */
        }

        /* CSS để làm in đậm các dropdown */
        #roleDropdown, #statusDropdown {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Account</b></h2>
                    </div>
                    <div class="col-sm-6 text-end">
                        <a href="AddAccount.jsp" class="btn btn-success">
                            <i class="material-icons">&#xE147;</i> <span>Add New Account</span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Search, Role and Status Dropdown in one row -->
            <div class="form-container">
                <form method="get" action="ManageAccountServlet">
                    <div class="row search-container">
                        <div class="col-md-2">
                            <label for="roleDropdown">Role</label>
                            <select id="roleDropdown" name="role" class="form-select">
                                 <option value="Admin">All Role</option>
                                <option value="Admin">Admin</option>
                                <option value="User">User</option>
                                <option value="">Guest</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <label for="statusDropdown">Status</label>
                            <select id="statusDropdown" name="status" class="form-select">
                                <option value="Active">All Status</option>
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <label for="keyword"></label>
                            <input type="text" id="keyword" name="keyword" class="form-control" placeholder="Enter name or email">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>

            <div>
                <table class="table table-striped table-bordered shadow-sm">
                    <thead>
                        <tr>
                            <th scope="col">Avatar</th>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col">Status</th>
                            <th scope="col">Account Created</th> <!-- New Column -->
                            <th scope="col">Last Login</th> <!-- New Column -->
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Sample User Data -->
                        <tr>
                            <td><img src="https://via.placeholder.com/50" alt="Avatar"></td>
                            <td>1</td>
                            <td>user1</td>
                            <td>user1@example.com</td>
                            <td>Admin</td>
                            <td>Active</td>
                            <td>2023-01-01</td> <!-- Sample date -->
                            <td>2023-01-10</td> <!-- Sample date -->
                            <td>
                                <a href="deleteaccount?uid=1" class="delete" title="Delete">
                                    <i class="material-icons">&#xE872;</i>
                                </a>
                                <a href="AccountDetail.jsp?uid=1" class="edit" title="Edit">
                                    <i class="material-icons">&#xE254;</i>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td><img src="https://via.placeholder.com/50" alt="Avatar"></td>
                            <td>2</td>
                            <td>user2</td>
                            <td>user2@example.com</td>
                            <td>User</td>
                            <td>Inactive</td>
                            <td>2023-02-05</td> <!-- Sample date -->
                            <td>2023-02-10</td> <!-- Sample date -->
                            <td>
                                <a href="deleteaccount?uid=2" class="delete" title="Delete">
                                    <i class="material-icons">&#xE872;</i>
                                </a>
                                <a href="AccountDetail.jsp?uid=2" class="edit" title="Edit">
                                    <i class="material-icons">&#xE254;</i>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="text-center mt-4">
                <a href="home.jsp">
                    <button type="button" class="btn btn-primary">Back to Home</button>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
