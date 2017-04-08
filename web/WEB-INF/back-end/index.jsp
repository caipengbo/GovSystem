
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理首页</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
</head>
<body class="easyui-layout panel-noscroll">
<!-- 顶部 -->
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
    <span class="fontlogo">后台管理</span>
    <div style="float:right;margin-top:10px">
        <span id="clock" style="font-size:1.5em"></span>
        <span style="font-size:1.5em">
            管理员：
				</span>
        <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#user_setting'">
            <span id="login_user" style="font-size:1.5em">${sessionScope.admin.name}</span></a>
        <div id="user_setting">
            <div><a href="javascript:openInfoDialog()">个人设置</a></div>
            <div><a href="javascript:exitSystem()">退出</a></div>
        </div>
        <!-- 修改个人信息对话框 -->
        <div id="modify_info_dialog" class="easyui-dialog" closed="true"   buttons="#dlg-buttons" style="width:400px;height:298px;padding-left:30px;padding-right:30px;padding-top:10px">
            <form id="private_modify_form" method="post" action="./controller/private_modify.php">
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="username" label="用户名" style="width:75%;" data-options="disabled:true">
                    <input id="username_hidden" type="hidden" name="username">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" label="姓名" name="name" style="width:75%;">
                </div>
                <div style="margin-bottom:20px">
                    <input id="old_password" class="easyui-passwordbox" label="原密码" prompt="旧密码" iconWidth="28" style="width:75%;">
                </div>
                <div style="margin-bottom:20px">
                    <input id="new_password" class="easyui-passwordbox" label="新密码" prompt="新密码" iconWidth="28" style="width:75%;">
                </div>
                <div style="margin-bottom:20px">
                    <input id="repeat_password" name="newpsw" class="easyui-passwordbox" label="重复密码" prompt="重复新密码" iconWidth="28" validType="confirmPass['#new_password']" style="width:75%;">
                </div>
            </form>
            <div id="dlg-buttons">
                <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">修改</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#modify_info_dialog').dialog('close')">取消</a>
            </div>
        </div><!-- 修改个人信息对话框结束 -->
    </div>
</div>
<!-- 左侧手风琴 -->
<div data-options="region:'west',split:true,title:'管理目录'" style="width:18%;padding:0px;overflow:hidden">
    <div id="left_accordion" class="easyui-accordion" style="width:100%;height: 100%;padding:0">
        <div title="用户管理" data-options="iconCls:'icon-production'" style="padding:0px;">
            <li><a href="javascript:addTab('用户管理','${pageContext.request.contextPath }/toUser.action')">用户管理</a></li>
            <li><a href="javascript:addAdminTab()">管理员管理</a></li>
        </div>
        <div title="信息管理" data-options="iconCls:'icon-sale'" style="padding:0px;">
            <li><a href="javascript:addTab('信息管理','${pageContext.request.contextPath }/toNews.action')">信息管理</a></li>
        </div>
        <div title="政民互动管理" data-options="iconCls:'icon-search'" style="padding:0px;">
            <li><a href="javascript:addTab('问卷调查','${pageContext.request.contextPath }/toQuestion.action')">问卷调查</a></li>
            <li><a href="javascript:addTab('网上听证','${pageContext.request.contextPath }/toVideoManage.action')">网上听证</a></li>
        </div>
    </div>
</div>


<!-- 中部主视图 -->
<div data-options="region:'center',border:false" style="height:50px;background:#A9FACD;padding:0px;">
    <div id="main-view" class="easyui-tabs" style="width:100%;height:100%;">
        <div title="主页">
            <iframe scrolling="auto" frameborder="0"  src="${pageContext.request.contextPath }/toHome.action" style="width:100%;height:100%;"></iframe>
        </div>
    </div>
</div>
<!-- 页脚底部 -->
<div data-options="region:'south',border:false" style="height:30px;background:#A9FACD;padding:2px;text-align:center;font-size:12px;overflow:hidden">
    Copyright © 2017 - 2018 All Rights Reserved<br>
</div>
<script>
    var loginuser = {"username":"${sessionScope.admin.username}","name":"${sessionScope.admin.name}","password":"${sessionScope.admin.password}"};
    //在主视图中添加标签
    function addTab(title, url){
        if ($('#main-view').tabs('exists', title)){
            $('#main-view').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
            $('#main-view').tabs('add',{
                title:title,
                content:content,
                closable:true,
            });
        }
    }
    // 实时刷新时间
    setInterval("setClock()",1000);
    function setClock(){
        var curTime = new Date();
        $("#clock").html(curTime.toLocaleString());
    }
    //打开修改个人信息对话框
    var oldPassword = "";
    function openInfoDialog(){
        $('#modify_info_dialog').dialog({
            onClose:function(){
                clearForm();
            }
        });
        $('#modify_info_dialog').dialog('open').dialog('setTitle','个人设置');
        $('#private_modify_form').form('load',loginuser);
        oldPassword = loginuser.password;
        $('#username_hidden').val(loginuser.username);
    }
    // 重复输入密码，验证
    $.extend($.fn.validatebox.defaults.rules, {
        confirmPass: {
            validator: function(value, param){
                var pass = $(param[0]).passwordbox('getValue');
                return value == pass;
            },
            message: '密码不一致'
        }
    });
    //个人设置表单（和private_setting.html功能一样）
    function submitForm(){
        $('#private_modify_form').form('submit',{
            onSubmit:function(){
                if (oldPassword !=  hex_md5($('#old_password').passwordbox('getValue'))) {
                    alert("原密码错误");
                    clearForm();
                    return false;
                }
                if ($('#new_password').passwordbox('getValue') != $('#repeat_password').passwordbox('getValue')) {
                    alert("两次密码不一致!");
                    clearForm();
                    return false;
                }
                return true;
            },
            success:function(data){
                var dataobj = eval('('+ data +')');
                if (dataobj.return_num == 1) {
                    //更新loginuser对象
                    loginuser.name = dataobj.new_name;
                    loginuser.password = dataobj.newpsw;
                    oldPassword = dataobj.newpsw;
                    //更新右上角名字
                    $("#login_user").text(loginuser.name);
                    $('#modify_info_dialog').dialog('close');
                    alert('已修改!','','success');
                } else {
                    alert('发生未知的错误!','','error');
                }
            }
        });
    }
    function addAdminTab() {
        var privilege = ${sessionScope.admin.privilege};
        if(privilege == 0) {
            addTab('管理员管理','${pageContext.request.contextPath }/toAdmin.action');
        } else {
            alert("您不是超级管理员，无次权限");
        }
    }
    function clearForm(){
        $('#old_password').passwordbox('clear');
        $('#new_password').passwordbox('clear');
        $('#repeat_password').passwordbox('clear');
    }
    //退出系统
    function exitSystem(){
        $.messager.confirm('退出', '确定退出吗?', function(r) {
            if (r) {
                    $.post("logout.action");
                    location.href = "toFrontEndIndex.action";
                }
        });
    }
</script>
</body>
</html>
