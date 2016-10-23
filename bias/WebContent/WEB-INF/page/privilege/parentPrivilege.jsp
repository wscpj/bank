<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
  </head>
  <body >
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：权限管理 >> 选择父权限</span></li>
      </ul>
    </div>
    <div class="pageContent"  >
      <form class="pageForm required-validate" method="post" action="${pageContext.request.contextPath }/JSGL/addDoRoleAuthority.action?ROLE_ID=${model.ROLE_ID}" onsubmit="return validateCallback(this, dialogAjaxDone);"  >
        <div class="pageFormContent" style="overflow-x:hidden" layoutH="76">
          <div id="layout" style="margin-top:-55px;margin-left:10px;" >
            <div id="leftside" >
              <div id="sidebar_s">
                <div class="collapse">
                  <div class="toggleCollapse"><div></div></div>
                </div>
              </div>
              <div id="sidebar" style=" border: solid 0px ; width:400px;"class="layoutBox" >
                <div class="accordionContent" id="t2" > 
                  <ul class="tree treeFolder treeCheck" >
                    <li >
                      <a>权限类型菜单 </a>
                        <c:forEach items="${privilegeTrees}" var="tree">
                          ${tree}
                        </c:forEach>
                     </li> 
                   </ul>
                 </div>
               </div>
             </div>
          </div>
        </div>
        <div class="formBar">
          <ul>
            <li>
              <div class="buttonActive"><div class="buttonContent"><button type="submit" onclick='treeclick()'>分配</button></div></div>
            </li>
          </ul>
        </div>
      </form>
    </div>
  </body>
</html>
