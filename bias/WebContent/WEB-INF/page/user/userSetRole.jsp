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
      <form method="post" postType="string" action="${pageContext.request.contextPath}/page/user/saveUserSetRole" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input type="hidden" name="userId" value="${userId }"/>
        <div class="pageFormContent" layoutH="90" >
            <c:forEach items="${userRoleList }" var="userSetRoleVo">
              <label>
                <c:choose> 
                  <c:when test="${userSetRoleVo.id == 0}">   
                    <input type="checkbox"  name="roleId" value="${userSetRoleVo.roleId }">${userSetRoleVo.roleDisplayName }
                  </c:when> 
                  <c:otherwise>   
                    <input type="checkbox" checked="true" name="roleId" value="${userSetRoleVo.roleId }">${userSetRoleVo.roleDisplayName }
                  </c:otherwise> 
                </c:choose> 
              </label>
            </c:forEach>
        </div>
        <div class="formBar">
        	<label style="float:left"><input type="checkbox" class="checkboxCtrl" group="roleId" />全选</label>
          <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="checkboxCtrl" group="roleId" selectType="invert">反选</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
          </ul>
        </div>
      </form>
    </div>
  </body>
</html>
