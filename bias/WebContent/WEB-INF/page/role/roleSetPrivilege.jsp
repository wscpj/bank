<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 角色设置权限</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/role/saveRoleSetPrivilege" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
        	${tree }
            <!-- <ul class="tree treeFolder treeCheck " oncheck="kkk">
                <li><a >系统管理</a>
                    <ul>
                        <li><a tname="name" tvalue="value1" checked="true">用户管理</a></li>
                        <li><a tname="name" tvalue="value2" checked="false">角色管理</a></li>
                    </ul>
                </li>
                <li><a tname="name" tvalue="test1">存取管理</a>
                    <ul>
                        <li><a tname="name" tvalue="test1.1" checked="true">存帐管理</a>
                            <ul>
                                <li><a tname="name" tvalue="test1.1.1" checked="true">我的存帐</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul> -->
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
