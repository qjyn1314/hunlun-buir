layui.use(["element", "jquery", "table", "layer", "form", "laydate", "authUtils",], function () {
    let authUtils = layui.authUtils, Action = layui.authUtils.Action, $ = layui.jquery, searchForm = $('#searchForm');
    let table = layui.table, layer = layui.layer, form = layui.form, laydate = layui.laydate;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    let dataTable = authUtils.TableInit({
        elem: '#userTable',
        url: Action.USERS_PAGE_URL,
        cols: [[
            {field: "id", title: "唯一ID", width: 80},
            {field: "userName", title: "用户名"},
            {field: "email", title: "邮箱"},
            {field: "phone", title: "联系电话"},
            {field: "sex", title: "性别",templet:function (res) {
                if (res.sex === '1') {
                    return '女';
                } else if (res.sex === '0') {
                    return '男';
                } else{
                    return '未知';
                }
            }},
            {field: "status", title: "状态", width: 80, toolbar: '#status'},
            {field: "lastLoginTime", title: "最近访问时间"},
            {field: "createDate", title: "注册时间",templet:function (res) {
                    return date_format(new Date(res.createDate));
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
            startTime: searchForm.find('input[name="startTime"]').val(),
            endTime: searchForm.find('input[name="endTime"]').val(),
            nickName: searchForm.find('input[name="username"]').val(),
            userName: searchForm.find("input[name='usermail']").val(),
            status: searchForm.find("select[name='status']").val(),
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
        authUtils.open("添加用户", "/page/user/user-add.html", "90%", "90%", function (layero,index) {
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
            case "del":
                del(data);
                break;
        }
    });

    //查看
    function detail(data) {
        authUtils.open("查看用户", "/page/user/user-info.html", "90%", "90%", function (layero) {
            let dataInfoForm = layero.find('iframe').contents().find('#dataInfoForm');
            setUserId(dataInfoForm,data);
        }, function () {
            dataTable.reload();
        })
    }

    function setUserId(dataForm,data) {
        for (const key in data) {
            if ('id' === key) {
                dataForm.find('input[name=' + key + ']').val(data[key]);
            }
        }
    }

    //关闭查看弹窗
    $('#infoClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

    //编辑
    function edit(data) {
        authUtils.open("编辑用户", "/page/user/user-edit.html", "90%", "90%", function (layero) {
            let dataEditForm = layero.find('iframe').contents().find('#dataEditForm');
            setUserId(dataEditForm,data);
        }, function () {
            dataTable.reload();
        })
    }

    //删除
    function del(data) {
        let content = "确定要删除昵称为：" + data.userName + "吗？";
        authUtils.confirm(content, function () {
            authUtils.axPost(Action.USERS_DEL_URL, {id: data.id}, function (result) {
                if (result.flag) {
                    authUtils.tableSuccessMsg(result.message);
                    dataTable.reload();
                } else {
                    authUtils.redCryMsg(result.message);
                }
            });
        })
    }

    //------------------------------------------添加页面
    let addUserForm = $('#addUserForm');

    //添加
    $('#addDataBtn').on('click', function () {
        authUtils.axPost(Action.USERS_ADD_URL, getAddParams(), function (result) {
            if (result.flag) {
                authUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                authUtils.redCryMsg(result.message);
            }
        });
    });

    //添加用户的form表单
    function getAddParams() {
        return {
            userName: addUserForm.find('input[name="userName"]').val().trim(),
            password: addUserForm.find('input[name="password"]').val().trim(),
            email: addUserForm.find('input[name="email"]').val().trim(),
            phone: addUserForm.find('input[name="phone"]').val().trim(),
            roleId: addUserForm.find("select[name='roleId']").val(),
            status: addUserForm.find("input[name='status']:checked").val(),
            sex: addUserForm.find("input[name='sex']:checked").val(),
        };
    }

    //重置
    $('#addReset').on('click', function () {
        addUserForm[0].reset();
    });

    //------------------------------------------编辑页面
    let dataEditForm = $('#dataEditForm');
    //编辑
    $('#editDataForm').on('click', function () {
        let editParams = getEditParams();
        authUtils.axPost(Action.USERS_UPDATE_URL, editParams, function (result) {
            if (result.flag) {
                authUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                authUtils.redCryMsg(result.message);
            }
        });
    });

    //编辑用户的form表单
    function getEditParams() {
        return {
            id: dataEditForm.find('input[name="id"]').val(),
            userName: dataEditForm.find('input[name="userName"]').val().trim(),
            email: dataEditForm.find('input[name="email"]').val().trim(),
            phone: dataEditForm.find('input[name="phone"]').val().trim(),
            roleId: dataEditForm.find("select[name='roleId']").val(),
            status: dataEditForm.find("input[name='status']:checked").val(),
            sex: dataEditForm.find("input[name='sex']:checked").val()
        };
    }

    //关闭
    $('#editClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

})