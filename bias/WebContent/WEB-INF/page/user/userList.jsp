<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
</head>
<body>
  <form id="pagerForm" method="post" action="${pageContext.request.contextPath}/manage/managerView.action">
    <input type="hidden" name="pageNum" value="${mp.pageNum}" />
    <input type="hidden" name="numPerPage" value="${mp.numPerPage}" />
  </form>
  <div class="panelBar" style=" border-width:1px;">
    <ul class="toolBar">
      <li><span style="margin-left:-25px;">当前位置：系统管理 >> 管理员列表</span></li>
    </ul>
  </div>
  <div class="pageContent">
    <div class="panelBar">
      <ul class="toolBar">
        <li><a class="add" href="${pageContext.request.contextPath}/manage/addView" target="dialog" rel="dialogid" resizable="false"  maxable="false" width="400" height="300"><span >添加管理员</span></a></li>
        <li><a class="edit" href="${pageContext.request.contextPath}/manage/modifyView?id={id}" target="dialog" rel="dialogid" resizable="false"  maxable="false"  width="400" height="300"><span >修改管理员密码</span></a></li>
        <li><a class="delete" href="${pageContext.request.contextPath}/manage/delete?id={id}" target="ajaxTodo" title="确定要删除吗"><span target="navTab">删除管理员</span></a></li>
      </ul>
    </div>
    <div id="w_list_print">
      <table class="list" width="100%" layoutH="80">
        <thead>
          <tr height="25" style="text-align: center;">
            <th class="center" >序号</th>
            <th class="center">姓名</th>
            <th class="center">密码</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${mp.list}" var="user" varStatus="status">
          <tr style="text-align: center;"height="20" rel="${user.id}" target="id">
            <td>${status.count+mp.flag}</td>
            <td>${user.name}</td>
            <td><input type="password" value="${user.password}" style="border:0px;background-color: white" readonly="readonly"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="panelBar" >
      <div class="pages">
        <span>显示</span>
        <select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <option value="5" <c:if test="${mp.numPerPage=='5' }">selected="selected"</c:if> >5</option>
            <option value="10" <c:if test="${mp.numPerPage=='10' }">selected="selected"</c:if>>10</option>
            <option value="15" <c:if test="${mp.numPerPage=='15' }">selected="selected"</c:if>>15</option>
        </select>
        <span>条,共${mp.totalCount}条,每页${mp.numPerPage}条</span>
      </div>
      <div class="pagination" targetType="navTab" totalCount="${mp.totalCount}" numPerPage="${mp.numPerPage}" pageNumShown="${mp.totalPage}" currentPage="${mp.pageNum}"></div>
    </div>
  </div>
</body>
</html>
