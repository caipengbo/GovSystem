<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息管理</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui.datagrid-detailview.js"></script>
</head>
<body>
<div class="easyui-panel" id="view_position">
    <div id="show_datagrid">
        <div style="margin-bottom:10px;margin-left:200px">
            <span>信息号：</span>
            <span><input class="easyui-searchbox" data-options="prompt:'请输入信息号...',searcher:doSearch" style="width:250px"></span>
        </div>
        <table id="data_grid" title="所有信息" class="easyui-datagrid" style="width:900px;height:370px"
               data-options="
		url:'getNewsToJson.action',
		method:'post',
		toolbar:'#toolbar',
		fitColumns:true,
		rownumbers:true	,
		singleSelect:true,
		striped:true,
		pagination:true
		">
            <thead>
            <tr>
                <th field="nid" width="10">信息号</th>
                <th field="title"  width="60">标题</th>
                <th field="digest"  width="60">摘要</th>
                <th field="postTime" width="30">发布日期</th>
                <th field="name"  width="15">发布人</th>
                <th data-options="field:'isPublic',width:'17', formatter:format">是否公开</th>
                <th field="applyNum"  width="18">申请人数</th>
                <th field="messageNum"  width="18">留言数目</th>
                <th field="lookedNum"  width="20">可查看人数</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="popAddDlg()">发布</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="popModifyDlg()">更改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteNews()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="applyManage()">申请处理</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-man" plain="true" onclick="lookUser()">可查看用户</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="lookLeaveMessage()">查看留言</a>
        </div>
    </div> <!-- show_datagrid div end -->
    <!-- 修改对话框 -->
    <div id="modifydlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#modifydlg-buttons">
        <form id="modify_news" method="post" action="modifyNews.action">
            <div class="dlg_body">
                <%--隐藏的nid与postTime--%>
                <input id="nid" type="hidden" name="nid">
                <input id="postTime" type="hidden" name="postTime">
                <div style="margin-bottom:20px">
                    <input type="radio" name="isPublic" value="1">公开</input>
                    <input type="radio" name="isPublic" value="0">不公开</input>
                </div>

                <div style="margin-bottom:20px">
                    <input id="title"  class="easyui-textbox" label="标题：" name="title" style="width:75%;">
                </div>
                <div style="margin-bottom:20px">
                    <input id="digest"  class="easyui-textbox" label="摘要：" labelPosition="top"
                           data-options="multiline:true" name="digest" style="width:250px;height:100px;height:100px">
                </div>
                <div style="margin-bottom:20px">
                    <span><input id="content" class="easyui-textbox" name="content"  label="内容："
                        labelPosition="top" data-options="multiline:true" style="width:250px;height:100px"></span>
                </div>

            </div>
        </form>
    </div> <!-- 修改对话框结束-->
    <div id="modifydlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="modifyNews()">确认</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#modifydlg').dialog('close')">取消</a>
    </div>
    <!-- 添加对话框 -->
    <div id="adddlg" class="easyui-dialog" style="padding:10px 20px"
         closed="true" buttons="#adddlg-buttons">
        <form id="add_news" method="post" action="addNews.action">
            <div class="dlg_body">
                <div style="margin-bottom:20px">
                    <input type="radio" name="isPublic" value="1" checked="checked">公开</input>
                    <input type="radio" name="isPublic" value="0">不公开</input>
                </div>

                <div style="margin-bottom:20px">
                    <input  class="easyui-textbox" label="标题：" name="title" style="width:75%;">
                </div>
                <div style="margin-bottom:20px">
                    <input  class="easyui-textbox" label="摘要：" labelPosition="top"
                           data-options="multiline:true" name="digest" style="width:250px;height:100px;height:100px">
                </div>
                <div style="margin-bottom:20px">
                    <span><input class="easyui-textbox" name="content"  label="内容："
                                 labelPosition="top" data-options="multiline:true" style="width:250px;height:100px"></span>
                </div>
            </div>
        </form>
    </div> <!-- 添加对话框结束-->
    <div id="adddlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addNews()">确认</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adddlg').dialog('close')">取消</a>
    </div>
    <%--查看申请对话框--%>
    <div id="applydlg" class="easyui-dialog" style="padding:10px 20px"
         closed="true">
        <table id="apply_data_grid" class="easyui-datagrid" style="width:570px;height:370px"
               data-options="
		method:'post',
		toolbar:'#appdg_toolbar',
		fitColumns:true,
		rownumbers:true	,
		singleSelect:true,
		striped:true,
		pagination:true
		">
            <thead>
            <tr>
                <th field="uid" width="40">用户ID</th>
                <th field="username"  width="50">用户名</th>
                <th field="name" width="40">姓名</th>
                <th field="birthday"  width="60">出生年月</th>
                <th field="identityCode"  width="60">身份证</th>
                <th data-options="field:'identityFlag',width:'45', formatter:formatAuth">是否实名</th>
            </tr>
            </thead>
        </table>
        <div id="appdg_toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="allowApply()">允许</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="refuseApply()">拒绝</a>
        </div>
    </div>  <%--查看申请对话框--%>
    <%--可查看用户对话框--%>
    <div id="userdlg" class="easyui-dialog" style="padding:10px 20px"
         closed="true">

        <table id="user_data_grid" class="easyui-datagrid" style="width:570px;height:370px"
               data-options="
		method:'post',
		toolbar:'#udg_toolbar',
		fitColumns:true,
		rownumbers:true	,
		singleSelect:true,
		striped:true,
		pagination:true
		">
            <thead>
            <tr>
                <th field="uid" width="40">用户ID</th>
                <th field="username"  width="50">用户名</th>
                <th field="name" width="40">姓名</th>
                <th field="birthday"  width="60">出生年月</th>
                <th field="identityCode"  width="60">身份证</th>
                <th data-options="field:'identityFlag',width:'45', formatter:formatAuth">是否实名</th>
            </tr>
            </thead>
        </table>
        <div id="udg_toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="refuseView()">禁止其查看</a>
        </div>
    </div>  <%--可查看用户对话框--%>
    <%--留言对话框--%>
    <div id="msgdlg" class="easyui-dialog" style="padding:10px 20px"
         closed="true">

        <table id="message_data_grid" class="easyui-datagrid" style="width:570px;height:370px"
               data-options="
		method:'post',
		toolbar:'#mdg_toolbar',
		fitColumns:true,
		rownumbers:true	,
		singleSelect:true,
		striped:true,
		pagination:true
		">
            <thead>
            <tr>
                <th field="mid" width="18">留言编号</th>
                <th field="content"  width="60">内容</th>
                <th field="postTime"  width="20">留言时间</th>
                <th field="uid" width="10">留言人</th>
                <th data-options="field:'state',width:'15', formatter:formatState">审核</th>
            </tr>
            </thead>
        </table>
        <div id="mdg_toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="allowMessage()">通过</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteMessage()">删除留言</a>
        </div>
    </div>  <%--留言对话框结束--%>

</div>
<script>
    function dgReload(){
        $('#data_grid').datagrid('reload');
    }
    <%--对话框关闭时候，自动刷新信息数据--%>
    $("#applydlg").dialog({
        onClose: function() {
            dgReload();
        }
    });
    $("#userdlg").dialog({
        onClose: function() {
            dgReload();
        }
    });
    $("#msgdlg").dialog({
        onClose: function() {
            dgReload();
        }
    });
    //Datagrid详情视图
    $('#data_grid').datagrid({
        view: detailview,
        detailFormatter:function(index,row){
            return '<div class="ddv" style="padding:5px 0"></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:false,
                cache:false,
                href:'getNewsContent.action?nid='+row.nid,
                onLoad:function(){
                    $('#data_grid').datagrid('fixDetailRowHeight',index);
                }
            });
            $('#data_grid').datagrid('fixDetailRowHeight',index);
        }
    });

    $(function(){
        $('#data_grid').datagrid('clientPaging');
        //自定义datagrid 的pagination对象
        var p = $('#data_grid').datagrid('getPager');
        if (p){
            $(p).pagination({
                showRefresh:false,
                buttons: [{
                    //由于使用了自己添加的客户端分页功能，使得原有的datagrid分页刷新按钮失灵，所以自己添加一个刷新按钮
                    iconCls:'icon-reload',
                    handler:function(){
                        dgReload();
                    }
                }]
            });
        }
    });
    //格式化表格显示
    function format(val,row,index){
        if (row.isPublic == 0)
            return "<span style='color:red'>非公开×</span>";
        else
            return "<span style='color:green'>公开√</span>";
    }

    function formatAuth(val,row,index){
        if (row.identityFlag == 0)
            return "<span style='color:red'>未认证×</span>";
        else
            return "<span style='color:green'>已认证√</span>";
    }
    function formatState(val,row,index){
        if (row.state == 0)
            return "<span style='color:red'>未通过×</span>";
        else
            return "<span style='color:green'>已通过√</span>";
    }
    //搜索栏实现
    function doSearch(value,name){
        $('#data_grid').datagrid('load',{
            nid: value
        });
    }

    //弹出增加对话框
    function popAddDlg(){
        $('#adddlg').window('open').window('resize',{width:'400px',height:'300px',top: 100,left:300});
        $('#adddlg').dialog('setTitle','增加');
    }
    //添加信息表单
    function addNews(){
        $('#add_news').form('submit',{
            onSubmit: function(){
                return $(this).form('validate');
            },
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#adddlg').dialog('close');
                if (result.msg == "success"){
                    dgReload();
                    $.messager.alert('成功','信息已添加！','info');
                } else {
                    $.messager.alert('失败','添加失败，请查看输入的信息是否符合规范！','error');
                }
            }
        });
    }

    //弹出修改对话框
    function popModifyDlg(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $('#modifydlg').window('open').window('resize',{width:'400px',height:'300px',top: 100,left:300});
            $('#modifydlg').dialog('setTitle','更改');
            $('#modify_news').form('load',row);
        }
    }
    //修改信息表单
    function modifyNews(){
        //注意form提交与下面ajax提交的返回的json结果的不同
        $('#modify_news').form('submit',{
            onSubmit: function(){
                return $(this).form('validate');
            },
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#modifydlg').dialog('close');
                if (result.msg == "success"){
                    dgReload();
                    $.messager.alert('成功','已成功修改！','info');
                } else {
                    $.messager.alert('失败','修改失败，请查看修改的信息是否符合规范！','error');
                }
            }
        });
    }
    //删除
    function deleteNews(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除', '确定删除该条信息吗?', function(r){
                if (r){
                    $.ajax({
                        type:'POST',
                        url:'deleteNewsById.action',
                        data:JSON.stringify(row),
                        dataType:'json',
                        contentType:'application/json',
                        success:function(result){
                            //  接收的是json对象
                            if (result.msg == 'success'){
                                dgReload();
                                $.messager.alert('成功','该信息已经成功删除！','info');
                            } else {
                                $.messager.alert('失败','删除失败！','error');
                            }
                        }
                    });
                }
            });
        }
    }
    function applyManage() {
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            if (row.isPublic == 1) {
                $.messager.alert('无法查看','该信息为公开信息，所有用户均可查看','error');
                return;
            }
            if (row.applyNum == 0) {
                $.messager.alert('无法查看','无用户申请查看该信息','error');
                return;
            }
            var urlstr = "getApplyUserToJson.action?nid=" + row.nid;
            $('#applydlg').window('open').window('resize',{width:'620px',height:'430px',top: 50,left:200});
            $('#applydlg').dialog('setTitle','用户申请');
            $('#apply_data_grid').datagrid({
                url: urlstr
            });
        }
    }

    function lookUser() {
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            if (row.isPublic == 1) {
                $.messager.alert('无法查看','该信息为公开信息，所有用户均可查看','error');
                return;
            }
            if (row.lookedNum == 0) {
                $.messager.alert('无法查看','无用户可查看该信息','error');
                return;
            }
            var urlstr = "getLookedUserToJson.action?nid=" + row.nid;
            $('#userdlg').window('open').window('resize',{width:'620px',height:'430px',top: 50,left:200});
            $('#userdlg').dialog('setTitle','可查看的用户');
            $('#user_data_grid').datagrid({
                url: urlstr
            });
        }
    }
    function lookLeaveMessage(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            if (row.messageNum == 0) {
                $.messager.alert('无法查看','该信息无对应留言','error');
                return;
            }
            var urlstr = "getMessageToJson.action?nid=" + row.nid;
            $('#msgdlg').window('open').window('resize',{width:'620px',height:'430px',top: 50,left:200});
            $('#msgdlg').dialog('setTitle','留言');
            $('#message_data_grid').datagrid({
                url: urlstr
            });
        }
    }
//    允许申请
    function allowApply() {
        var row1 = $('#data_grid').datagrid('getSelected');
        var row2 = $('#apply_data_grid').datagrid('getSelected');
        if (row2) {
            $.ajax({
                type:'GET',
                url:'allowApply.action?nid=' + row1.nid + '&uid='+row2.uid,
                contentType:'application/json',
                success:function(result){
                    //  接收的是json对象
                    if (result.msg == 'success') {
                        $('#apply_data_grid').datagrid('reload');
                        $.messager.alert('成功','已经允许该用户查看信息！','info');
                    } else {
                        $.messager.alert('失败','出错','error');
                    }
                }
            });
        }
    }
//    拒绝用户申请
    function refuseApply() {
        var row1 = $('#data_grid').datagrid('getSelected');
        var row2 = $('#apply_data_grid').datagrid('getSelected');
        if (row2) {
            $.ajax({
                type:'GET',
                url:'refuseApply.action?nid=' + row1.nid + '&uid='+row2.uid,
                contentType:'application/json',
                success:function(result){
                    //  接收的是json对象
                    if (result.msg == 'success') {
                        $('#apply_data_grid').datagrid('reload');
                        $.messager.alert('成功','已拒绝该用户申请！','info');
                    } else {
                        $.messager.alert('失败','出错','error');
                    }
                }
            });
        }
    }


//    拒绝查看
    function refuseView() {
        var row1 = $('#data_grid').datagrid('getSelected');
        var row2 = $('#user_data_grid').datagrid('getSelected');
        if (row2) {
            $.ajax({
                type:'GET',
                url:'refuseView.action?nid=' + row1.nid + '&uid='+row2.uid,
                contentType:'application/json',
                success:function(result){
                    //  接收的是json对象
                    if (result.msg == 'success') {
                        $('#user_data_grid').datagrid('reload');
                        $.messager.alert('成功','已拒绝该用户查看信息！','info');
                    } else {
                        $.messager.alert('失败','出错','error');
                    }
                }
            });
        }
    }
    //审核通过留言
    function allowMessage() {
        var row = $('#message_data_grid').datagrid('getSelected');
        if (row) {
            $.ajax({
                type:'POST',
                url:'checkMessage.action',
                data:JSON.stringify(row),
                dataType:'json',
                contentType:'application/json',
                success:function(result){
                    //  接收的是json对象
                    if (result.msg == 'success') {
                        $('#apply_data_grid').datagrid('reload');
                        $.messager.alert('成功','已经允许该用户查看信息！','info');
                    } else {
                        $.messager.alert('失败','出错','error');
                    }
                }
            });
        }
    }
    function deleteMessage() {
        var row = $('#message_data_grid').datagrid('getSelected');
        if (row) {
            $.ajax({
                type:'POST',
                url:'deleteMessage.action',
                data:JSON.stringify(row),
                dataType:'json',
                contentType:'application/json',
                success:function(result){
                    //  接收的是json对象
                    if (result.msg == 'success') {
                        $('#message_data_grid').datagrid('reload');
                        $.messager.alert('成功','已删除该留言！','info');
                    } else {
                        $.messager.alert('失败','出错','error');
                    }
                }
            });
        }
    }
</script>
</body>
</html>