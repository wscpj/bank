<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 设置管理员</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/user/saveUserRole" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
            <c:forEach items="${roleList }" var="role">
              <label>
            	 <input type="checkbox" name="checkbox1" value="${role.id }">${role.displayName }
              </label>
            </c:forEach>
        </div>
        <div class="formBar">
        	<label style="float:left"><input type="checkbox" class="checkboxCtrl" group="checkbox1" />全选</label>
          <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="checkboxCtrl" group="checkbox1" selectType="invert">反选</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
          </ul>
        </div>
      </form>
    </div>
  </body>
</html>
