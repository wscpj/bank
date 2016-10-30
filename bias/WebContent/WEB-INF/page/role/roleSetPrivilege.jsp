<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<script type="text/javascript">
    $("#submit").click(function(){
        var str="";
        $("#tree input:checked").each(function(i,a){
            str += a.value + ",";
        });
        str = str.substring(0,str.length-1);
        $(".privilegeIds").val(str);
    });
</script>
  <body >
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 角色设置权限</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/role/saveRoleSetPrivilege" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input type="hidden" name="roleId" value="${roleId}" />
        <input class="privilegeIds" type="hidden" name="privilegeIds" value="" />
        <div class="pageFormContent" layoutH="90" id ="tree">
        	${tree }
        </div>
        <div class="formBar">
          <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="submit">保存</button></div></div></li>
            <li>
                <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
            </li>
          </ul>
        </div>
      </form>  
    </div>
  </body>
</html>
