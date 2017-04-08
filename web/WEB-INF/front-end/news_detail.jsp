<%@ page import="govsystem.domain.News" %>
<%@ page import="govsystem.domain.Message" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron">
                <h2>
                    ${requestScope.get("news").title}
                </h2>
                <br>
                <p>
                    ${requestScope.get("news").content}
                </p>
                <blockquote class='pull-right'>
                    <p>
                    ${requestScope.get("news").name}
                    </p>
                    <small >${requestScope.get("news").postTime}</small>
                </blockquote>
            </div>
            <div class="list-group">
                <a href="#" class="list-group-item active" id="comment">评论</a>
                <%
                    List<Message> messageList = (List<Message>)request.getAttribute("messageList");
                    for(Message message:messageList) {
                        String userName = message.getName();
                        String comment = message.getContent();
                        out.print("<div class=\"list-group-item\">");
                        out.println(userName + ":" + comment);
                        out.print("</div>");
                    }
                %>
                <br><br>
                <div id="comInput">
                    <form action="addMessage.action?nid=${requestScope.get("news").nid}" method="POST">
                         <textarea class="form-control " id="input" name="comment" >
                         </textarea>
                        <div class="col-md-10"></div>
                        <div class="col-md-2">
                            <button class="btn btn-default btn-info btn-block" type="submit">发表</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
