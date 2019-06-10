<%-- 
    Document   : check_out
    Created on : 2019/3/25, 下午 04:07:09
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.ShippingType"%>
<%@page import="uuu.vgb.entity.PaymentType"%>
<%@page import="uuu.vgb.entity.Customer"%>
<%@page import="uuu.vgb.entity.Outlet"%>
<%@page import="uuu.vgb.entity.Product"%>
<%@page import="uuu.vgb.entity.CartItem"%>
<%@page import="uuu.vgb.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta charset="UTF-8">
        <title>結帳作業</title>

        <style>
            table, th, td {border: 1px solid black;padding: 3px}
            table{border-collapse:collapse}        
        </style>
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
        <script>
            $(document).on("click", "#goShoppingButton", goShopping);
            function goShopping() {
                //alert("goShopping");
                location.href = "../products_list.jsp";
            }
//            function checkOut() {  
//                //同步GET請求
//                location.href = "check_out.jsp";
//            }

        </script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="sub.title" value="結帳"/>
        </jsp:include>

        <div class="article">
            <%
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            %>
            <% if (cart != null && !cart.isEmpty()) {%>
            ${requestScope.errors}
            <form method="POST" action="check_out.do">
                <table style="width:80%; min-width: 400px; margin: auto">
                    <caption>購物車</caption>                
                    <thead>
                        <tr><th>名稱</th><th>顏色</th><th>定價</th><th>折扣</th><th>售價</th><th>數量</th></tr>
                    </thead>
                    <tbody>
                        <% for (CartItem item : cart.getCartItemSet()) {
                                Product p = item.getProduct();
                        %>
                        <tr>
                            <td><img src="<%= p.getPhotoUrl()%>" style="width:50px;vertical-align: middle"><%= p.getName()%></td>
                            <td><%= item.getColor()%></td>
                            <td><%= p instanceof Outlet ? ((Outlet) p).getListPrice() : p.getUnitPrice()%></td>
                            <% if (p instanceof Outlet) {%>                            
                            <td><%= ((Outlet) p).getDiscountString()%></td>
                            <% } else {%>
                            <td></td>
                            <% }%>
                            <td><%= cart.getUnitPrice(item)%></td>
                            <td><%= cart.getQuantity(item)%></td>
                        </tr>                    
                        <% }%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">共<%= cart.size()%>項, <%= cart.getTotalQuantity()%>件</td>
                            <td colspan="3">共<%= cart.getTotalAmount()%>元</td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <span style="width:45%;float: left">
                                    <label>付款方式:</label>
                                    <select name="paymentType" id="paymentType" required>
                                        <option value="">請選擇...</option>
                                        <%-- for(int i=0; i<PaymentType.values().length; i++){ --%>
                                        <% for (PaymentType pType : PaymentType.values()){%>
                                        <option value="<%= pType.name()%>">
                                            <%= pType%>
                                        </option>
                                        <%}%>
                                    </select>
                                </span>
                                <span style="width:45%;float: right">
                                    <label disabled>貨運方式:</label>
                                    <select name="shippingType" id="shippingType" required>
                                        <option value="">請選擇...</option>
                                        <% for (ShippingType sType : ShippingType.values()) {%>
                                        <option value="<%= sType.name()%>">
                                            <%= sType%>
                                        </option>
                                        <%}%>
                                    </select>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <span style="width:45%;float: left">
                                    <fieldset>
                                        <legend>訂貨人:</legend>
                                        <label>姓名:</label><input readonly value="<%= ((Customer) session.getAttribute("member")).getName()%>"><br>
                                        <label>email:</label><input readonly value="${sessionScope.member.email}"  ><br>
                                        <label>電話:</label><input readonly value="${sessionScope.member.phone}" ><br>
                                        <label>地址:</label><input readonly value="${sessionScope.member.address}" ><br>
                                    </fieldset>
                                </span>
                                <span style="width:45%;float: right">
                                    <fieldset>
                                        <legend>收件人:</legend>
                                        <label>姓名:</label><input required value="${empty param.name ? sessionScope.member.name : param.name}" name="name"><br>
                                        <label>email:</label><input required value="${empty param.email ? sessionScope.member.email : param.email}" name="email"><br>
                                        <label>電話:</label><input required value="${empty param.phone ? sessionScope.member.phone : param.phone}" name="phone"><br>
                                        <label>地址:</label><input required value="${empty param.address ? sessionScope.member.address : param.address}" name="address"><br>
                                    </fieldset>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><input type="button" value="回到賣場" id="goShoppingButton"></td>
                            <td colspan="3">

                                <input type="submit" value="送出訂單" name="submit">
                                <!--input type="button" value="送出訂單"onclick="checkOut()"-->
                            </td>
                        </tr>
                    </tfoot>                
                </table>
            </form>

            <%} else {%>

            <p>購物車是空的，快快快快快<a href="../products_list.jsp">回賣場</a>多買一點</p>
            <%}%>
        </div>


        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
