<%-- 
    Document   : welcome.jsp
    Created on : Feb 2, 2025, 11:38:50 PM
    Author     : Doan Quan
--%>

<%@ page import="Model.GoogleAccount" %>
<%@ page import="controller.account.GoogleLogin" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="DAO.dao" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        
        String code = request.getParameter("code");
        //neu nguoi dung huy uy quyen
      
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
        dao dao = new dao();
                String email = acc.getEmail();
                String name = acc.getName();
               
                if (dao.isGoogleAccountExist(acc.getId())|| dao.checkemailExist(email)) {
                    session = request.getSession();
                     GoogleAccount a = dao.loginggg(email);
                    session.setAttribute("accc", a);

                    session.setAttribute("name", a.getName());
                    session.setAttribute("googleAccount", a);
                                        request.getRequestDispatcher("Home.jsp").forward(request, response);

                } else {
                    // Nếu chưa tồn tại, lưu thông tin tài khoản Google vào database
                    dao.saveGoogleAccount(acc, email);
                     GoogleAccount a = dao.loginggg(email);
                  session = request.getSession();
                 
                    session.setAttribute("accc", a);

                    session.setAttribute("name", acc.getName());
                    session.setAttribute("googleAccount", acc);
                    request.getRequestDispatcher("Home.jsp").forward(request, response);
            }
        
%>
    </body>
</html>
