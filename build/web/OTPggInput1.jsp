<%-- 
    Document   : OTPggInput
    Created on : Feb 17, 2025, 10:06:38 AM
    Author     : Doan Quan
--%>






<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nhập mã OTP</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <form action="SendOTP1" method="post">
        <c:if test="${erOTP !=null}">${erOTP}</c:if>
        <c:if test="${otpgg !=null}"> ${otpgg}</c:if>
            <body class="flex items-center justify-center h-screen bg-gradient-to-r from-purple-500 to-indigo-600 text-white">
                <div class="bg-white p-6 rounded-2xl shadow-lg text-black w-80 text-center">
                    <h2 class="text-xl font-bold mb-4">Nhập mã OTP</h2>
                    <div class="flex justify-center gap-2 mb-4">
                        <input type="hidden" name="otpGG" value="${sessionScope.otpgg}">
                    <input  name="OTP" type="number"   placeholder="Enter OTP" required="" maxlength="1">

                </div>
                <button class="w-full bg-indigo-500 hover:bg-indigo-600 text-white py-2 rounded-lg">Xác nhận</button>
            </div>
        </body></form>
</html>
