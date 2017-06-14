<%@ page import="govsystem.domain.Message" %>
<%@ page import="govsystem.domain.User" %>
<%@ page import="java.util.List" %><%--
  Description： 
  Created by Myth on 6/6/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <title>${requestScope.get("news").title}</title>
    <style type="text/css">
        body {
            background-color: #FFFFFF;
        }
        .main.container {
            margin-top: 2em;
        }

        .main.menu {
            margin-top: 4em;
            border-radius: 0;
            border: none;
            box-shadow: none;
            transition:
                    box-shadow 0.5s ease,
                    padding 0.5s ease
        ;
        }
        .main.menu .item img.logo {
            margin-right: 1.5em;
        }

        .overlay {
            float: left;
            margin: 0em 3em 1em 0em;
        }
        .overlay .menu {
            position: relative;
            left: 0;
            transition: left 0.5s ease;
        }

        .main.menu.fixed {
            background-color: #FFFFFF;
            border: 1px solid #DDD;
            box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.2);
        }
        .overlay.fixed .menu {
            left: 800px;
        }

        .text.container .left.floated.image {
            margin: 2em 2em 2em -4em;
        }
        .text.container .right.floated.image {
            margin: 2em -4em 2em 2em;
        }

        .ui.footer.segment {
            margin: 5em 0em 0em;
            padding: 5em 0em;
        }
    </style>
</head>
<body>
<div class="ui main text container">
    <h1>${requestScope.get("news").title}</h1>
    <div class="metadata">
        <div class="author">${requestScope.get("news").name}</div>
        <div class="date">${requestScope.get("news").postTime}</div>
    </div>
    <div class="overlay">
        <div class="ui labeled icon vertical menu">
            <a class="item" href="toNewsView.action"><i class="unhide icon"></i>公开新闻</a>
            <%
                if(session.getAttribute("user") == null) {
                    out.println("<a class=\"item\" href=\"javascript:alert('您未登陆,请先登录')\"><i class=\"ban icon\"></i>未公开信息</a>");

                } else {
                    if(((User)session.getAttribute("user")).getIdentityFlag() == 0) {
                        //未实名认证
                        out.println("<a class=\"item\" href=\"javascript:alert('您未实名认证,请先实名认证')\"><i class=\"ban icon\"></i>未公开信息</a>");

                    } else {
                        out.println("<a class=\"item\" href=\"toPrivateNews.action\"><i class=\"ban icon\"></i>未公开信息</a>");

                    }
                }
            %>
            <a class="item" href="toFrontEndIndex.action"><i class="edit icon"></i>返回首页</a>
        </div>
    </div>
    ${requestScope.get("news").content}
    <div class="ui comments">
            <%
                List<Message> messageList = (List<Message>)request.getAttribute("messageList");
                String imgSrc = "frontcss/themes/avatar/lambda.jpg";
                for(Message message:messageList) {
                    String userName = message.getName();
                    String comment = message.getContent();
                    String postTime = message.getPostTime();
                    out.println("<div class=\"comment\">");
                    out.println("<a class=\"avatar\">");
                    out.println("<img src=\""+imgSrc+"\">");
                    out.println("</a>");
                    out.println("<div class=\"content\">\n" +
                            "<div class=\"author\">"+userName+"</div>\n" +
                            "<div class=\"metadata\">\n" +
                            "<div class=\"date\">"+postTime+"</div>\n" +
                            "</div>\n" +
                            "<div class=\"text\">\n" +
                            comment +
                            "</div>\n" +
                            "</div>");
                    out.println("</div>");
                }
            %>
        <form class="ui reply form" action="addMessage.action?nid=${requestScope.get("news").nid}" method="POST">
            <div class="field">
                <textarea id="input" name="comment"></textarea>
            </div>

            <%
                if(session.getAttribute("user") == null) {
                    out.println("<a class=\"ui button\" href=\"javascript:alert('您未登陆,请先登录')\"><i class=\"icon edit\"></i>添加评论</a>");
                } else {
                    if(((User)session.getAttribute("user")).getIdentityFlag() == 0) {
                        //未实名认证
                        out.println("<a class=\"ui button\" href=\"javascript:alert('您未实名认证,请先实名认证')\"><i class=\"icon edit\"></i>添加评论</a>");
                    } else {
                        out.println("<button class=\"ui primary submit labeled icon button\" type=\"submit\"><i class=\"icon edit\"></i>添加评论</button>");
                    }
                }
            %>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/front-end/footer.jsp" %>
</body>
    <script>
    $(document)
        .ready(function() {
            // fix main menu to page on passing
            $('.main.menu').visibility({
                type: 'fixed'
            });
            $('.overlay').visibility({
                type: 'fixed',
                offset: 80
            });
            // lazy load images
            $('.image').visibility({
                type: 'image',
                transition: 'vertical flip in',
                duration: 500
            });

            // show dropdown on hover
            $('.main.menu  .ui.dropdown').dropdown({
                on: 'hover'
            });
        });
</script>

</html>