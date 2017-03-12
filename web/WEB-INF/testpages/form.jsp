<%--
  Created by IntelliJ IDEA.
  User: Myth
  Date: 3/3/2017
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表单数据提交</title>
    <script/>
</head>
<body>
<form action="${pageContext.request.contextPath }/getStudentInfo.action" method="post">
    姓名：<input name="name" value=""/>
    <br/>
    年龄<input name="age" value=""/>
    <br/>
    <br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
