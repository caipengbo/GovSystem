<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Myth
  Date: 3/1/2017
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
<%
    List<String> stringList = (List<String>)request.getAttribute("list");
    for(int i=0; i<stringList.size(); i++){
        out.println(i + "->" + stringList.get(i)+"");
    }

%>


</body>
</html>
