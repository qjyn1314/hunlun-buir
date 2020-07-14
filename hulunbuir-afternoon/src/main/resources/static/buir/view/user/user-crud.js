"use strict";
layui.use(["element", "jquery", "table", "layer", "form", "laydate", "asucUtils",], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    let searchForm = $('#searchForm');
    let table = layui.table;
    let layer = layui.layer;
    let laydate = layui.laydate;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    let dataTable = asucUtils.tableInit({
        elem: '#userTable',
        url: asucUtils.backendURL + "/buirUser/userPage",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", title: "ID", width: 80},
            {field: "nickName", title: "用户昵称",},
            {field: "userName", title: "用户登录邮箱"},
            {field: "status", title: "状态",width: 80,toolbar: '#status'},
            {field: "createTime", title: "创建时间",},
            {field: "updateTime", title: "更新时间",},
            {title: "操作",fixed: 'right', width: 165, align:'center', toolbar: '#operations'}
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
            nickName: searchForm.find('input[name="username"]').val().trim(),
            userName: searchForm.find("input[name='usermail']").val().trim(),
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
        //首先是请求了后台接口，之后跳转到相应的页面789999+
        asucUtils.open("添加用户", "/view/user/user-add.html.do", "90%", "90%", null, function () {
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
    let infoUserForm = $('#infoUserForm');
    //查看
    function detail(data) {
        asucUtils.open("查看用户", "/view/user/user-info.html.do", "90%", "90%", function (layero) {
            let iframeWin = window[layero.find("iframe")[0]["name"]];

                infoUserForm.find('input[name="nickName"]').val(data.nickName);
                // infoUserForm.find("input[name='userName']").val().trim();
                // infoUserForm.find("select[name='status']").val();

            // var body = layer.getChildFrame('body', index);
            // var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            // console.log(body.html()) //得到iframe页的body内容
            // body.find('input').val('Hi，我是从父页来的')

        }, function () {
            dataTable.reload();
        })
    }
    //编辑
    function edit(data) {
        asucUtils.open("编辑用户", "/view/user/user-edit.html.do", "90%", "90%", function (layero) {
            let iframeWin = window[layero.find("iframe")[0]["name"]];

        }, function () {
            dataTable.reload();
        })
    }
    //删除
    function del(data) {
        let content = "确定要删除昵称为："+data.nickName+"吗？";
        asucUtils.confirm(content, function () {
            asucUtils.axPost("/buirUser/userDel", {id:data.id},function (result) {
                if(result.flag){
                    asucUtils.tableSuccessMsg(result.message);
                    dataTable.reload();
                }else{
                    asucUtils.redCryMsg(result.message);
                }
            });
        })
    }

    //------------------------------------------
    let addUserForm = $('#addUserForm');
    //添加
    $('#addDataForm').on('click', function () {
        asucUtils.axPost("/buirUser/userAdd", getQueryParams(),function (result) {
            console.log(result);
            if(result.flag){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                dataTable.reload();
            }else{
                asucUtils.redCryMsg(result.message);
            }
        },function (error) {
            console.log(error)
        });
    });
    //添加用户的form表单
    function getQueryParams() {
        return {
            nickName: addUserForm.find('input[name="nickName"]').val().trim(),
            userName: addUserForm.find('input[name="userName"]').val().trim(),
            password: addUserForm.find('input[name="password"]').val().trim(),
            status: addUserForm.find("input[name='status']:checked").val(),
            sex: addUserForm.find("input[name='sex']:checked").val(),
        };
    }
    //重置
    $('#addReset').on('click', function () {
        addUserForm[0].reset();
    });











    // function add() {
    //     okLayer.open("添加用户", "user-add.html", "90%", "90%", null, function () {
    //         userTable.reload();
    //     })
    // }

    // function edit(data) {
    //     okLayer.open("更新用户", "user-edit.html", "90%", "90%", function (layero) {
    //         let iframeWin = window[layero.find("iframe")[0]["name"]];
    //         iframeWin.initForm(data);
    //     }, function () {
    //         userTable.reload();
    //     })
    // }

    // function del(id) {
    //     okLayer.confirm("确定要删除吗？", function () {
    //         okUtils.ajax("/user/deleteUser", "delete", {idsStr: id}, true).done(function (response) {
    //             console.log(response);
    //             okUtils.tableSuccessMsg(response.msg);
    //         }).fail(function (error) {
    //             console.log(error)
    //         });
    //     })
    // }

})