layui.use(["element", "jquery", "table", "form", "laydate", "asucUtils", ], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    let searchForm = $('#searchForm');
    let laydate = layui.laydate;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    var dataTable =  asucUtils.tableInit({
        elem: '#generatorTable',
        url: asucUtils.backendURL + "/generation/tables",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "schemaName", title: "数据库名称",width: 100},
            {field: "tableName", title: "表名称",},
            {field: "createTime", title: "创建时间",width: 160},
            {field: "updateTime", title: "更新时间",width: 160},
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
    // dataTable.on('tool(generatorTable)', function (obj) {
    // // dataTable.on('toolbar(configureTable)', function (obj) {
    //     console.log(obj.data);
    //     console.log(obj.event);
    //     // var data = obj.data,
    //     //     layEvent = obj.event;
    //     // if (layEvent === 'generate') {
    //     //     febs.modal.confirm('代码生成', '确定生成数据表<strong> ' + data.name + ' </strong>对应的前后端代码？', function () {
    //     //         febs.download(ctx + 'generator', data, data.name + '_code.zip');
    //     //     });
    //     // }
    // });







})

