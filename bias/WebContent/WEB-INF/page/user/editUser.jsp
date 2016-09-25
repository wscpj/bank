<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <title>修改管理员密码</title>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 修改管理员</span></li>
      </ul>
    </div>
    <div  class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/manage/modifyPassword" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
          <p>
            <label>管理员名称：</label>
            <input name="userName" class="required" type="text" size="30" value="" alt="请输入管理员名称" />
          </p>
          <p>
            <label>管理员密码：</label>
            <input name="password" class="required" type="password" size="30" value="" alt="请输入管理员密码" />
          </p>
          <p>
            <label>管理员性别：</label>
            <select name="gender" class="required combox" style="width:100px;" >
              <option value="">请选择</option>
              <option value="0">男</option>
              <option value="1">女</option>
            </select>
          </p>
          <p>
            <label>管理员email：</label>
            <input name="email" class="required" type="text" size="30" value="" alt="请输入管理员邮箱" />
          </p>
          <p>
            <label>管理员手机：</label>
            <input name="phone" class="required" type="text" size="30" value="" alt="请输入管理员手机号" />
          </p>
        </div>
        <div class="formBar">
          <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
            <li>
                <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
            </li>
          </ul>
        </div>
      </form>
    </div>
  </body>
</html>
