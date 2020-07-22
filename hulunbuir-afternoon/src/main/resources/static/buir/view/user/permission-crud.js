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
        elem: '#tableList',
        url: asucUtils.backendURL + "/buirPermission/buirPermissionPage",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", title: "ID", width: 80},
            {field: "perName", title: "权限名称", width: 90},
            {field: "perUrl", title: "权限路径", width: 250},
            {field: "perCode", title: "权限编码", width: 100},
            {field: "perIcon", title: "图表展示", width: 90},
            {field: "description", title: "权限说明", width: 200},
            {field: "createdTime", title: "创建时间"},
            {title: "操作", align: "center", fixed: "right", width: 165, toolbar: '#operations'}
        ]],
    });

    //搜索
    $('#searchForm').on('click', function (data) {
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
        //首先是请求了后台接口，之后跳转到相应的页面
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

    //查看
    function detail(data) {
        asucUtils.open("查看用户", "/view/user/user-info.html.do", "90%", "90%", function (layero) {
            //给弹框赋值方法，参考与：https://blog.csdn.net/lm9521/article/details/84789691
            // 其实就是获取的 子页面的 form表单
            let dataInfoForm = layero.find('iframe').contents().find('#dataInfoForm');
            for(const key in data){
                if('status' === key){
                    let statusValue = data[key] === 0 ? "待审核" : data[key] === 1 ? "已审核" : "已冻结";
                    dataInfoForm.find('input[name='+key+']').val(statusValue);
                }else{
                    dataInfoForm.find('input[name='+key+']').val(data[key]);
                }
            }
        }, function () {
            dataTable.reload();
        })
    }

    //编辑
    function edit(data) {
        asucUtils.open("编辑用户", "/view/user/user-edit.html.do", "90%", "90%", function (layero) {
            let dataInfoForm = layero.find('iframe').contents().find('#dataEditForm');
            for(const key in data){
                if('status' === key){
                    dataInfoForm.find('input[name='+key+'][value='+data[key]+']').prop("checked","checked");
                }else{
                    dataInfoForm.find('input[name='+key+']').val(data[key]);
                }
            }
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

    //------------------------------------------添加页面
    let addUserForm = $('#addUserForm');
    //添加
    $(addUserForm).on('click', function () {
        asucUtils.axPost("/buirUser/userAdd", getAddParams(),function (result) {
            if(result.flag){
                asucUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            }else{
                asucUtils.redCryMsg(result.message);
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
        asucUtils.axPost("/buirUser/userEdit", editParams,function (result) {
            console.log(result);
            if(result.flag){
                asucUtils.tableSuccessMsg(result.message);
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            }else{
                asucUtils.redCryMsg(result.message);
            }
        });
    });
    //编辑用户的form表单
    function getEditParams() {
        return {
            id:dataEditForm.find('input[name="id"]').val(),
            nickName: dataEditForm.find('input[name="nickName"]').val().trim(),
            userName: dataEditForm.find('input[name="userName"]').val().trim(),
            status: dataEditForm.find("input[name='status']:checked").val(),
            sex: dataEditForm.find("input[name='sex']:checked").val()
        };
    }
    //重置
    $('#editClose').on('click', function () {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    });

})