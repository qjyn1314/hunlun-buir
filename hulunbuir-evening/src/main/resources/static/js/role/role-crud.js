layui.use(["element", "jquery", "tree", "table", "layer", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery, searchForm = $('#searchForm'),
        table = layui.table;
    let layer = layui.layer, tree = layui.tree, laydate = layui.laydate;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    let dataTable = authUtils.TableInit({
        elem: '#tableList',
        url: Action.ROLE_LIST_URL,
        cols: [[
            {field: "id", title: "ID", width: 80},
            {field: "roleName", title: "角色名称", width: 120},
            {field: "roleCode", title: "角色编码", width: 110},
            {field: "description", title: "角色说明"},
            {field: "createdTime", title: "创建时间",width: 200,templet:function (res) {
                return date_format(new Date(res.createdTime));
            }},
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
            roleName: searchForm.find('input[name="roleName"]').val().trim(),
            roleCode: searchForm.find("input[name='roleCode']").val().trim(),
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
        authUtils.open("添加角色", "/page/role/role-add.html", "90%", "90%", function (layero) {
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
    function treeRender(treeDivId, treeData, dataAddForm) {
        let perIds = [];
        let perNames = [];
        layui.tree({
            elem: treeDivId,  //绑定元素
            nodes: treeData,  //数据源
            click: function (obj) {
                let data = obj;
                if (perIds.length === 0) {
                    perIds.push(data.id)
                    perNames.push(data.name)
                } else if (!(perIds.indexOf(data.id) > -1) || !(perNames.indexOf(data.name) > -1)) {
                    perIds.push(data.id)
                    perNames.push(data.name)
                } else if ((perIds.indexOf(data.id) > -1) || (perNames.indexOf(data.name) > -1)) {
                    perIds.splice(perIds.indexOf(data.id), 1);
                    perNames.splice(perNames.indexOf(data.name), 1);
                }
                if (undefined !== data.children) {
                    let children = data.children;
                    for (let i = 0; i < children.length; i++) {
                        if (perIds.length === 0) {
                            perIds.push(children[i].id)
                            perNames.push(children[i].name)
                        } else if (!(perIds.indexOf(children[i].id) > -1) || !(perNames.indexOf(children[i].name) > -1)) {
                            perIds.push(children[i].id)
                            perNames.push(children[i].name)
                        } else if ((perIds.indexOf(children[i].id) > -1) || (perNames.indexOf(children[i].name) > -1)) {
                            perIds.splice(perIds.indexOf(children[i].id), 1);
                            perNames.splice(perNames.indexOf(children[i].name), 1);
                        }
                    }
                }
                dataAddForm.find('input[name=permission]').val(perIds.join(','));
                dataAddForm.find('input[name=permissionName]').val(perNames.join(','));
            }
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
        authUtils.open("查看角色", "/page/role/role-info.html", "90%", "90%", function (layero) {
            let dataInfoForm = layero.find('iframe').contents().find('#dataInfoForm');
            for (const key in data) {
                dataInfoForm.find('input[name=' + key + ']').val(data[key]);
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
        authUtils.open("编辑角色", "/page/role/role-edit.html", "90%", "90%", function (layero) {
            let dataEditForm = layero.find('iframe').contents().find('#dataEditForm');
            authUtils.axGet(Action.ROLE_SEL_URL, {id: data.id}, function (result) {
                if (result.flag) {
                    let dataes = result.data;
                    for (const key in dataes) {
                        dataEditForm.find('input[name=' + key + ']').val(dataes[key]);
                    }
                } else {
                    authUtils.redCryMsg(result.message);
                }
            });
            let perTree = layero.find('iframe').contents().find('#perTree');
            initPerTree(perTree, dataEditForm);
        }, function () {
            dataTable.reload();
        })
    }

    //------------------------------------------添加页面
    let dataAddForm = $('#dataAddForm');
    //添加
    $('#addDataBtn').on('click', function () {
        authUtils.axPost(Action.ROLE_ADD_URL, getAddParams(), function (result) {
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
            permission: dataAddForm.find('input[name="permission"]').val().trim(),
            roleName: dataAddForm.find('input[name="roleName"]').val().trim(),
            roleCode: dataAddForm.find('input[name="roleCode"]').val().trim(),
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
        authUtils.axPost(Action.ROLE_UPDATE_URL, getEditParams(), function (result) {
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
            permission: dataEditForm.find('input[name="permission"]').val(),
            roleName: dataEditForm.find('input[name="roleName"]').val().trim(),
            roleCode: dataEditForm.find('input[name="roleCode"]').val().trim(),
            description: dataEditForm.find("input[name='description']").val().trim()
        };
    }

    //关闭编辑弹窗
    $('#editClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

})