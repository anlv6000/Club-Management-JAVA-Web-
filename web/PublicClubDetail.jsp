<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        </style>
    </head>
    <body>
        <div class="club-container">
            <h1>Public Club Details</h1>
            <div class="club-section">
                <label for="clubName">Club Name</label>
                <div class="content" id="clubName"></div>
            </div>
            <div class="club-section">
                <label for="clubDescription">Description</label>
                <div class="content" id="clubDescription"></div>
            </div>
            <div class="club-section">
                <label for="clubMembers">Number of Members</label>
                <div class="content" id="clubMembers"></div>
            </div>
            <div class="club-section">
                <label for="clubLocation">Location</label>
                <div class="content" id="clubLocation"></div>
            </div>
            <div class="club-section">
                <label for="clubContact">Contact Information</label>
                <div class="content" id="clubContact"></div>
            </div>
            <div class="club-section">
                <label for="clubSchedule">Schedule</label>
                <div class="content" id="clubSchedule"></div>
            </div>
        </div>
    </body>
</html>
