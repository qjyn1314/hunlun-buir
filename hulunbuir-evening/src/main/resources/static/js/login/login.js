layui.use(['form', 'layer', 'laydate', 'table', 'laytpl', 'bodyTab','authUtils'], function () {
    var  $ = layui.jquery,tab = layui.bodyTab,authUtils = layui.authUtils,Action = layui.authUtils.Action;
    $('.input-field').on('change',function(){
        var $this = $(this),
            value = $.trim($this.val()),
            $parent = $this.parent();
        if(value !== '' && !$parent.hasClass('field-focus')){
            $parent.addClass('field-focus');
        }else{
            $parent.removeClass('field-focus');
        }
    })

    $('#register_button').on('click', function () {
        authUtils.axsPost(
            Action.REGISTER_URL,
            {
                username: $('#username').val(),
                password: $('#password').val(),
            },
            function (result) {
                if (result.flag) {
                    authUtils.greenLaughMsg("注册成功！！请登录")
                    setTimeout(index,2000);
                } else {
                    authUtils.redCryMsg("失败！！" + result.message + "请联系：qjyn1314@163.com");
                }
            },
            function (error) {
                authUtils.redCryMsg("失败！！请联系：qjyn1314@163.com");
            }
        )
    });

    function index(){
        window.location.href = "/";
    }

});