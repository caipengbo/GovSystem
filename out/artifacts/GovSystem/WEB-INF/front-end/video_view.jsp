<%@ page import="govsystem.domain.User" %>
<%@ page import="govsystem.domain.Video" %>
<%@ page import="java.util.List" %><%--
  Description： 
  Created by Myth on 6/7/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <title>听证视频</title>
    <style type="text/css">
        body {
            background-color: #FFFFFF;
        }
        .m{ width: 640px; height: 264; margin-left: auto; margin-right: auto; margin-top: 100px; }
        .ui.menu .item img.logo {
            margin-right: 1.5em;
        }
        .main.container {
            margin-top: 7em;
        }
        .wireframe {
            margin-top: 2em;
        }
        .ui.footer.segment {
            margin: 5em 0em 0em;
            padding: 5em 0em;
        }
    </style>
</head>
<body>

<%--顶部固定导航栏--%>
<div class="ui fixed inverted menu">
    <div class="ui container">
        <a href="#" class="header item">
            <img class="logo" src="img/lambda_logo.png">
            Lambda
        </a>
        <a href="toFrontEndIndex.action" class="item">主页</a>
        <a href="toNewsView.action" class="item">公开信息</a>
        <%
            if(session.getAttribute("user") == null) {
                out.println("<a href=\"javascript:alert('您未登陆,请先登录')\" class=\"item\">未公开信息</a>");
                out.println("<a href=\"javascript:alert('您未登陆,请先登录')\" class=\"item\">问卷</a>");
                out.println("<a href=\"javascript:alert('您未登陆,请先登录')\" class=\"item\">视频</a>");
            } else {
                if(((User)session.getAttribute("user")).getIdentityFlag() == 0) {
                    //未实名认证
                    out.println("<a href=\"javascript:alert('您未实名认证,请先实名认证')\" class=\"item\">未公开信息</a>");
                    out.println("<a href=\"javascript:alert('您未实名认证,请先实名认证')\" class=\"item\">问卷</a>");
                } else {
                    out.println("<a href=\"toPrivateNews.action\" class=\"item\">未公开信息</a>");
                    out.println("<a href=\"toQuestionView.action\" class=\"item\">问卷</a>");
                }
                out.println("<a href=\"toVideoView.action\" class=\"item\">视频</a>");
            }
        %>
    </div>
</div>

<!--text container begin-->
<div class="pusher dimmed">

    <div class="ui main text container">
        <div class="ui divided items">
            <%
                String href;
                String imgSrc="img/myface.jpg";
                List<Video> videoList = (List<Video>)request.getAttribute("videoList");
                for(Video video:videoList) {
                    href="toVideoDetail.action?fileName=" + video.getFileName();
                    out.println("<div class=\"item\">");
                    out.println("<div class=\"image\">");
                    out.println("<img src=\""+imgSrc+"\">");
                    out.println("</div>");
                    out.println("<div class=\"content\">");
                    out.println("<div class=\"header\">"+video.getTitle()+"</div>");
                    out.println("<div class=\"meta\">");
                    out.println("<span class=\"date\">"+video.getName() +"&nbsp"+ video.getPosttime() +"</span>");
                    out.println("</div>");
                    out.println(" <div class=\"description\">\n<p>" +
                            video.getDescription() +
                            "</div>");
                    out.println("<div class=\"extra\">\n" + "<div class=\"ui label\">公开</div>");
                    out.println("<a class=\"ui right floated orange button\" href=\""+href+"\">");
                    out.println("观看\n" + "<i class=\"right chevron icon\"></i>\n" +
                            "</a>\n" + "</div>\n" + "</div>\n" + "</div>");
                }
            %>
        </div>
    </div>

</div>  <!--text container end-->

<%@include file="/WEB-INF/front-end/footer.jsp" %>
<script>

</script>
</body>
</html>