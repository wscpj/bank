<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <title>会话超时</title>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：会话超时 >> 重新登陆</span></li>
      </ul>
    </div>
    <div  class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/manage/login?method=reLogin" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent nowrap" style="overflow-x:hidden" layoutH="56">
          <table style="margin-top:50px;margin-left:-10px;height:130px;">
            <tbody style="text-align:right;">
              <tr><td><label>用户名：</label></td><td><input type="text" name="name" class="required" style="width:200px;" maxlength="600"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td><label>用户密码：</label></td><td><input type="password" name="password" class="required" style="width:200px;" maxlength="600"/></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr><td></td><td></td></tr>
              <tr >
                <td colspan="2">
                  <div class="buttonActive"  style="margin-left:150px;"><div class="buttonContent"><button type="submit" >登陆</button></div></div>
                  <div class="button" style="margin-left:80px;"><div class="buttonContent"><button type="reset"  class="close">取消</button></div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </form>
    </div>
  </body>
</html>
