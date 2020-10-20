layui.use(["element", "jquery", "tree", "table", "layer", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery, table = layui.table;
    let layer = layui.layer, tree = layui.tree, laydate = layui.laydate, searchForm = $('#searchForm');

    //列表
    let dataTable = authUtils.TableInit({
        elem: '#tableList',
        url: Action.DATASOURCE_PAGE_URL,
        cols: [[
            {field: "id", title: "ID", width: 50},
            {field: "datasourceName", title: "SPRING中动态数据源的名称"},
            {field: "databaseName", title: "数据库名称"},
            {field: "databaseIp", title: "数据库IP"},
            {field: "databasePort", title: "数据库端口号"},
            {field: "username", title: "用户名"},
            {field: "password", title: "密码"},
            {field: "url", title: "url"},
            {title: "操作", align: "center", fixed: "right", width: 165, toolbar: '#operations'}
        ]],
    });

    //搜索
    $('#query').on('click', function (data) {
        dataTable.reload({where: getQueryParams(), page: {curr: 1}});
    });

    //搜索条件
    function getQueryParams() {
        return {
            perName: searchForm.find('input[name="name"]').val(),
        };
    }

    //刷新
    $('#reset').on('click', function () {
        searchForm[0].reset();
        dataTable.reload({where: getQueryParams(), page: {curr: 1}});
    });

    //添加
    $('#addData').on('click', function () {
        //首先是请求了后台接口，之后跳转到相应的页面
        authUtils.open("添加权限", "/page/datasource/datasource-add.html", "90%", "90%", function (layero) {
        }, function () {
            dataTable.reload();
        })
    });

    //操作列的监听事件
    //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    table.on("tool(tableFilter)", function (obj) {
        let data = obj.data;
        let event = obj.event;
        switch (event) {
            case "detail":
                detail(data);
                break;
            case "edit":
                edit(data);
                break;
        }
    });

    //编辑
    function edit(data) {
        authUtils.open("编辑权限", "/page/datasource/datasource-edit.html", "90%", "90%", function (layero) {
            let dataEditForm = layero.find('iframe').contents().find('#dataEditForm');
            for (const key in data) {
                dataEditForm.find('input[name=' + key + ']').val(data[key]);
            }
        }, function () {
            dataTable.reload();
        })
    }

    //------------------------------------------添加页面
    let dataAddForm = $('#dataAddForm');
    //添加
    $('#addDataBtn').on('click', function () {
        authUtils.axPost(Action.DATASOURCE_SAVE_URL, getAddParams(), function (result) {
            if (result.flag) {
                authUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                authUtils.redCryMsg(result.message);
            }
        });
    });

    //添加form表单
    function getAddParams() {
        return {
            datasourceName: dataAddForm.find('input[name="datasourceName"]').val().trim(),
            databaseName: dataAddForm.find('input[name="databaseName"]').val().trim(),
            databaseIp: dataAddForm.find('input[name="databaseIp"]').val().trim(),
            databasePort: dataAddForm.find('input[name="databasePort"]').val().trim(),
            username: dataAddForm.find('input[name="username"]').val().trim(),
            password: dataAddForm.find('input[name="password"]').val().trim(),
            url: dataAddForm.find('input[name="url"]').val().trim(),
        };
    }

    //重置
    $('#addReset').on('click', function () {
        dataAddForm[0].reset();
    });

    //------------------------------------------编辑页面
    let dataEditForm = $('#dataEditForm');
    //编辑
    $('#editDataBtn').on('click', function () {
        authUtils.axPost(Action.DATASOURCE_UPDATE_URL, getEditParams(), function (result) {
            if (result.flag) {
                authUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                authUtils.redCryMsg(result.message);
            }
        });
    });

    //编辑form表单
    function getEditParams() {
        return {
            id: dataEditForm.find('input[name="id"]').val(),
            datasourceName: dataEditForm.find('input[name="datasourceName"]').val(),
            databaseName: dataEditForm.find('input[name="databaseName"]').val(),
            databaseIp: dataEditForm.find('input[name="databaseIp"]').val(),
            databasePort: dataEditForm.find('input[name="databasePort"]').val(),
            username: dataEditForm.find('input[name="username"]').val(),
            password: dataEditForm.find('input[name="password"]').val(),
            url: dataEditForm.find('input[name="url"]').val()
        };
    }

    //关闭编辑弹窗
    $('#editClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

})