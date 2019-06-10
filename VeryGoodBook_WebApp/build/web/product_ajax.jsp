<%-- 
    Document   : product
    Created on : 2019/3/19, 下午 04:14:33
    Author     : Admin
--%>
<%@page import="java.util.Arrays"%>
<%@page import="uuu.vgb.entity.Book"%>
<%@page import="uuu.vgb.entity.Outlet"%>
<%@page import="uuu.vgb.entity.Product"%>
<%@page import="uuu.vgb.model.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function addCart() { //對AddToCartServlet送出請求，將整個表單送出去
        //同步的請求
        //$("#cartForm").submit();

        //非同步請求 
        var valid = validate();
        console.log(valid);
        if (valid) {
            var url = $("#cartForm").attr("action");
            var method = $("#cartForm").attr("method");
            console.log(url, method);
            $.ajax({
                url:url,
                method:method,
                data:$("#cartForm").serialize()  //jquery ajax form data serialize (可同時解決傳資料時中文編碼問題)
            }).done(addCartDoneHandler);
        }
    }
    
    function addCartDoneHandler(result, status, xhr){
        console.log(result);
        $("#cartTotalQuantity").html(result);  //這是在header內 顯示購物車數量的部分
        
    }
    function validate() {
        var min = parseInt($("#quantity").attr("min"));
        var max = parseInt($("#quantity").attr("max"));
        var value = parseInt($("#quantity").val());
        var colorInput = document.getElementById("color");
        console.log(min, max, value, colorInput);
        var message = "請";
        var isCorrect = true;
        if (colorInput) {
            isCorrect = ($(colorInput).val() != "");
            if (!isCorrect)
                message += "選擇顏色";
        }
        if (isNaN(value) || value < min || value > max) {
            isCorrect = isCorrect && (value >= min && value <= max);
            if (message != "請")
                message += "和";
            message += "輸入正確的數量(" + min + "~" + max + ")";
        }
        if (!isCorrect) {
            alert(message);
        }
        return (isCorrect);
        
    }
</script>
<%
    String productId = request.getParameter("productId");

    ProductService service = new ProductService();
    Product p = null;
    if (productId != null) {
        p = service.searchProductById(productId);
    }
%>
<div class="productDetail" >
    <% if (p != null) {%>	        
    <img style="width:40%;min-width:300px;max-width:500px;float:left" src="<%= p.getPhotoUrl()%>">
    <div style="padding-left: 10px">
        <h3><%= p.getName()%></h3>
        <% if (p instanceof Book) {%>
        <p>作者： <%= Arrays.toString(((Book) p).getAuthors())%></p>
        <p>出版社：<%= ((Book) p).getPublisher()%></p>
        <p>出版日期：<%= ((Book) p).getPublishedDate()%></p>
        <p>語言：<%= ((Book) p).getLanguage()%></p>
        <%}%>
        <% if (p instanceof Outlet) {%>
        <p>定價：<%= ((Outlet) p).getListPrice()%></p>
        <%}%>
        <p>優惠價： <%= p instanceof Outlet ? ((Outlet) p).getDiscountString() : ""%> <%= p.getUnitPrice()%>元                          
        </p>	    
        <form id='cartForm' onsubmit="return validate()" method="POST" action="add_cart.do">                        
            <input type='hidden' name='productId' value='<%= p.getId()%>'> <!--這行是藏data用的，之後會隨著表格其他內容送出去-->
            <% if (p.getColors() != null && p.getColors().length > 0) {%>
            <p>
                <label>顏色:</label>
                <select name="color" id="color">
                    <option value="">請選擇...</option>
                    <% for (int i = 0; i < p.getColors().length; i++) {%>
                    <option value="<%= p.getColors()[i]%>"><%= p.getColors()[i]%></option>
                    <% } %>
                </select>
            </p>     
            <%}%>
            <p>
                <label>數量:</label>
                <input type='number' id='quantity' name='quantity' required min='<%= p.getStock()>0?1:0 %>' max='<%= p.getStock()>10?10:p.getStock() %>'>                        
                <a href='javascript:addCart()'><img style='width: 32px;vertical-align: middle' src='images/cart.png'></a>
            </p>
        </form>
    </div>
    <div style="clear:both">
        <hr>
        <%= p.getDescription()%>
    </div>
    <%} else {%>
    <p>查無此產品!</p>
    <%}%>        
</div>