<%--
 带状态的新闻视图
--%>
<%@ page import="govsystem.domain.News" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <!-- Custom Theme files -->
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <style>
        *{ font-family: Microsoft YaHei,'宋体' , Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
            margin: 0;
            padding: 0;
            border: 0;
        }
        .bottom{
            margin-top: 80px;
        }
        .h_contral{
        }
        .text-center{
            font-size: 30px;
        }
        blockquote{
            position:relative;
            bottom: 0px;

        }
        .state{
            position: absolute;;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <div class="list-group">
                <a href="#" class="list-group-item active">非公开信息</a>
                <%
                    String href="";
                    String imgSrc="img/myface.jpg";
                    List<News> newsList = (List<News>)request.getAttribute("newsList");
                    String buttonHref;
                    for(News news:newsList) {
                        int status = news.getStatus();

                        String title = news.getTitle();
                        String content = news.getDigest();
                        String date = news.getPostTime();
                        String author = news.getName();
                        out.print("<div class='list-group-item'> <div class='row clearfix h_contral'> <div class='col-md-2 column'> <img src='img/myface.jpg' class='img-thumbnail'></div> <div class='col-md-10 column'> ");
                        out.print("<p class='text-center'>" + title + "</p>");
                        out.print("<p class='content'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + content + "</p>");
                        out.print("<blockquote class='pull-right'><p>" + author + "</p> <small>" + date + "</small>");
                        switch (status) {
                            case 0:
                                buttonHref = "applyViewNews.action?nid=" + news.getNid();
                                out.print("<a href='" + buttonHref + "'><button class='btn btn-default btn-danger'>申请查看</button></a>");
                                break;
                            case 1:
                                out.print("<button class='btn btn-default btn-warning disabled'>申请中</button></a><br>");
                                break;
                            case 2:
                                buttonHref = "toNewsDetail.action?nid=" + news.getNid();
                                out.print("<a href='" + buttonHref + "'><button class='btn btn-default btn-success'>可查看</button></a>");
                                break;
                        }
                        out.print("</blockquote>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                %>
            </div> <a class="list-group-item active"></a>
        </div>
    </div>
</div>
<script>
    for(var i=0;i<$(".h_contral").size();i++) {
        var now=$(".h_contral").get(i);
        //   console.log(now.children());
        //  $(".h_contral").get(i).height(  $(".h_contral").get(i).children(':first').height());
        $(".h_contral").get(i).height(  $(".h_contral").get(i).children(':first').height());

        //  console.log($("#test").children().get(0));
    }
</script>
</body>

</html>

