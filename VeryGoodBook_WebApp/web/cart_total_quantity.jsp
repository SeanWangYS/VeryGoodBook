<%-- 
    Document   : cart_total_quantity
    Created on : 2019/3/25, 下午 01:57:24
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart != null && cart.getTotalQuantity() > 0) {

%>
<%= cart.getTotalQuantity()%>
<%}%>

