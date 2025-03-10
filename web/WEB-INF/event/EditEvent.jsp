<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Event</title>
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
                text-align: center;
            }
            .club-container h1 {
                margin-bottom: 20px;
            }
            .club-section {
                margin-bottom: 15px;
                text-align: left;
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
            .edit-btn {
                width: 100%;
                padding: 10px;
                border: none;
                background-color: #007bff;
                color: white;
                font-size: 16px;
                border-radius: 4px;
                cursor: pointer;
                margin-top: 15px;
            }
            .edit-btn:hover {
                background-color: #0056b3;
            }
        </style>
        <script>
            function confirmEdit(event) {
                let confirmAction = confirm("Bạn có muốn lưu thay đổi không?");
                if (!confirmAction) {
                    event.preventDefault();
                    window.location.href = "evenList.jsp";
                }
            }
        </script>
    </head>
    <body>
        <div class="club-container">
            <h1>Edit Event</h1>
            <form action="editEvent" method="post" onsubmit="confirmEdit(event)">
                <input type="hidden" name="id" value=${o.id}>
                <div class="club-section">
                    <label for="clubName">ID</label>
                    <input type="text" id="clubName" name="ID" value=${o.clubId}>
                </div>
                <div class="club-section">
                    <label for="eventName">Event Name</label>
                    <input type="text" id="eventName" name="eventName" value=${o.eventName}>
                </div>
                <div class="club-section">
                    <label for="clubDescription">Description</label>
                    <textarea id="clubDescription" name="clubDescription">${o.description}</textarea>
                </div>
                <div class="club-section">
                    <label for="eventTime">Time</label>
                   
                    <input type="datetime-local" id="eventTime" name="eventTime" value="${o.eventDate}"}>
                </div>
                    <div class="club-section">
                        <label>Event Type</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="eventType" value="true" ${o.type ? 'checked' : ''}> Online
                            </label>
                            <label>
                                <input type="radio" name="eventType" value="false" ${!o.type ? 'checked' : ''}> Offline
                            </label>
                        </div>
                    </div>

                    <div class="club-section">
                        <label>Event Status</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="eventStatus" value="true" ${o.status ? 'checked' : ''}> Active
                            </label>
                            <label>
                                <input type="radio" name="eventStatus" value="false" ${!o.status ? 'checked' : ''}> Inactive
                            </label>
                        </div>
                    </div>                <div class="club-section">
                    <label for="clubMembers">Images (URL)</label>
                    <input type="text" id="clubMembers" name="clubMembers" value=${o.eventImageURL}>
                </div>
                <div class="club-section">
                    <label for="contactInfo">Contact Information</label>
                    <input type="text" id="contactInfo" name="contactInfo" value="0966980651">
                </div>
                <button type="submit" class="edit-btn">Edit</button>
            </form>
            <a href="listEvent">Back to Event List</a>
        </div>
    </body>
</html>
