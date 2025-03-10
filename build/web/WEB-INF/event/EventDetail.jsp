<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Public Club Details</title>
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
            .club-section .content {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #f9f9f9;
            }
            .radio-group label {
                display: inline-block;
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <div class="club-container">
            <h1>Event Details</h1>
             
            <h2><a href="listEvent" class="event-list-link">Event List</a></h2>
          
            <div class="club-section">
                
                <label for="clubName">Name Club</label>
                <div class="content" id="clubName">${o.getNameCLub()}</div>
            </div>
            
            <div class="club-section">
                <label for="eventName">Event Name</label>
                <div class="content" id="eventName">${o.eventName}</div>
            </div>
            
            <div class="club-section">
                <label for="clubDescription">Description </label>
                <div class="content" id="clubDescription">${o.description}</div>
            </div>
            
            <div class="club-section">
                <label for="eventTime">eventTime</label>
                <div class="content" id="eventTime">${o.eventDate}</div>
            </div>
            
            
           
            
                <div class="club-section">
                    <label for="eventType">Event Type</label>
                    <div class="content" id="eventType">
                        <label>
                            <input type="radio" name="eventType" value="true" ${o.type ? 'checked' : ''}> Online
                        </label>
                        <label>
                            <input type="radio" name="eventType" value="false" ${!o.type ? 'checked' : ''}> Offline
                        </label>
                    </div>
                </div>
               <div class="club-section">
                    <label for="eventType">Event Status</label>
                    <div class="content" id="eventType">
                        <label>
                            <input type="radio" name="eventStatus" value="true" ${o.status ? 'checked' : ''}> Active</label>
                        <label>
                            <input type="radio" name="eventStatus" value="false" ${!o.status ? 'checked' : ''}>Inactive</label>
                    </div>
                </div>
                             
                
            
            
            
            <div class="club-section">
                <label for="clubMembers">Name Club</label>
                <div class="content" id="clubMembers">${o.getNameCLub()}</div>
            </div>
            
            <div class="club-section">
                <label for="contactInfo">Event Image</label>
                <div class="content" id="contactInfo">${o.eventImageURL}</div>
            </div>
     
        </div>
    </body>
</html>