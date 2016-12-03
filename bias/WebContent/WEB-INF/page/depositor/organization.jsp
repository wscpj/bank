<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查找组织木山木金</title>
</head>
<body>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/page/org/searchOrgs?method=suggest">
    <input type="hidden" name="pageNum" value="${paginationDTO.currentPage}" />
    <input type="hidden" name="numPerPage" value="${paginationDTO.pageSize}" />
</form>
<div class="panelBar" style=" border-width:1px;">
  <ul class="toolBar">
    <li><span style="margin-left:-25px; font-weight: bolder;">当前位置：添加权限 >> 查找父权限</span></li>
  </ul>
</div>
<div class="pageHeader">
  <form method="post" action="${pageContext.request.contextPath}/page/org/searchOrgs?method=suggest" onsubmit="return dwzSearch(this, 'dialog');">
     <input type="hidden" name="pageNum" value="${paginationDTO.currentPage}" />
     <input type="hidden" name="numPerPage" value="${paginationDTO.pageSize}" />
     <div class="searchBar">
       <table class="searchContent">
         <tbody style="text-align:right;" >
           <tr>
             <td>
               <label>机构名称：</label>
             </td>
             <td><input type="text"  name="organizationName" value="${organizationName}" style="width:300px;"/></td>
             <td>
               <div class="buttonActive">
                 <div class="buttonContent">
                   <button type="submit">检索</button>
                 </div>
               </div>
             </td>
           </tr>
         </tbody>
       </table>
     </div>
   </form>
</div>
<div class="pageContent">
  <div id="w_list_print">
    <table class="list" width="100%" layoutH="90">
      <thead>
        <tr height="25" style="text-align: center;">
          <th class="center">序号</th>
          <th class="center">组织机构名称</th>
          <th class="center">请选择</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${paginationDTO.itemList}" var="organization" varStatus="status">
          <tr style="text-align: center;"height="20" rel="${organization.id}" target="id">
            <td>${status.index+1}</td>
            <td>${organization.organizationName}</td>
            <td  class="center"><a class="btnSelect" href="javascript:$.bringBack({orgId:'${organization.id}', organizationName:'${organization.organizationName}'})" title="查找带回">选择</a></td>
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
        </select>
        <span>条,共${paginationDTO.totalRowCount}条,每页${paginationDTO.pageSize}条</span>
      </div>
      <div class="pagination" targetType="navTab" totalCount="${paginationDTO.totalRowCount}" numPerPage="${paginationDTO.pageSize}" pageNumShown="${paginationDTO.totalRowCount}" currentPage="${paginationDTO.currentPage}"></div>
    </div>
  </div>
</body>
</html>