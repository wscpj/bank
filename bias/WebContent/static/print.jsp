<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../static/js/lib/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<title>print template</title>
</head>
<body id="main">
  <div id="print">
    <div style="width:650px;font-size:15px;">
       <table style="border:1px solid red;width:700px;color:red;" border=1 cellpadding="0" cellspacing="0">
        <caption style="text-align:center;color:red;"><h3>固始县赵岗乡石堰村农民资金互助合作社股金单</h3><h4 style="color:red;margin-left:340px;">No:0476</h4></caption>
        <tr>
           <td colspan="7">
            &nbsp;股民名称：张亚坡<br><br>
            &nbsp;股金金额(人民币大写)：壹佰万元整<br><br>
            &nbsp;入股期限：一年<br><br>
            <font style="margin-left:150px;">石堰村农民资金互助合作社：(公章)</font><br><br>
           </td>
        </tr>
        <tr style="text-align:center;">
            <td>入股日期</td>
            <td>股金金额(小写)</td>
            <td>期限</td>
            <td>回报率</td>
            <td>约定到期日</td>
            <td>取款方式</td>
            <td>经办人(签章)</td>
        </tr>
        <tr style="text-align:center;">
            <td>2016-01-14</td>
            <td>100000</td>
            <td>一年</td>
            <td>月息7%</td>
            <td>2017-01-14</td>
            <td>现金</td>
            <td>张亚坡</td>
        </tr>
        </table>
        <br><br><div style="float:right;">复核人(签章)：</div>
    </div>
     <table width="1px" height="550" border="0 " cellpadding="0" cellspacing="0" style="border-left:2px dashed red;margin-top:-500px;margin-left:750px;" >
      <tr>
        <td width="11"> </td>
      </tr>
    </table>
    <div style="border:2px solid red;width:300px;font-size:15px;height:220px;color:red;margin-top:-310px;margin-left:800px;">
    <table border=0 style="margin-top:-80px;">
      <caption><pre><h3>固始县赵岗乡石堰村农民资金互助
合作社股金单(存根)No:0476</h3></pre></caption>
      <tr>
        <td colspan="2">股民名称：贾传顺</td>
      </tr>
      <tr>
        <td colspan="2">股金金额(人民币小写)：100000</td>
      </tr>
      <tr>
        <td colspan="2">入股期限：一年</td>
      </tr>
      <tr>
        <td colspan="2">利率：</td>
      </tr>
      <tr>
        <td colspan="2">结息时间：2017-01-14</td>
      </tr>
      <tr>
        <td colspan="2">合计利息： </td>
      </tr>
      <tr>
        <td>经办人：张亚坡</td>
        <td>复核人：张亚坡</td>
      </tr>
    </table>
    </div>
    <br><br><br><br><br><br>
    <div style="width:650px;height:500px;font-size:15px;">
       <table style="border:1px solid red;color:red;width:700px;height:150px;" border=1 cellpadding="0" cellspacing="0">
        <tr>
        <td><pre> 股民需知：1.股金单位应为机器打印，手写涂改无效，凭此单取款，保持平整。
          2.如股金单遗失，不论是否到期，应提供股金单的户名，股权证编号、股金金额和存入日期。
          并凭身份证件立即向资金互助合作社(4215168)申请挂失。</pre>
        </td>
        </tr>
        <tr style="text-align:left;">
        <td><pre> 股民签收：               证件名称：            证件号码：          </pre></td>
        </tr>
        <tr style="text-align:left;">
        <td><pre> 代理人签名:              代理人证件名称:             证件号码:            </pre></td>
        </tr>
        <tr style="text-align:left;">
        <td><pre> 取款日期：             支付分红金额：                          </pre></td>
        </tr>
        </table>
    </div>
    <div>
    <button id="btnprint" >打印</button>
    </div>
  </div>
  </body>
  <script type="text/javascript">

  $(function () {
      //添加打印边框
     /*  var tableOutside = $("#JqueryTable_Outside").DataTable().on("draw.dt", function () {
          var tdborderTop = $("#tableInfo").find("td").css("border-top");
          var tdborderBottom = $("#tableInfo").find("td").css("border-bottom");
          $(this).attr("border", "1").css("border-color", "black").find("th").css("border-top", tdborderTop)
              .css("border-bottom", tdborderBottom).end().find("td").css("border-top", tdborderTop).css("border-bottom", tdborderBottom);
      });

      var tableDetails = $("#JqueryTable_details").DataTable().on("draw.dt", function () {
          var tdborderTop = $("#tableInfo").find("td").css("border-top");
          var tdborderBottom = $("#tableInfo").find("td").css("border-bottom");
          $(this).attr("border", "1").css("border-color", "black").find("th").css("border-top", tdborderTop)
              .css("border-bottom", tdborderBottom).end().find("td").css("border-top", tdborderTop).css("border-bottom", tdborderBottom);
      });

      var tableAudit = $("#JqueryTable_audit").DataTable().on("draw.dt", function () {
          var tdborderTop = $("#tableInfo").find("td").css("border-top");
          var tdborderBottom = $("#tableInfo").find("td").css("border-bottom");
          $(this).attr("border", "1").css("border-color", "black").find("th").css("border-top", tdborderTop)
              .css("border-bottom", tdborderBottom).end().find("td").css("border-top", tdborderTop).css("border-bottom", tdborderBottom);
      });
 */
      $("#btnprint").click(function(){
          preview();
      });

      $("#btnback").click(function () {
          history.go(-1);
      });
  });

  function preview() {
      var windowOld = window.document.body.innerHTML;
      window.document.body.innerHTML = $("#main").parent().html();
      window.print();

      window.document.body.innerHTML = windowOld;
  }
  //function preview() {
  //    bdhtml = window.document.body.innerHTML;
  //    sprnstr = "<!--startprint-->";
  //    eprnstr = "<!--endprint-->";
  //    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
  //    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
  //    window.document.body.innerHTML = prnhtml;
  //    window.print();
  //}

  </script>
  </html>