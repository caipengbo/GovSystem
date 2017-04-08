<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>听证视频管理</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
</head>
<body>
<div class="easyui-panel" id="view_position_2">
    <div id="user_manage">
        <table id="data_grid" title="所有管理员" class="easyui-datagrid" style="width:900px;height:380px"
               data-options="
		url:'getVideoToJson.action',
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
                <th field="vid" width="20" hidden="true">问卷编号</th>
                <th field="fileName" width="90">文件名</th>
                <th field="title" width="15">标题</th>
                <th field="description" width="80">描述</th>
                <th field="name"  width="15">发布人</th>
                <th field="posttime"  width="30">发布时间</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="popAddDlg()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="popUpdateDlg()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteVideo()">删除</a>
        </div>
    </div> <!-- user_manage div end -->
    <!-- add dialog begin-->
    <div id="adddlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#adddlg-buttons">
        <form id="addvideo_form" method="post" action="addVideo.action" enctype="multipart/form-data">
            <div style="margin-bottom:20px">
                <input  name="video" type="file" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <input  data-options="required:true" class="easyui-textbox" label="标题" name="title" style="width:75%;">
            </div>
            <div style="margin-bottom:20px">
                <span><input id="description" class="easyui-textbox" name="description"  label="描述："
                              data-options="multiline:true" style="width:250px;height:100px"></span>
            </div>
        </form>
    </div>
    <div id="adddlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addVideo()" style="width:80px">确认</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adddlg').dialog('close')" style="width:80px;margin-left:20px;margin-right:40px">取消</a>
    </div> 	<!-- add dialog end -->
    <!-- update dialog begin -->
    <div id="updatedlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#updatedlg-buttons">
        <form id="updatevideo_form" method="post" action="modifyVideo.action">
            <div style="margin-bottom:20px">
                <input type="hidden" name="vid">
                <div style="margin-bottom:20px">
                    <input  data-options="required:true" class="easyui-textbox" label="标题" name="title" style="width:75%;">
                </div>
            </div>
            <span><input id="description" class="easyui-textbox" name="description"  label="描述："
                         data-options="multiline:true" style="width:250px;height:100px"></span>
        </form>
    </div>
    <div id="updatedlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-ok" onclick="updateVideo()" style="width:80px">修改</a>
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

    function popAddDlg(){
        $('#adddlg').dialog({
            onClose:function(){
                $('#addvideo_form').form('clear');
            }
        });
        $('#adddlg').dialog('open').dialog('setTitle','添加新听证视频');
    }
    function addVideo(){
        $('#addvideo_form').form('submit',{
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
                    $.messager.alert('成功','已添加！','info');
                } else {
                    $.messager.alert('失败','该文件已经存在','error');
                }
            }
        });
    }
    function popUpdateDlg() {
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $('#updatedlg').dialog('open').dialog('setTitle','修改听证信息');
            $('#updatevideo_form').form('load',row);
        }
    }
    function updateVideo() {
        $('#updatevideo_form').form('submit',{
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
    function deleteVideo(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除', '确定删除该听证视频吗?', function(r){
                if (r){
                    $.ajax({
                        type:'POST',
                        url:'deleteVideo.action?vid='+row.vid,
                        contentType:'application/json',
                        success:function(result){
                            //  接收的是json对象
                            if (result.msg == 'success'){
                                $('#data_grid').datagrid('reload');
                                $.messager.alert('成功','该听证视频已经成功删除！','info');
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
