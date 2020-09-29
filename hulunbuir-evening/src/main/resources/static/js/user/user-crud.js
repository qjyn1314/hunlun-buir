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
            {field: "sex", title: "性别"},
            {field: "avatar", title: "头像"},
            {field: "status", title: "状态", width: 80, toolbar: '#status'},
            {field: "lastLoginTime", title: "最近访问时间"},
            {field: "createTime", title: "注册时间"},
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
            setOptionValues(layero.find('iframe').contents().find('#roles'))
        }, function () {
            dataTable.reload();
        })
    });

    function setOptionValues(roles) {
        //查询权限并赋值
        authUtils.axGet(Action.ROLE_LISTS_URL, null, function (result) {
            if (result.flag) {
                let option = "";
                roles.append("");
                option = "<option value=''>请选择用户角色</option>";
                for (const key in result.data) {
                    option += '<option value="' + result.data[key].id + '">' + result.data[key].id + " -- " + result.data[key].roleName + '</option>';
                }
                roles.append(option);
            } else {
                authUtils.redCryMsg(result.message);
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
            case "del":
                del(data);
                break;
        }
    });

    //查看
    function detail(data) {
        authUtils.open("查看用户", "/page/user/user-info.html", "90%", "90%", function (layero) {
            //给弹框赋值方法，参考与：https://blog.csdn.net/lm9521/article/details/84789691
            // 其实就是获取的 子页面的 form表单
            let dataInfoForm = layero.find('iframe').contents().find('#dataInfoForm');
            //初始化角色列表
            for (const key in data) {
                if ('status' === key) {
                    let statusValue = data[key] === 0 ? "待审核" : data[key] === 1 ? "已审核" : "已冻结";
                    dataInfoForm.find('input[name=' + key + ']').val(statusValue);
                } else if ('roleId' === key) {
                    authUtils.axGet(Action.ROLE_SEL_URL, {id: data[key]}, function (result) {
                        dataInfoForm.find('input[name=' + key + ']').val(result.data.id + " -- " + result.data.roleName);
                    });
                } else {
                    dataInfoForm.find('input[name=' + key + ']').val(data[key]);
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
        authUtils.open("编辑用户", "/page/user/user-edit.html", "90%", "90%", function (layero) {
            let dataInfoForm = layero.find('iframe').contents().find('#dataEditForm');
            let rolesOption = layero.find('iframe').contents().find('#roles');
            setOptionValues(rolesOption);
            for (const key in data) {
                if ('status' === key) {
                    dataInfoForm.find('input[name=' + key + '][value=' + data[key] + ']').prop("checked", "checked");
                } else if ('roleId' === key) {
                    console.log(key);
                    console.log(data[key]);
                    dataInfoForm.find('select option[name=' + key + '][value=' + data[key] + ']').attr("selected", "selected");
                } else {
                    dataInfoForm.find('input[name=' + key + ']').val(data[key]);
                }
            }
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
            nickName: addUserForm.find('input[name="nickName"]').val().trim(),
            userName: addUserForm.find('input[name="userName"]').val().trim(),
            password: addUserForm.find('input[name="password"]').val().trim(),
            status: addUserForm.find("input[name='status']:checked").val(),
            roleId: addUserForm.find("select[name='roleId']").val(),
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
        console.log(editParams)
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