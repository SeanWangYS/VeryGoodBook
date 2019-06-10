<%-- 
    Document   : orders_history
    Created on : 2019/3/18, 上午 11:33:11
    Author     : Admin
--%>

<%@page import="uuu.vgb.model.OrderService"%>
<%@page import="java.util.List"%>
<%@page import="uuu.vgb.entity.Order"%>
<%@page import="uuu.vgb.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta charset="UTF-8">
        <title>歷史訂單</title>
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
        <style>
            table, th, td {border: 1px solid black;padding: 3px}

            table{border-collapse:collapse}        
        </style>    
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="sub.title" value="訂單紀錄"/>
        </jsp:include>
        <div class="article">
            <%
                Customer member = (Customer) session.getAttribute("member");
                List<Order> list = null;

                if (member != null) {
                    OrderService service = new OrderService();
                    list = service.searchtOrderByCustomerId(member.getId());
                }
                if (list != null && list.size() > 0) {
            %>
            <table style="width:90%">
                <thead>
                    <tr>
                        <th>訂單編號</th><th>日期</th><th>付款方式</th><th>貨運方式</th><th>總金額</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Order order : list) {%>
                    <tr>
                        <td>
                            <a href="order.jsp?orderId=<%= order.getId()%>">
                                <%= order.getFormatedId()%>
                            </a>
                        </td>
                        <td><%=order.getOrderDate()%>
                        </td><td>付款方式</td><td>貨運方式</td>
                        <td><%= order.getTotalAmount() + order.getPaymentFee() + order.getShippingFee()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <%} else {%>
            <p>查無訂單紀錄</p>
            <%}%>
        </div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
