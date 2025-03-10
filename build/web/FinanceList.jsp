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
                text-align: left;
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
                <form action="${pageContext.request.contextPath}/finance?action=insert" method="post" onsubmit="return validateForm()">
                    <div class="form-group">
                        <label for="finance_name">Finance Name*</label>
                        <input type="text" id="finance_name" name="finance_name" maxlength="200" required>
                        <span id="nameError" class="error-message" style="color: red; display: none;">Finance Name cannot be more than 50 characters.</span>
                    </div>
                    <div class="form-group">
                        <label for="is_expense">Type</label>
                        <select id="is_expense" name="is_expense" required>
                            <option value="Yes">Expense</option>
                            <option value="No">Unexpense</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount">Amount (VND)</label>
                        <input type="number" id="amount" name="amount" step="0.01" required>
                        <span id="amountError" class="error-message" style="color: red; display: none;">Amount cannot be more than 99999.</span>
                    </div>
                    <div class="form-group">
                        <label for="finance_date">Finance Date*</label>
                        <input type="datetime-local" id="finance_date" name="finance_date" required>
                    </div>
                    <div class="form-group">
                        <label for="details">Details</label>
                        <textarea id="details" name="details"></textarea> <!-- Có thể để trống -->
                    </div>
                    <div class="form-group" style="align-items: center;">
                        <button type="submit">Save</button>

                    </div>
                </form>
            </div>
        </div>


        <script>
            function validateForm()
            {
                let isValid = true;

                // Validate Finance Name
                var financeName = document.getElementById('finance_name').value;
                var nameError = document.getElementById('nameError');
                if (financeName.length > 50) {
                    nameError.style.display = 'block'; // Hiển thị thông báo lỗi
                    isValid = false;
                } else {
                    nameError.style.display = 'none'; // Ẩn thông báo lỗi nếu hợp lệ
                }

                // Validate Amount
                var amount = document.getElementById('amount').value;
                var amountError = document.getElementById('amountError');
                if (amount > 99999) {
                    amountError.style.display = 'block'; // Hiển thị thông báo lỗi
                    isValid = false;
                } else {
                    amountError.style.display = 'none'; // Ẩn thông báo lỗi nếu hợp lệ
                }

                return isValid;
            }


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
                        <h2 style="color: #6e9ee0;">
                            <c:forEach var="finance" items="${financeList}" varStatus="status">
                                <c:if test="${status.index == 0}">
                                    ${finance.clubName}
                                </c:if>
                            </c:forEach>
                        </h2>

                    </div>
                    <div class="container-fluid fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <div class="d-flex justify-content-between align-items-center">
                            <!-- Phần bên trái -->
                            <div class="left-section">
                                <label for="search">Search:</label>
                                <input type="text" id="search" onkeyup="searchTransactions()" placeholder="Search for transactions...">

                                <label for="filter">Filter:</label>
                                <select id="filter" onchange="filterTransactions(this.value)">
                                    <option value="all">All</option>
                                    <option value="Expense">Expense</option>
                                    <option value="Unexpense">Unexpense</option>
                                </select>
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

                                <th>Status</th>    
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="finance" items="${financeList}">
                                <tr>
                                    <td style="color: #118df0;">${finance.financeId}</td>
                                    <td><a href="${pageContext.request.contextPath}/financeDetail?financeId=${finance.financeId}">${finance.financeName}</a></td>
                                    <td style="color: #666666;">${finance.amount}</td>
                                    <td style="color: #666666;">${finance.memberName}</td>
                                    <td style="color: #666666;">${finance.isExpense}</td>
                                    <td style="color: #666666;">${finance.financeDate}</td>
                                    <td class="<c:choose>
                                            <c:when test="${finance.status == 'Saved'}">status-saved</c:when>
                                            <c:when test="${finance.status == 'Submitted for Approval'}">status-submitted</c:when>
                                            <c:when test="${finance.status == 'Approve'}">status-approved</c:when>
                                            <c:when test="${finance.status == 'Reject'}">status-rejected</c:when>
                                        </c:choose>">${finance.status}</td>
                                </tr>
                            </c:forEach>




                        </tbody>
                    </table>



                    <!-- Phân trang -->
                    <div class="col-md-12 text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <c:forEach var="page" begin="1" end="${totalPages}">
                                    <li class="${page == currentPage ? 'active' : ''}">
                                        <a href="finance?action=list&currentPage=${page}">${page}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                    
                    <c:if test="${sessionScope.acc.role == 'ClubMember'|| sessionScope.accc.role == 'ClubMember'}">
                    <div class="col-md-6 col-md-offset-6 text-right fh5co-heading animate-box" data-animate-effect="fadeIn">
                        <p style="color: #f15656;">*You can only fix your finance*</p>
                    </div>
                    </c:if>
                    
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


