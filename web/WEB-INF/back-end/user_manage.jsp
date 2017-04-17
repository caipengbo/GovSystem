<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
</head>
<body>
<div class="easyui-panel" id="view_position">
    <div id="show_datagrid">
        <div style="margin-bottom:10px;margin-left:200px">
            <span>用户名：</span>
            <span><input class="easyui-searchbox" data-options="prompt:'请输入用户名...',searcher:doSearch" style="width:250px"></span>
        </div>
        <table id="data_grid" title="用户" class="easyui-datagrid" style="width:900px;height:370px"
               data-options="
		url:'getUsersToJson.action',
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
            <th field="uid" width="80">用户ID</th>
            <th field="username"  width="50">用户名</th>
            <th field="name" width="60">姓名</th>
            <th field="birthday"  width="60">出生年月</th>
            <th field="identityCode"  width="60">身份证</th>
            <th data-options="field:'identityFlag',width:'30', formatter:format">是否实名</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="popModifyDlg()">更改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteUser()">删除</a>
        </div>
    </div> <!-- show_datagrid div end -->
    <!-- 修改对话框 -->
    <div id="modifydlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#modifydlg-buttons">
        <form id="modify_user" method="post" action="modifyUser.action">
            <div class="dlg_body">
                <%--uid username password name birthday identitycode identityflag--%>
                <div style="margin-bottom:20px">
                    <%--隐藏的uid--%>
                    <input id="uid" type="hidden" name="uid">
                    <input id="username" class="easyui-textbox" data-options="readonly:true"
                           name="username" label="用户名" style="width:75%;">
                    <input id="password" type="hidden" name="password">
                </div>

                <div style="margin-bottom:20px">
                    <input id="name" data-options="required:true" class="easyui-textbox" label="姓名" name="name" style="width:75%;">
                </div>

                <div style="margin-bottom:20px">
                    <input id="birthday" data-options="required:true" class="easyui-datebox" label="生日" name="birthday" style="width:75%;">
                </div>

                <div style="margin-bottom:20px">
                    <input id="identitycode"  class="easyui-textbox" label="身份证号" data-options="readonly:true" name="identityCode" style="width:75%;">
                </div>

            </div>
        </form>
    </div> <!-- 修改对话框结束-->
    <div id="modifydlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="modifyUser()">确认</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#modifydlg').dialog('close')">取消</a>
    </div>
</div>
<script>
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
                        $('#data_grid').datagrid('reload');
                    }
                }]
            });
        }
    });
    //格式化表格显示
    function format(val,row,index){
        if (row.identityFlag == 0) {
            return "<span style='color:red'>未认证×</span>";
        } else if (row.identityFlag == 1) {
            return "<span style='color:green'>已认证√</span>";
        }
    }
    function doSearch(value){
        $('#data_grid').datagrid('load',{
            username: value
        });
    }
    //弹出修改对话框
    function popModifyDlg(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $('#modifydlg').dialog('open').dialog('setTitle','修改用户');
            $('#modify_user').form('load',row);
        }
    }
    //修改用户
    function modifyUser(){
        //注意form提交与下面ajax提交的返回的json结果的不同
        $('#modify_user').form('submit',{
            onSubmit: function(){
                return $(this).form('validate');
            },
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#modifydlg').dialog('close');
                if (result.msg == "success"){
                    $('#data_grid').datagrid('reload');
                    $.messager.alert('成功','已成功修改！','info');
                } else {
                    $.messager.alert('失败','修改失败，请查看修改的信息是否符合规范！','error');
                }
            }
        });
    }
    //删除
    function deleteUser(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除', '确定删除该条用户吗?', function(r){
                if (r){
                    $.ajax({
                        type:'POST',
                        url:'deleteUserById.action',
                        data:JSON.stringify(row),
                        dataType:'json',
                        contentType:'application/json',
                        success:function(result){
                        //  接收的是json对象
                            if (result.msg == 'success'){
                                $('#data_grid').datagrid('reload');
                                $.messager.alert('成功','该用户已经成功删除！','info');
                            } else {
                                $.messager.alert('失败','删除失败！','error');
                            }
                        }
                    });
                }
            });
        }
    }
</script>
</body>
</html>