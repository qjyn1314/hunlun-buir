layui.use(["element", "jquery", "layer", "table", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery, searchForm = $('#searchForm'),
        laydate = layui.laydate;
    let layer = layui.layer, table = layui.table
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    const dataTable = authUtils.TableInit({
        elem: '#generatorTable',
        url: Action.GENERATOR_TABLE_URL,
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "schemaName", title: "数据库名称", width: 100},
            {field: "tableName", title: "表名称", width: 200},
            {field: "createTime", title: "创建时间", width: 160},
            {field: "dataRows", title: "数据量", width: 80},
            {field: "remark", title: "备注",},
            {field: "coding", title: "编码方式",},
            {title: '操作', toolbar: '#generator-option', minWidth: 140}
        ]],
    });

    //搜索按钮
    $('#query').on('click', function (data) {
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
                schemaName: data.schemaName,
                tableName: data.tableName
            };
            authUtils.confirm(content, function () {
                authUtils.download(Action.DOWNLOAD_GENERATOR_URL, dataes, data.tableName + '_code.zip');
                authUtils.greenTickMsg("下载成功！");
            })
        }
    });

    let saveForm = $('#generator-configure-form');
    //回显生成代码模板所在的文件夹列表
    authUtils.axsGet(Action.GENERATION_FOLDER_URL, null,
        function (result) {
            if (result.flag) {
                let folders = result.data;
                for (let i = 0; i < folders.length; i++) {
                    $("#templateFolder").append("<option value=" + folders[i] + ">" + folders[i] + "</option>");
                }
            } else {
                alert(result.message);
            }
        });
    //回显生成代码的配置
    authUtils.axsGet(Action.GENERATION_SETTING_URL, null,
        function (result) {
            if (result.flag) {
                setFormValue(result.data);
            } else {
                alert(result.message);
            }
        });

    function setFormValue(data) {
        for (var key in data) {
            saveForm.find('input[name=' + key + ']').val(data[key])
        }
        $("#templateFolder option[value=" + data.templateFolder + "]").attr("selected", "selected");
    }

    //保存接口
    $("#save_config").click(function () {
        let params = getRequestParams();
        authUtils.axsPost(Action.SAVE_GENERATION_SETTING_URL, params,
            function (result) {
                if (result.flag) {
                    authUtils.tableSuccessMsg(result.message);
                } else {
                    authUtils.redCryMsg(result.message);
                }
            });
    });

    function getRequestParams() {
        return {
            author: saveForm.find('input[name="author"]').val(),
            templateFolder: saveForm.find('select[name="templateFolder"]').val(),
            basePackage: saveForm.find('input[name="basePackage"]').val(),
            entityPackage: saveForm.find('input[name="entityPackage"]').val(),
            mapperPackage: saveForm.find('input[name="mapperPackage"]').val(),
            mapperXmlPackage: saveForm.find('input[name="mapperXmlPackage"]').val(),
            servicePackage: saveForm.find('input[name="servicePackage"]').val(),
            serviceImplPackage: saveForm.find('input[name="serviceImplPackage"]').val(),
            controllerPackage: saveForm.find('input[name="controllerPackage"]').val(),
        }
    }

})

