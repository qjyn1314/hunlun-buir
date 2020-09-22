var $, tab, dataStr, layer;
layui.use(['authUtils', 'bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    const form = layui.form, authUtils = layui.authUtils,Action = layui.authUtils.Action, element = layui.element;
    const $ = layui.$,layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({openTabNum: "10"});
    //获取登录用户的信息
    getUserInfo();
    function getUserInfo() {
        authUtils.axsGet(
            Action.USER_INFO_URL,
            {}, function (result) {
                handleUser(result);
            }, function (error) {
                console.log(error)
            }
        )
    }
    function handleUser(result) {
        let data = result.data;
        let storageKey = "user_";
        authUtils.localStorage(storageKey, data);
        let storageUser = authUtils.localStorage(storageKey,null);
        $(".userName").html(storageUser.userName)
    }

    //获取左侧二三级菜单，获取登录用户的信息，封装一个登录公共方法
    getData();
    function getData() {
        $.getJSON(Action.NAVS_URL, function (data) {
            dataStr = data;
            tab.render();
        })
    }

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        if ($(".topLevelMenus li.layui-this a").data("url")) {
            //主要为了避免左侧显示的内容与顶部菜单不匹配
            layer.msg("此栏目状态下左侧菜单不可展开");
            return false;
        }
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        tab.tabMove();
    })

    //手机设备的简单适配
    $('.site-tree-mobile').on('click', function () {
        $('body').addClass('site-mobile');
    });
    $('.site-mobile-shade').on('click', function () {
        $('body').removeClass('site-mobile');
    });

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function () {
        //如果不存在子级
        if ($(this).siblings().length === 0) {
            addTab($(this));
            //移动端点击菜单关闭菜单层
            $('body').removeClass('site-mobile');
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })

    //清除缓存
    $(".clearCache").click(function () {
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('清除缓存中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("缓存清除成功！");
        }, 1000);
    })

    //刷新后还原打开的窗口
    if (cacheStr === "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].icon) {
                    if (menu[i].icon.split("-")[0] === 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //定位到刷新前的窗口
                if (curmenu !== "undefined") {
                    if (curmenu === '' || curmenu === "null") {  //定位到后台首页
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title === menu[i].title) {  //定位到刷新前的页面
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //渲染顶部窗口
            tab.tabMove();
        }
    } else {
        window.sessionStorage.removeItem("menu");
        window.sessionStorage.removeItem("curmenu");
    }
})

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}