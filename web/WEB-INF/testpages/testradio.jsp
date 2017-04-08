<%@ page import="govsystem.domain.QuestionItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <head>
        <%@include file="/WEB-INF/front-end/header.jsp" %>
        <link href='frontcss/font.css' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="frontjs/jquery.min.js"></script>
        <script type="text/javascript" src="frontjs/jquery-ui.min.js"></script>
        <script type="text/javascript" src="frontjs/jquery.cookie.js"></script>
        <script type="text/javascript" src="frontjs/bootstrap-3.1.1.min.js"></script>
        <script type="text/javascript" src="frontjs/jedate/jedate.min.js"></script>
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
            blockquote{
                position:relative;
                bottom: 0px;
            }
            .list-group-item{
                font-size: larger;
            }
            .questionName{
                font-size: 25px;
            }
            h3{
                font-size: 30px;
            }
            .list-group{
                background-color: #00A1CB;;
            }
            label{
                width: 100%;
            }
        </style>
    </head>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron well-lg">
                <h1>
                    关于。。。的调查问卷
                </h1>
                <p>
                    民主选举基层领导干部是民主管理的“硬件设施”。它直接表达职工群众的意愿，也直接体现职工群众参加民主管理、参与民主决策的行为。就目前来看.民主选举一经试点推开，就已显现了干部人事制度方面的活力。它首先激励基层领导干部树起“勤政为民”的形象，也同时引导有才干的职工群众或一般干部竞争上岗，发挥自己的聪明才智。
                </p>
            </div>
            <div class="list-group">
                <h3><a href="#" class="list-group-item active">调查问卷</a></h3>
                <form action="radioSubmit.action" method="POST">
                    <%

                        String itemName = "";
                        for(int i=0;i<5; i++) {
                            itemName = "item" + i;
                            out.print("<div class='list-group-item'>");
                            out.print("<span class='questionName'>问题"+ i +":您对政府服务态度是否满意?</span><br>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='0' />A. 非常满意 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='1' />B. 比较满意 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='2' />C. 一般 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='3' />D. 不满意   <br><br></label>");
                            out.print(" </div>");
                        }
                        out.print("<input type=\"hidden\" name=\"count\" value=\"5\"/>");
                    %>
                    <button class="btn btn-block btn-lg btn-info" type="submit">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function change(node){
        $("label").css("background-color","#FFFFFF");
        var $qNode=$(node);
        $qNode.css("background-color","#00A1CB");
    }
    $("label").attr('onmouseenter','change(this)');
</script>
</body>
</html>