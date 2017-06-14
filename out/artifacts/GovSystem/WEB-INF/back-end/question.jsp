<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>问卷调查</title>
    <%@include file="/WEB-INF/back-end/header.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui.datagrid-detailview.js"></script>
</head>
<body>
<div class="easyui-panel" id="view_position">
    <div id="show_datagrid">
        <table id="data_grid" class="easyui-datagrid" style="width:900px;height:340px">
            <thead>
            <tr>
                <th field="qid" width="20" hidden="true">问卷编号</th>
                <th field="title" width="90">标题</th>
                <th data-options="field:'available',width:'15', formatter:showAvailable">是否公开</th>
                <th field="name"  width="15">发布人</th>
                <th field="postTime"  width="30">发布时间</th>
                <th field="allNum" width="15">参与人数</th>
                <th data-options="field:'aNum',width:'20', formatter:statisticA">非常满意</th>
                <th data-options="field:'bNum',width:'20', formatter:statisticB">比较满意</th>
                <th data-options="field:'cNum',width:'20', formatter:statisticC">一般</th>
                <th data-options="field:'dNum',width:'20', formatter:statisticD">不满意</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="popAddDlg()">添加问卷</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="popAddDlg2()">添加问题</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="popModifyDlg()">修改问卷</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteQuestion()">删除</a>
        </div>
    </div> <!-- show_datagrid div end -->
    <!-- 添加问卷对话框 -->
    <div id="adddlg" class="easyui-dialog" style="padding:10px 20px" closed="true" buttons="#adddlg-buttons">
        <form id="add_question" method="post" action="addQuestion.action">
            <div class="dlg_body">
                <div style="margin-bottom:20px">
                    <input type="radio" name="available" value="1" checked="checked">可填写</input>
                    <input type="radio" name="available" value="0">不可填写</input>
                </div>
                <div style="margin-bottom:20px">
                    <input  class="easyui-textbox" label="标题：" labelPosition="top" data-options="multiline:true"
                            name="title" style="width:250px;height:100px;height:100px">
                </div>
            </div>
        </form>
        <div id="adddlg-buttons">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addQuestion()">确认</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adddlg').dialog('close')">取消</a>
        </div>
    </div> <!-- 添加问卷对话框-->

    <!-- 添加问题对话框 -->
    <div id="adddlg2" class="easyui-dialog" style="padding:10px 20px"
         closed="true" buttons="#adddlg2-buttons">
        <form id="add_item" method="post" action="addQuestionItem.action">
            <div class="dlg_body">
                <input id="qid" type="hidden" name="qid">
                <div style="margin-bottom:20px">
                    <span><input class="easyui-textbox" name="content"  label="内容："
                                 labelPosition="top" data-options="multiline:true" style="width:250px;height:100px"></span>
                </div>
            </div>
        </form>
        <div id="adddlg2-buttons">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addItem()">确认</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#adddlg2').dialog('close')">取消</a>
        </div>
    </div> <!-- 添加问题对话框结束-->
    <!-- 修改对话框 -->
    <div id="modifydlg" class="easyui-dialog" style="width:400px;height:298px;padding:10px 20px"
         closed="true" buttons="#modifydlg-buttons">
        <form id="modify_question" method="post" action="modifyQuestion.action">
            <div class="dlg_body">
                <input type="hidden" name="qid">
                <input id="postTime" type="hidden" name="postTime">
                <div style="margin-bottom:20px">
                    <input type="radio" name="available" value="1">公开</input>
                    <input type="radio" name="available" value="0">不公开</input>
                </div>
                <div style="margin-bottom:20px">
                    <span><input id="title" class="easyui-textbox" name="title"  label="标题："
                                 labelPosition="top" data-options="multiline:true" style="width:250px;height:100px"></span>
                </div>
            </div>
        </form>
        <div id="modifydlg-buttons">
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="modifyQuestion()">确认</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#modifydlg').dialog('close')">取消</a>
        </div>
    </div> <!-- 修改对话框结束-->
</div>
<script>
    $("#adddlg").dialog({
        onClose: function() {
            $('#add_question').form('clear');
        }
    });
    $("#adddlg2").dialog({
        onClose: function() {
            $('#add_item').form('clear');
        }
    });
    $(function(){
        $('#data_grid').datagrid({
            view: detailview,
            url:"getQuestionToJson.action",
            toolbar:'#toolbar',
            method:'post',
            title:"具体问题",
            pagination:true,
            rownumbers:true,
            singleSelect:true,
            fitColumns:true,
            detailFormatter:function(index,row){
                return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
            },
            onExpandRow: function(index,row){
                $('#ddv-'+index).datagrid({
                    url:'getQuetionItemToJson.action?qid='+row.qid,
                    fitColumns:true,
                    singleSelect:true,
                    rownumbers:true,
                    loadMsg:'',
                    height:'auto',
                    columns:[[
                        {field:'qid',title:'隐藏-问卷编号',hidden:true},
                        {field:'no',title:'隐藏-问题编号',hidden:true},
                        {field:'content',title:'内容',width:100},
                        // 删除链接
                        {field:'link',width:20,title:'删除',formatter:addDeleteLink}
                    ]],
                    onResize:function(){
                        $('#data_grid').datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        setTimeout(function(){
                            $('#data_grid').datagrid('fixDetailRowHeight',index);
                        },0);
                    }
                });
                $('#data_grid').datagrid('fixDetailRowHeight',index);
            }
        });
    });
    //格式化表格显示
    function showAvailable(val,row,index){
        if (row.available == 0)
            return "<span style='color:red'>关闭×</span>";
        else
            return "<span style='color:green'>开放√</span>";
    }
    //统计每个选项
    function statisticA(val,row,index) {
        var sum = row.aNum + row.bNum + row.cNum + row.dNum;
        var aNum = row.aNum;
        if (aNum != 0)
            return aNum + "(" + (aNum/sum*100).toFixed(2)+"%)";
        else
            return aNum;
    }
    function statisticB(val,row,index) {
        var sum = row.aNum + row.bNum + row.cNum + row.dNum;
        var bNum = row.bNum;
        if (bNum != 0)
            return bNum + "(" + (bNum/sum*100).toFixed(2)+"%)";
        else
            return bNum;
    }
    function statisticC(val,row,index) {
        var sum = row.aNum + row.bNum + row.cNum + row.dNum;
        var cNum = row.cNum;
        if (cNum != 0)
            return cNum + "(" + (cNum/sum*100).toFixed(2)+"%)";
        else
            return cNum;
    }
    function statisticD(val,row,index) {
        var sum = row.aNum + row.bNum + row.cNum + row.dNum;
        var dNum = row.dNum;
        if (dNum != 0)
            return dNum + "(" + (dNum/sum*100).toFixed(2)+"%)";
        else
            return dNum;
    }

    function popAddDlg(){
        $('#adddlg').window('open').window('resize',{width:'400px',height:'300px',top: 100,left:300});
        $('#adddlg').dialog('setTitle','添加问卷');
    }
    function addQuestion() {
        $('#add_question').form('submit',{
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#adddlg').dialog('close');
                if (result.msg == "success"){
                    $('#data_grid').datagrid("reload");
                    $.messager.alert('成功','问卷已添加！','info');
                } else {
                    $.messager.alert('失败','添加失败，请查看输入的问卷是否符合规范！','error');
                }
            }
        });
    }
    function popAddDlg2(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row) {
            $('#adddlg2').window('open').window('resize', {width: '400px', height: '300px', top: 100, left: 300});
            $('#adddlg2').dialog('setTitle', '添加问题');
            $('#qid').val(row.qid);
        }
    }
    function addItem() {
            $('#add_item').form('submit',{
                contentType:'application/json',
                success: function(result){
                    // 接收的是json串
                    result=eval("("+result+")");
                    $('#adddlg2').dialog('close');
                    if (result.msg == "success"){
                        $('#data_grid').datagrid("reload");
                        $.messager.alert('成功','问卷已添加！','info');
                    } else {
                        $.messager.alert('失败','添加失败，请查看输入的问卷是否符合规范！','error');
                    }
                }
            });
    }
    function popModifyDlg(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $('#modifydlg').window('open').window('resize',{width:'400px',height:'300px',top: 100,left:300});
            $('#modifydlg').dialog('setTitle','更改');
            $('#modify_question').form('load',row);
        }
    }
    function modifyQuestion() {
        $('#modify_question').form('submit',{
            contentType:'application/json',
            success: function(result){
                // 接收的是json串
                result=eval("("+result+")");
                $('#modifydlg').dialog('close');
                if (result.msg == "success"){
                    $('#data_grid').datagrid("reload");
                    $.messager.alert('成功','已成功修改该问卷！','info');
                } else {
                    $.messager.alert('失败','修改失败，请查看修改的问卷是否符合规范！','error');
                }
            }
        });
    }
    function deleteQuestion(){
        var row = $('#data_grid').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除', '确定删除该问卷吗?', function(r){
                if (r){
                    $.ajax({
                        type:'GET',
                        url:'deleteQuestion.action?qid='+row.qid,
                        contentType:'application/json',
                        success:function(result){
                            //  接收的是json对象
                            if (result.msg == 'success'){
                                $('#data_grid').datagrid("reload");
                                $.messager.alert('成功','该问卷已经成功删除！','info');
                            } else {
                                $.messager.alert('失败','删除失败！','error');
                            }
                        }
                    });
                }
            });
        }
    }
    // 根据sid 和 cid 生成退货链接
    function addDeleteLink(val,row,index){
        var str = "删除";
        return "<a  href=\"javascript:openLink('"+row.qid+"','"+row.no+"')\">"+str+"</a>";
    }
    // 点击链接发送数据到后台
    function openLink(qid,no) {
        $.messager.confirm('确认','确认删除该问题?',function(r){
            if (r){
                $.ajax({
                    type: "GET",
                    url: "deleteQuestionItem.action?qid="+qid+"&no="+no,
                    contentType:'application/json',
                    success: function(result){
                        if (result.msg == 'success') {
                            $('#data_grid').datagrid("reload");
                            alert("已删除");
                        }
                        else
                            alert("删除失败");
                    }
                });
            }
        });
    }
</script>
</body>
</html>
