<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
    <style>
        #fh5co-header {
            position: absolute;
            z-index: 1001;
            width: 100%;
            margin: 0px 0 0 0;
            background: rgba(0, 0, 0, 0.5);
            margin-bottom: 20px;
        }
        .user-info {
            display: flex;
            align-items: center;
            margin-top: 20px;
            color :#fff;
            font-size: 20px;
        }

        .user-info img {
            margin-left: 20px; /* Táº¡o khoáº£ng cÃ¡ch 20px giá»¯a tÃªn vÃ  áº£nh */
        }


        .dropdown-menu {
            background-color: black;
        }

        .dropdown-menu:hover {
            background-color: rgba(0, 0, 0, 0); 
        }

        .dropdown-menu .admin-item:hover {
            background-color: rgba(0, 0, 0, 0);
            color: rgba(0, 0, 0, 0);
        }

        .dropdown-menu:hover.select {
            background-color: rgba(0, 0, 0, 0);
        }
        .dropdown-menu li.active {
            background-color: rgba(0, 0, 0, 0);
        }

        @media screen and (max-width: 768px) {
            #fh5co-header {
                margin: 0px 0 0 0;
            }
        }
        #fh5co-header img {
            height: 40px;
            border-radius: 10px;
            color: #fff;
        }
        #fh5co-header .header-inner {
            color: #fff;
            display: flex;
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 80px;
            padding-left: 20px;
            padding-right: 20px;
            padding-bottom: 30px;
            float: contour;
            width: 100%;
            -webkit-border-radius: 7px;
            -moz-border-radius: 7px;
            -ms-border-radius: 7px;
            border-radius: 7px;
        }
        #fh5co-header h1 {
            float: left;
            padding-right: 10px;
            font-weight: 700;
            line-height: 0;
            font-size: 25px;
            margin-left: 20px;
        }
        #fh5co-header h1 a {
            color: white;
        }
        #fh5co-header h1 a > span {
            color: #118DF0;
        }
        #fh5co-header h1 a:hover, #fh5co-header h1 a:active, #fh5co-header h1 a:focus {
            text-decoration: none;
            outline: none;
        }
        #fh5co-header h1, #fh5co-header nav {
            margin: 38px 0 0 0;
        }
        #fh5co-header nav {
            float: right;
            padding: 0;
        }
        @media screen and (max-width: 768px) {
            #fh5co-header nav {
                display: none;
            }
        }
        #fh5co-header nav ul {
            padding: 0;
            margin: 0 -0px 0 0;
            line-height: 0;
        }
        #fh5co-header nav ul li {
            padding: 0;
            margin: 0;
            list-style: none;
            color: rgba(255, 255, 255, 0.7);
            display: -moz-inline-stack;
            display: inline-block;
            zoom: 1;
            *display: inline;
        }
        #fh5co-header nav ul li.call {
            margin-left: 20px;
        }
        @media screen and (max-width: 992px) {
            #fh5co-header nav ul li.call {
                margin-left: 0px;
            }
        }
        #fh5co-header nav ul li.call a:hover:after {
            display: none !important;
        }
        #fh5co-header nav ul li a {
            color: #fff;
            font-size: 18px;
            padding: 10px;
            position: relative;
            -webkit-transition: 0.2s;
            -o-transition: 0.2s;
            transition: 0.2s;
        }
        #fh5co-header nav ul li a img{
            color: #fff;

        }
        #fh5co-header nav ul li a i {
            line-height: 0;
            font-size: 20px;
            position: relative;
            top: 3px;
        }
        #fh5co-header nav ul li a:after {
            content: "";
            position: absolute;
            height: 2px;
            bottom: 7px;
            left: 10px;
            right: 10px;
            background-color: #fff;
            visibility: hidden;
            -webkit-transform: scaleX(0);
            -moz-transform: scaleX(0);
            -ms-transform: scaleX(0);
            -o-transform: scaleX(0);
            transform: scaleX(0);
            -webkit-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            -moz-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            -ms-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            -o-transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
        }
        #fh5co-header nav ul li a:hover {
            text-decoration: none;
            color: white;
        }
        #fh5co-header nav ul li a:hover:after {
            visibility: visible;
            -webkit-transform: scaleX(1);
            -moz-transform: scaleX(1);
            -ms-transform: scaleX(1);
            -o-transform: scaleX(1);
            transform: scaleX(1);
        }
        #fh5co-header nav ul li a:active, #fh5co-header nav ul li a:focus {
            outline: none;
            text-decoration: none;
        }
        #fh5co-header nav ul li.cta {
            margin-left: 20px;
        }
        @media screen and (max-width: 992px) {
            #fh5co-header nav ul li.cta {
                margin-left: 0px;
            }
        }
        #fh5co-header nav ul li.cta a {
            padding-left: 16px !important;
            padding-right: 16px !important;
            padding-top: 7px !important;
            padding-bottom: 7px !important;
            border: 2px solid rgba(255, 255, 255, 0.7);
            -webkit-border-radius: 30px;
            -moz-border-radius: 30px;
            -ms-border-radius: 30px;
            border-radius: 30px;
        }
        #fh5co-header nav ul li.cta a:hover {
            background: #fff;
            color: #118DF0;
        }
        #fh5co-header nav ul li.cta a:hover:after {
            display: none;
        }
        #fh5co-header nav ul li.active a {
            text-decoration: none;
            color: white;
        }
        #fh5co-header nav ul li.active a:after {
            visibility: visible;
            -webkit-transform: scaleX(1);
            -moz-transform: scaleX(1);
            -ms-transform: scaleX(1);
            -o-transform: scaleX(1);
            transform: scaleX(1);
        }
    </style>
    <header id="fh5co-header" role="banner">
        <div class="container">
            <div class="row">

                <h1><a  href="${pageContext.request.contextPath}/home">FPT Club Manager<span>.</span></a></h1>
                <div class="header-inner">

                    <nav role="navigation">
                        <ul>
                            <a href="home1"><li><img src="https://icon-library.com/images/home-icon-png-white/home-icon-png-white-1.jpg" class="homeIcon"></li></a>
                                    <c:if test="${sessionScope.acc == null && sessionScope.accc == null}">
                                <li><a href="Login.jsp">Sign in</a></li>
                                <li><a href="Register.jsp">Sign up</a></li>
                                <li><a href="${pageContext.request.contextPath}/PublicPost">Public Post</a></li>
                                </c:if>
                                <c:if test="${sessionScope.acc != null || sessionScope.accc != null }">

                                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                                
                                <li><a href="${pageContext.request.contextPath}/PublicPost">Public Post</a></li>

                                <c:if test="${sessionScope.acc.role == 'ClubMember'|| sessionScope.accc.role == 'ClubMember' ||sessionScope.acc.role == 'WebUser' ||sessionScope.accc.role == 'WebUser' || sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin'|| sessionScope.accc.role == 'ClubMember' ||sessionScope.acc.role == 'ClubLeader'}">
                                    
                                    
                                    <li><a href="${pageContext.request.contextPath}/Categories">Club List</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.acc.role == 'ClubMember'|| sessionScope.accc.role == 'ClubMember' ||sessionScope.acc.role == 'ClubLeader' ||sessionScope.accc.role == 'ClubLeader'||sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin' }">
                                    
                                    <li><a href="${pageContext.request.contextPath}/finance">Finance</a></li>
                                     <li><a href="${pageContext.request.contextPath}/taskList">Task</a></li>
                                     <li><a href="${pageContext.request.contextPath}/ClubMember">Member Manage</a></li>
                                    </c:if>
                                <c:if test="${sessionScope.acc.role == 'Admin'|| sessionScope.accc.role == 'Admin'}">
                                <!-- HTML -->
                                <li class="dropdown admin-menu">
                                    <a href="#" class="dropdown-toggle admin-link" data-toggle="dropdown">Admin <b class="caret"></b></a>
                                    <ul class="dropdown-menu admin-dropdown">
                                        
                                            <li><a href="${pageContext.request.contextPath}/ClubServlet" class="admin-item">Club Manager</a></li>
                                            <li><a href="${pageContext.request.contextPath}/settings" class="admin-item">Settings</a></li>
                                            <li><a href="${pageContext.request.contextPath}/ManageAccount" class="admin-item">Account Manage</a></li>
                                            <li><a href="${pageContext.request.contextPath}/listEvent" class="admin-item">Event Manager</a></li>
                                            
                                    </ul>
                                </li>
                                    </c:if>

                            </c:if>
                            
                        </ul>
                    </nav>

                </div>

                <c:if test="${sessionScope.acc != null && sessionScope.googleAccount == null}">
                    <div class="user-info">
                        ${sessionScope.acc.userName}
                        <img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo" >
                    </div>
                </c:if>


                <c:if test="${sessionScope.googleAccount != null}">

                    <div class="user-info">

                        <div>${sessionScope.name}</div>
                        <div ><img src="https://tse1.mm.bing.net/th?id=OIP.xyVi_Y3F3YwEIKzQm_j_jQHaHa&rs=1&pid=ImgDetMain" alt="Logo"></div>
                    </div>
                </c:if>

            </div>
        </div>
    </header>
</html>