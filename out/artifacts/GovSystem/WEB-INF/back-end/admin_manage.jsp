<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员账户管理</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
</head>
<body>
<div class="easyui-panel" id="view_position_2">
    <div id="user_manage">
        <table id="data_grid" title="所有管理员" class="easyui-datagrid" style="width:900px;height:380px"
               data-options="
		url:'getAdminToJson.action',
		method:'post',
		toolbar:'#toolbar',
		fitColumns:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true
		">
            <thead>
            <tr>
                <th field="username" width="80">用户名</th>
                <th field="name"  width="50">姓名</th>
                <th field="birthday"  width="50">生日</th>
                <th field="privilege" width="60" formatter="formatter">权限</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="popAddDlg()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="popUpdateDlg()">修改信息</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteAdmin()">删除</a>
        </div>
    </div> <!-- user_manage div end -->
    <!-- add dialog begin-->
    <div id="adddlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#adddlg-buttons">
        <form id="addadmin_form" method="post" action="addAdmin.action">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" data-options="required:true" name="username" label="用户名" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" label="初始密码" name="password" data-options="required:true" iconWidth="28" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input  data-options="required:true" class="easyui-textbox" label="姓名" name="name" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input  data-options="required:true" class="easyui-datebox" label="生日" name="birthday" style="width:75%;">
            </div>
        </form>
    </div>
    <div id="adddlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addAdmin()" style="width:80px">确认</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adddlg').dialog('close')" style="width:80px;margin-left:20px;margin-right:40px">取消</a>
    </div> 	<!-- add dialog end -->
    <!-- update dialog begin -->
    <div id="updatedlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#updatedlg-buttons">
        <form id="updateadmin_form" method="post" action="/modifyAdmin.action">
            <div style="margin-bottom:20px">
                <input type="hidden" name="aid">
                <input class="easyui-textbox" data-options="required:true" name="username" label="用户名" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input id="name" data-options="required:true" class="easyui-textbox" label="姓名" name="name" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" label="重设密码" name="password" iconWidth="28" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input  data-options="required:true" class="easyui-datebox" label="生日" name="birthday" style="width:75%;">
            </div>
        </form>
    </div>
    <div id="updatedlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-ok" onclick="updateAdmin()" style="width:80px">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#updatedlg').dialog('close')" style="width:80px;margin-left:20px;margin-right:40px">取消</a>
    </div> <!-- update dialog end --><!-- 对话框结束-->
</div>
<script>
    $(document).ready(function(){
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

    function formatter(val,row){
        if (val == 1){
            return '<span style="color:blue;">普通管理员</span>';
        } else {
            return "超级管理员";
        }
    }
    function popAddDlg(){
        $('#adddlg').dialog({
            onClose:function(){
                $('#addadmin_form').form('clear');
            }
        });
        $('#adddlg').dialog('open').dialog('setTitle','添加新管理员');
    }
    function addAdmin(){
        $('#addadmin_form').form('submit',{
            onSubmit: function(){
                return $(this).form('validate');
            },
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#adddlg').dialog('close');
                if (result.msg == "success"){
                    $('#data_grid').datagrid('reload');
                    $.messager.alert('成功','该管理员已添加！','info');
                } else {
                    $.messager.alert('失败','用户名已存在！！','error');
                }
            }
        });
    }
    function popUpdateDlg() {
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $('#updatedlg').dialog('open').dialog('setTitle','修改管理员信息');
            $('#updateadmin_form').form('load',row);
        }
    }
    function updateAdmin() {
        $('#updateadmin_form').form('submit',{
            onSubmit: function(){
                return $(this).form('validate');
            },
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#updatedlg').dialog('close');
                if (result.msg == "success"){
                    $('#data_grid').datagrid('reload');
                    $.messager.alert('成功','已成功修改！','info');
                } else {
                    $.messager.alert('失败','修改失败，请查看修改的信息是否符合规范！','error');
                }
            }
        });
    }
    function deleteAdmin(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除', '确定删除该管理员吗?', function(r){
                if (r){
                    $.ajax({
                        type:'POST',
                        url:'deleteAdminById.action',
                        data:JSON.stringify(row),
                        dataType:'json',
                        contentType:'application/json',
                        success:function(result){
                            //  接收的是json对象
                            if (result.msg == 'success'){
                                $('#data_grid').datagrid('reload');
                                $.messager.alert('成功','该管理员已经成功删除！','info');
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
