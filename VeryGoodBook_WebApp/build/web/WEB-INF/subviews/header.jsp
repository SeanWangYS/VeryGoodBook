
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uuu.vgb.entity.Customer"%>
<!--header.jsp-->
<%
    Customer member = (Customer) session.getAttribute("member");
%>
<div class="header">
    <h1><a href="<%=request.getContextPath()%>">非常好書</a>
        <sub><%=request.getParameter("sub.title") == null ? "歡迎光臨" : request.getParameter("sub.title")%></sub></h1>
</div>
<a href="<%=request.getContextPath()%>/member/cart.jsp"><img  src='<%=request.getContextPath()%>/images/cart.png' style="width: 32px;">
    <span id="cartTotalQuantity" style="color:red">
        <%@include file="/cart_total_quantity.jsp" %>
    </span>
</a>    
<div class="nav">
    <% if (member == null) {%>

    <a href='javascript:login()'>登入</a>  
    <a href="<%=request.getContextPath()%>/register.jsp">註冊</a>
    <%} else {%>
    <a href='<%=request.getContextPath()%>/logout.do'>登出</a>  
    <a href="<%=request.getContextPath()%>/member/update.jsp">會員修改</a>
    <a href="<%=request.getContextPath()%>/member/orders_history.jsp">歷史訂單</a>

    <%}%>

    <span style="float:right"><%= member != null ? member.getName() + " 歡迎回來" : ""%>  </span>
    <hr>
</div>
<div id="loginBox"></div><!--for 燈箱效果-->
<!--header.jsp end-->