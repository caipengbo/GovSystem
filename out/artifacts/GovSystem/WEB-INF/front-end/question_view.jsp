<%@ page import="com.sun.xml.internal.bind.v2.TODO" %>
<%@ page import="govsystem.domain.Question" %>
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
                <a href="#" class="list-group-item active">问卷调查</a>
                <%
                    String imgSrc="img/myface.jpg";
                    List<Question> questionList = (List<Question>)request.getAttribute("questionList");
                    String href;
                    for(Question question:questionList) {
                        href="toQuestionDetail.action?qid=" + question.getQid();
                        out.print("<a href=\"" + href + "\" target=\"_blank\">");
                        out.print("<div class=\"list-group-item\">");
                        out.print("<div class='row clearfix h_contral' >");
                        out.print("<div class='col-md-2 column'>");
                        out.print("<img src='"+imgSrc+"' class='img-thumbnail'>");
                        out.print("</div>");
                        out.print("<div class='col-md-10 column' class='state'>");
                        out.print("<p class='text-center'>");
                        out.print(question.getTitle());
                        out.print("</p>");
                        out.print("<blockquote class='pull-right'>");
                        out.print("<p>发布人:"+question.getName()+"</p> <small >"+question.getPostTime()+"</small>");
                        out.print("</blockquote>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</a>");
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
