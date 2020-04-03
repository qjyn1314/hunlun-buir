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
        console.log("注册邮箱：" + mail);
        $.axsPost("/login/sendMailCode", {userMail: mail},
            function (result) {
                console.log("是否发送成功:" + result.flag);
                console.log(result);
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
                console.log("是否注册成功:" + result.flag);
                console.log(result);
                if(result.flag){
                    alert(result.message);
                    $(".login_form input[name = 'userMail']").val(userMail);
                    $(".login_form input[name = 'userPassword']").val(userPassword);
                    $(".dowebok").removeClass("right-panel-active");
                    clearForm(".reg_form");
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
    $(".user_login").click(function () {
        let userMail = $(".login_form input[name = 'userMail']").val();
        let userPassword = $(".login_form input[name = 'userPassword']").val();
        $.axsPost("/login/login",
            {userMail :userMail,
                   userPassword :userPassword
                   },
            function (result) {
                console.log(result);
                console.log("是否登录成功:" + result.flag);
                if(result.flag){
                    alert(result.message);
                }else{
                    alert(result.message);
                }
            });
    });



});