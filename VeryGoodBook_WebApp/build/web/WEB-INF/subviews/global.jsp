
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--golbal.jsp-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/vgb.css">
<script
    src="https://code.jquery.com/jquery-3.0.0.js"
    integrity="sha256-jrPLZ+8vDxt2FnE1zvZXCkCcebI/C8Dt5xyaQBjxQIo="
    crossorigin="anonymous">
</script>

<link href="<%=request.getContextPath()%>/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/fancybox/jquery.fancybox.js"></script>
<script>
        function login() {
            //alert("login");
            //同步GET請求
            //location.href = "login.jsp";
            //
            //非同步請求
            $.ajax({
                url: '<%=request.getContextPath()%>/login.jsp',
                method: 'GET'
            }).done(loginDoneHandler); //logindoneHandler加小括號 就變成呼叫function   而不是事件的註冊

            function loginDoneHandler(result, status, xhr) {
                    //console.log("Login Done" + result);
                $("#loginBox").html(result);
                $.fancybox.open({
                    src: '#loginBox'
                })
            }
        }
</script>
<!--golbal.jsp end-->