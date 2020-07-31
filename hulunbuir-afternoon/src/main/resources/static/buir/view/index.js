"use strict";
layui.use(["element", "jquery", "table", "layer", "form", "laydate", "asucUtils",], function () {
    let asucUtils = layui.asucUtils;
    let $ = layui.jquery;
    console.log("访问首页-将获取权限");
    asucUtils.axGet("/getPermissionTreeList", {},function (result) {
        if(result.flag){
            console.log(result.data)
            let permissionTree = result.data;
            let navHtml = "";
            navHtml += '<ul class="layui-nav layui-nav-tree" id="Nav">';
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
    });


/*<li class="layui-nav-item">
        <a href="javascript:;">
        <i class="layui-icon">&#xe68e;</i>
    <em>主页</em>
    </a>
    <dl class="layui-nav-child">
        <dd><a href="/view/index.html.do">控制台</a></dd>
    </dl>
    </li>
    */



})





























