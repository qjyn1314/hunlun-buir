layui.use(["asucUtils", ], function () {

    let asucUtils = layui.asucUtils;

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
        asucUtils.axsPost("/login/sendMailCode", {userMail: mail},
            function (result) {
                if (result.flag) {
                    countdown();
                    alert(result.message);
                } else {
                    alert(result.message);
                }
            });
    });

    //发送验证码的秒数倒计时算法
    function countdown() {
        let count = 60;
        const countdown = setInterval(FinalCountDown, 1000);

        function FinalCountDown() {
            $('.send_verification').attr("disabled", true);
            $('.send_verification').html(`${count}S`);
            if (count === 0) {
                $(".send_verification").html("发送").removeAttr("disabled");
                clearInterval(countdown);
            }
            count--;
        }
    }

    //用户注册
    $(".reg_user").click(function () {
        let userName = $('.reg_form input[name="regUserName"]').val();
        let userMail = $('.reg_form input[name="regUserMail"]').val();
        let userPassword = $('.reg_form input[name="regUserPassword"]').val();
        let verification = $('.reg_form input[name="verification"]').val();
        asucUtils.axsPost("/login/regUser", {
                userName: userName,
                userMail: userMail,
                userPassword: userPassword,
                verification: verification
            },
            function (result) {
                if (result.flag) {
                    alert(result.message);
                    window.location.reload();
                    $(".login_form input[name = 'userMail']").val(userMail);
                    $(".login_form input[name = 'userPassword']").val(userPassword);
                    $(".dowebok").removeClass("right-panel-active");
                    clearForm(".reg_form");

                } else {
                    alert(result.message);
                }
            });
    });

    //清空表单
    function clearForm(formClass) {
        let inputs = $(formClass + " input");
        for (let i = 0; i < inputs.length; i++) {
            let input = inputs[i];
            input.value = ""
        }
    }

    //用户鼠标点击登录按钮
    $('.user_login').click(function () {
        login();
    });

    //用户的鼠标焦点在此form表单中的时候，按下enter键进行登录
    $('.login_form').keydown(function (event) {
        if (event.keyCode === 13) {   // enter 键值 为13
            login();
        }
    });

    //登录方法
    function login() {
        let userMail = $(".login_form input[name = 'userMail']").val();
        let userPassword = $(".login_form input[name = 'userPassword']").val();
        let captcha = $(".login_form input[name = 'captcha']").val();
        asucUtils.axsPost("/login",
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
                if (error.responseJSON != null) {
                    let message = error.responseJSON.message;
                    let textMessage = message.substring(message.lastIndexOf(":") + 1, message.length);
                    alert(textMessage);
                }
                //当登录成功之后是没有返回的json数据的，即可刷新页面直接进入登录成功之后的首页，get请求：/login
                window.location.reload();
        });
    }

});