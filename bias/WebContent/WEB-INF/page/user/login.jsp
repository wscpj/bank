<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="com.bank.common.AppConstants" %>
<!DOCTYPE>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>资金互助合作社业务管理平台</title>
    <link href="${pageContext.request.contextPath}/static/style/login/login.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/static/style/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lib/dwz/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lib/jquery-ui-1.9.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login/login.js"></script>
  </head>
  <body>
    <%String message = (String)request.getAttribute(AppConstants.MESSAGE);
      String visibility = "hidden";
      if (null != message && !message.equals("")) {
          visibility = "visible";
      }
    %>
    <div id="dialog" title="系统提示">
        <p class="autoLoginMessage" hidden="hidden">系统暂不支持该功能！</p>
        <p class="forgetPasswordMessage" hidden="hidden">忘记密码请尽快联系管理员！</p>
    </div>
    <img class="bg_img" alt="background" src="${pageContext.request.contextPath}/static/images/login_background.png"/>
    <div class="main">
      <div class="logo">资金互助合作社业务管理平台</div>
      <div class="loginForm">
        <form action="<%=request.getContextPath()%>/page/user/login" method="POST" id="loginForm">
          <div class="center">
            <div class="lblUserLogin">用户登录</div>
            <div class="tipMessage_div">
              <label class="tipMessage" style="visibility:${visibility}">${message}</label>
            </div>
            <div class="username_div">
              <label>用户名</label>
              <input type="text" class="username" id="username" name="name" class="login_input" value="admin"/>
            </div>
            <div>
              <label>密码</label>
              <input type="password" class="password" id="password" name="password" class="login_input" value="admin"/>
            </div>
            <div class="submit_div">
              <div class="submit_btn">登录</div>
              <div class="clear_btn">清空</div>
            </div>
            <div>
              <input type="checkbox" class="autoLogin_input" id="autoLogin"/><label class="autoLogin_label">自动登录</label>
              <a class="forgotPassword_a" href="#">忘记密码?</a>
            </div>
            <div class="bottom_div">
              <label>欢迎登录合作社业务管理平台！</label>
            </div>
          </div>
        </form>
      </div>
      <div class="login_footer">Copyright © 2016 CPJ. All Rights Reserved.</div>
    </div>
  </body>
</html>