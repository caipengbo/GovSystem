<%--
  Description： 
  Created by Myth on 6/7/2017.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/front-end/header.jsp" %>
    <link href='${pageContext.request.contextPath }/frontcss/video-js.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/videojs-ie8.min.js"></script>
    <title>${requestScope.get("video").title}</title>
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

<div class="ui fixed inverted menu">
    <div class="ui container">
        <a href="#" class="header item">
            <img class="logo" src="img/lambda_logo.png">
            Lambda
        </a>
        <a href="toFrontEndIndex.action" class="item">主页</a>
        <a href="toVideoView.action" class="item">返回</a>
        <a href="toNewsView.action" class="item">信息</a>
    </div>
</div>
</div>
</div>

<div class="ui main text container">
    <h1 class="ui header">视频播放</h1>
    <p>${requestScope.get("video").name}　${requestScope.get("video").posttime}</p>
    <p>${requestScope.get("video").description}</p>
    <video id="my-video" class="video-js" controls preload="auto" width="700" height="380"
           poster="MY_VIDEO_POSTER.jpg" data-setup="{}">
        <source src="mp4/${requestScope.get("video").fileName}" type="video/mp4">

        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a web browser that
        </p>
    </video>
    <script type="text/javascript">
        var myPlayer = videojs('my-video');
        videojs("my-video").ready(function(){
            var myPlayer = this;
            myPlayer.play();
        });
    </script>
</div>
<%@include file="/WEB-INF/front-end/footer.jsp" %>
</body>
</html>
