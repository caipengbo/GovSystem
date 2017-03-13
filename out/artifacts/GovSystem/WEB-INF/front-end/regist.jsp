<%--
  Created by IntelliJ IDEA.
  User: Myth
  Date: 3/12/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/regist.action" method="post">
        用户名：<input type="text" name="username" value=""/>
        <br/>
        密码：<input type="password" name="password" value=""/>
        <br/>
        姓名：<input  type="text" name="name" value=""/>
        <br/>
        生日：<input  type="date" name="birthday" value=""/>
        <br/>
        <br/>
        <input type="submit" value="注册"/>
    </form>
</body>
</html>
