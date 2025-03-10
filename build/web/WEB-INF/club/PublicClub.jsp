<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${club.name} - Club Details</title>
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f4f4f9;
            color: #333;
        }

        header {
            background-color: #0044cc;
            color: white;
            text-align: center;
            padding: 20px;
            font-size: 28px;
            font-weight: bold;
        }

        .club-image {
            text-align: center;
            margin: 20px 0;
        }

        .club-image img {
            width: 80%;
            max-width: 600px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        }

        .content-section {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #0044cc;
            margin-bottom: 10px;
        }

        p, ul {
            font-size: 16px;
            line-height: 1.6;
        }

        ul {
            padding-left: 20px;
        }

        .club-events {
            text-align: center;
        }

        .event-card {
            display: inline-block;
            width: 300px;
            margin: 15px;
            padding: 15px;
            border-radius: 10px;
            background-color: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            text-align: left;
        }

        .event-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 10px;
        }

        .event-card p {
            margin: 10px 0;
        }

        .event-card strong {
            color: #0044cc;
        }

        .contact {
            text-align: center;
            background-color: #0044cc;
            color: white;
            padding: 20px;
            margin-top: 30px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <header>
        <h1>${detail.getClubName()}</h1>
    </header>

    <main>
        <div class="club-image">
            <img src="${detail.getImgURL()}" alt="${club.name}">
        </div>

        <section class="content-section">
            <h2>Giới thiệu về câu lạc bộ</h2>
            <p>${detail.description}</p>
        </section>

        <section class="content-section">
            <h2>Thời Khóa Biểu</h2>
            <p>${detail.schedule}</p>
        </section>

        <section class="content-section">
            <h2>Cơ cấu tổ chức</h2>
            <p><strong>Ban chủ nhiệm :${detail.clubleader}</strong></p>
            <p><strong>Các ban chức năng:</strong></p>
            <ul>
                <li>Ban Chuyên môn</li>
                <li>Ban Truyền thông - Kỹ thuật</li>
                <li>Ban Đối ngoại</li>
                <li>Ban Tài chính - Nhân sự</li>
            </ul>
        </section>

        <section class="content-section club-events">
            <h2>Hoạt động và thành tích nổi bật</h2>
            <c:forEach var="event" items="${event}">
                <div class="event-card">
                    <img src="${event.eventImageURL}" alt="${event.eventName}">
                    <p><strong>${event.eventName}</strong></p>
                    <p>${event.description}</p>
                    <p><strong>Thời gian:</strong> ${event.eventDate}</p>
                </div>
            </c:forEach>
        </section>
    </main>

    <footer class="contact">
        <h2>Liên hệ</h2>
        <p>Email: ${detail.contactClub}</p>
        
    </footer>
</body>
</html>
