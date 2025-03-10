<%@page import="Model.Club"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="css/Club.css">
<!DOCTYPE html>
<html>
    

<head>
    
    
    <title>Club Detail</title>
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
            background-color: #ffc107;
            color: black;
            border: none;
            padding: 10px;
            width: 100%;
            cursor: pointer;
            margin-top: 10px;
            border-radius: 5px;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .back-btn {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Club Details</h2>

        <%
            Club club = (Club) request.getAttribute("club");
            if (club != null) {
        %>
        <form action="UpdateClubServlet" method="post">
            <input type="hidden" name="clubID" value="<%= club.getClubID() %>">

            <label>Name:</label> 
            <input type="text" name="name" value="<%= club.getClubName() %>" required><br>

            <label>Category:</label> 
            <input type="text" name="category" value="<%= club.getCategory() %>" required><br>

            <label>Description:</label> 
            <textarea name="description" required><%= club.getDescription() %></textarea><br>

            <label>Image URL:</label> 
            <input type="text" name="imgURL" value="<%= club.getImgURL() %>" required><br>

            <label>Public:</label> 
            <select name="isPublic">
                <option value="true" <%= club.isIsPublic() ? "selected" : "" %>>Yes</option>
                <option value="false" <%= !club.isIsPublic() ? "selected" : "" %>>No</option>
            </select><br>

            <button type="submit">Update</button>
        </form>

<!--        <form action="DeleteClubServlet" method="post">
            <input type="hidden" name="clubID" value="<%= club.getClubID() %>">
            <button type="submit" class="delete-btn" onclick="return confirm('Are you sure?')">Delete</button>
        </form>-->

        <button class="back-btn" onclick="window.location.href='ClubServlet'">Back to List</button>
        <% } else { %>
            <p>Club not found!</p>
        <% } %>
    </div>
</body>
</html>
