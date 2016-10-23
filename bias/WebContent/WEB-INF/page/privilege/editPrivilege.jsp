<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 修改权限</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/privilege/updatePrivilege" class="pageForm required-validate"   onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
        	<input type="hidden" name="id" value="${privilege.id }"/>
          <p>
            <label>权限名称：</label>
            <input name="displayName" class="required" type="text" size="30" value="${privilege.displayName }" alt="请输入权限名称" />
          </p>
          <p>
            <label>权限英文名称：</label>
            <input name="privilegeName" class="required" type="text" size="30" value="${privilege.privilegeName }" alt="请输入权限英文名称" />
          </p>
          <p>
            <label>权限代码：</label>
            <input name="privilegeCode" class="required" type="text" size="30" value="${privilege.privilegeCode }" alt="请输入权限代码" />
          </p>
          <p>
            <label>权限URL：</label>
            <input name="url" class="required" type="text" size="30" value="${privilege.url }" alt="请输入权限URL" />
          </p>
          <p>
            <label>父权限ID：</label>
            <input name="parentId" class="required" type="text" size="30" value="${privilege.parentId}" alt="请输入父权限ID" />
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
