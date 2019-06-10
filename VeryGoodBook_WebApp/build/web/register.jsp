<%-- 
    Document   : register
    Created on : 2019/3/14, 下午 12:04:22
    Author     : Admin
--%>

<%@page import="uuu.vgb.entity.Customer"%>
<%@page import="uuu.vgb.entity.BloodType"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>會員註冊</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
        <script>
            function refreshCaptcha() {
                //alert("register_refleshCaptcha()");
                $("#captchaImage").attr("src", "images/register_captcha.jpeg?get=" + new Date()); //看出來了嗎jQuery用的getter and setter是同一個方法
            }
        </script>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
        %>
        <jsp:include page="/WEB-INF/subviews/header.jsp" >
            <jsp:param name="sub.title" value="會員註冊"/>
        </jsp:include>
        <div class="article">
            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && errors.size() > 0) {
            %>
            <%= errors%>
            <%}%>
            <form action='register.do' method="POST">
                <p>
                    <label>帳號:</label>
                    <input name='userid' type='text' required pattern="[A-Z][12]\d\d\d\d\d\d\d\d"
                           placeholder='請輸入身分證號' 
                           value='<%= request.getMethod().equals("GET") ? "" : request.getParameter("userid")%>'>
                </p>
                <p>
                    <label>姓名:</label>
                    <input name='name' type='text' required placeholder='請輸入姓名' 
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("name") : ""%>" >
                </p>
                <p>
                    <label>密碼:</label>
                    <input name='password1' type='password' placeholder='請輸入密碼(6~20個字)'
                           minlength="6" maxlength="20" required><br>
                    <label>確認:</label>
                    <input name='password2' type='password' placeholder='請再確認密碼(6~20個字)'
                           minlength="6" maxlength="20" required>
                </p>
                <p>
                    <label>性別:</label> 
                    <input type='radio' name='gender' id='male' value="<%= Customer.MALE%>" required <%= String.valueOf(Customer.MALE).equals(request.getParameter("gender")) ? "checked" : ""%> ><label for='male'>男</label>
                    <input type='radio' name='gender' id='female' value="<%= Customer.FEMALE%>" required <%= "F".equals(request.getParameter("gender")) ? "checked" : ""%>><label for='female'>女</label>
                </p>
                <p>
                    <label>Email:</label>
                    <input name='email' type='email' required placeholder='請輸入Email' value="<%= !request.getMethod().equals("GET") ? request.getParameter("email") : ""%>">
                </p>              
                <p>
                    <label>生日:</label>
                    <input name='birthday' type='date'required>
                </p>                 
                <p>
                    <label>婚姻狀況:</label>
                    <input type="checkbox" name='married' value="married" checked><label>已婚</label>
                </p>
                <p>
                    <label>血型:</label>
                    <select name='bloodType'>
                        <option value=''>請選擇...</option>
                        <% for (int i = 0; i < BloodType.values().length; i++) {%>
                        <option value='<%= BloodType.values()[i]%>'><%= BloodType.values()[i]%>型</option>
                        <% }%>
                    </select>
                </p>

                <p>

                    <label>驗證碼:</label>
                    <input name='captcha' type='text' required style='width: 12em'
                           placeholder='請依右圖輸入(不分大小寫)'><br>
                    <!--<img src='images/captcha_login.png' style="vertical-align: middle;">-->
                    <a href="javascript:refreshCaptcha()">
                        <img id="captchaImage" src="/images/register_captcha.jpeg" style="vertical-align: middle">
                    </a>                    
                </p>
                <input type='submit' value="確定">
            </form>
        </div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>