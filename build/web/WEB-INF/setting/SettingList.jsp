<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/home/sidebar.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Setting List</title>
        <style>
            .popup {
                position: fixed;
                bottom: 20px;
                right: 20px;
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                opacity: 0;
                transition: opacity 0.3s ease-in-out;
                z-index: 1000;
            }
            .popup.show {
                opacity: 1;
            }
        </style>

        


        <style>
            body {
                position: relative;
                background-image: url('https://daihoc.fpt.edu.vn/en/wp-content/uploads/2024/09/DHFPT6-1.webp');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
            }

            body::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: rgba(255, 255, 255, 0.5); /* 50% transparency layer */
                z-index: -1; /* Place it behind any content */
            }

            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-wrap: wrap;
                padding: 20px;
                background-color: #333;
                color: white;
            }
            header img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                cursor: pointer;
            }
            footer {
                text-align: center;
                padding: 20px;
                background-color: #333;
                color: white;
                bottom: 0;
                width: 100%;
                font-size: 14px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            footer div {
                margin: 5px 0;
            }
            footer a {
                color: white;
                margin: 0 10px;
                text-decoration: none;
            }
            footer a:hover {
                text-decoration: underline;
            }
            .header-logo {
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .header-logo img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                cursor: pointer;
                object-fit: cover;
            }
            .header-logo h1 {
                margin: 0;
                font-size: 20px;
                color: #f4f4f9;
            }
            body {
                display: flex;
                justify-content: center;
                align-items: first;
                min-height: 100vh;
                margin: 0;
            }
            .container {
                margin-left: 250px;
                width: 80%;
                margin-top: 50px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #f4f4f9;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
                position: relative;
            }
            th {
                background-color: #4a637d;
                color: white;
            }
            .sort-buttons {
                display: flex;
                flex-direction: column;
                margin-left: 5px;
            }
            .sort-buttons button {
                background: none;
                border: none;
                cursor: pointer;
                padding: 0;
                margin: 0;
            }
            .sort-buttons img {
                width: 10px;
                height: 10px;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;

            }
            .pagination a {
                margin: 0 5px;
                padding: 8px 16px;
                text-decoration: none;
                border: 1px solid #ddd;
                background-color: #f4f4f9;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;

            }
        </style>

    </head>
    <body>
        <div id="noResultsPopup" class="popup">Không có kết quả phù hợp!</div>
        <div id="successPopup" class="popup">Cài đặt đã được lưu thành công!</div>
        <div class="container">
            <h1>Setting List</h1>
            <div>
                <form action="${pageContext.request.contextPath}/settings" method="get" style="float: left;">
                    <input type="text" id="searchKeyword" name="searchKeyword" placeholder="Enter keyword(s) to search">
                    <button type="submit">Search</button>
                </form>
                <form action="settingForm.jsp" method="get" style="float: right;">
                    <button type="submit">New Setting</button>
                </form>
            </div>
            <br>
            <br>


            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Type

                        </th>
                        <th>Value</th>
                        <th>Priority</th>
                        <th>Status
                            <div class="sort-buttons" style="display: inline-block;">
                                <form action="${pageContext.request.contextPath}/settings" method="get" style="display: inline;">
                                    <button type="submit" name="statusSort" value="az">
                                        <img src="https://www.pngarts.com/files/2/Up-Arrow-PNG-Background-Image.png" alt="Sort A-Z">
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/settings" method="get" style="display: inline;">
                                    <button type="submit" name="statusSort" value="za">
                                        <img src="https://th.bing.com/th/id/R.61784c37a2e26bd4afa05c2777d50907?rik=dRxz2txHboft%2bA&riu=http%3a%2f%2fpluspng.com%2fimg-png%2fdown-arrow-png-down-icon-1600.png&ehk=AtCYQs09Cv%2b2Br%2fb7%2bOJShaXA8L4Iwcv6m%2fWOcZI4f0%3d&risl=&pid=ImgRaw&r=0" alt="Sort Z-A">
                                    </button>
                                </form>
                            </div>
                        </th>
                        <th>User Type
                            <div class="sort-buttons" style="display: inline-block;">
                                <form action="${pageContext.request.contextPath}/settings" method="get" style="display: inline;">
                                    <button type="submit" name="typeSort" value="az">
                                        <img src="https://www.pngarts.com/files/2/Up-Arrow-PNG-Background-Image.png" alt="Sort A-Z">
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/settings" method="get" style="display: inline;">
                                    <button type="submit" name="typeSort" value="za">
                                        <img src="https://th.bing.com/th/id/R.61784c37a2e26bd4afa05c2777d50907?rik=dRxz2txHboft%2bA&riu=http%3a%2f%2fpluspng.com%2fimg-png%2fdown-arrow-png-down-icon-1600.png&ehk=AtCYQs09Cv%2b2Br%2fb7%2bOJShaXA8L4Iwcv6m%2fWOcZI4f0%3d&risl=&pid=ImgRaw&r=0" alt="Sort Z-A">
                                    </button>
                                </form>
                            </div>
                        </th>

                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="setting" items="${settingsList}">
                        <tr>
                            <td>${setting.id}</td>
                            <td>${setting.name}</td>
                            <td>${setting.type}</td>
                            <td>${setting.value}</td>
                            <td>${setting.priority}</td>
                            <td>${setting.status}</td>
                            <td>${setting.userType}</td>
                            <td>
                                <a href="settingForm.jsp?id=${setting.id}">Edit</a>
                                <a href="toggleStatus?id=${setting.id}&currentStatus=${setting.status}">${setting.status == 'Active' ? 'Deactivate' : 'Activate'}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <a href="${pageContext.request.contextPath}/settings?currentPage=${page}">${page}</a>
                </c:forEach>
            </div>
        </div>
        <script>
            function showSuccessPopup() {
                var popup = document.getElementById('successPopup');
                popup.classList.add('show');
                setTimeout(function () {
                    popup.classList.remove('show');
                }, 3000);
            }

            // Kiểm tra xem URL có chứa tham số success=true hay không
            if (window.location.search.includes('success=true')) {
                showSuccessPopup();
            }
        </script>
        <script>
            function showNoResultsPopup() {
                var popup = document.getElementById('noResultsPopup');
                popup.classList.add('show');
                setTimeout(function () {
                    popup.classList.remove('show');
                }, 3000);
            }

            // Kiểm tra xem URL có chứa tham số noResults=true hay không
            if (window.location.search.includes('noResults=true')) {
                showNoResultsPopup();
            }
        </script>


    </body>
</html>
