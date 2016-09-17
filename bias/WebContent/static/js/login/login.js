$(function () {
    if (event.keyCode == 13) {
        Login.Login();
    }
    $(".submit_btn").click(function () {
        Login.Login();
    });
    
});
var Login = {
    Login: function () {
        var username = $.trim($("#username").val());
        var password = $.trim($("#password").val());
        if (username == '' || password == '') {
            $(".tipMessage").html("用户名和密码不能为空.").css("visibility", "visible");
        } else {
            $("#loginForm").submit();
        }
        
    }
};
