<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon"/>
  <script type="text/javascript">
    $("#cleanButton").click(function(){
        $(".searchBar input").val("");
    });
  </script>
</head>
<body>
  <div class="panelBar" style=" border-width:1px;">
    <ul class="toolBar">
      <li><span style="margin-left:-25px;">当前位置：系统管理 >> 管理员列表</span></li>
    </ul>
  </div>
  <div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/page/user/search" method="post">
      <input type="hidden" name="pageNum" value="${paginationDTO.currentPage}" />
      <input type="hidden" name="numPerPage" value="${paginationDTO.pageSize}" />
      <div class="searchBar" >
        <table class="searchContent">
          <tr>
            <td>用户名称：<input type="text" name="userName" value="${paramsMap.userName }"/></td>
            <td>开始日期：<input type="text" name="beginTime" class="date" readonly="true" value="${paramsMap.beginTime }"/></td>
            <td>结束日期：<input type="text" name="endTime" class="date" readonly="true" value="${paramsMap.endTime }"/></td>
          </tr>
        </table>
        <div class="subBar" style="margin-top:-25px;">
          <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button type="button" id="cleanButton">清空</button></div></div></li>
            <li><div class="buttonActive"><div class="buttonContent"><button type="submit">高级检索</button></div></div></li>
          </ul>
        </div>
      </div>
    </form>
  </div>
  <div class="pageContent">
    <div class="panelBar">
      <ul class="toolBar">
        <li><a class="add" href="${pageContext.request.contextPath}/page/user/add" target="dialog" rel="dialogid" resizable="false"  maxable="false" width="500" height="400"><span>添加管理员</span></a></li>
        <li><a class="edit" href="${pageContext.request.contextPath}/page/user/edit/{id}" target="dialog" rel="dialogid" resizable="false"  maxable="false"  width="400" height="300"><span>修改管理员</span></a></li>
        <li><a class="edit" href="${pageContext.request.contextPath}/page/user/userSetRole/{id}" target="dialog" rel="dialogid" resizable="false"  maxable="false"  width="400" height="300"><span>设置管理员角色</span></a></li>
        <li><a class="delete" postType="string" href="${pageContext.request.contextPath}/page/user/delete" target="selectedTodo" title="确定要删除吗"><span target="navTab">删除管理员</span></a></li>
      </ul>
    </div>
    <div id="w_list_print">
      <table class="list" width="100%" layoutH="118">
        <thead>
          <tr height="25" style="text-align: center;">
          	<th width="5%" align="center" ><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th class="center">序号</th>
            <th class="center">姓名</th>
            <th class="center">密码</th>
            <th class="center">性别</th>
            <th class="center">邮箱</th>
            <th class="center">手机</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${paginationDTO.itemList}" var="user" varStatus="status">
          <tr style="text-align: center;"height="20" rel="${user.id}" target="id">
          	<td><input name="ids" type="checkbox" value="${user.id}"></td>
            <td>${status.count+mp.flag}</td>
            <td>${user.userName}</td>
            <td><input type="password" value="${user.password}" style="border:0px;background-color: white" readonly="readonly"/></td>
            <td>${user.gender}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="panelBar" >
      <div class="pages">
        <span>显示</span>
        <select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <option value="5" <c:if test="${paginationDTO.pageSize=='5' }">selected="selected"</c:if> >5</option>
            <option value="10" <c:if test="${paginationDTO.pageSize=='10' }">selected="selected"</c:if>>10</option>
            <option value="15" <c:if test="${paginationDTO.pageSize=='15' }">selected="selected"</c:if>>15</option>
        </select>
        <span>条,共${paginationDTO.totalRowCount}条,每页${paginationDTO.pageSize}条</span>
      </div>
      <div class="pagination" targetType="navTab" totalCount="${paginationDTO.totalRowCount}" numPerPage="${paginationDTO.pageSize}" pageNumShown="${paginationDTO.totalRowCount}" currentPage="${paginationDTO.currentPage}"></div>
    </div>
  </div>
</body>
</html>
