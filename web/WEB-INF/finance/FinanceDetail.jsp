<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Finance Detail</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
        <meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
        <meta name="author" content="FreeHTML5.co" />

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
        <style>
            .form-container {
                width: calc(100% - 400px); /* 100% width minus 200px margin on each side */
                max-width: none;
                padding: 25px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                margin: auto;
                margin-left: 200px;
                margin-right: 200px;
            }

            .form-group {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                margin-bottom: 10px;
                width: 100%;
            }
            .form-group-horizontal {
                display: flex;
                justify-content: space-between;
                width: 100%;
                align-items: flex-start;

            }
            .form-group label {
                margin-bottom: 5px;
                font-weight: bold;
                color: #333;
                font-size: 1.1em; /* Increased font size */
            }
            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1.1em; /* Increased font size */
            }
            .form-group-half {
                width: 48%;
            }
            .form-container button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1.1em; /* Increased font size */
            }
            input:focus,
            select:focus,
            textarea:focus {
                border-color: #4CAF50;
                outline: none;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            }
            .back-btn {
                margin-top: 15px;
                background-color: #333;
                font-size: 1.1em; /* Increased font size */
            }

        </style>
        <jsp:include page="header.jsp"></jsp:include>

        </head>
        <body>

            <div class="fh5co-page-title" style="background-image: url(images/slide_6.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 animate-box">
                            <h1><span class="colored">Finance</span> Detail</h1>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="form-container">
                <form action="financeDetail" method="post" onsubmit="showNotification('Action successful!')">
                    <input type="hidden" id="financeId" name="financeId" value="${finance.financeId}">

                <div class="form-group-horizontal">
                    <div class="form-group form-group-half">
                        <label for="financeName">Finance Name*</label>
                        <input type="text" id="financeName" name="financeName" value="${finance.financeName}" required>
                    </div>
                    <div class="form-group form-group-half">
                        <label for="type">Type</label>
                        <div class="radio-group">
                            <label for="expense" style="margin-right: 10px">
                                <input type="radio" id="expense" name="isExpense" value="true" <c:if test="${finance.isExpense == 'Expense'}">checked</c:if>> Expense
                                </label>
                                <label for="income">
                                    <input type="radio" id="income" name="isExpense" value="false" <c:if test="${finance.isExpense == 'Unexpense'}">checked</c:if>> Income
                                </label>

                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="amount">Amount</label>
                        <input type="text" id="amount" name="amount" value="${finance.amount}" required>
                </div>
                <div class="form-group">
                    <label for="memberName">Member Name</label>
                    <input type="text" id="memberName" name="memberName" value="${finance.memberName}" readonly>
                </div>
                <div class="form-group">

                    <input type="hidden" id="memberId" name="memberId" value="${finance.memberId}">
                </div>

                <div class="form-group">
                    <label for="financeDate">Finance Date*</label>
                    <input type="text" id="financeDate" name="financeDate" value="${finance.financeDate}" required>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <input type="text" id="status" name="status" value="${finance.status}" readonly>
                </div>
                <div class="form-group">
                    <label for="updatedDate">Last Updated</label>
                    <input type="text" id="updatedDate" name="updatedDate" value="${finance.updatedDate}" readonly>
                </div>
                <div class="form-group">
                        <label for="details">Details</label>
                        <textarea id="details" name="details"></textarea> <!-- Có thể để trống -->
                    </div>

                <div class="form-group" style="align-items: center;">
                    <div class="buttons">
                        <c:if test="${sessionScope.acc.role == 'ClubMember'|| sessionScope.accc.role == 'ClubMember' ||sessionScope.acc.role == 'ClubLeader' ||sessionScope.accc.role == 'ClubLeader'||sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin' }">
                            <button type="submit" class="btn" name="action" value="save">Save</button>
                            <button type="submit" class="btn" name="action" value="submit">Submit</button>
                        </c:if>

                        <c:if test="${sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin'||sessionScope.acc.role == 'ClubLeader' ||sessionScope.accc.role == 'ClubLeader' }">
                            <button type="submit" class="btn" name="action" value="approve">Approve</button>
                            <button type="submit" class="btn btn-reject" name="action" value="reject">Reject</button>
                        </c:if>
                    </div>
                    <br>
                    <div class="buttons">
                        <a href="${pageContext.request.contextPath}/finance?action=list" class="btn">Back to list</a>
                    </div>
                </div>
            </form>
        </div>








        <br>
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
                            <p>&ldquo; The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She had a last view back on the skyline of her hometown Bookmarksgrove. &rdquo;</p>
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

        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <!-- jQuery Easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></
        
        <!-- MAIN JS -->
        <script src="js/main.js"></script>
            <jsp:include page="footer.jsp" />
    </body>
</html>
