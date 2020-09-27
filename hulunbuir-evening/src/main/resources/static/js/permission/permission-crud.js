layui.use(["element", "jquery", "tree", "table", "layer", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery, table = layui.table;
    let layer = layui.layer, tree = layui.tree, laydate = layui.laydate, searchForm = $('#searchForm');
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    let dataTable = authUtils.TableInit({
        elem: '#tableList',
        url: Action.PERMISSION_LIST_URL,
        cols: [[
            {field: "id", title: "ID", width: 50},
            {field: "parentId", title: "父级ID", width: 90},
            {field: "perName", title: "权限名称", width: 100},
            {field: "perUrl", title: "权限路径"},
            {field: "perCode", title: "权限编码", width: 105},
            {field: "perIcon", title: "图表展示", width: 90,templet:function (res) {
                    return '<i class="layui-icon">'+res.perIcon+'</i>';
            }},
            {field: "description", title: "权限说明", width: 170},
            {field: "perSort", title: "菜单排序", width: 90},
            {field: "perStatus", title: "是否启用", toolbar: '#perStatus', width: 90},
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
            perName: searchForm.find('input[name="perName"]').val(),
            perUrl: searchForm.find('input[name="perUrl"]').val()
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
        authUtils.open("添加权限", "/page/permission/permission-add.html", "90%", "90%", function (layero) {
            let dataAddForm = layero.find('iframe').contents().find('#dataAddForm');
            let perTree = layero.find('iframe').contents().find('#perTree');
            initPerTree(perTree, dataAddForm);
        }, function () {
            dataTable.reload();
        })
    });

    //初始化树形权限
    function initPerTree(treeDivId, dataAddForm) {
        authUtils.axGet(Action.PERMISSIONTREE_URL, null, function (result) {
            if (result.flag) {
                treeRender(treeDivId, result.data, dataAddForm);
            } else {
                authUtils.redCryMsg(result.message);
            }
        });
    }

    //回显树结构数据
    function treeRender(treeDivId,treeData,dataAddForm){
        layui.tree({
            elem: treeDivId, //指定元素，生成的树放到哪个元素上
            nodes: treeData,
            click: function(obj) { //点击节点回调
                dataAddForm.find('input[name=parentId]').val(obj.id);
                dataAddForm.find('input[name=parentName]').val(obj.name);
            },
        });
    }

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

    //查看
    function detail(data) {
        authUtils.open("查看权限", "/page/permission/permission-info.html", "90%", "90%", function (layero) {
            //给弹框赋值方法，参考与：https://blog.csdn.net/lm9521/article/details/84789691
            //其实就是获取的 子页面的 form表单
            let dataInfoForm = layero.find('iframe').contents().find('#dataInfoForm');
            for (const key in data) {
                dataInfoForm.find('input[name=' + key + ']').val(data[key]);
                if (key === 'perStatus') {
                    dataInfoForm.find('input[name=' + key + ']').val(data[key] === 1 ? "启用" : "不启用");
                }
            }
        }, function () {
            dataTable.reload();
        })
    }

    //关闭查看弹窗
    $('#infoClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

    //编辑
    function edit(data) {
        authUtils.open("编辑权限", "/page/permission/permission-edit.html", "90%", "90%", function (layero) {
            let dataEditForm = layero.find('iframe').contents().find('#dataEditForm');
            for (const key in data) {
                dataEditForm.find('input[name=' + key + ']').val(data[key]);
            }
            $("#perStatus option[value=" + data.perStatus + "]").attr("selected", "selected");
        }, function () {
            dataTable.reload();
        })
    }

    //------------------------------------------添加页面
    let dataAddForm = $('#dataAddForm');
    //添加
    $('#addDataBtn').on('click', function () {
        authUtils.axPost(Action.PERMISSION_ADD_URL, getAddParams(), function (result) {
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
            parentId: dataAddForm.find('input[name="parentId"]').val().trim(),
            perSort: dataAddForm.find('input[name="perSort"]').val().trim(),
            perName: dataAddForm.find('input[name="perName"]').val().trim(),
            perUrl: dataAddForm.find('input[name="perUrl"]').val().trim(),
            perCode: dataAddForm.find('input[name="perCode"]').val().trim(),
            perIcon: dataAddForm.find('input[name="perIcon"]').val().trim(),
            perStatus: dataAddForm.find('select[name="perStatus"]').val(),
            description: dataAddForm.find('input[name="description"]').val().trim()
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
        authUtils.axPost(Action.PERMISSION_UPDATE_URL, getEditParams(), function (result) {
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
            id: dataEditForm.find('input[name="id"]').val().trim(),
            perName: dataEditForm.find('input[name="perName"]').val().trim(),
            perSort: dataEditForm.find('input[name="perSort"]').val().trim(),
            perUrl: dataEditForm.find('input[name="perUrl"]').val().trim(),
            perCode: dataEditForm.find('input[name="perCode"]').val().trim(),
            perIcon: dataEditForm.find('input[name="perIcon"]').val().trim(),
            perStatus: dataEditForm.find('select[name="perStatus"]').val(),
            description: dataEditForm.find('input[name="description"]').val().trim()
        };
    }

    //关闭编辑弹窗
    $('#editClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

})