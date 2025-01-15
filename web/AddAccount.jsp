<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Account</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
        }

        .modal-content {
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .modal-header {
            border-bottom: 2px solid #007bff;
            text-align: center;
        }

        .modal-header h4 {
            color: #007bff;
        }

        .modal-footer {
            border-top: 2px solid #007bff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .modal-footer .btn {
            padding: 8px 20px;
            font-size: 14px;
            border-radius: 5px;
        }

        .btn-success {
            background-color: #28a745;
            border: none;
        }

        .btn-default {
            background-color: #f8f9fa;
            border: none;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
            color: #555;
        }

        .form-control {
            border-radius: 6px;
            border: 1px solid #ccc;
            padding: 10px;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
        }

        .form-group input[type="radio"] {
            margin-right: 10px;
        }

        .modal-body {
            padding: 20px;
        }

        .form-group .radio-group {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .form-group .radio-group input {
            margin-right: 5px;
        }

    </style>
</head>
<body>
    <div id="addAccountModal" class="modal fade show" style="display:block;" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="AddAccountServlet" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Add Account</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="user">User</label>
                            <input name="user" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="em">Email</label>
                            <input name="em" type="email" class="form-control" required>
                        </div>

                        <!-- Thêm phần chọn Role -->
                        <div class="form-group">
                            <label for="role">Role</label>
                            <select name="role" class="form-control" required>
                                <option value="admin">Admin</option>
                                <option value="user">User</option>
                                <option value="guest">Guest</option>
                            </select>
                        </div>

                        <!-- Thêm phần chọn Status -->
                        <div class="form-group">
                            <label>Status</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="status" value="active" id="active" required> Active
                                </label>
                                <label>
                                    <input type="radio" name="status" value="inactive" id="inactive"> Inactive
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
