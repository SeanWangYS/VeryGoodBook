<%-- 
    Document   : products_list
    Created on : 2019/3/11, 下午 12:32:39
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.Outlet"%>
<%@page import="uuu.vgb.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="uuu.vgb.model.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!--老師OS:這行給老娘背起來 (jsp最上面一定要先加上編碼方式)-->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">  <!--在jsp底下 這行無作用-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>產品清單</title>
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
        <style>
            .productItem{display:inline-table; width:250px;box-shadow:gray 1px 1px 5px;padding:10px 5px;height:250px;vertical-align: bottom;}
            .productItem img{padding-left: 80px}
        </style>
        <script>
            function getBookimage(target) {
                $(target).attr("src", "images/book.png");
            }
            function getProduct(pid) {
                //同步請求
                //location.href = "product.jsp?productId=" + pid;

                //非同步GET請求
                $.ajax({
                    url: "product_ajax.jsp?productId=" + pid
                }).done(getPruductDoneHandler);
            }

            function getPruductDoneHandler(result, status, xhr) {
                console.log(result);
                $("#productDetail").html(result);
                $.fancybox.open({
                    src: "#productDetail"
                });
            }


        </script>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
        %>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="sub.title" value="產品清單"/>
        </jsp:include>
        <div class="article" >
            <%
                String search = request.getParameter("search");
                ProductService service = new ProductService();
                List<Product> list = null;
                if (search != null && search.length() > 0) {
                    list = service.searchProductsByName(search);
                } else {
                    list = service.getAllProducts();
                }
            %>  
            <form method>
                <input type="search" placeholder="請輸入查詢關鍵字" style="width:30%; min-width: 16em" name="search">
                <input type="submit" value="查詢">
            </form>
            <hr>
            <% if (list != null && list.size() > 0) { %>
            <ul>
                <%for (int i = 0; i < list.size(); i++) {
                        Product p = list.get(i);
                %>
                <li class="productItem">
                    <a href='javascript:getProduct(<%= p.getId()%>)'>
                        <h5><%=p.getName()%></h5>
                        <img style="width:85px" src="<% out.print(p.getPhotoUrl()); %>" onerror="getBookimage(this)">
                    </a>
                    <div>優惠價:
                        <%
                            if (p instanceof Outlet) {
                                out.print(((Outlet) p).getDiscountString());
                            }
                        %>
                        <%= p.getUnitPrice()%> 元 
                        <% if(p.getStock()>0){ 
                            if(p.getColors()!=null && p.getColors().length>0){
                        %>
                        <a href="javascript:getProduct(<%= p.getId()%>)">                                                    
                            <%}else{%>
                        <a href="add_cart.do?productId=<%= p.getId() %>&quantity=1"> <!--同步GET請求送給AddToCartServlet-->
                            <%}%>
                            <img style="width:32px;vertical-align: middle" src="images/cart_64.png">
                        </a>
                    <%}%>
                    </div>
                </li>
                <%}%>
            </ul>
            <%} else {%>
            <p>查無符合資料<span style="color:blue;"><%=search%></span></p>
                <%}%>
            <hr>
        </div>
        <div id="productDetail"></div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>