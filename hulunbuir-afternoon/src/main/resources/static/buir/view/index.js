"use strict";
layui.use(["element", "jquery", "table", "layer", "form", "laydate", "asucUtils",], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    console.log("访问首页-将获取权限");
var indes = "[{\"x\":116.387112,\"y\":39.920977},{\"x\":116.385243,\"y\":39.913063},{\"x\":116.394226,\"y\":39.917988},{\"x\":116.401772,\"y\":39.921364},{\"x\":116.41248,\"y\":39.927893}]";
    let parse = JSON.parse(indes);
    console.log(parse)
var indess = [{x:116.387112, y:39.920977},{x:116.385243, y:39.913063},{x:116.394226, y:39.917988},{x:116.401772, y:39.921364},{x:116.41248, y:39.927893}];
    let stringify = JSON.stringify(indess);
    console.log(stringify)

var url = "http://192.168.1.7:8930/standards/w/IndustryLayerArea/get";
var token = 'bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwib3BlbklkIjoiMSIsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV91c2VyIiwiUk9MRV9hY2NvdW50IiwiUk9MRV9hZG1pbiIsInN5czpiYWNrdXAiLCJhdXRoZWQiLCJzeXM6Y3VzdG9tZXIiLCJhY3RpdmVkIiwic3lzOnNjaGVkdWxlIl0sImV4cCI6MTU5ODAxNDg5OX0.AN4y_uXjOqpht8X-dTidjkKWiaAdfXR851JkJjXl5wykIyl8abUparpCqVAhLPoYA9VLBQ_dNHlOb8XAfaIzodNU4LTxxS6yWU2l0ByE3eCeTMHidjSPJwAfFMTPD6GShnkjO9_YfXm5048mxTZkhmMZkFnfJ7sDm5Q1FpKk1A0';
var data = {
    "id":0
};























    asucUtils.axGet("/getCurrentUser", {},function (result) {
        if(result.flag) {
            let data = result.data;
            console.log(data)
            $('#user_name').html(data.userName)
            if(data.status === 1 && data.roleCode === 'admin'){
                $('#userCrud').removeAttr("style")
            }
        }
    })

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





























