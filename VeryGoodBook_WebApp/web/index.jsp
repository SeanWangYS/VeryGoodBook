<%-- 
    Document   : index
    Created on : 2019/3/14, 下午 05:29:38
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>非常好書</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">     	
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
    </head>

    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <div class="article" >
            <%--= 1/0 --%>
            <a href='products_list.jsp'><img src='images/cart.png'></a>
        </div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>

    </body>

</html>
