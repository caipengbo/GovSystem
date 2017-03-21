<%@ page import="govsystem.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

<a href="${pageContext.request.contextPath }/logout.action">注销</a><br>
Session里面的user${sessionScope.user.name} <br>
Session里面的admin${sessionScope.admin.name} <br>
-----------------------------------<br>
<%
    out.print("Jsp：Session："+((User)session.getAttribute("role")).toString());
%>
</body>
</html>
