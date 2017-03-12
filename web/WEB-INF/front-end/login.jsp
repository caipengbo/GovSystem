<%--
  Created by IntelliJ IDEA.
  User: Myth
  Date: 3/12/2017
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/login.action" method="post">
        用户名：<input type="text" name="username" value=""/>
        <br/>
        密码：<input type="password" name="password" value=""/>
        <br/>
        验证码：<input type="text" name="captcha" value=""/> 1234
        <br/>

        <br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
