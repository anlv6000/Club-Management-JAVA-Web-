<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <style>
            .status-saved {
                color: gray;
            }
            .status-submitted {
                color: #d58512;
            }
            .status-approved {
                color: green;
            }
            .status-rejected {
                color: red;
            }
        </style>

        <style>
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
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }
        </style>

        <style>
            .table {
                width: 100%;
                border-collapse: collapse;
            }
            .table th, .table td {
                border: 1px solid #ddd;
                padding: 8px;
            }
            .table th {
                background-color: #8c9190;
                color: white;
                text-align: center;
            }
            .table tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .table th.sortable:hover {
                cursor: pointer;
            }





            .d-flex {
                display: flex;
            }
            /* CSS Reset */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Container chiếm toàn bộ chiều rộng */
            .container-fluid {
                width: 100%;
            }

            /* Flexbox cho bố cục */
            .d-flex {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
            }

            /* Phần bên trái */
            .left-section {
                display: flex;
                align-items: center;
            }

            /* Phần bên phải */
            .right-section {
                color: #6e9ee0;
            }

            /* Loại bỏ khoảng cách mặc định của các phần tử */
            .left-section label,
            .left-section select,
            .left-section input,
            .right-section a {
                margin: 0;
                padding: 0;
            }

            /* Khoảng cách giữa các phần tử trong left-section */
            .left-section label,
            .left-section select,
            .left-section input {
                margin-right: 5px;
            }

            /* Đảm bảo nút nằm sát lề phải */
            .right-section a {
                display: block;
            }

            /* Loại bỏ khoảng cách của body và html */
            body, html {
                margin: 0;
                padding: 0;
            }



            .notification {
                position: fixed;
                top: 200px; /* Margin from the top */
                right: 20px; /* Align to the right */
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                border-radius: 5px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
            }






        </style>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Finance</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

        <!-- 
              //////////////////////////////////////////////////////
      
              FREE HTML5 TEMPLATE 
              DESIGNED & DEVELOPED by FreeHTML5.co
                      
              Website: 		http://freehtml5.co/
              Email: 			info@freehtml5.co
              Twitter: 		http://twitter.com/fh5co
              Facebook: 		https://www.facebook.com/fh5co
      
              //////////////////////////////////////////////////////
        -->

        <!-- Facebook and Twitter integration -->
        <meta property="og:title" content=""/>
        <meta property="og:image" content=""/>
        <meta property="og:url" content=""/>
        <meta property="og:site_name" content=""/>
        <meta property="og:description" content=""/>
        <meta name="twitter:title" content="" />
        <meta name="twitter:image" content="" />
        <meta name="twitter:url" content="" />
        <meta name="twitter:card" content="" />

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" href="favicon.ico">
        <link href='https://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'> -->

        <!-- Animate.css -->
        <link rel="stylesheet" href="css/animate.css">
        <!-- Icomoon Icon Fonts-->
        <link rel="stylesheet" href="css/icomoon.css">
        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <!-- Flexslider  -->
        <link rel="stylesheet" href="css/flexslider.css">
        <!-- Theme style  -->
        <link rel="stylesheet" href="css/style.css">

        <!-- Modernizr JS -->
        <script src="js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="js/respond.min.js"></script>
        <![endif]-->
        <script>
            function sortTransactions(columnIndex, order) {
                var table = document.querySelector(".table tbody");
                var rowsArray = Array.from(table.rows);

                rowsArray.sort(function (a, b) {
                    var textA = a.cells[columnIndex].innerText.trim().toLowerCase();
                    var textB = b.cells[columnIndex].innerText.trim().toLowerCase();

                    if (columnIndex === 2) { // Sắp xếp theo Amount
                        var numA = parseFloat(textA.replace(/,/g, ''));
                        var numB = parseFloat(textB.replace(/,/g, ''));
                        if (order === 'asc') {
                            return numA - numB;
                        } else {
                            return numB - numA;
                        }
                    } else if (columnIndex === 5) { // Sắp xếp theo Transaction Type
                        if (order === 'asc') {
                            return textA.localeCompare(textB);
                        } else {
                            return textB.localeCompare(textA);
                        }
                    } else {
                        // Các cột khác
                        if (order === 'asc') {
                            return textA.localeCompare(textB);
                        } else {
                            return textB.localeCompare(textA);
                        }
                    }
                });

                table.innerHTML = "";
                rowsArray.forEach(function (row) {
                    table.appendChild(row);
                });
            }

            function filterTransactions(type) {
                var table = document.querySelector(".table tbody");
                var rowsArray = Array.from(table.rows);

                rowsArray.forEach(function (row) {
                    var isExpense = row.cells[4].innerText.trim(); // Assuming isExpense is in column 5

                    if (isExpense.toLowerCase() === type.toLowerCase() || type.toLowerCase() === 'all') {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }

            function filterTransactionsByStatus(status) {
                var table = document.querySelector(".table tbody");
                var rowsArray = Array.from(table.rows);

                rowsArray.forEach(function (row) {
                    var transactionStatus = row.cells[6].innerText.trim(); // Assuming status is in column 7

                    if (transactionStatus.toLowerCase() === status.toLowerCase() || status.toLowerCase() === 'all') {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }


            function searchTransactions() {
                var input = document.getElementById("search");
                var filter = input.value.toLowerCase();
                var table = document.querySelector(".table tbody");
                var rows = table.getElementsByTagName("tr");

                for (var i = 0; i < rows.length; i++) {
                    var cells = rows[i].getElementsByTagName("td");
                    var match = false;

                    for (var j = 0; j < cells.length; j++) {
                        if (cells[j].innerText.toLowerCase().includes(filter)) {
                            match = true;
                            break;
                        }
                    }

                    if (match) {
                        rows[i].style.display = "";
                    } else {
                        rows[i].style.display = "none";
                    }
                }
            }

            // Open the Modal
            function openModal() {
                document.getElementById("myModal").style.display = "block";
                document.body.style.overflow = 'hidden'; // Ngăn chặn cuộn khi modal mở
            }

// Close the Modal
            function closeModal() {
                document.getElementById("myModal").style.display = "none";
                document.body.style.overflow = ''; // Cho phép cuộn lại khi modal đóng
            }

// Close the modal when the user clicks outside of it
            window.onclick = function (event) {
                if (event.target == document.getElementById("myModal")) {
                    document.getElementById("myModal").style.display = "none";
                    document.body.style.overflow = ''; // Cho phép cuộn lại khi modal đóng
                }
            }


            function showNotification(message) {
                const notification = document.createElement('div');
                notification.className = 'notification';
                notification.innerText = message;
                document.body.appendChild(notification);

                setTimeout(() => {
                    notification.remove();
                }, 3000); // Display for 3 seconds
            }
        </script>

        <style>
            .table th:nth-child(7), .table td:nth-child(7) {
                width: 200px; /* Điều chỉnh độ rộng theo ý muốn */
            }
            .table th:nth-child(2), .table td:nth-child(2) {
                width: 200px; /* Điều chỉnh độ rộng theo ý muốn */
            }
            .table th:nth-child(3), .table td:nth-child(3) {
                width: 150px; /* Điều chỉnh độ rộng theo ý muốn */
            }
            .table th:nth-child(4), .table td:nth-child(4) {
                width: 110px; /* Điều chỉnh độ rộng theo ý muốn */
            }
            .table th:nth-child(5), .table td:nth-child(5) {
                width: 110px; /* Điều chỉnh độ rộng theo ý muốn */
            }
            .table th:nth-child(6), .table td:nth-child(6) {
                width: 165px; /* Điều chỉnh độ rộng theo ý muốn */
            }
        </style>

        <style>
            /* The Modal (background) */
            .modal {
                display: none;
                position: fixed;
                z-index: 9999; /* Đảm bảo modal nằm trên các phần tử khác */
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: hidden; /* Ngăn chặn trang popup lăn lên xuống */
                background-color: rgba(0,0,0,0.4); /* Màu nền mờ */
                justify-content: center; /* Canh giữa theo chiều ngang */
                align-items: center; /* Canh giữa theo chiều dọc */
                padding-top: 10px;
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: auto; /* Canh giữa theo chiều dọc */
                padding: 20px;
                border: 1px solid #888;
                width: 60%; /* Giảm kích thước xuống 40% */
                max-width: 600px; /* Giới hạn chiều rộng tối đa */
                overflow: hidden; /* Ngăn chặn nội dung trong modal cuộn */
                z-index: 10000; /* Đảm bảo modal-content nằm trên modal background */
            }

            /* The Close Button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                z-index: 10001; /* Đảm bảo nút đóng nằm trên cùng */
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .form-group {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                margin-bottom: 15px;
                width: 100%;
            }

            .form-group label {
                margin-bottom: 5px;
                width: 100%;
            }

            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 8px;
                border: 2px solid #f4f4f9;
                border-radius: 4px;
            }
            .form-row {
                display: flex;
                gap: 20px; /* Space between the fields */
                align-items: center;
                flex-wrap: wrap; /* Ensure responsiveness */
            }

            .form-amount, .form-date {
                flex: 1;
                min-width: 250px; /* Minimum width to maintain layout */
            }

            .form-amount input,
            .form-date input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .amount-error-message {
                color: red;
                font-size: 0.85em;
                display: none; /* Hidden by default */
            }


        </style>
        <jsp:include page="header.jsp"></jsp:include>
        </head>
        <body>
        <c:if test="${not empty sessionScope.notificationMessage}">
            <script>
                showNotification("${sessionScope.notificationMessage}");
            </script>
            <c:remove var="notificationMessage" scope="session"/>
        </c:if>
        <a href="${pageContext.request.contextPath}/FinancePage?settingID=${financeSettingID}"></a>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <h1>New Finance</h1>
                <form action="${pageContext.request.contextPath}/finance?action=insert" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                    <div style="display: flex; align-items: flex-start; gap: 20px;">
                        <div class="form-group" style="flex: 1;">
                            <label for="finance_name">Finance Name*</label>
                            <input type="text" id="finance_name" name="finance_name" maxlength="200" >
                            <span id="nameError" class="error-message" style="color: red; display: none;">Finance Name cannot be more than 50 characters.</span>
                        </div>
                        <div class="form-group" style="flex: 1;">
                            <label>Type</label>
                            <div style="display: flex; gap: 10px;">
                                <label style="display: flex; align-items: center;">
                                    <input type="radio" id="is_expense_yes" name="is_expense" value="Yes" required>
                                    Expense
                                </label>
                                <label style="display: flex; align-items: center;">
                                    <input type="radio" id="is_expense_no" name="is_expense" value="No" required>
                                    Unexpense
                                </label>
                            </div>
                        </div>
                    </div>
                    <div style="display: flex; align-items: center; gap: 10px;">
                        <div class="form-group" style="flex: 1;">
                            <label for="amount">Amount (K VND)</label>
                            <input type="number" id="amount" name="amount" step="0.01" >
                            <span id="amountError" class="error-message" style="color: red; display: none;">Amount cannot be more than 99999 or less than 0</span>
                        </div>
                        <div class="form-group" style="flex: 1;">
                            <label for="finance_date">Finance Date*</label>
                            <input type="datetime-local" id="finance_date" name="finance_date" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="club_id">Select CLub*</label>
                        <select id="club_id" name="club_id" required>
                            <c:forEach var="club" items="${clubsList}">
                                <option value="${club.clubID}">${club.clubName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="details">Details (*Ghi rõ chi tiêu cho việc gì)</label>
                        <textarea id="details" name="details"></textarea> <!-- Có thể để trống -->
                    </div>
                    <div class="form-group">
                        <label for="imgurl">Upload Image* (Proof)</label>
                        <input type="file" id="imgurl" name="imgurl" accept="image/*" required />
                    </div>


                    <div class="form-group" style="align-items: center;">
                        <button type="submit" onclick="handleSave()">Save</button>

                    </div>
                </form>
            </div>
        </div>


        <script>
            // Validate form data
            function validateForm() {
                let isValid = true;

                // Validate Finance Name
                const financeName = document.getElementById('finance_name')?.value.trim() || '';
                const nameError = document.getElementById('nameError');
                if (financeName.length === 0 || financeName.length > 50) {
                    nameError.style.display = 'block';
                    isValid = false;
                } else {
                    nameError.style.display = 'none';
                }

                // Validate Amount
                const amount = parseFloat(document.getElementById('amount')?.value || '0');
                const amountError = document.getElementById('amountError');
                if (isNaN(amount) || amount <= 0 || amount > 99999) {
                    amountError.style.display = 'block';
                    isValid = false;
                } else {
                    amountError.style.display = 'none';
                }

                return isValid; // Return validation status
            }

// Handle form save and submit
            function handleSave() {
                const isFormValid = validateForm();
                if (isFormValid) {
                    localStorage.setItem('financeSaved', 'true'); // Save success status
                    const form = document.querySelector('form');
                    form?.submit(); // Submit the form
                } else {
                    showToastMessage('Form validation failed! Please check the inputs.', '#f44336'); // Show error toast
                }
            }

// Show toast message
            function showToastMessage(message, backgroundColor = '#4CAF50') {
                const toast = document.createElement('div');
                toast.textContent = message;
                toast.style.position = 'fixed';
                toast.style.top = '250px';
                toast.style.right = '20px';
                toast.style.backgroundColor = backgroundColor;
                toast.style.color = 'white';
                toast.style.padding = '15px 30px';
                toast.style.borderRadius = '5px';
                toast.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.2)';
                toast.style.zIndex = '1000';
                toast.style.fontSize = '16px';

                document.body.appendChild(toast);

                setTimeout(() => {
                    toast.remove();
                }, 3000); // Auto-hide toast
            }

// Show success toast on page load if saved successfully
            window.onload = function () {
                const financeSaved = localStorage.getItem('financeSaved');
                if (financeSaved === 'true') {
                    showToastMessage('Finance record has been successfully saved!');
                    localStorage.removeItem('financeSaved'); // Clear save status after showing
                }
            };
        </script>


        <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 animate-box">
                        <h1><span class="colored">Finance</span> List</h1>
                    </div>
                </div>
            </div>
        </div>

        <div id="best-deal">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <h2>Manage Your Club's Finances Effectively</h2>
                        <p>Track and manage financial transactions for your club with ease and efficiency.</p>


                    </div>
                    <div class="container-fluid fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <div class="d-flex justify-content-between align-items-center">
                            <!-- Phần bên trái -->
                            <div class="left-section">
                                <form action="finance?action=filter" method="get">
                                    <input type="text" name="search" placeholder="Search for transactions..." value="${search}">

                                    <select name="type">
                                        <option value="all" ${type == 'all' ? 'selected' : ''}>Type</option>
                                        <option value="Yes" ${type == 'Yes' ? 'selected' : ''}>Expense</option>
                                        <option value="No" ${type == 'No' ? 'selected' : ''}>Unexpense</option>
                                    </select>

                                    <select name="status">
                                        <option value="all" ${status == 'all' ? 'selected' : ''}>Status</option>
                                        <option value="Saved" ${status == 'Saved' ? 'selected' : ''}>Saved</option>
                                        <option value="Submitted for Approval" ${status == 'Submitted for Approval' ? 'selected' : ''}>Submitted for Approval</option>
                                        <option value="Approve" ${status == 'Approve' ? 'selected' : ''}>Approve</option>
                                        <option value="Reject" ${status == 'Reject' ? 'selected' : ''}>Reject</option>
                                    </select>




                                    <button type="submit">Search and Filter</button>
                                </form>
                            </div>
                            <!-- Phần bên phải -->
                            <div class="right-section">

                                <button onclick="openModal()">..   New Finance  ..</button>
                            </div>
                        </div>
                    </div>

                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Finance Name</th>
                                <th>Amount (VND)
                                    <button onclick="sortTransactions(2, 'asc')">
                                        <img src="https://www.pngarts.com/files/2/Up-Arrow-PNG-Background-Image.png" alt="Ascending" width="16" height="16">
                                    </button>
                                    <button onclick="sortTransactions(2, 'desc')">
                                        <img src="https://th.bing.com/th/id/R.61784c37a2e26bd4afa05c2777d50907?rik=dRxz2txHboft%2bA&riu=http%3a%2f%2fpluspng.com%2fimg-png%2fdown-arrow-png-down-icon-1600.png&ehk=AtCYQs09Cv%2b2Br%2fb7%2bOJShaXA8L4Iwcv6m%2fWOcZI4f0%3d&risl=&pid=ImgRaw&r=0" alt="Descending" width="16" height="16">
                                    </button>
                                </th>

                                <th>Author</th>
                                <th>Transaction Type

                                </th>
                                <th>Transaction Date
                                    <button onclick="sortTransactions(6, 'asc')">
                                        <img src="https://www.pngarts.com/files/2/Up-Arrow-PNG-Background-Image.png" alt="Ascending" width="16" height="16">
                                    </button>
                                    <button onclick="sortTransactions(6, 'desc')">
                                        <img src="https://th.bing.com/th/id/R.61784c37a2e26bd4afa05c2777d50907?rik=dRxz2txHboft%2bA&riu=http%3a%2f%2fpluspng.com%2fimg-png%2fdown-arrow-png-down-icon-1600.png&ehk=AtCYQs09Cv%2b2Br%2fb7%2bOJShaXA8L4Iwcv6m%2fWOcZI4f0%3d&risl=&pid=ImgRaw&r=0" alt="Descending" width="16" height="16">
                                    </button></th>
                                <th>Club Name</th>
                                <th>Status</th>   
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="finance" items="${financeList}">
                                <tr>
                                    <td><a href="#">${finance.financeId}</a></td>
                                    <td>${finance.financeName}</td>
                                    <td>${finance.amount}</td>
                                    <td>${finance.memberName}</td>
                                    <td>${finance.isExpense}</td>
                                    <td>${finance.financeDate}</td>
                                    <td>${finance.clubName}</td>
                                    <td class="<c:choose>
                                            <c:when test="${finance.status == 'Saved'}">status-saved</c:when>
                                            <c:when test="${finance.status == 'Submitted for Approval'}">status-submitted</c:when>
                                            <c:when test="${finance.status == 'Approve'}">status-approved</c:when>
                                            <c:when test="${finance.status == 'Reject'}">status-rejected</c:when>
                                        </c:choose>">${finance.status}</td>
                                    <td>
                                        <c:if test="${(sessionScope.acc.role == 'ClubMember' || sessionScope.acc.role == 'WebUser' || 
                                                      sessionScope.acc.role == 'ProjectLeader' || sessionScope.acc.role == 'EventLeader') &&
                                                      (finance.status == 'Saved' || finance.status == 'Submitted for Approval')}">


                                              <a href="${pageContext.request.contextPath}/financeDetail?financeId=${finance.financeId}">Edit</a>
                                        </c:if>

                                        <c:if test="${sessionScope.acc.role == 'ClubLeader' && 
                                                      (finance.status == 'Submitted for Approval' || finance.status == 'Reject' || finance.status == 'Approve')}">
                                              <a href="${pageContext.request.contextPath}/financeDetail?financeId=${finance.financeId}">Edit</a>
                                        </c:if>

                                        <c:if test="${(sessionScope.acc.role != 'ClubMember' &&
                                                      sessionScope.acc.role != 'WebUser' &&
                                                      sessionScope.acc.role != 'ProjectLeader' &&
                                                      sessionScope.acc.role != 'EventLeader' &&
                                                      sessionScope.acc.role != 'ClubLeader')}">
                                              <a href="${pageContext.request.contextPath}/financeDetail?financeId=${finance.financeId}">Edit</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>



                    <!-- Phân trang -->
                    <div class="col-md-12 text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <!-- Loop through pages -->
                                <c:forEach var="page" begin="1" end="${totalPages}">
                                    <li class="${page == currentPage ? 'active' : ''}">
                                        <!-- Handle dynamic URLs for search/filter or regular listing -->
                                        <c:choose>
                                            <c:when test="${not empty search or not empty type or not empty status or not empty clubName}">
                                                <!-- Add search/filter parameters to the pagination links -->
                                                <a href="finance?action=list&currentPage=${page}&search=${search}&type=${type}&status=${status}&clubName=${clubName}">
                                                    ${page}
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Default pagination for listing without filters -->
                                                <a href="finance?action=list&currentPage=${page}">
                                                    ${page}
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>

        <div id="fh5co-testimonial">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3 text-center fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <h2>Happy Clients</h2>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                        <blockquote>
                            <p>&ldquo; She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Jason Davidson</cite></span><i class="icon twitter-color icon-twitter pull-right"></i></p>

                        </blockquote>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">
                        <blockquote>
                            <p>&ldquo; Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. She had a last view back on the skyline of her hometown Bookmarksgrove, the headline of. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Kyle Smith</cite></span><i class="icon googleplus-color icon-google-plus pull-right"></i></p>
                        </blockquote>
                    </div>
                    <div class="col-md-4 text-center item-block animate-box" data-animate-effect="fadeIn">

                        <blockquote>
                            <p>&ldquo; The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. S	he had a last view back on the skyline of her hometown Bookmarksgrove. &rdquo;</p>
                            <p><span class="fh5co-author"><cite>Rick Cook</cite></span><i class="icon facebook-color icon-facebook pull-right"></i></p>
                        </blockquote>
                    </div>
                </div>
            </div>
        </div>







        <div class="fh5co-cta" style="background-image: url(images/slide_4.jpg);">
            <div class="overlay"></div>
            <div class="container">
                <div class="col-md-12 text-center animate-box">
                    <h3>We Try To Update The Site Everyday</h3>
                    <p><a href="#" class="btn btn-primary btn-outline with-arrow">Get started now! <i class="icon-arrow-right"></i></a></p>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp" />




        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></script>

        <!-- MAIN JS -->
        <script src="js/main.js"></script>

    </body>
</html>


