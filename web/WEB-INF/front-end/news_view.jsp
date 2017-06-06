<%@ page import="govsystem.domain.News" %>
<%@ page import="java.util.List" %><%--
  Description： 
  Created by Myth on 6/6/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <title>公开信息</title>
</head>
<body>
<div id="leftsidebar" class="ui left vertical inverted labeled icon sidebar menu">
    <a class="item">
        <i class="home icon"></i>
        主页
    </a>
    <a class="item">
        <i class="smile icon"></i>
        公开信息
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
        <div class="ui divided items">
            <%
            String href;
            String imgSrc="img/myface.jpg";
            List<News> newsList = (List<News>)request.getAttribute("newsList");
                for(News news:newsList) {
                    href="toNewsDetail.action?nid=" + news.getNid();
                    out.println("<div class=\"item\">");
                    out.println("<div class=\"image\">");
                    out.println("<img src=\""+imgSrc+"\">");
                    out.println("</div>");
                    out.println("<div class=\"content\">");
                    out.println("<div class=\"header\">"+news.getTitle()+"</div>");
                    out.println("<div class=\"meta\">");
                    out.println("<span class=\"date\">"+news.getName() +"&nbsp"+ news.getPostTime() +"</span>");
                    out.println("</div>");
                    out.println(" <div class=\"description\">\n<p>" +
                            news.getDigest() +
                            "</div>");
                    out.println("<div class=\"extra\">\n" + "<div class=\"ui label\">公开</div>");
                    out.println("<a class=\"ui right floated orange button\" href=\""+href+"\">");
                    out.println("查看\n" + "<i class=\"right chevron icon\"></i>\n" +
                            "</a>\n" + "</div>\n" + "</div>\n" + "</div>");
                }
            %>
        </div>
    </div>

</div>  <!--text container end-->

<%@include file="/WEB-INF/front-end/footer.jsp" %>
<script>
    $("#nav").click(function(){
        $('#leftsidebar').sidebar('toggle')
        ;
    });
</script>
</body>
</html>
