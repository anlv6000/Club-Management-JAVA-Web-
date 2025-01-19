<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT Club Manager</title>
       <link href="css/Home.css" rel="stylesheet" type="text/css"/>
    <style>
        /*
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/CascadeStyleSheet.css to edit this template
*/
/* 
    Created on : Jan 14, 2025, 10:09:33 PM
    Author     : Doan Quan
*/

/*
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/CascadeStyleSheet.css to edit this template
*/
/* 
    Created on : Jan 14, 2025, 9:58:46 PM
    Author     : Doan Quan
*/


    </style>
   
</head>
<body>
    <header>
        <div>
            <img <img src="https://aothethaothietke.com/wp-content/uploads/2022/05/Logo-cau-lac-bo-bong-da-Ngoai-hang-Anh-%E2%80%93-Manchester-United.jpg.web" alt="alt"/>
            <h1>FPT Club Manager</h1>
        </div>
        
        <c:if test="${sessionScope.acc != null }"><div> hello ${sessionScope.txtUsername} </div></c:if>
        <div id="logo-container">
            <!-- Default logo -->
            <img  src="https://media.discordapp.net/attachments/1326181143841869847/1328762115007451279/image.png?ex=6787e1a7&is=67869027&hm=982177fd4bb7b909b36866ac49ca606b0ccc15ffa87e3ade47020082b388cd13&=&format=webp&quality=lossless"   alt="Logo">
        </div>
         
    </header>
    <div>
        <a href="#">Home</a>
            <a href="PublicClub.jsp">Public Club</a>
            <c:if test="${sessionScope.acc ==null}">
            <a href="Login.jsp">Sign in</a>
            
            <a href="Register.jsp">Sign up</a>
            </c:if> 
            <c:if test="${sessionScope.acc !=null}">
                <a href="Login.jsp">Log out</a>
            </c:if>
            
    </div>

   

    <div class="body-content">
    <h2>Welcome to FPT Club!</h2>
    <p>Explore our club activities and join us today!</p>

    <!-- Images representing club activities -->
    <div class="image-grid">
        <div class="image-item">
            <img src="https://scontent.fhan14-5.fna.fbcdn.net/v/t39.30808-6/469172292_1140603951400568_7011573355440393714_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=XM7Ez2rumNYQ7kNvgEG1eP1&_nc_oc=Adh1sGToFQMGPC5btfObw7Exfayn-YjMlCEwr_eoZCJLRAV5AAavq3MN4nl15DfgwqU&_nc_zt=23&_nc_ht=scontent.fhan14-5.fna&_nc_gid=AdRXLh8QSaqHIQFZoXkNAmC&oh=00_AYB-Yadj7VdwCGgzCY4Dje4gpMfvQ-O9eO0gzr5xL6FFvg&oe=678C6AC0" alt="FPTU Vovinam Club">
            <div>FPTU Vovinam Club</div>
        </div>
        <div class="image-item">
            <img src="https://scontent.fhan14-5.fna.fbcdn.net/v/t39.30808-6/471409525_1123149743147613_3414230291685001860_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeFAmqAC7Cbvo4iPpv_jooaTriim5f8m-nCuKKbl_yb6cPNwKt0HyUL05Qx0fhBiF4QB-6S4Zt51l3wjxuxRXy3g&_nc_ohc=g2bJ3s-Geo0Q7kNvgGhGF0I&_nc_oc=AdiPEq1AJz6WuZHXGl0H1gL2axoeSLGyoxnI1J0AyVK9TPoTDIT2dLeK2CEM12GpQGA&_nc_zt=23&_nc_ht=scontent.fhan14-5.fna&_nc_gid=AINfsGy_wJmzjCcci5eMzZ3&oh=00_AYCd5CBbk6CVDz8ibgIyPWfIyCCUCqfz0cxbI7tUsFX5DQ&oe=678C4E9E" alt="FPTU JS Club">
            <div>FPTU JS Club</div>
        </div>
        <div class="image-item">
            <img src="https://scontent.fhan5-3.fna.fbcdn.net/v/t39.30808-6/347821076_980098233008646_7495727165848509253_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeEmg5OqmbZnaW5x-bArnRWuwKCEU4qLW9_AoIRTiotb37MK8KYNjkhGoYc6BOn9o2Ff1LxdPOArauVQ2z0Z2o-G&_nc_ohc=5iqfTJaJqLIQ7kNvgF60377&_nc_zt=23&_nc_ht=scontent.fhan5-3.fna&_nc_gid=ARqGiI2ALfoVgmQo7krzW_Z&oh=00_AYBE2H6-rWwamGAtdltf31Xs8hxOrZ8IAcxAZ0Nj8x662Q&oe=678C4855" alt="FPTU Trượt ván Club">
            <div>FPTU Trượt ván Club</div>
        </div>
        <div class="image-item">
            <img src="https://scontent.fhan5-7.fna.fbcdn.net/v/t39.30808-6/302164671_591888282641082_916418633174518780_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeGRuEqBZrEHOGf-92uXrPGQrQH5QZQEx3WtAflBlATHdZjK3FvhBmtTcvknlW9duGMkhYV62LOOBTBAOvYeYO5g&_nc_ohc=TYVwfIXBtu4Q7kNvgEFYWKt&_nc_zt=23&_nc_ht=scontent.fhan5-7.fna&_nc_gid=AG-Dncx-9PTufkKazi87455&oh=00_AYCCgsiC2UCNM7NiZBcfPF2CrU_n-gt6gpg57u138Ha8DA&oe=678C4974" alt="FPT bóng chuyền Club">
            <div>FPT bóng chuyền Club</div>
        </div>
        <div class="image-item">
            <img src="https://scontent.fhan5-7.fna.fbcdn.net/v/t39.30808-6/473107664_1079187614005921_4012436355800079522_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeGdSy0nzgwRpy1uQ6-63a3spNH96Zyo1gik0f3pnKjWCHSvQ_a6dqgkpu5my2dwh_oxbjA9-kISkojdqQ9yQ-UD&_nc_ohc=4gCGBLj9ARQQ7kNvgFGwUuP&_nc_zt=23&_nc_ht=scontent.fhan5-7.fna&_nc_gid=AGIktRzo4ttz2JizeO5DGXc&oh=00_AYCb45GScZZ1BJjoxYs3CsDnGADsc7JD5e2O_auagRZQIQ&oe=678C3B0E" alt="FPT Muay Thai">
            <div>FPT Muay Thai</div>
        </div>
    </div>
</div>

    <footer>
        <p>&copy; 2025 FPT Club Manager. All Rights Reserved.</p>
    </footer>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const logo = document.getElementById("logo");

            // Simulate logged-in state for demonstration
            let isLoggedIn = false;
            let userAvatar = "user-avatar.png"; // Change to actual uploaded avatar

            // Update logo based on login state
            if (isLoggedIn) {
                logo.src = userAvatar;
                logo.alt = "User Avatar";
            } else {
                logo.src = "default-user.png";
                logo.alt = "Default Logo";
            }

            // Add click event to the logo
            logo.addEventListener("click", function() {
                if (isLoggedIn) {
                    alert("Welcome back, User!");
                } else {
                    const options = confirm("You are not logged in. Would you like to login or register?");
                    if (options) {
                        window.location.href = "#"; // Link to login/register page
                    }
                }
            });
        });
       
    </script>
</body>
</html>