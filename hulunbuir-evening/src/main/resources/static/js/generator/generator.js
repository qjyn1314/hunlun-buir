layui.use(["element", "jquery", "layer","table", "form", "laydate", "asucUtils", ], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    let searchForm = $('#searchForm');
    let laydate = layui.laydate;
    let layer = layui.layer;
    let table = layui.table;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    var dataTable =  asucUtils.tableInit({
        elem: '#generatorTable',
        url: asucUtils.backendURL + "/generation/tables",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "schemaName", title: "数据库名称",width: 100},
            {field: "tableName", title: "表名称",width: 200},
            {field: "createTime", title: "创建时间",width: 160},
            {field: "dataRows", title: "数据量",width: 80},
            {field: "remark", title: "备注",},
            {field: "coding", title: "编码方式",},
            {title: '操作', toolbar: '#generator-option', minWidth: 140}
        ]],
    });

    //搜索按钮
    $('#query').on('click',function (data) {
        dataTable.reload({where: getQueryParams(), page: {curr: 1}});
    });

    //搜索条件
    function getQueryParams() {
        return {
            schemaName: searchForm.find('input[name="schemaName"]').val(),
        };
    }

    //代码生成按钮点击事件
    table.on('tool(generatorTable)', function (obj) {
        var data = obj.data, layEvent = obj.event;
        if (layEvent === 'generate') {
            let content = "确定生成数据库<strong> " + data.schemaName + ' </strong>中的数据表<strong> ' + data.tableName + " </strong>对应的后端代码?"
            let dataes = {
                schemaName:data.schemaName,
                tableName:data.tableName
            };
            asucUtils.confirm(content, function () {
                asucUtils.download('/generation/generationCodeDownload', dataes, data.tableName + '_code.zip');
                asucUtils.greenTickMsg("下载成功！");
            })
        }
    });

    let saveForm = $('#generator-configure-form');
    //回显生成代码模板所在的文件夹列表
    asucUtils.axsGet("/generation/getFolderList", null,
        function (result) {
            if (result.flag) {
                let folders = result.data;
                for (let i = 0; i < folders.length; i++) {
                    $("#templateFolder").append("<option value="+folders[i]+">"+folders[i]+"</option>");
                }
            } else {
                alert(result.message);
            }
        });
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
        for(var key in data){
            saveForm.find('input[name='+key+']').val(data[key])
        }
        $("#templateFolder option[value="+data.templateFolder+"]").attr("selected", "selected");
    }
    //保存接口
    $("#save_config").click(function () {
        let params = getRequestParams();
        asucUtils.axsPost("/generation/saveGeneration", params,
            function (result) {
                if (result.flag) {
                    asucUtils.tableSuccessMsg(result.message);
                } else {
                    asucUtils.redCryMsg(result.message);
                }
        });
    });

    function getRequestParams(){
        return {
            author : saveForm.find('input[name="author"]').val(),
            templateFolder : saveForm.find('select[name="templateFolder"]').val(),
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

