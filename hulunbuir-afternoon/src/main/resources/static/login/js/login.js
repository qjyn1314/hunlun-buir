$(function () {
    $("#signUp").click(function () {
        $(".dowebok").addClass("right-panel-active");
    });
    $("#signIn").click(function () {
        $(".dowebok").removeClass("right-panel-active");
    });
    var reg_data = {

    };
    //用户注册
    $(".reg_user").click(function () {
        $.axsPost("/login/sendMailCode", reg_data,
            function (result) {
                console.log("是否发送成功:" + result);
            });
    });
    var login_data = {

    };
    //用户登录
    $(".user_login").click(function () {
        $.axsPost("/login/sendMailCode", login_data,
            function (result) {
                console.log("是否发送成功:" + result);
            },function (error) {
                console.log(error);
            });
    });

    //发送邮箱注册验证码
    $(".send_verification").click(function () {
        var mail = $('.login_form input[name="userMail"]').val();
        console.log("登录邮箱：" + mail);
        $.axsPost("/login/sendMailCode", {userMail: mail},
            function (result) {
                console.log("是否发送成功:" + result);
            });
    });

});