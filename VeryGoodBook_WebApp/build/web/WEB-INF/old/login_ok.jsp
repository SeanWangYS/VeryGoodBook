<%-- 
    Document   : login_ok
    Created on : 2019/3/12, 下午 06:55:44
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登入成功</title>
    </head>
    <%
        Customer c = (Customer)session.getAttribute("member");
    
    %>
    <body>
        <h1><%= c!= null?c.getName():"" %> 登入成功</h1>
    </body>
</html>
