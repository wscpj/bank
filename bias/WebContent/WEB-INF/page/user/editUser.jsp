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
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 修改管理员密码</span></li>
      </ul>
    </div>
    <div  class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/manage/modifyPassword" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent nowrap" style="overflow-x:hidden" layoutH="56">
          <table style="margin-top:20px;margin-left:-10px;height:170px;">
            <tbody style="text-align:right;">
              <tr><td><label>用户名：</label></td><td><input type="text" name="name" class="required" style="width:200px;" maxlength="600" value="${user.name}" readonly="readonly"/></td></tr>
              <tr><td></td><td><input type="hidden"  name="id" value="${user.id}"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td><label>旧密码：</label></td><td><input type="password" name="password1" class="required" style="width:200px;" maxlength="600" value="${user.password}" readonly="readonly"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td><label>新密码：</label></td><td><input type="password" name="password2" class="required" style="width:200px;" maxlength="600"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td><label>确认新密码：</label></td><td><input type="password" name="password" class="required" style="width:200px;" maxlength="600"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr>
                <td colspan="2">
                  <div class="buttonActive"  style="margin-left:150px;"><div class="buttonContent"><button type="submit" >保存</button></div></div>
                  <div class="button" style="margin-left:80px;"><div class="buttonContent"><button type="reset"  class="close">取消</button></div>
                </td>
              </tr>
            </tbody>
          </table>
        <div>
      </form>
    </div>
  </body>
</html>
