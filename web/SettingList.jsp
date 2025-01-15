<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Setting List</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            width: 80%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            border: 1px solid #ddd;
        }
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Setting List</h1>
        <div>
            <label for="typeFilter">All Types</label>
            <select id="typeFilter">
                <option value="all">All Types</option>
            </select>
            <label for="statusFilter">All Statuses</label>
            <select id="statusFilter">
                <option value="all">All Statuses</option>
            </select>
            <input type="text" id="searchKeyword" placeholder="Enter keyword(s) to search">
            <button type="button" onclick="search()">Search</button>
            <a href="SettingDetails.jsp" style="float: right;">New Setting</a>

        </div>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Value</th>
                    <th>Priority</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Manager</td>
                    <td>User Role</td>
                    <td></td>
                    <td>2</td>
                    <td>Active</td>
                    <td><a href="#">Edit</a> <a href="#">Deactivate</a></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Admin</td>
                    <td>User Role</td>
                    <td></td>
                    <td>1</td>
                    <td>Inactive</td>
                    <td><a href="#">Edit</a> <a href="#">Activate</a></td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Teacher</td>
                    <td>User Role</td>
                    <td></td>
                    <td>4</td>
                    <td>Active</td>
                    <td><a href="#">Edit</a> <a href="#">Deactivate</a></td>
                </tr>
            </tbody>
        </table>
        <div class="pagination">
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">...</a>
            <a href="#">9</a>
            <a href="#">10</a>
        </div>
    </div>
    <script>
        function search() {
            // Implement search functionality here
        }
    </script>
</body>
</html>
