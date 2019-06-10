<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 這是jsp註解 --%>
<%--
    if (true) {
        response.setStatus(404);
        return;
    }
--%>
<%!
    private String welcome = "歡迎";

    public void jspInit() {
        String welcome = this.getInitParameter("welcome");
        if (welcome != null) {
        this.welcome= welcome;
        }

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello JSP</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            /* 這是CSS註解*/
        </style>
    </head>
    <body>
        <!--這是html註解-->
        <script>
            /*這是js註解*/
        </script>
        <script src="hello.js" charset="BIG5">/*charset 是跟瀏覽器說要用什麼編碼方式讀取js檔案(這個js檔案適用BIG5編碼)*/</script>
        <div><%= welcome%>，Served at: 
            <%
                //這是java註解
                /*這是java註解*/
                out.print(request.getContextPath());
            %>
            <div>
                <mark>jsp sciptlet語法</mark>示範:
                    <% //  示範課本範例CH12_20
                        final int i = 20; //這裡宣告的是區域變數，他會在_jspService方法中，若要另外宣告屬性或方法，則要用另個jsp語法
                        if (i > 10) {
                    %>
                It is a big number
                <%} else {%>
                It is a small number
                <%}%>
            </div>
            <p>
                用<mark>jsp expression語法</mark>輸出後端伺服器的時間:<%= new Date()%>
            </p>
            <hr>
            <h1>jsp隱含變數(Implicit Variables)</h1>
            <h3>request</h3>
            <p>method type: <%= request.getMethod()%></p>
            <p>url: <%= request.getRequestURL()%></p>
            <p>uri: <%= request.getRequestURI()%></p>
            <p>protocol: <%= request.getProtocol()%></p>
            <p>server: <%= request.getLocalName()%></p>
            <p>server port: <%= request.getLocalPort()%></p>
            <p>client: <%= request.getRemoteHost()%></p>
            <p>client IP: <%= request.getLocalAddr()%></p>
            <p>client port: <%= request.getRemotePort()%></p>
            <p>context-path: <%= request.getContextPath()%></p>
            <p>user-agent: <%= request.getHeader("user-agent")%></p>
            <p>使用者輸入的name: <%= request.getParameter("name")%></p>
            <p>
                <% request.setCharacterEncoding("UTF-8");%>
                <%--請記得，寫DAO時，request在第一次讀取之前要改編碼 --%>
                <%--但我們不在jsp or servlet上動 response/request 的內容，因為jsp and servlet 是 <<view>> --%>
            <form method="POST">
                <lable>輸入使用者name: </lable><input type="text" name="name" placeholder="請輸入名稱">
                <input type="submit" value="submit">
            </form>
        </p>
        <p>
            query string: <%= request.getQueryString()%>
        </p>
        <hr>
        <h3>response</h3>
        <p>response content type: <%= response.getContentType()%></p>
        <p>response status code: <%= response.getStatus()%></p>
        <hr>
        <h3>out</h3>
        <p><% out.print("Hello");%></p>
        <p>out buffer size: <%=out.getBufferSize()%></p>
        <hr>
        <h3>session</h3>
        <p>session id: <%= session.getId()%></p>
        <p>session 建立時間: <%= session.getCreationTime() %></p>
        <p>session 建立時間(換成Date單位): <%= new Date(session.getCreationTime()) %></p>
        <p>session timeout: <%= session.getMaxInactiveInterval() %></p>  <!--單位是秒-->
        <p>最後一次去伺服器的時間: <%= new Date(session.getLastAccessedTime() ) %></p>
        <hr>
        <h3>application</h3>
        <p>application REAL PATH(網站實際上執行的位置，並不是你存放程式的地方): <%= application.getRealPath("/")%></p>
        <hr>
        <h3>config(通常用來讀初始化參數)</h3>
        <p>ServletName: <%= config.getServletName()%></p>
        <p>ServletName: <%= this.getServletName()%></p>
        <p>init parameter(welcome): <%= welcome%></p>
        <hr>
        <h3>page</h3>
        <p>page==this: <%= page==this %></p> 
        <p>this.toString(): <%= this.toString()%></p>
        <p>ServletName: <%= ((HttpServlet)page).getServletName()%></p>
        <hr>
        <h3>pageContext(content資訊打成一包)</h3>
        <p>session id: <%= pageContext.getSession().getId()%></p>
        <p>session id: <%= session.getId()%></p>
        <hr>
        <h3>exception</h3>
        <p>exception message(已註解): <%--= exception.getMessage()--%></p>

    </div>

</body>
</html>

