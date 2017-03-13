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
                <!-- cname,tname,small_unit,big_unit,cnum,ccost,csticker_price,cunit_value  -->
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
        <form id="modify_account" method="post" action="">
            <div class="dlg_body">
                <div>
                    <input id="aid" type="hidden" name="aid">
                    <span><input class="easyui-textbox" name="aitem"  label="账目记录：" labelPosition="top" data-options="multiline:true" style="width:200px;height:100px"></span>
                </div>
                <div>
                    <span><input class="easyui-numberbox"  label="账目金额：" labelPosition="left" name="aitem_money" data-options="precision:0,width:'185px'" required=true></span>
                </div>
                <div>
                    <input type="radio" name="isrepay" value="0">未归还</input>
                    <input type="radio" name="isrepay" value="1">已归还</input>
                </div>
            </div>
        </form>
    </div> <!-- 修改对话框结束-->
    <div id="modifydlg-buttons">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="modifyAccount()">确认</a>
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
        if (row.identityFlag == 0)
            return "<span style='color:red'>未认证×</span>";
        else
            return "<span style='color:green'>已认证√</span>";
    }
    function doSearch(value,name){
        if (name == "all")
            name="";
        $('#data_grid').datagrid('load',{
            cname: value,
            tname: name
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
