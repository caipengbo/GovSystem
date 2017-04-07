<%@ page import="govsystem.domain.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Home</title>
  <%@include file="/WEB-INF/front-end/header.jsp" %>
  <link href='${pageContext.request.contextPath }/frontcss/sweetalert2.min.css' rel='stylesheet' type='text/css'>
  <script type="text/javascript" src="${pageContext.request.contextPath }/frontjs/sweetalert2.min.js"></script>
  <!--//theme-style-->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
  <style>
    *{ font-family: Microsoft YaHei,'宋体' , Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
      margin: 0;
      padding: 0;
      border: 0;
    }
    #year{
      width: 20%;
    }
    #month{
      width: 20%;
    }
    #day{
      width: 20%;
    }
    iframe{

      border: 0px;
      scrolling:no;
      width: 100%;
    }

  </style>
  <script>

      function signup() {  //注册
          var username=$("#signUserName").val();
          var password=$("#signPassword").val();
          var name=$("#signName").val();

          $.ajax({
              type:"GET",
            url:"registUser.action?username="+username+"&password="+password+"&name="+name,
            contentType:'application/json',
              success:function(result){
                if (result.msg == "success") {
                    location.href = "toFrontEndIndex.action";
                    alert("注册成功");
                } else {
                  alert("注册失败");
                }
              }
          })
      }
      function forget(){
          var email=$("#forgetEmail").val();
          console.log(email);
          AV.User.requestPasswordReset(email).then(function (success) {
              alert("我们已经找回密码方式发送至"+email+"，请前往邮箱进行查看");
          }, function (error) {
              alert(error.message);
          });
      }
      function login() { //登陆
          var username=$("#logUserName").val();
          var password=$("#logPassword").val();
          var isAdm=false;
          if($("#isAdm").is(":checked")) {
              isAdm=1;
          }else{
              isAdm=0;
          }
          $.ajax({
              type:"GET",
              url:"login.action?username="+username+"&password="+password+"&isAdm="+isAdm,
              contentType:'application/json',
              success:function(result){
                  if (result.msg == "success") {
                      if (result.roleName == "user") {
                        location.href = "toFrontEndIndex.action";
                      } else {
                        location.href = "toBackEndIndex.action";
                      }
                  } else {
                    alert("登录失败,用户名或密码错误！");
                  }
              }
          })
      }

  </script>
</head>
<body>
<!---------导航栏---->
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <nav class="navbar navbar-fixed-top   navbar-default" role="navigation" id="nav1">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">here</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="${pageContext.request.contextPath }/toPublicNews.action?ispublic=1" target="mainIframe">主页</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav" id="nav">
            <li  class='dropdown'><a href='#' class='dropdown-toggle' data-toggle="dropdown">所有信息<!--<span class="badge">14</span>  -->
              <strong class='caret'>
              </strong></a>
              <ul class='dropdown-menu'>
                <li> <a href="toNewsView.action" target="mainIframe" >公开</a> </li>
                <li> <a href="toPrivateNews.action"  target="mainIframe">未公开</a> </li>
                <li class='divider'>
                </li>
              </ul>
            </li>
            <li  class='dropdown'><a href='#' class='dropdown-toggle' data-toggle="dropdown">问卷
              <strong class='caret'>
              </strong></a>
              <ul class='dropdown-menu'>
                <li> <a href="toQuestionView.action" target="mainIframe" >查看问卷</a> </li>
                <%--<li> <a href="uploadFile_dMyFile"  target="mainIframe">问卷</a> </li>--%>
                <li class='divider'>
                </li>
              </ul>
            </li>
            <li  class='dropdown'><a href='#' class='dropdown-toggle' data-toggle="dropdown">网上听证
              <strong class='caret'>
              </strong></a>
              <ul class='dropdown-menu'>
                <li> <a href="toVideoView.action" target="mainIframe" id='myDiy'>听证视频</a> </li>
                <li class='divider'>
                </li>
              </ul>
            </li>

          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href="#signUp"  data-toggle="modal">
                <button type="button" class="btn btn-default btn-success btn-block"> &nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;&nbsp; </button>
              </a>
            </li>
            <!-- 未登录时 -->
            <%
              if(session.getAttribute("user") == null) { //未登录
                out.println("<li id='log'><a href='#login' data-toggle='modal' ><button  id='loginDom' type='button' class='btn btn-default btn-success btn-block'>&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;</button></a></li>");
              } else {  //登录状态下
              out.println("<li id='log' class='dropdown'><a href='#' class='dropdown-toggle' data-toggle='dropdown'>"+((User)session.getAttribute("user")).getUsername()+"&nbsp;&nbsp;&nbsp;<strong class='caret'></strong></a><ul class='dropdown-menu'> <li> <a href='toSetting.action' target='mainIframe' id='myDiy'>个人设置</a> </li>    <li class='divider'> </li> <li> <a href='#concel' data-toggle='modal'>注销</a> </li> </ul></li>");
            }
            %>

          </ul>
        </div>
      </nav>
      <!--上面是导航栏---->

      <!-----下面是内联框架------->
      <iframe id="Iframe1" src="${pageContext.request.contextPath }/toNewsView.action" name="mainIframe">

      </iframe>
      <!--------上面是内联框架-------->
    </div>
  </div>
</div>
<!-------------下面是遮罩窗体--->
<div class="modal fade" id="signUp" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="myModalLabel1">
          注册
        </h4>
      </div>
      <div class="modal-body">
        <form role="form" class="form-horizontal">
          <div class="form-group">
            <label >用户名</label><input type="text" class="form-control" id="signUserName" />
          </div>
          <div class="form-group">
            <label >密码</label><input type="password" class="form-control" id="signPassword" />
          </div>
          <div class="form-group">
            <label >姓名</label><input type="text" class="form-control" id="signName" />
          </div>
          <p type="submit" class="btn-default btn btn-success btn-block"   onclick="signup()">注册</p>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="login" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="myModalLabel">
          登陆
        </h4>
      </div>
      <div class="modal-body">
        <form role="form" >
          <div class="form-group" >
            <label >用户名</label><input type="text" class="form-control" id="logUserName" />
          </div>
          <div class="form-group">
            <label >密码</label><input type="password" class="form-control" id="logPassword" />
          </div>
          <div class="checkbox">
            <label><input type="checkbox" name="isAdm" id="isAdm" value="true" />管理员登录</label>
          </div>
          <div class="form-group">
            <p  class="btn btn-default btn-success btn-block" onclick="login()">登陆</p>
          </div>
          <div class="form-group">

            <div class="row clearfix">
              <div class="col-md-12 column">
                <a href="#signUp" data-toggle="modal" data-dismiss="modal"><button class="btn btn-default btn-block btn-info">前去注册</button></a>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="forget" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" >
          忘记密码
        </h4>
      </div>
      <div class="modal-body">
        <form role="form">
          <div class="form-group">
            <label >注册邮箱</label><input type="email" class="form-control" id="forgetEmail" />
          </div>
          <p type="submit" class="btn btn-default btn-success btn-block" onclick="forget()">找回密码</p>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>



<!--下面是登陆成功-->
<div class="modal fade" id="concel" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      </div>
      <div class="modal-body">
        <h1>确定要注销登陆吗?</h1>
        <a href="${pageContext.request.contextPath }/logout.action"><p class="btn btn-default btn-block btn-info" id="logOut">注销</p></a>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" >
    //下面用于注销的JavaScript
    var h=document.documentElement.clientHeight;
    var myframe=$("#Iframe1");
    var mynav=$("#nav1");
    var fheigh=h-$("#nav1").outerHeight();//$("#nav1").css("marginBottom");
    myframe.height(h-mynav.innerHeight()-5);
    myframe.css("margin-top",mynav.innerHeight());
    //myframe.marginTop($("#nav1").height());
</script>
</body>
</html>