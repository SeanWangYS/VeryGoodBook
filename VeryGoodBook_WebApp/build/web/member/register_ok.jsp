<%-- 
    Document   : login_ok
    Created on : 2019/3/12, 下午 06:55:12
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>註冊成功</title>
    </head>
    <%
        //Customer c = (Customer)request.getAttribute("customer");    
    %>
    <body>
        <h1>恭喜你，${requestScope.customer.name}，已成功完成會員註冊</h1>
    </body>
</html>
