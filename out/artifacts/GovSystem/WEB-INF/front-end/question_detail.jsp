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
                    ${requestScope.get("question").title}
                </h1>
                <p>

                </p>
            </div>
            <div class="list-group">
                <%--<h3><a href="#" class="list-group-item active">调查问卷</a></h3>--%>
                <form action="radioSubmit.action?qid=${requestScope.get("question").qid}" method="POST">
                    <%
                        List<QuestionItem> questionItemList = (List<QuestionItem>)request.getAttribute("questionItemList");
                        String itemName = "";
                        int count = questionItemList.size();
                        for(int i=0;i<count; i++) {
                            itemName = "item" + i;
                            out.print("<div class='list-group-item'>");
                            out.print("<span class='questionName'>问题"+ (i+1) +":" + questionItemList.get(i).getContent() + "</span><br>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='0' />A. 非常满意 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='1' />B. 比较满意 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='2' />C. 一般 <br><br></label>");
                            out.print("<label><input name='"+itemName+"' type='radio' value='3' />D. 不满意   <br><br></label>");
                            out.print(" </div>");
                        }
                        out.print("<input type=\"hidden\" name=\"count\" value=\""+count+"\"/>");
                    %>
                    <button class="btn btn-block btn-lg btn-warning" type="submit">提交</button>
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