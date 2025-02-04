<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Setting Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .form-container {
            width: 80%;
            max-width: 600px;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-bottom: 15px;
            width: 100%;
        }
        .form-group-horizontal {
            display: flex;
            justify-content: space-between;
            width: 100%;
            align-items: flex-start;
        }
        .form-group label {
            margin-bottom: 5px;
            width: 100%;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 2px solid #f4f4f9;
            border-radius: 4px;
        }
        .form-group-half {
            width: 48%;
        }
        .form-container button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #45a049;
        }
        input:focus,
        select:focus,
        textarea:focus {
            border-color: #4CAF50;
            outline: none;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>New Setting</h1>
        <form action="newSetting" method="post">
            <div class="form-group-horizontal">
                <div class="form-group form-group-half">
                    <label for="name">Name*</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group form-group-half">
                    <label for="type">Type</label>
                    <select id="type" name="type" required>
                        <option value="Number">Number</option>
                        <option value="Boolean">Boolean</option>
                        <option value="String">String</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="value">Value</label>
                <input type="text" id="value" name="value" required>
            </div>
            <div class="form-group">
                <label for="priority">Priority</label>
                <input type="number" id="priority" name="priority" value="2" required>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <div class="radio-group" style="display: flex; align-items: center;">
                    <label for="active" style="margin-right: 10px;">
                        <input type="radio" id="active" name="status" value="Active" checked> Active
                    </label>
                    <label for="inactive">
                        <input type="radio" id="inactive" name="status" value="Inactive"> Inactive
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="userType">User Type</label>
                <select id="userType" name="userType" required>
                    <option value="Admin">Admin</option>
                    <option value="ClubLeader">Club Leader</option>
                    <option value="ClubMember">Club Member</option>
                    <option value="WebUser">Web User</option>
                </select>
            </div>
            <div class="form-group" style="align-items: center;">
                <button type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>
