<%-- 
    Document   : 99乘法表
    Created on : 2019/3/11, 下午 05:11:00
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>99乘法表</title>
    </head>
    <style>
      table{border-collapse: collapse;}
      tr td{border: solid 1px; }
    </style>
    <body>
        <h1>99乘法表</h1>

        <%
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    out.println(i + " * " + j + " = " + i * j + "<br>");
                }
            }
        %> 

        <table>
            <% for (int i = 1; i <= 9; i++) {%>
            <tr>
                <%for (int j = 1; j <= 9; j++) {%>
                <td><%=i + " * " + j + " = " + i * j%></td>
                <%}%>
            <tr>
                <%}%>
        </table>



    </body>
</html>
