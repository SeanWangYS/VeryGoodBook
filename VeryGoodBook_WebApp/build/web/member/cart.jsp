<%-- 
    Document   : cart
    Created on : 2019/3/21, 下午 12:02:11
    Author     : Admin
--%>
<%@page import="uuu.vgb.entity.Outlet"%>
<%@page import="uuu.vgb.entity.Product"%>
<%@page import="uuu.vgb.entity.CartItem"%>
<%@page import="uuu.vgb.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">           
        <title>購物車</title>
        <%@include file="/WEB-INF/subviews/global.jsp"  %>
        <style>
            table, th, td {border: 1px solid black;padding: 3px}
            table{border-collapse:collapse}        
        </style>
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
        <jsp:include page="/WEB-INF/subviews/header.jsp">
            <jsp:param name="sub.title" value="購物車" />
        </jsp:include>
        <div class="article">
            <%
                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            %>
            <%--= cart--%>
            <% if (cart != null && !cart.isEmpty()) { %>
            <form method="POST" action="update_cart.do">
                <table style="width:80%;margin: auto">
                    <caption>購物車</caption>                
                    <thead>
                        <tr><th>名稱</th><th>顏色</th><th>定價</th><th>折扣</th><th>售價</th><th>數量</th><th>刪除</th></tr>
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
                            <td><input style="width:3em" type="number" name="quantity_<%= item.hashCode()%>" id="quantity" 
                                       value="<%= cart.getQuantity(item)%>" min="<%= p.getStock() > 0 ? 1 : 0%>" max="<%= p.getStock()%>"> </td>
                            <td><input type="checkbox" name="delete_<%= item.hashCode()%>"></td>
                        </tr>                    
                        <% }%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3">共<%= cart.size()%>項, <%= cart.getTotalQuantity()%>件</td>
                            <td colspan="4">共<%= cart.getTotalAmount()%>元</td>
                        </tr>
                        <tr>
                            <td colspan="3"><input type="button" value="回到賣場" id="goShoppingButton"></td>
                            <td colspan="4">
                                <input type="submit" value="確定修改" name="submit">
                                <!--input type="button" value="我要結帳" onclick="checkOut()"-->  <!--原本是直接去結帳畫面，但想要改成結帳進結帳畫面前，先做去修改-->
                                <input type="submit" value="我要結帳" name="submit"> <!--改成先去做修改(優化流程的概念)--><!--而且改成submit 後，可藉由前段寫的code在Submit前做庫存檢查--><!--TO DO 應該還沒做前面那段-->
                            </td>
                        </tr>
                    </tfoot>                
                </table>
            </form>
            <%} else {%>
            <p>購物車是空的，還不<a href="../products_list.jsp">回賣場</a>多買一點</p    >
            <%}%>
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp"  %>
    </body>
</html>
