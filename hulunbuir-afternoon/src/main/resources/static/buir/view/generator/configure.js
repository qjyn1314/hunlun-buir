layui.use(["element", "jquery", "table", "form", "laydate", "asucUtils", ], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    let saveForm = $('#generator-configure-form');
    //回显生成代码的配置
    asucUtils.axsGet("/generation/getGeneration", null,
        function (result) {
            if (result.flag) {
                setFormValue(result.data);
            } else {
                alert(result.message);
            }
        });

    function setFormValue(data) {
        console.log(data);
        for(var key in data){
            console.log(key+':'+data[key]);
        }

        // let inputs = saveForm.find('input');
        // for (var i = 0;i<inputs.length;i++){
        //     let input = inputs[i];
        //     console.info(inputs);
        // }
    }
    //保存接口
    $("#save_config").click(function () {
        let params = getRequestParams();
        console.info(params);
        asucUtils.axsPost("/generation/saveGeneration", params,
            function (result) {
                if (result.flag) {
                    alert(result.message);
                } else {
                    alert(result.message);
                }
            });
    });

    function getRequestParams(){
        return {
            author : saveForm.find('input[name="author"]').val(),
            basePackage : saveForm.find('input[name="basePackage"]').val(),
            entityPackage : saveForm.find('input[name="entityPackage"]').val(),
            mapperPackage : saveForm.find('input[name="mapperPackage"]').val(),
            mapperXmlPackage : saveForm.find('input[name="mapperXmlPackage"]').val(),
            servicePackage : saveForm.find('input[name="servicePackage"]').val(),
            serviceImplPackage : saveForm.find('input[name="serviceImplPackage"]').val(),
            controllerPackage : saveForm.find('input[name="controllerPackage"]').val(),
        }
    }



})