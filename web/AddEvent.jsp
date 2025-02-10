<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Event</title>
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
            .club-container {
                width: 80%;
                max-width: 800px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .club-container h1 {
                text-align: center;
                margin-bottom: 20px;
            }
            .club-section {
                margin-bottom: 15px;
            }
            .club-section label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }
            .club-section input, .club-section textarea, .club-section select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #f9f9f9;
            }
            .radio-group {
                display: flex;
                gap: 15px;
            }
            .radio-group label {
                display: flex;
                align-items: center;
                gap: 5px;
                font-weight: normal;
            }
            .error-message {
                color: red;
                font-size: 14px;
                display: none;
            }
            .submit-btn {
                width: 100%;
                padding: 10px;
                border: none;
                background-color: #28a745;
                color: white;
                font-size: 16px;
                border-radius: 4px;
                cursor: pointer;
            }
            .submit-btn:hover {
                background-color: #218838;
            }
        </style>
        <script>
            function validateForm(event) {
                let isValid = true;
                let fields = document.querySelectorAll("input[required], textarea[required]");
                
                fields.forEach(field => {
                    let errorMsg = field.nextElementSibling;
                    if (!field.value.trim()) {
                        errorMsg.style.display = "block";
                        isValid = false;
                    } else {
                        errorMsg.style.display = "none";
                    }
                });
                
                if (!isValid) {
                    event.preventDefault();
                }
            }
        </script>
    </head>
    <body>
        <div class="club-container">
            <h1>Add New Event</h1>
            <h2><a href="listEvent" class="event-list-link">Event List</a></h2>
            <form action="addEvent" method="post" onsubmit="validateForm(event)">
                <div class="club-section">
                    <label for="clubName">Club Name</label>
                    <input type="text" id="clubName" name="clubName" required>
                    <span class="error-message">This field is required.</span>
                </div>
                <div class="club-section">
                    <label for="eventName">Event Name</label>
                    <input type="text" id="eventName" name="eventName" required>
                    <span class="error-message">This field is required.</span>
                </div>
                <div class="club-section">
                    <label for="clubDescription">Description</label>
                    <textarea id="clubDescription" name="clubDescription" required></textarea>
                    <span class="error-message">This field is required.</span>
                </div>
                <div class="club-section">
                    <label for="eventTime">Time</label>
                    <input type="datetime-local" id="eventTime" name="eventTime" required>
                    <span class="error-message">This field is required.</span>
                </div>
                <div class="club-section">
                    <label>Event Type</label>
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="eventType" value="true" required> Online
                        </label>
                        <label>
                            <input type="radio" name="eventType" value="false" required> Offline
                        </label>
                    </div>
                </div>

                <div class="club-section">
                    <label>Event Status</label>
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="eventStatus" value="true" required> Active
                        </label>
                        <label>
                            <input type="radio" name="eventStatus" value="false" required> Inactive
                        </label>
                    </div>
</div>
                <div class="club-section">
                    <label for="clubMembers">Images (URL)</label>
                    <input type="text" id="clubMembers" name="Images">
                </div>
                <div class="club-section">
                    <label for="contactInfo">Contact Information</label>
                    <input type="text" id="contactInfo" name="contactInfo" required>
                    <span class="error-message">This field is required.</span>
                </div>
                <button type="submit" class="submit-btn">Add Event</button>
            </form>
        </div>
    </body>
</html>
