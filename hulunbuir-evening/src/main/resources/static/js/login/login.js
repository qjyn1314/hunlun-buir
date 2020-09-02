layui.config({base: "../../js/"}).extend({"bodyTab": "bodyTab"}).use(['form', 'layer', 'laydate', 'table', 'laytpl', 'bodyTab'], function () {
    var  $ = layui.jquery,tab = layui.bodyTab;

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

    $('#login_btn').on('click',function () {
        console.log("点击了登录");

    });
});