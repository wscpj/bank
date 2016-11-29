<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
  <head>
  </head>
  <body style="background-repeat: no-repeat;background-position: center;background: url('${pageContext.request.contextPath}/Images/background.jpg');">
    <div class="panelBar" style=" border-width:1px;">
      <ul class="toolBar">
        <li><span style="margin-left:-25px;">当前位置：平台用户管理  >> 账户管理>>修改账户</span></li>
      </ul>
    </div>
    <div class="pageContent">
      <form method="post" action="${pageContext.request.contextPath}/page/account/update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
      	<input type="hidden" name="id" value="${account.id}" />
        <div class="pageFormContent" layoutH="90">
          <p>
            <label>卡号：</label>
            <input name="accountNum" class="required" type="text" size="30"  value="${account.accountNum}" alt="请输入卡号" />
          </p>
          <p>
            <label>开户人：</label>
            <input name="opener" class="required" type="text" size="30"  value="${account.opener}" alt="请输入开户人" />
          </p>
          <p>
            <label>卡类型：</label>
            <input name="cardType" class="required" type="text" size="30"  value="${account.cardType}" alt="请输入卡类型" />
          </p>
          
          <p>
            <label>开户时间：</label>
            <input name="openTime" type="text" size="30" class="date textInput readonly valid" datefmt="yyyy-MM-dd HH:mm:ss"  value="${account.openTime}" />
          </p>
          <p>
            <label>存折号：</label>
            <input name="bankBook" class="required" type="text" size="30"  value="${account.bankBook}" alt="请输入存折号" />
          </p>
          <p>
            <label>账户类型：</label>
            <input name="accountType" class="required" type="text" size="30"  value="${account.accountType}" alt="请输入账户类型" />
          </p>
          <p>
            <label>卡标志：</label>
            <input name="cardMark" class="required" type="text" size="30"  value="${account.cardMark}" alt="请输入卡标志" />
          </p>
          <p>
            <label>支付密码：</label>
            <input name="payPasssWord" class="required" type="text" size="30"  value="${account.payPasssWord}" alt="请输入支付密码" />
          </p>
          <p>
            <label>账户状态：</label>
            <input name="accountStatus" class="required" type="text" size="30"  value="${account.accountStatus}" alt="请输入账户状态" />
          </p>
          <p>
            <label>储户ID：</label>
            <input name="depositorId" class="required" type="text" size="30"  value="${account.depositorId}" alt="请输入储户ID" />
          </p>
          <p>
            <label>账户余额：</label>
            <input name="remainMoney" class="required" type="text" size="30"  value="${account.remainMoney}" alt="请输入账户余额" />
          </p>
          <p>
            <label>起息日期：</label>
            <input name="interestStartTime" class="date" type="text" size="30"  value="${account.interestStartTime}" />
          </p>
          <p>
            <label>结息日期：</label>
            <input name="interestStopTime" class="date" type="text" size="30"  value="${account.interestStopTime}" />
          </p>
          <p>
            <label>利率ID：</label>
            <input name="rateId" class="required" type="text" size="30"  value="${account.rateId}" alt="请输入利率ID" />
          </p>
          <p>
            <label>存款定期编号：</label>
            <input name="constantCode" class="required" type="text" size="30"  value="${account.constantCode}" alt="请输入存款定期编号" />
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
