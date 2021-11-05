<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>用户登录</title>
    </head>
    <body>
        <form action="/login" method="post">
            用户名：<input type="text" name="username"/>
            密码：<input type="text" name="password"/>
            <input type="submit" value="登录"/>
        </form>
    </body>
</html>
