<%@page import="java.util.List"%>
<%@page pageEncoding='UTF-8'%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>登入</title>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style/vgb.css">    
        <script>
            function refreshCaptcha(){
                var image = document.getElementById("captchaImage");
                image.src="images/captcha.jpeg?refresh=" + new Date();
            }
        </script>

    </head>
    <body>
        <div class="header">
            <h1><a href="/vgb">非常好書</a> <sub>登入</sub></h1>
        </div>
        <div>
            <%
                //這裡是做記住登入帳號的功能
                Cookie[] cookies = request.getCookies();
                String id="";
                String auto="";
                if(cookies!=null && cookies.length>0 ){
                    for(int i=0; i<cookies.length; i++){
                        Cookie cookie = cookies[i];
                        if(cookie.getName().equals("id")){
                            id = cookie.getValue();
                        }
                        if(cookie.getName().equals("auto")){
                            auto = cookie.getValue();
                        }
                    }
                }
            %>
            
            <%
                List<String> errors = (List<String>)request.getAttribute("errors");
                   if(errors!=null && errors.size()>0)out.println(errors); //[問題] 忘記什麼情況下需要寫這兩行code??
            %>
            <form method="POST" action="login.do"> 
                <!--action="login.do" 這個動作會產生一個網址叫做 http://localhost:8084/vgb/login.do-->
                <!--這個網址會跟 每個servlet程式 註解的網址做比對，去找到對的sevlet類別並執行該類別對應的 post method-->
                <p>
                    <label for='uid'>帳號:</label>
                    <input type="text" placeholder="請輸入身分證號或email" id='uid' name="id" pattern='[A-Z][12][0-9]{8}'
                           required value='<%= request.getMethod().equals("GET")? id :request.getParameter("id") %>' >
                    <input type="checkbox" name="auto" value="ON" <%= auto %> > 記住我的帳號
                </p>
                <p>
                    <label>密碼:</label>
                    <input type="password" placeholder="請輸入6~20個字元的密碼" 
                           name="pwd" required>
                </p>
                <p>        			
                    <label>驗證碼:</label>
                    <input type="text" placeholder="請依上圖輸入驗證碼" 
                           name="captcha" required value='<%= request.getMethod().equals("GET")? "" :request.getParameter("captcha")  %>' autocomplete="off">
                    <!--<a href="images/captcha.jpeg"><img src="images/captcha.jpeg" style="vertical-align: middle"></a> 體驗一下<a> 是同步請求-->
                    <a href="javascript:refreshCaptcha()">
                        <img id="captchaImage" src="images/captcha.jpeg" style="vertical-align: middle">
                    </a>
                </p>
                <button type="submit">確定</button>
            </form>
        </div>
    </body>
</html>
