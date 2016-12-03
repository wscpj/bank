<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <script type="text/javascript">
       $(function(){
           $("#button").click(function(){
             var orgId = $(".orgId").val();
             var orgName = $(".orgName").val();
             if (orgId != null && orgId != undefined && orgId != "") {
                 $("#orgId").val($(".orgId").val());
             }
             if (orgName != null && orgName != undefined && orgName != "") {
                 $("#orgName").val($(".orgName").val());
             }
           });
       });
    </script>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：平台用户管理  >> 社员管理>>修改社员</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/depositor/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
      	<input type="hidden" name="id" value="${depositor.id}" />
        <div class="pageFormContent" layoutH="90">
          <p>
            <label>姓名：</label>
            <input name="depositorName" class="required" type="text" size="30" value="${depositor.depositorName }" alt="请输入姓名" />
          </p>
          <p>
            <label>身份证号：</label>
            <input name="cardNum" class="required" type="text" size="30" value="${depositor.cardNum }" alt="请输入身份证号" />
          </p>
          <p>
            <label>储户编号：</label>
            <input name="depositorCode" class="required" type="text" size="30" value="${depositor.depositorCode }" alt="请输入储户编号" />
          </p>
          
          <p>
            <label>储户性别：</label>
            <select name="gender" class="required combox" style="width:300px;" alt="请输入储户性别" >
              <option value="">请选择</option>
              <option value="0" <c:if test="${depositor.gender =='0'}">selected</c:if> >男</option>
              <option value="1" <c:if test="${depositor.gender =='1'}">selected</c:if> >女</option>
            </select>
          </p>
          <p>
            <label>储户生日：</label>
            <input name="birthday" class="date required" type="text" size="30" value="${depositor.birthday }" />
          </p>
          <p>
            <label>加入时间：</label>
            <input name="joinTime" class="date required" type="text" size="30" value="${depositor.joinTime }" alt="请输入加入时间" />
          </p>
          <p>
            <label>政治面貌：</label>
            <select name="politicalStatus" class="required combox" style="width:700px;" alt="请输入政治面貌" >
              <option value="">请选择</option>
              <option value="0" <c:if test="${depositor.politicalStatus =='0'}">selected</c:if> >群众</option>
              <option value="1" <c:if test="${depositor.politicalStatus =='1'}">selected</c:if> >团员</option>
              <option value="2" <c:if test="${depositor.politicalStatus =='2'}">selected</c:if> >党员</option>
            </select>
          </p>
          <p>
            <label>担当职务：</label>
            <input name="duty" class="required" type="text" size="30" value="${depositor.duty }" alt="请输入担当职务" />
          </p>
          <p>
            <label>任职时间：</label>
            <input name="workTime" class="date required" type="text" size="30" value="${depositor.workTime }" alt="请输入任职时间" />
          </p>
          <p>
            <label>信用等级：</label>
            <select name="creditLevel" class="required combox" style="width:700px;" alt="请输入政治面貌" >
              <option value="">请选择</option>
              <option value="0" <c:if test="${depositor.creditLevel =='0'}">selected</c:if> >低</option>
              <option value="1" <c:if test="${depositor.creditLevel =='1'}">selected</c:if> >一般</option>
              <option value="2" <c:if test="${depositor.creditLevel =='2'}">selected</c:if> >高</option>
            </select>
          </p>
          <p>
            <label>固定电话：</label>
            <input name="telephone" class="required" type="text" size="30" value="${depositor.telephone }" alt="请输入固定电话" />
          </p>
          <p>
            <label>手机：</label>
            <input name="mobilePhone" class="required" type="text" size="30" value="${depositor.mobilePhone }" alt="请输入手机"/>
          </p>
          <p>
            <label>介绍人：</label>
            <input name="introducer" class="required" type="text" size="30" value="${depositor.introducer }" alt="请输入介绍人"/>
          </p>
          <p>
            <label>成员住址：</label>
            <input name="address" class="required" type="text" size="30" value="${depositor.address }" alt="请输入成员住址" />
          </p>
          <p>
            <label>储户照片：</label>
            <input name="image" class="required" type="text" size="30" value="${depositor.image }" alt="请输入储户照片" />
          </p>
          <p>
            <label>机构ID：</label>
            <input type="hidden" id="orgId" name="orgId" value="${depositor.orgId}" />
            <input type="hidden" class="orgId" name="orgnaization.orgId"/>
            <input type="hidden" id="orgName" name="orgName" value="${depositor.orgName}" />
            <input type="hidden" class="orgName" name="orgnaization.organizationName"/>
            <input type="text" readonly="readonly" name="privilege.organizationName" value="${depositor.orgName}" alt="请输入组织机构" class="required" size="30" maxlength="600" /> 
            <a class="btnLook" href="${pageContext.request.contextPath}/page/org/searchOrgs?method=suggest"  lookupGroup="organization" resizable="false" maxable="false"  width="500" height="400" lookupPk="orgNum" title="查找" ></a>
          </p>
        </div>
        <div class="formBar">
          <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button id="button" type="submit">保存</button></div></div></li>
            <li>
                <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
            </li>
          </ul>
        </div>
      </form>  
    </div>
  </body>
</html>
