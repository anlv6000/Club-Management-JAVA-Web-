<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.User" %>
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
            .form-group {
                display: flex;
                justify-content: space-between;
                gap: 30px;
                margin-bottom: 20px;
                flex-wrap: wrap;
            }
            .club-section {
                flex: 1;
                min-width: 48%;
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
                margin-top: 5px;
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
    </head>
    <body>
        <div class="club-container">
            <h2><a href="listEvent" class="event-list-link">Event List</a></h2>
            <form action="addEvent" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="club-section">
                        <label for="clubName">Club Name</label>
                        <select id="clubName" name="clubName" required>
                            <c:forEach var="club" items="${c}">
                                <option value="${club.clubID}" <c:if test="${club.clubID == clubName}">selected</c:if>>${club.clubName}</option>
                            </c:forEach>
                        </select>
                        <div class="error-message">${errors['clubError']}</div>
                    </div>
                    <div class="club-section">
                        <label for="eventName">Event Name</label>
                        <input type="text" id="eventName" name="eventName" value="${eventName}" required>
                        <div class="error-message">${errors['nameError']}</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="club-section">
                        <label for="clubDescription">Description</label>
                        <textarea id="clubDescription" name="clubDescription" required>${clubDescription}</textarea>
                        <div class="error-message">${errors['descriptionError']}</div>
                    </div>
                    <div class="club-section">
                        <label for="eventTime">Time Start</label>
                        <input type="datetime-local" id="eventTime" name="eventTime" value="${eventTime}" required>
                        <div class="error-message">${errors['timeError']}</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="club-section">
                        <label for="Images">Images</label>
                        <!--                    <input type="file" id="Images" name="Images" accept="image/*">
                                            <div class="error-message">${errors['imageError']}</div>-->
                        <input type="file" id="Images" name="Images" accept="image/*">
                        <div class="error-message">${errors['imageError']}</div>

                        <c:if test="${not empty imagePreviewPath}">
                            <div>
                                <p>Ảnh bạn đã chọn:</p>
                                <img src="${pageContext.request.contextPath}/${imagePreviewPath}" alt="Ảnh đã chọn" width="150" />
                                <p><small>Bạn có thể chọn lại ảnh nếu muốn.</small></p>
                            </div>
                        </c:if>

                    </div>
                    <div class="club-section">
                        <label for="eventTimeEnd">Time End</label>
                        <input type="datetime-local" id="eventTimeEnd" name="eventTimeEnd" value="${eventTimeEnd}" required>
                        <div class="error-message">${errors['timeEndError']}</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="club-section">
                        <label>Event Type</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="eventType" value="true" <c:if test="${eventType == 'true'}">checked</c:if> required> Online
                                </label>
                                <label>
                                    <input type="radio" name="eventType" value="false" <c:if test="${eventType == 'false'}">checked</c:if> required> Offline
                                </label>
                            </div>
                            <div class="error-message">${errors['typeError']}</div>
                    </div>
                    <div class="club-section">
                        <label>Event Status</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="eventStatus" value="true" <c:if test="${eventStatus == 'true'}">checked</c:if> required> Active
                                </label>
                                <label>
                                    <input type="radio" name="eventStatus" value="false" <c:if test="${eventStatus == 'false'}">checked</c:if> required> Inactive
                                </label>
                            </div>
                            <div class="error-message">${errors['statusError']}</div>
                    </div>
                </div>
                <button type="submit" class="submit-btn">Add Event</button>
            </form>
        </div>
    </body>
</html>