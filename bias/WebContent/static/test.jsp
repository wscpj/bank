<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
  <title></title>
  <style type="text/css">
    .tooltip
    {
      background-color:#CCCCCC;
    }
  </style>
  <script src="${pageContext.request.contextPath}/static/js/lib/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
  <script type="text/javascript">
    $(function () {
      $("body").mouseup(function (e) {
    	  console.log(e);
        var x = 10;
        var y = 10;
        var r = "";
        //IE9以下支持：document.selection 　
        if (document.selection) {
          r = document.selection.createRange().text;
        }
        //IE9、Firefox、Safari、Chrome和Opera支持：window.getSelection()
        else if (window.getSelection()) {
          r = window.getSelection();
        }
        if (r!= "") {
          //var bowen = "发送到新浪微博";
          var tooltip = "<div id='tooltip' class='tooltip'>" + r + "</div>";
          $("body").append(tooltip);
          $("#tooltip").css({
            "top": (e.pageY + y) + "px",
            "left": (e.pageX + x) + "px",
            "position": "absolute"
          }).show("fast");
        }
      }).mousedown(function () {
        $("#tooltip").remove();
      });
    })
    /* function ask(r) {
      if (r != "") {
        window.open('http://v.t.sina.com.cn/share/share.php?searchPic=false&title='+r+'&url=http://www.nowwamagic.net&sourceUrl=http%3A%2F%2Fblog.sina.com.cn&content=utf-8&appkey=1617465124', '_blank', 'height=515, width=598, toolbar=no, menubar=no, scrollbars=auto, resizable=yes, location=no, status=yes');
      }
    } */
  </script>
</head>
<body>
  <div id="blogContent">
    words words words words words words words words words<br/>
    @###$%$#$&^*&(*)*)((%$))
  </div>
</body>
</html>