<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <title>修改角色</title>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 修改角色</span></li>
      </ul>
    </div>
    <div  class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/role/updateRole" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
        	<input type="hidden" name="id" value="${role.id }"/>
          <p>
            <label>角色编号：</label>
            <input name="roleCode" class="required" value="${role.roleCode}" type="text" size="30" value="" alt="请输入角色编号" />
          </p>
           <p>
            <label>展示名称：</label>
            <input name="displayName" class="required" value="${role.displayName }"type="text" size="30" value="" alt="请输入展示名称" />
          </p>
          <p>
            <label>角色名称：</label>
            <input name="roleName" class="required" value="${role.roleName }"type="text" size="30" value="" alt="请输入角色名称" />
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
