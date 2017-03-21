<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/loginExmple.action" method="post">
        用户名：<input type="text" name="username" value=""/>
        <br/>
        密码：<input type="password" name="password" value=""/>
        <br/>
        用户<input type="radio" name="isAdm" value="0">
        管理员<input type="radio" name="isAdm" value="1">
        <br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
