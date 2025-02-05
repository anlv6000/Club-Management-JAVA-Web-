<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Account</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        
        <style>
            body {
                font-family: 'Varela Round', sans-serif;
                background-color: #f8f9fa;
            }

            .container {
                margin-top: 40px;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }

            .table-title {
                background: #007bff;
                color: white;
                padding: 15px;
                border-radius: 5px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .table-title h2 {
                margin: 0;
                flex-grow: 1; /* Ensures title takes the remaining space */
            }

            .table-title a {
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border-radius: 5px;
                text-decoration: none;
                font-weight: bold;
                transition: 0.3s;
            }

            .table-title a:hover {
                background-color: #218838;
            }

            .table {
                margin-top: 20px;
                border-radius: 10px;
                overflow: hidden;
            }

            .table th {
                background: #007bff;
                color: white;
            }

            .table tbody tr:hover {
                background-color: #f1f1f1;
            }

            .table td {
                vertical-align: middle;
            }

            img {
                width: 50px;
                height: 50px;
                object-fit: cover;
                border-radius: 50%;
            }

            .status-active {
                color: green;
                font-weight: bold;
            }

            .status-inactive {
                color: red;
                font-weight: bold;
            }

            .btn {
                transition: 0.3s;
            }

            .btn:hover {
                transform: scale(1.05);
            }

            .search-container {
                display: flex;
                align-items: center;
margin-top: 15px;
                gap: 15px;
                flex-wrap: nowrap;
            }

            .search-container .col-md-2 {
                flex: 1;
                min-width: 200px;
            }

            .search-container label {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .search-container select, .search-container input {
                height: 35px;
                padding: 5px;
            }

            .search-container button {
                white-space: nowrap;
                padding: 8px 20px;
margin-top: 20px;
                height: 35px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <h2>Manage <b>Account</b></h2>
                    <!-- Nút Add New Account -->
                    <a href="AddAccount.jsp" class="btn btn-success">
                        <i class="material-icons">&#xE147;</i> <span>Add New Account</span>
                    </a>
                </div>
                <div class="form-container">
                    <form method="get" action="ManageAccount">
                        <div class="row search-container">
                            <div class="col-md-2">
                                <label for="roleDropdown">Role</label>
                                <select id="roleDropdown" name="role" class="form-select">
                                    <option value="">All Roles</option>
                                    <option value="Admin">Admin</option>
                                    <option value="ClubLeader">Club Leader</option>
                                    <option value="ClubMember">Club Member</option>
                                    <option value="WebUser">Web User</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label for="statusDropdown">Status</label>
                                <select id="statusDropdown" name="status" class="form-select">
                                    <option value="">All Status</option>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label for="keyword">Keyword</label>
                                <input type="text" id="keyword" name="keyword" class="form-control" placeholder="Enter name or email">
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </form>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Avatar</th>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col">Status</th>
                            <th scope="col">Account Created</th>
                            <th scope="col">Last Login</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${accountlist}" var="o">
                            <tr>
                                <td><img src="${o.imageURL != null && !o.imageURL.isEmpty() ? o.imageURL : 'https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg'}" alt="Avarta" /></td>
                                <td>${o.id}</td>
                                <td>${o.username}</td>
                                <td>${o.email}</td>
                                <td>${o.role}</td>
                                <td class="${o.status == 'Active' ? 'status-active' : 'status-inactive'}">${o.status}</td>
                                <td>${o.accountCreatedDate}</td>
                                <td>${empty o.lastLoginDate ? "N/A" : o.lastLoginDate}</td>
                                <td>
                                    <a href="ManageAccount?uid=${o.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?')">
                                        <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                    </a>
                                    <a href="DisplayAccount?accountId=${o.id}" class="edit">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <a href="Home.jsp"><button type="button" class="btn btn-primary">Back to home</button></a>
            </div>
        </div>
    </body>
</html>
