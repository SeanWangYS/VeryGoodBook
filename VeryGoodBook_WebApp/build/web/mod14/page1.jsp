<%-- 
    Document   : page1
    Created on : 2019/3/15, 下午 06:10:16
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page1</title>
    </head>
    <body>
        <h1>Hello Page1!</h1>
        <%--
        out.flush(); // 標籤都還沒讀取完，強迫response送出去
        request.getRequestDispatcher("page2.jsp").forward(request, response);
        --%>
        <jsp:include page="page2.jsp"/>
    </body>
</html>
