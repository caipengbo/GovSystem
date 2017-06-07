<%@ page import="govsystem.domain.User" %><%--
  Description： 
  Created by Myth on 6/6/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <title>个人设置</title>
</head>
<body>
<body>
<div id="leftsidebar" class="ui left vertical inverted labeled icon sidebar menu">
    <a class="item">
        <i class="home icon"></i>
        主页
    </a>
    <a class="item">
        <i class="smile icon"></i>
        所有信息
    </a>
    <a class="item">
        <i class="calendar icon"></i>
        非公开信息
    </a>
    <a class="item">
        <i class="mail icon"></i>
        问卷
    </a>
    <a class="item">
        <i class="chat icon"></i>
        视频
    </a>
</div>
<div id="nav" class="ui black big launch right attached fixed button" style="position:fixed;margin-top: 20px;"><i class="content icon"></i><span class="text">导航</span></div>
<!--text container begin-->
<div class="pusher dimmed">
    <div class="ui text container">
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h2 class="ui orange image header">
                    <img src="img/lambda_logo.png" class="image">
                    <div class="content">
                        个人信息设置
                    </div>
                </h2>
                <form class="ui large form">
                    <div class="ui stacked segment">
                        <div class="disabled field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" id="username" name="username" readonly="readonly" value="${sessionScope.user.username}">
                            </div>
                        </div>
                        <div class="disabled field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" id="name" name="name" readonly="readonly" value="${sessionScope.user.name}">
                            </div>
                        </div>
                        <div class="required field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="date" placeholder="未选择" value="${sessionScope.user.birthday}">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui fluid large yellow button" id="set">修改密码</div>
                        </div>

                        <div class="field">
                            <%
                                //TODO modal的隐藏
                                int identityflag = ((User)session.getAttribute("user")).getIdentityFlag();
                                if( identityflag == 0 ) {  //经过判断没有实名认证
                                    out.print("<a href='#authentication' data-toggle='modal' id='nameConfirm'><button type='button' class='btn btn-default btn-block  btn-danger' id='authenticationBtn' readonly='readonly'>实名认证</button></a><span id='control'></span>");
                                } else if (identityflag == 1) {
                                    out.print("<button type='button' class='btn btn-default btn-block  btn-success' disabled='disabled'>已实名认证</button>");
                                }
                            %>
                            <!-- <div class="ui fluid large green submit button">已实名认证</div> -->
                            <!-- <div class="ui fluid large green disabled  button"><i class="warning circle icon"></i>实名认证中</div> -->
                            <div class="ui fluid large green submit button" id="realname">实名认证</div>
                        </div>
                        <div class="field">
                            <div class="ui fluid large orange button">提交修改</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><!--text container end-->
<!--实名认证begin-->
<div id="real" class="ui modal">
    <div class="ui middle aligned center aligned grid" style="padding-top: 70px;padding-bottom: 70px;padding-left: 10px;padding-right: 10px" >
        <div class="column" style="max-width:400px" >
            <h2 class="ui orange image header">
                <img src="img/lambda_logo.png" class="image">
                <div class="content">
                    实名认证
                </div>
            </h2>
            <form class="ui large form">
                <div class="ui stacked segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="text" name="personalID" placeholder="PersonalID">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui fluid large yellow submit button" >确认</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>  <!--实名认证end-->
<!-- 修改密码 begin-->
<div id="setup" class="ui modal">
    <div class="ui middle aligned center aligned grid" style="padding-top: 70px;padding-bottom: 70px;padding-left: 10px;padding-right: 10px" >
        <div class="column" style="max-width:400px" >
            <h2 class="ui orange image header">
                <img src="img/lambda_logo.png" class="image">
                <div class="content">
                    修改密码
                </div>
            </h2>
            <form class="ui large form">
                <div class="ui stacked segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" name="password" placeholder="OldPassword">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" name="password" placeholder="NewPassword">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input type="password" name="password" placeholder="ReInputNewPassword">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui fluid large yellow submit button">确认</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div> <!-- 修改密码 end-->

<%@include file="/WEB-INF/front-end/footer.jsp" %>
<script>
    $("#set").click(function(){
        $('#setup').modal('show');
    });
    $("#realname").click(function(){
        $('#real').modal('show');
    });
    $("#nav").click(function(){
        $('#leftsidebar').sidebar('toggle')
        ;
    });
</script>
</body>
</html>
