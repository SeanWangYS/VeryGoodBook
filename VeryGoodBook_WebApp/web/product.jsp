<%-- 
    Document   : product
    Created on : 2019/3/19, 下午 04:14:33
    Author     : Admin
--%>
<%@page import="uuu.vgb.entity.Outlet"%>
<%@page import="uuu.vgb.entity.Product"%>
<%@page import="uuu.vgb.model.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>產品明細</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="style/vgb.css">
        <%@include file="/WEB-INF/subviews/global.jsp"  %>
        <script>
            function addCart(){
                $("#cartForm").submit();
            }
            function validate(){
                var min = parseInt($("#quantity").attr("min"));
                var max = parseInt($("#quantity").attr("max"));
                var value = parseInt($("#quantity").val());
                console.log(min, max, value); 
                if(value<=max && value >= min){
                    return true;
                }else{
                    return false;
                }
            }
        </script>
</head>
<%
    String productId = request.getParameter("productId");    

    ProductService service = new ProductService();
    Product p = null;
    if(productId!=null){
        p = service.searchProductById(productId);
    }
%>
<body>
    <jsp:include page="/WEB-INF/subviews/header.jsp" />
        <div class="article" >
            <% if(p!=null) {%>	        
                <img style="width:40%;min-width:300px;max-width:500px;float:left" src="<%= p.getPhotoUrl() %>">
                <div style="padding-left: 10px">
                    <h3><%= p.getName() %></h3>
                    <p>作者： 陳會安</p>
                    <p>出版社：碁峰</p>
                    <p>出版日期：2018/12/24</p>
                    <p>語言：繁體中文</p>
                    <% if (p instanceof Outlet) {%>
                    <p>定價：<%= ((Outlet) p).getListPrice()%></p>
                    <%}%>
                    <p>優惠價： <%= p instanceof Outlet ? ((Outlet) p).getDiscountString() : ""%> <%= p.getUnitPrice()%>元                          
                    </p>	    
                    <form id='cartForm' onsubmit="return validate()" method="GET" action="member/cart.jsp">
                        <p>
                            <input type='hidden' name='productId' value='<%= p.getId() %>'>
                            <label>數量:</label>
                            <input type='number' id='quantity' name='quantity' required min='1' max='<%= p.getStock() %>'>                        
                            <a href='javascript:addCart()'><img style='width: 32px;vertical-align: middle' src='images/cart.png'></a>
                        </p>
                    </form>
	        </div>
	        <div style="clear:both">
	        <hr>
                <%= p.getDescription() %>
                </div>
        <%}else{%>
        <p>查無此產品!</p>
        <%}%>
        
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp"  %> 
</body>
</html>