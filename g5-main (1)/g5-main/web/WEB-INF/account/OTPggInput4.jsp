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
    <c:if test="${erOTP !=null}">${erOTP}</c:if>
    <c:if test="${otpgg !=null}"> ${otpgg}</c:if>
        <body class="flex items-center justify-center h-screen bg-gradient-to-r from-purple-500 to-indigo-600 text-white">

            <div class="bg-white p-6 rounded-2xl shadow-lg text-black w-80 text-center">
                <!-- Tiêu đề -->
                <h2 class="text-xl font-bold mb-4">Nhập mã OTP</h2>

                <!-- Hiển thị thông báo lỗi -->
            <c:if test="${not empty errorMessage}">
                <div class="text-red-500 font-semibold mb-4">
                    ${errorMessage}
                </div>
            </c:if>

            <!-- Form nhập OTP -->
            <form action="${pageContext.request.contextPath}/userProfile?action=verifyOTP" method="post">
                <!-- Input OTP -->

                <div class="flex flex-col gap-4 mb-4">
                    <input 
                        name="otp" 
                        type="text" 
                        placeholder="Nhập mã OTP"
                        required 
                        class="border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-500" 
                        maxlength="6"
                        >
                </div>

                <!-- Nút xác nhận -->
                <button 
                    type="submit" 
                    class="w-full bg-indigo-500 hover:bg-indigo-600 text-white py-2 rounded-lg transition-colors">
                    Xác nhận
                </button>
            </form>
        </div>
    </body>
</html>