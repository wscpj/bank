<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
    <script type="text/javascript">
       $(function(){
           $("#button").click(function(){
             $("#orgId").val($(".orgId").val());
             $("#orgName").val($(".orgName").val());
           });
       });
    </script>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：平台用户管理  >> 账户管理>>添加储户</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/depositor/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="90">
          <p>
            <label>姓名：</label>
            <input name="depositorName" class="required" type="text" size="30" value="" alt="请输入姓名" />
          </p>
          <p>
            <label>身份证号：</label>
            <input name="cardNum" class="required" type="text" size="30" value="" alt="请输入身份证号" />
          </p>
          <p>
            <label>储户编号：</label>
            <input name="depositorCode" class="required" type="text" size="30" value="" alt="请输入储户编号" />
          </p>
          
          <p>
            <label>储户性别：</label>
            <select name="gender" class="required combox" style="width:300px;" alt="请输入储户性别" >
              <option value="">请选择</option>
              <option value="0">男</option>
              <option value="1">女</option>
            </select>
          </p>
          <p>
            <label>储户生日：</label>
            <input name="birthday" class="date required" type="text" size="30" value="" />
          </p>
          <p>
            <label>加入时间：</label>
            <input name="joinTime" class="date required" type="text" size="30" value="" alt="请输入加入时间" />
          </p>
          <p>
            <label>政治面貌：</label>
            <select name="politicalStatus" class="required combox" style="width:700px;" alt="请输入政治面貌" >
              <option value="">请选择</option>
              <option value="0">群众</option>
              <option value="1">团员</option>
              <option value="2">党员</option>
            </select>
          </p>
          <p>
            <label>担当职务：</label>
            <input name="duty" class="required" type="text" size="30" value="" alt="请输入担当职务" />
          </p>
          <p>
            <label>任职时间：</label>
            <input name="workTime" class="date required" type="text" size="30" value="" alt="请输入任职时间" />
          </p>
          <p>
            <label>信用等级：</label>
            <select name="creditLevel" class="required combox" style="width:700px;" alt="请输入政治面貌" >
              <option value="">请选择</option>
              <option value="0">低</option>
              <option value="1">一般</option>
              <option value="2">高</option>
            </select>
          </p>
          <p>
            <label>固定电话：</label>
            <input name="telephone" class="required" type="text" size="30" value="" alt="请输入固定电话" />
          </p>
          <p>
            <label>手机：</label>
            <input name="mobilePhone" class="required" type="text" size="30" value="" alt="请输入手机"/>
          </p>
          <p>
            <label>介绍人：</label>
            <input name="introducer" class="required" type="text" size="30" value="" alt="请输入介绍人"/>
          </p>
          <p>
            <label>成员住址：</label>
            <input name="address" class="required" type="text" size="30" value="" alt="请输入成员住址" />
          </p>
          <p>
            <label>储户照片：</label>
            <input name="image" class="required" type="text" size="30" value="" alt="请输入储户照片" />
          </p>
          <p>
            <label>机构ID：</label>
            <input type="hidden" id="orgId" name="orgId" value="" />
            <input type="hidden" class="orgId" name="organization.orgId"/>
            <input type="hidden" id="orgName" name="orgName" value="" />
            <input type="hidden" class="orgName" name="organization.organizationName"/>
            <input type="text" readonly="readonly"  name="organization.organizationName" class="required" size="30" maxlength="600" alt="请选择机构"/> 
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
