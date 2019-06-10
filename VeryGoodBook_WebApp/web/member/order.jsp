<%-- 
    Document   : order
    Created on : 2019/3/29, 下午 12:32:58
    Author     : Admin
--%>

<%@page import="uuu.vgb.model.OrderService"%>
<%@page import="uuu.vgb.entity.Customer"%>
<%@page import="uuu.vgb.entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="sub.title" value="訂單明細"/>
        </jsp:include>
        
        <%
            Customer member = (Customer)session.getAttribute("member");
            Order order = (Order)request.getAttribute("order");  
            String orderId = request.getParameter("orderId");
            if(order==null && orderId!=null){
                OrderService service = new OrderService();
//                order = service.getOrderById(orderId);
//                if(order!=null && member!=null && !member.equals(order.getMember())){
//                    order=null;
//                }
            }
        %>
        <div class="article">            
            <%  if(order!=null) {%>
                <p>訂單編號: ${requestScope.order.id}, 訂購時間: ${requestScope.order.orderDate} ${requestScope.order.orderDate}</p>
                <fieldset>
                    <legend>收件人</legend>
                    <label>姓名:</label><input readonly value="<%=  order.getRecipientName() %>"> 

                </fieldset>
            <%} else {%>    
                <p>查無此訂單</p>
            <%}%>    
        </div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
