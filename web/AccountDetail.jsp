<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 70%;
            margin: 20px auto;
            background: white;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
        }
        .header {
            text-align: center;
            color: red;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .form-container {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 20px;
        }
        .profile-pic {
            text-align: center;
        }
        .profile-pic img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 1px solid #ddd;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group .radio-group {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .form-group .radio-group input {
            margin-right: 5px;
        }
        .form-actions {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .form-actions button {
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-actions .save {
            background-color: #28a745;
            color: white;
        }
        .form-actions .cancel {
            background-color: #ddd;
            color: black;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">Account Information</div>
        <form>
            <div class="form-container">
                <!-- Left Column -->
                <div class="profile-pic">
                    <img src="https://via.placeholder.com/100" alt="Profile Picture">
                </div>
                <!-- Right Column -->
                <div>
                    <div class="form-group">
                        <label for="account-id">ID</label>
                        <input type="text" id="account-id" name="account-id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="account-name">Name Account</label>
                        <input type="text" id="account-name" name="account-name">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select id="role" name="role">
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="status" value="active"> Active
                            </label>
                            <label>
                                <input type="radio" name="status" value="inactive"> Inactive
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Name Club</label>
                        <input type="text" name="name-club">
                    </div>
                    <!-- Adding creation date and last modified date -->
                    <div class="form-group">
                        <label for="created-at">Account Created</label>
                        <input type="text" id="created-at" name="created-at" readonly>
                    </div>
                    <div class="form-group">
                        <label for="last-modified">Last Modified</label>
                        <input type="text" id="last-modified" name="last-modified" readonly>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="save">Save</button>
                <button type="button" class="cancel">Cancel</button>
            </div>
        </form>
    </div>
</body>
</html>
