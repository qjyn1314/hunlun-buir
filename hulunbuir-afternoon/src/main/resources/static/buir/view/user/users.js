layui.use(["element", "jquery", "table", "form", "laydate", "asucUtils", ], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    let searchForm = $('#searchForm');
    let laydate = layui.laydate;
    laydate.render({elem: "#startTime", type: "datetime"});
    laydate.render({elem: "#endTime", type: "datetime"});

    //列表
    let dataTable =  asucUtils.tableInit({
        elem: '#userTable',
        url: asucUtils.backendURL + "/buirUser/userPage",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", title: "ID",width: 80},
            {field: "nickName", title: "用户昵称",},
            {field: "userName", title: "用户登录邮箱"},
            {field: "status", title: "状态",},
            {field: "createTime", title: "创建时间",},
            {field: "updateTime", title: "更新时间",},
            {title: "操作", width: 100, align: "center", fixed: "right", templet: "#operationTpl"}
        ]],
    });

    //搜索
    $('#query').on('click',function (data) {
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
        dataTable.reload('userTable', {page: {curr: 1}, where: getQueryParams()});
    });

    // form.on("submit(search)", function (data) {
    //     userTable.reload({
    //         where: data.field,
    //         page: {curr: 1}
    //     });
    //     return false;
    // });

    // table.on("toolbar(tableFilter)", function (obj) {
    //     switch (obj.event) {
    //         case "batchEnabled":
    //             batchEnabled();
    //             break;
    //         case "batchDisabled":
    //             batchDisabled();
    //             break;
    //         case "batchDel":
    //             batchDel();
    //             break;
    //         case "add":
    //             add();
    //             break;
    //     }
    // });
    //
    // table.on("tool(tableFilter)", function (obj) {
    //     let data = obj.data;
    //     switch (obj.event) {
    //         case "edit":
    //             edit(data);
    //             break;
    //         case "del":
    //             del(data.id);
    //             break;
    //     }
    // });
    //
    // function batchEnabled() {
    //     okLayer.confirm("确定要批量启用吗？", function (index) {
    //         layer.close(index);
    //         let idsStr = okUtils.tableBatchCheck(table);
    //         if (idsStr) {
    //             okUtils.ajax("/user/normalUser", "put", {idsStr: idsStr}, true).done(function (response) {
    //                 console.log(response);
    //                 okUtils.tableSuccessMsg(response.msg);
    //             }).fail(function (error) {
    //                 console.log(error)
    //             });
    //         }
    //     });
    // }
    //
    // function batchDisabled() {
    //     okLayer.confirm("确定要批量停用吗？", function (index) {
    //         layer.close(index);
    //         let idsStr = okUtils.tableBatchCheck(table);
    //         if (idsStr) {
    //             okUtils.ajax("/user/stopUser", "put", {idsStr: idsStr}, true).done(function (response) {
    //                 console.log(response);
    //                 okUtils.tableSuccessMsg(response.msg);
    //             }).fail(function (error) {
    //                 console.log(error)
    //             });
    //         }
    //     });
    // }
    //
    // function batchDel() {
    //     okLayer.confirm("确定要批量删除吗？", function (index) {
    //         layer.close(index);
    //         let idsStr = okUtils.tableBatchCheck(table);
    //         if (idsStr) {
    //             okUtils.ajax("/user/deleteUser", "delete", {idsStr: idsStr}, true).done(function (response) {
    //                 console.log(response);
    //                 okUtils.tableSuccessMsg(response.msg);
    //             }).fail(function (error) {
    //                 console.log(error)
    //             });
    //         }
    //     });
    // }
    //
    // function add() {
    //     okLayer.open("添加用户", "user-add.html", "90%", "90%", null, function () {
    //         userTable.reload();
    //     })
    // }
    //
    // function edit(data) {
    //     okLayer.open("更新用户", "user-edit.html", "90%", "90%", function (layero) {
    //         let iframeWin = window[layero.find("iframe")[0]["name"]];
    //         iframeWin.initForm(data);
    //     }, function () {
    //         userTable.reload();
    //     })
    // }
    //
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