<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <title>添加 角色</title>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：系统管理 >> 角色设置权限</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/role/saveRoleSetPrivilege" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
            <ul class="tree treeFolder treeCheck expand" oncheck="kkk">
                <li><a >框架面板</a>
                    <ul>
                        <li><a tname="name" tvalue="value1" checked="true">我的主页</a></li>
                        <li><a tname="name" tvalue="value2">页面一</a></li>
                        <li><a tname="name" tvalue="value3">替换页面一</a></li>
                        <li><a tname="name" tvalue="value4">页面二</a></li>
                        <li><a tname="name" tvalue="value5">页面三</a></li>
                    </ul>
                </li>
                <li><a tname="name" tvalue="test1">Test 1</a>
                    <ul>
                        <li><a tname="name" tvalue="test1.1">Test 1.1</a>
                            <ul>
                                <li><a tname="name" tvalue="test1.1.1" checked="false">Test 1.1.1</a></li>
                                <li><a tname="name" tvalue="test1.1.2" >Test 1.1.2</a></li>
                            </ul>
                        </li>
                        <li><a tname="name" tvalue="test1.2" checked="true">Test 1.2</a></li>
                    </ul>
                </li>
                <li><a tname="name" tvalue="test2" >Test 2</a></li>
            </ul>
            <!-- <input type="submit" value="Submit"/> -->
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
