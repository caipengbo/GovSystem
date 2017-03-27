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
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">

        <div class="col-md-12 column">
            <div class="list-group">
                <a href="#" class="list-group-item active">视频</a>
                <%
                    for(int i=0;i<5;i++){

                        String src="mp4/001.mp4";

                        out.print("<div class=\"list-group-item\">");
                        out.print(" <div class='row clearfix h_contral' >");
                        out.print("<div class='col-md-2 column'> ");

                        out.print("<video src=\""+src+"\"  width=\"320\" height=\"240\" controls=\"controls\">" +
                                "Your browser does not support the video tag.\n" +
                                "</video>");
                        out.print("</div> ");
                        out.print("</div> ");
                        out.print("</div> ");

                    }
                %>
            </div> <a class="list-group-item active"></a>
        </div>
    </div>
</div>
</div>
</body>
<script>
    for(var i=0;i<$(".h_contral").size();i++) {
        var now=$(".h_contral").get(i);
        //   console.log(now.children());
        //  $(".h_contral").get(i).height(  $(".h_contral").get(i).children(':first').height());
        $(".h_contral").get(i).height(  $(".h_contral").get(i).children(':first').height());

        //  console.log($("#test").children().get(0));
    }
</script>
</html>
