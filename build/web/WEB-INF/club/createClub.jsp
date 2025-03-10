<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    
<head>
    
    
    <title>Create Club</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }
        .form-container {
            background: white;
            padding: 20px;
            border: 2px solid #333;
            border-radius: 10px;
            box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            cursor: pointer;
            margin-top: 10px;
            border-radius: 5px;
        }
        .back-btn {
            background-color: #007bff;
        }
    </style>
</head>
<body>
    
    <div class="form-container">
        <h2>Create New Club</h2>
        <form action="CreateClubServlet" method="post">
            <label>Name:</label> 
            <input type="text" name="name" required><br>

            <label>Category:</label> 
            <input type="text" name="category" required><br>

            <label>Description:</label> 
            <textarea name="description" required></textarea><br>

            <label>Image URL:</label> 
            <input type="text" name="imgURL" required><br>

            <label>Public:</label> 
            <select name="isPublic">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
            </select><br>

            <button type="submit">Create</button>
        </form>

        <button class="back-btn" onclick="window.location.href='ClubServlet'">Back to List</button>
    </div>
</body>
</html>
