$(function () {
    $("#signUp").click(function () {
        clearForm(".reg_form");
        $(".dowebok").addClass("right-panel-active");
    });
    $("#signIn").click(function () {
        $(".dowebok").removeClass("right-panel-active");
        clearForm(".reg_form");
    });

    //发送注册邮箱的验证码
    $(".send_verification").click(function () {
        var mail = $('.reg_form input[name="regUserMail"]').val();
        $.axsPost("/login/sendMailCode", {userMail: mail},
            function (result) {
                if(result.flag){
                    alert(result.message);
                }else{
                    alert(result.message);
                }
            });
    });

    //用户注册
    $(".reg_user").click(function () {
        let userName = $('.reg_form input[name="regUserName"]').val();
        let userMail = $('.reg_form input[name="regUserMail"]').val();
        let userPassword = $('.reg_form input[name="regUserPassword"]').val();
        let verification = $('.reg_form input[name="verification"]').val();
        $.axsPost("/login/regUser", {
                userName: userName,
                userMail: userMail,
                userPassword: userPassword,
                verification: verification
            },
            function (result) {
                if(result.flag){
                    alert(result.message);
                    $(".login_form input[name = 'userMail']").val(userMail);
                    $(".login_form input[name = 'userPassword']").val(userPassword);
                    $(".dowebok").removeClass("right-panel-active");
                    clearForm(".reg_form");
                    window.location.reload();
                }else{
                    alert(result.message);
                }
        });
    });

    //清空表单
    function clearForm(formClass) {
        let inputs = $(formClass+" input");
        for (let i = 0;i<inputs.length;i++){
            let input = inputs[i];
            input.value = ""
        }
    }

    //用户登录
    $('.user_login').click(function () {
        login();
    });
    $(`.login_form`).keypress(function(event){
          var keycode = (event.keyCode ? event.keyCode : event.which);
            if(keycode === '13'){
                 alert('You pressed a "enter" key in textbox');
             }
      });

    function login() {
        let userMail = $(".login_form input[name = 'userMail']").val();
        let userPassword = $(".login_form input[name = 'userPassword']").val();
        let captcha = $(".login_form input[name = 'captcha']").val();
        $.axsPost("/login",
            {
                username: userMail,
                password: userPassword,
                captcha: captcha
            },
            function (result) {
                if (result.flag) {
                    alert(result.message);
                } else {
                    alert(result.message);
                }
            }, function (error) {
                if(error.responseJSON != null){
                    let message = error.responseJSON.message;
                    let textMessage = message.substring(message.lastIndexOf(":") + 1, message.length);
                    alert(textMessage);
                }
                //当登录成功之后是没有返回的json数据的，即可刷新页面直接进入登录成功之后的首页，get请求：/login
                window.location.reload();
            });
    }

});