"use strict";
layui.use(["element", "jquery", "table", "layer", "form", "laydate", "asucUtils",], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    console.log("访问首页-将获取权限");

    /*asucUtils.axGet("/getCurrentUser", {},function (result) {
        if(result.flag) {
            let data = result.data;
            console.log(data)
            $('#user_name').html(data.userName)
            if(data.status === 1 && data.roleCode === 'admin'){
                $('#userCrud').removeAttr("style")
            }
        }
    })*/

   /* asucUtils.axGet("/getPermissionTreeList", {},function (result) {
        if(result.flag){
            console.log(result.data)
            let permissionTree = result.data;
            let navHtml = "";
            for(const key in permissionTree){
                let permission = permissionTree[key];
                console.log(permission)
                navHtml += '<li class="layui-nav-item">';
                navHtml +=  '<a href="javascript:;">' +
                                permission.perIcon +
                                '<em>'+permission.perName+'</em>' +
                            '</a>';
                    if (undefined !== permission.children) {
                        let children = permission.children;
                        for(const key in children){
                            let child = children[key];
                            navHtml += '<dl class="layui-nav-child">' +
                                            '<dd><a href="'+child.url+'">'+child.perName+'</a></dd>' +
                                       '</dl>';
                        }
                    }
                navHtml += '</li>'
            }
            navHtml += '</ul>';
            // $('#Nav').prepend(navHtml);
            // $('#Nav').html(navHtml);
            console.log(navHtml)
        } else {
            asucUtils.redCryMsg(result.message);
        }
    });*/

})





























