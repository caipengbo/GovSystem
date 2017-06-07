<%@ page import="govsystem.domain.News" %>
<%@ page import="govsystem.domain.Question" %>
<%@ page import="govsystem.domain.User" %>
<%@ page import="govsystem.domain.Video" %>
<%@ page import="java.util.List" %><%--
  Description： 用户前端首页
  Created by Myth on 6/6/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <style type="text/css">
        .hidden.menu {
            display: none;
        }
        .masthead.segment {
            min-height: 700px;
            padding: 1em 0em;
        }
        .masthead .logo.item img {
            margin-right: 1em;
        }
        .masthead .ui.menu .ui.button {
            margin-left: 0.5em;
        }
        .masthead h1.ui.header {
            margin-top: 3em;
            margin-bottom: 0em;
            font-size: 4em;
            font-weight: normal;
        }
        .masthead h2 {
            font-size: 1.7em;
            font-weight: normal;
        }
        .ui.vertical.stripe {
            padding: 8em 0em;
        }
        .ui.vertical.stripe h3 {
            font-size: 2em;
        }
        .ui.vertical.stripe .button + h3,
        .ui.vertical.stripe p + h3 {
            margin-top: 3em;
        }
        .ui.vertical.stripe .floated.image {
            clear: both;
        }
        .ui.vertical.stripe p {
            font-size: 1.33em;
        }
        .ui.vertical.stripe .horizontal.divider {
            margin: 3em 0em;
        }
        .quote.stripe.segment {
            padding: 0em;
        }
        .quote.stripe.segment .grid .column {
            padding-top: 5em;
            padding-bottom: 5em;
        }

        .footer.segment {
            padding: 5em 0em;
        }

        .secondary.pointing.menu .toc.item {
            display: none;
        }

        @media only screen and (max-width: 700px) {
            .ui.fixed.menu {
                display: none !important;
            }
            .secondary.pointing.menu .item,
            .secondary.pointing.menu .menu {
                display: none;
            }
            .secondary.pointing.menu .toc.item {
                display: block;
            }
            .masthead.segment {
                min-height: 350px;
            }
            .masthead h1.ui.header {
                font-size: 2em;
                margin-top: 1.5em;
            }
            .masthead h2 {
                margin-top: 0.5em;
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body >
<!-- Following Menu 白色导航-->
<div class="ui large top fixed hidden menu">
    <div class="ui container">
        <a href="#" class="active item">首页</a>
        <div class="ui compact menu">
            <div class="ui simple dropdown item">
                所有信息
                <div class="menu">
                    <a class="item" href="toNewsView.action"><i class="checkmark icon"></i>公开</a>
                    <%
                        if(session.getAttribute("user") == null) {
                            out.println("<a class=\"item\" href=\"javascript:alert('您未登陆,请先登录')\"><i class=\"remove icon\"></i>非公开</a>");
                        } else {  //登录状态下
                            out.println("<a class=\"item\" href=\"toPrivateNews.action\"><i class=\"remove icon\"></i>非公开</a>");
                        }
                    %>
                </div>
                <i class="dropdown icon"></i>
            </div>
        </div>
        <a class="item" href="#survey">问卷调查</a>
        <a class="item" href="#discuss">网上听证</a>
        <div class="right menu">
            <div class="item">
                <%
                    if(session.getAttribute("user") == null) { //未登录
                    out.println("<a class=\"ui button\" id=\"login-button\">登录</a>");
                    } else {
                    out.println("<div class=\"ui compact menu\">\n" +
                    "                <div class=\"ui simple dropdown item\">" +
                    ((User)session.getAttribute("user")).getUsername()
                    + "<div class=\"menu\">\n" +
                    "<a class=\"item ui button\" href=\"toSetting.action\"><i class=\"checkmark icon\"></i>个人设置</a>\n" +
                    "<a id=\"logout-button\" class=\"item ui button\"><i class=\"remove icon\"></i>注销</a>\n" +
                    "</div>\n" +
                    "<i class=\"dropdown icon\"></i>\n" +
                    "</div>\n" +
                    "</div>"
                    );
                    }
                %>
            </div>
            <div class="item">
                <a class="ui primary button" id="sign-button">注册</a>
            </div>
        </div>
    </div>
</div> <!-- Following Menu end-->

<!-- Page Contents 黑色导航-->
<div class="pusher" >
    <div class="ui inverted vertical masthead center aligned segment"  >
        <div class="ui container" style="background-color: Black">
            <div class="ui large secondary inverted pointing menu">
                <a class="toc item">
                    <i class="sidebar icon"></i>
                </a>
                <a class="active item">首页</a>
                <a class="item" href="#news">查看信息</a>
                <a class="item" href="#survey">问卷调查</a>
                <a class="item" href="#discuss">网上听证</a>
                <div class="right item">
                    <%
                        if(session.getAttribute("user") == null) { //未登录
                            out.println("<div id=\"login2-button\" class=\"ui inverted button\">登录</div>");
                            out.println("<div id=\"sign2-button\" class=\"ui inverted button\">注册</div>");
                        } else {
                            out.println(
                                    "<div class=\"item ui button\" onclick=\"toSetting.action\"><i class=\"checkmark icon\"></i>"+((User)session.getAttribute("user")).getUsername()+"</div>\n" +
                                    "<div id=\"logout-button2\" class=\"item ui button\"><i class=\"remove icon\"></i>注销</div>\n"
                            );
                        }
                    %>
                </div>
            </div>
        </div>

        <div class="ui text container"  >
            <h1 class="ui inverted header" >
                Lambda, All In
            </h1>
            <h2 >政务信息发布系统</h2>
            <a class="ui huge  orange button" href="#news">信息<i class="right arrow icon"></i></a>
        </div>

    </div>

    <div class="ui vertical stripe segment">
        <div class="ui middle aligned stackable grid container" >
            <div class="row">
                <div class="eight wide column">
                    <%
                    List<News> newsList = (List<News>)request.getAttribute("newsList");
                    for(int i=0; i<2 && i< newsList.size(); i++) {
                        out.println("<h3 class=\"ui header\"><a name=\"news\" style=\"text-decoration:none;\">"+newsList.get(i).getTitle()+"</a></h3>");
                        out.println("<p>"+newsList.get(i).getDigest() +"</p>");
                    }
                    %>
                </div>
                <div class="six wide right floated column">
                    <video width="500" height="400" controls="controls" autoplay="autoplay">
                        <source src="./movie.ogg" type="video/ogg" />
                    </video>
                </div>
            </div>
            <div class="row">
                <div class="center aligned column">
                    <a class="ui huge orange button" href="toNewsView.action">更多...</a>
                </div>
            </div>
        </div>
    </div>

    <div class="ui vertical stripe segment" >
        <div class="ui text container">
            <%
                List<Question> questionList = (List<Question>)request.getAttribute("questionList");
                for(int i=0; i<1 && i< questionList.size(); i++) {
                    out.println("<h3 class=\"ui header\" ><a name=\"survey\">"+questionList.get(i).getTitle()+"</a></h3>");
                }
            %>

            <a class="ui large orange button" href="toQuestionView.action">参与问卷</a>
            <h4 class="ui horizontal header divider">
                <a href="#">——</a>
            </h4>
            <%
                List<Video> videoList = (List<Video>)request.getAttribute("videoList");
                for(int i=0; i<1 && i< videoList.size(); i++) {
                    out.println("<h3 class=\"ui header\"><a name=\"discuss\">"+videoList.get(i).getTitle()+"</a></h3>");
                    out.println("<p>"+videoList.get(i).getDescription()+"</p>");
                }
            %>
            <a class="ui large  orange button" href="toVideoView.action">观看视频</a>
        </div>
    </div>

    <%@include file="/WEB-INF/front-end/footer.jsp" %>

</div>

<!-- 用户登录modal begin-->
<div id="login-modal" class="ui modal" >
    <div class="ui middle aligned center aligned grid" style="padding-top: 0px;padding-bottom: 0px;padding-left: 0px;padding-right: 0px" >
        <div class="column" style="max-width:400px ">
            <h2 class="ui teal image header">
                <img src="img/lambda_logo.png" class="image">
                <div class="ui orange header">
                    新闻问卷调查用户登录
                </div>
            </h2>
            <form class="ui large form">
                <div class="ui stacked segment">
                    <div class="required field">
                        <div class="ui left icon  input">
                            <i class="user icon"></i>
                            <input  id="logUserName" type="text" name="username" placeholder="用户名">
                        </div>
                    </div>
                    <div class="required field">
                        <div class="ui left icon input">
                            <i class="lock icon"></i>
                            <input  id="logPassword" type="password" name="password" placeholder="密码">
                        </div>
                    </div>
                    <div class="ui slider checkbox">
                        <input id="isAdm" name="isAdm" type="checkbox" value="true">
                        <label>是否为超级用户</label>
                    </div>
                    <a class="ui fluid large orange submit button" href="javascript:login()">登录</a>
                </div>

            </form>

        </div>
    </div>  <!-- 用户登录悬浮窗结束-->
</div>
<!-- 用户注册modal begin-->
<div id="signup-modal" class="ui modal" >
        <div class="ui middle aligned center aligned grid" style="padding-top: 0px;padding-bottom: 0px;padding-left: 0px;padding-right: 0px">
            <div class="column" style="max-width:400px ">
                <h2 class="ui teal image header">
                    <img src="img/lambda_logo.png" class="image">
                    <div class="ui orange header">
                        用户注册
                    </div>
                </h2>
                <form class="ui large form">
                    <div class="ui stacked segment">
                        <div class="required field">
                            <div class="ui black header"></div>

                            <div class="ui left icon  input">
                                <i class="user icon"></i>
                                <input id="signUserName" type="text" placeholder="新用户名">
                            </div>
                        </div>
                        <div class="required field">
                            <div class="ui black header"></div>
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" id="signPassword" placeholder="新密码">
                            </div>
                        </div>
                        <div class="required field">
                            <div class="ui black header"></div>
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="text" id="signName" placeholder="姓名">
                            </div>
                        </div>
                    </div>
                </form>
                <a class="ui fluid large orange submit button"href="javascript:signup()">注册</a>
            </div>
        </div>  <!-- 用户注册悬浮窗结束-->
    </div>

<!--注销begin-->
<div id="logout-modal" class="ui basic modal" style="overflow:hidden">
    <i class="close icon"></i>
    <div class="header">
        确定注销?
    </div>
    <div class="content">
        <div class="description">
            <p>是否退出当前用户？</p>
        </div>
    </div>
    <div class="actions">
        <div class="two fluid ui inverted buttons">
            <a class="ui green basic inverted button" href="${pageContext.request.contextPath }/logout.action">
                <i class="checkmark icon"></i>
                确定
            </a>
        </div>
    </div>
</div>  <!--注销end-->
</body>
<script>
    $("#login-button").click(function(){
        $('#login-modal').modal('show');
    });
    $("#login2-button").click(function(){
        $('#login-modal').modal('show');
    });
    $("#sign-button").click(function(){
        $('#signup-modal').modal('show');
    });
    $("#sign2-button").click(function(){
        $('#signup-modal').modal('show');
    });
    $("#logout-button").click(function(){
        $('#logout-modal').modal('show');
    });
    $("#logout-button2").click(function(){
        $('#logout-modal').modal('show');
    });
    $(document)
        .ready(function() {
            // fix menu when passed
            $('.masthead')
                .visibility({
                    once: false,
                    onBottomPassed: function() {
                        $('.fixed.menu').transition('fade in');
                    },
                    onBottomPassedReverse: function() {
                        $('.fixed.menu').transition('fade out');
                    }
                })
            ;

            // create sidebar and attach to menu open
            $('.ui.sidebar')
                .sidebar('attach events', '.toc.item')
            ;

        });

    function signup() {  //注册
        var username=$("#signUserName").val();
        var password=$("#signPassword").val();
        var name=$("#signName").val();

        $.ajax({
            type:"GET",
            url:"registUser.action?username="+username+"&password="+password+"&name="+name,
            contentType:'application/json',
            success:function(result){
                if (result.msg == "success") {
                    location.href = "toFrontEndIndex2.action";
                    alert("注册成功");
                } else {
                    alert("注册失败");
                }
            }
        })
    }

    function login() { //登陆
        var username=$("#logUserName").val();
        var password=$("#logPassword").val();
        var isAdm=false;
        if($("#isAdm").is(":checked")) {
            isAdm=1;
        } else {
            isAdm=0;
        }
        $.ajax({
            type:"GET",
            url:"login.action?username="+username+"&password="+password+"&isAdm="+isAdm,
            contentType:'application/json',
            success:function(result){
                if (result.msg == "success") {
                    if (result.roleName == "user") {
                        location.href = "toFrontEndIndex.action";
                    } else {
                        location.href = "toBackEndIndex.action";
                    }
                } else {
                    alert("登录失败,用户名或密码错误！");
                }
            }
        })
    }

</script>
</html>