$(function () {
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            Login.Login();
         }
    }

    $(".submit_btn").click(function () {
        Login.Login();
    });

    $(".clear_btn").click(function () {
        $(".username").val("");
        $(".password").val("");
    });

    $( "#dialog" ).dialog({
      resizable: false,
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });

    $(".autoLogin_input").click(function () {
        $(".forgetPasswordMessage").hide();
        $(".autoLoginMessage").show();
        $( "#dialog" ).dialog("open");
    });

    $(".forgotPassword_a").click(function () {
        $(".autoLoginMessage").hide();
        $(".forgetPasswordMessage").show();
        $( "#dialog" ).dialog("open");
    });
});
var Login = {
    Login: function () {
        var username = $.trim($("#username").val());
        var password = $.trim($("#password").val());
        if (username == '') {
            $(".tipMessage").html("用户名不能为空.").css("visibility", "visible");
        } else if (password == '') {
            $(".tipMessage").html("密码不能为空.").css("visibility", "visible");
        } else {
            $("#loginForm").submit();
        }

    }
};
