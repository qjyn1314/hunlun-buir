$("#asdasdadsa").click(function () {
    dlg.toggle.bind(dlg);
});
// ======================================================
$("#signUp").click(function () {
    $(".dowebok").addClass("right-panel-active");
});
$("#signIn").click(function () {
    $(".dowebok").removeClass("right-panel-active");
});
$(".reg_user").click(function() {
    $(".reg_form").validate({
        rules: {
            userName: {
                required: true,
                minlength: 4,
                maxlength: 12
            },
            userMail: {
                required: true,
                email: true
            },
            userPassword: {
                required: true,
                minlength: 4,
                maxlength: 16
            }
        },
        messages: {
            userName: {
                required: "用户名不能为空哦~",
                minlength: "用户名长度至少为4位",
                maxlength: "用户名长度至少为12位"
            },
            userMail: {
                required: "邮箱不能为空哦~",
                email: "请输入正确的电子邮箱地址"
            },
            userPassword: {
                required: "密码不能为空哦~",
                minlength: "密码长度至少为4位",
                maxlength: "密码长度至少为16位"
            }
        }
    });
    var valid_reg_form = $(".reg_form").valid();
    console.log(valid_reg_form);
    // if(valid_reg_form){
    //     console.info("进入post开始提交~~");
    //     $.post("/user/regUser", $(".reg_form").serialize(),function (data, textStatus, jqXHR) {
    //         console.info("post提交成功并返回数据~~");
    //         console.log(data);
    //         var flag = data.flag;
    //         var message = data.message;
    //         if(flag){
    //             alert("恭喜您注册成功!!");
    //             console.log("恭喜您注册成功!!");
    //         }else{
    //             console.log(message);
    //         }
    //     });
    //     console.info("进入post开始提交之后~~")
    // }
});