<%@ page import="govsystem.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人设置</title>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <style>
        *{ font-family: Microsoft YaHei, Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
            margin: 0;
            padding: 0;
            border: 0;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-3 column"></div>
        <div class="col-md-6 column">
            <form role="form" id="myform">
                <div class="form-group">
                    <br><br>
                    <label for="username">用户名</label><input type="text" class="form-control" id="username" name="username" readonly value="${sessionScope.user.username}" />
                    <br><br>
                    <label for="username">姓名</label><input type="text" class="form-control" id="name" name="name"  value="${sessionScope.user.name}" />
                </div>
                出生日期
                <input class="datainp form-control" name="birthday" id="datebut" type="text" placeholder="未选择" value="${sessionScope.user.birthday}" readonly onClick="jeDate({dateCell:'#datebut',isTime:true,format:'YYYY-MM-DD'})" />
                </br>
                <%--<button type="button" class="btn btn-default btn-block btn-success " class="w-control" onClick="jeDate({dateCell:'#datebut',isTime:true,format:'YYYY-MM-DD'})">选择日期</button>--%>
                <br>
                <a href="#foget" data-toggle="modal">
                    <button type="button" class="btn btn-default btn-block btn-info " class="w-control">修改密码</button>
                </a>
                <br><br>
                <%
                    int identityflag = ((User)session.getAttribute("user")).getIdentityFlag();
                    if( identityflag == 0 ) {  //经过判断没有实名认证
                        out.print("<a href='#authentication' data-toggle='modal' id='nameConfirm'><button type='button' class='btn btn-default btn-block  btn-danger' id='authenticationBtn' readonly='readonly'>实名认证</button></a><span id='control'></span>");
                    } else if (identityflag == 1) {
                        out.print("<button type='button' class='btn btn-default btn-block  btn-success' disabled='disabled'>已实名认证</button>");
                    }
                %>
                <span id="control"></span>
                <br><br><br><br>
                <button type="button" class="btn btn-default btn-block  btn-info" onclick="save()">提交</button>
                <br>
            </form>
        </div>
        <div class="col-md-3 column"> </div>
    </div>
</div>

<!--实名认证-->
<div class="modal fade" id="authentication" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">
                    实名认证
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" action="" >
                    <div class="form-group" >
                        <label >身份证号</label><input type="text" class="form-control" id="idNum" />
                    </div>

                    <div class="form-group">
                        <p  class="btn btn-default btn-success btn-block" onclick="authentication()" data-dismiss="modal">提交</p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="foget" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    修改密码
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" action="" >
                    <div class="form-group" >
                        <label >原密码</label><input type="password" class="form-control" id="oldPassword" name="oldPassword" />
                    </div>
                    <div class="form-group">
                        <label >新密码</label><input type="password" class="form-control" id="newPassword" name="newPassword" />
                    </div>
                    <div class="form-group">
                        <label >确认密码</label><input type="password" class="form-control" id="confirmPassword" name="confirmPassword" />
                    </div>

                    <div class="form-group">
                        <p  class="btn btn-default btn-success btn-block" onclick="changePassword()">提交</p>
                        <%--<p  class="btn btn-default btn-success btn-block" type="submit">表单同步提交</p>--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
    //实名认证
    function authentication() {
        var identitycode = $("#idNum").val();
        $("#nameConfirm").remove();
        $('#authentication').modal('hide');
        $.ajax({
            type: "GET",
            url: "authenticate.action?identitycode=" + identitycode,
            dataType: "json",
            success: function (result) {
                if (result.msg == "success") {
                    $("#control").append("<button type='button' class='btn btn-default btn-block  btn-success' disabled='disabled'>已实名认证</button>");
                    alert("认证成功");
                } else {
                    $("#control").append("<a href='#authentication' data-toggle='modal' id='nameConfirm'><button type='button' class='btn btn-default btn-block  btn-danger' id='authenticationBtn' readonly='readonly'>实名认证</button></a><span id='control'></span>");
                    alert("信息错误,认证失败");
                }
            }
        })
    }
    function changePassword(){ //修改密码
        var oldPassword=$("#oldPassword").val();
        var newPassword=$("#newPassword").val();
        var confirmPassword=$("#confirmPassword").val();
        if(confirmPassword!=newPassword){  //密码不一样
            alert("两次密码输入不正确，请重新输入");
            return;
        }
        $.ajax({
            type:"GET",
            url:"changePassword.action?oldPassword=" + oldPassword+"&newPassword="+newPassword,
            contentType:"application/json",
            success:function(result){
                if(result.msg == "success") {
                    alert("成功");
                } else {
                    alert("失败");
                }

            }
        })
    }
    //修改
    function save(){
        var username=$("#username").val();
        var name=$("#name").val();
        var birthday=$("#datebut").val();
        var user = {"username":username,"name":name,"birthday":birthday};
        $.ajax({
            type:"POST",
            url:"saveUser.action",
            data:JSON.stringify(user),
            dataType:"json",
            contentType:"application/json",
            success:function(result){
                if(result.msg == "success") {
                    alert("修改成功");
                } else {
                    alert("失败");
                }
            }
        })
    }
</script>
</body>
</html>
