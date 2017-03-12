<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试JSON</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
</head>
<body>
<table id="dg" title="My Users" class="easyui-datagrid" style="width:550px;height:250px"
       url="${pageContext.request.contextPath }/outputJson.action"
       toolbar="#toolbar"
       rownumbers="true"
       fitColumns="true"
       singleSelect="true">
    <thead>
    <tr>
        <th field="name" width="50">Name</th>
        <th field="age" width="50">Age</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>
<a href="param.action?param1=test1&param2=test2">test</a>
</body>
</html>