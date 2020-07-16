"use strict";
layui.define(["layer", 'jquery', 'table'], function (exprots) {
    /**
     * 获取浏览器地址栏中的请求路径
     * @author wangjunming
     * @since 2020/7/15 14:22
     */
    function baseUrlHandle() {
        let url = window.location.href;
        let doubleSlash = url.indexOf("/") + 2;
        let doubleSlashUrl = url.substring(doubleSlash);
        let singleSlash = doubleSlashUrl.indexOf("/");
        return url.substring(0, singleSlash + doubleSlash);
    }

    var baseUrl = baseUrlHandle();
    //封装了ajax
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;
    var asucUtils = {
        /**
         * 是否前后端分离
         */
        isFrontendBackendSeparate: true,
        /**
         * 服务器地址
         */
        backendURL: baseUrl,
        /**
         * 获取body的总宽度
         */
        getBodyWidth: function () {
            return document.body.scrollWidth;
        },
        /**
         * 主要用于对ECharts视图自动适应宽度
         */
        echartsResize: function (element) {
            var element = element || [];
            window.addEventListener("resize", function () {
                var isResize = localStorage.getItem("isResize");
                for (let i = 0; i < element.length; i++) {
                    element[i].resize();
                }
            });
        },
        /**
         * 封装初始化表格
         *
         * @param params
         * @returns {*}
         */
        tableInit: function (params) {
            var defaultSetting = {
                page: true,
                skin: 'line',
                limit: 10,
                limits: [10, 20, 30, 40, 100],
                autoSort: false,
                size: "lg",
                request: {
                    pageName: 'current',
                    limitName: 'pageSize'
                },
                parseData: function (res) {
                    return {
                        "code": res.flag ? 0 : 500,
                        "count": res.data.totalRows,
                        "data": res.data.rows
                    }
                }
            };
            return table.render($.extend(defaultSetting, params));
        },
        /**
         * 在表格页面操作成功后弹窗提示
         * @param content
         */
        tableSuccessMsg: function (content) {
            layer.msg(content, {icon: 1, time: 1000}, function () {
                // 刷新当前页table数据
                $(".layui-laypage-btn")[0].click();
            });
        },
        /**
         * 主要用于针对表格批量操作操作之前的检查
         * @param table
         * @returns {string}
         */
        tableBatchCheck: function (table) {
            var checkStatus = table.checkStatus("tableId");
            var rows = checkStatus.data.length;
            if (rows > 0) {
                var idsStr = "";
                for (var i = 0; i < checkStatus.data.length; i++) {
                    idsStr += checkStatus.data[i].id + ",";
                }
                return idsStr;
            } else {
                layer.msg("未选择有效数据", {offset: "t", anim: 6});
            }
        },
        /**
         * 简单封装ajax，post请求
         * @param url 发送请求的地址
         * @param data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
         * @param successfn 成功回调函数
         */
        axPost: function (url, data, successfn) {
            url = asucUtils.backendURL + url;
            data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
            $.ajax({
                type: "post",
                data: data,
                url: url,
                dataType: "json",
                success: function (d) {
                    successfn(d);
                }
            });
        },
        /**
         * 简单封装ajax，post请求
         * @param url 发送请求的地址
         * @param data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
         * @param successfn 成功回调函数
         * @param errorfn 失败回调函数
         */
        axsPost: function (url, data, successfn, errorfn) {
            url = asucUtils.backendURL + url;
            data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
            $.ajax({
                type: "post",
                data: data,
                url: url,
                dataType: "json",
                success: function (d) {
                    successfn(d);
                },
                error: function (e) {
                    errorfn(e);
                }
            });
        },
        /**
         * ajax封装，get请求
         * url 发送请求的地址
         * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
         * successfn 成功回调函数
         */
        axGet: function (url, data, successfn) {
            url = asucUtils.backendURL + url;
            data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
            $.ajax({
                type: "get",
                data: data,
                url: url,
                dataType: "json",
                success: function (d) {
                    successfn(d);
                }
            });
        },
        /**
         * ajax封装,get请求
         * url 发送请求的地址
         * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
         * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
         * successfn 成功回调函数
         * errorfn 失败回调函数
         */
        axsGet: function (url, data, successfn, errorfn) {
            url = asucUtils.backendURL + url;
            data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
            $.ajax({
                type: "get",
                data: data,
                url: url,
                dataType: "json",
                success: function (d) {
                    successfn(d);
                },
                error: function (e) {
                    errorfn(e);
                }
            });
        },
        download: function (url, params, fileName) {
            url += '?' + asucUtils.parseParams(params);
            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.responseType = "blob";
            xhr.onload = function () {
                if (this.status === 200) {
                    var fileType = this.response.type;
                    var blob = this.response;
                    var reader = new FileReader();
                    reader.readAsDataURL(blob);
                    reader.onload = function (e) {
                        if ('msSaveOrOpenBlob' in navigator) { // IE，Edge
                            var base64file = e.target.result + '';
                            window.navigator.msSaveOrOpenBlob(asucUtils.createFile(base64file.replace('data:' + fileType + ';base64,', ''), fileType), fileName);
                        } else { // chrome，firefox
                            var link = document.createElement('a');
                            link.style.display = 'none';
                            link.href = e.target.result;
                            link.setAttribute('download', fileName);
                            document.body.appendChild(link);
                            link.click();
                            $(link).remove();
                        }
                    }
                } else {
                    asucUtils.redCryMsg("下载失败!!")
                }
            };
            xhr.send();
        },
        parseParams: function (param, key, encode) {
            if (param == null) return '';
            var arr = [];
            var t = typeof (param);
            if (t === 'string' || t === 'number' || t === 'boolean') {
                arr.push(key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param));
            } else {
                for (var i in param) {
                    var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
                    arr.push(asucUtils.parseParams(param[i], k, encode));
                }
            }
            return arr.join("&");
        },
        // 解析 BASE64文件内容 for IE，Edge
        createFile: function (urlData, fileType) {
            var bytes = window.atob(urlData),
                n = bytes.length,
                u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bytes.charCodeAt(n);
            }
            return new Blob([u8arr], {type: fileType});
        },
        /**
         * localStorage 二次封装
         */
        localStorage: function (name, value) {
            if (value) { /**设置*/
                if (typeof value == "object") {
                    localStorage.setItem(name, JSON.stringify(value));
                } else {
                    localStorage.setItem(name, value);
                }
            } else if (null !== value) {
                /**获取*/
                let val = localStorage.getItem(name);
                try {
                    val = JSON.parse(val);
                    return val;
                } catch (err) {
                    return val;
                }
            } else { /**清除*/
                return localStorage.removeItem(name);
            }
        },
        /**
         * 格式化当前日期
         * @param date
         * @param fmt
         * @returns {void | string}
         */
        dateFormat: function (date, fmt) {
            date = date || new Date();
            fmt = fmt || "yyyy年M月s日";
            var o = {
                "M+": date.getMonth() + 1,
                "d+": date.getDate(),
                "h+": date.getHours(),
                "m+": date.getMinutes(),
                "s+": date.getSeconds(),
                "q+": Math.floor((date.getMonth() + 3) / 3),
                "S": date.getMilliseconds()
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        },
        number: {
            /**
             * 判断是否为一个正常的数字
             * @param num
             */
            isNumber: function (num) {
                if (num && !isNaN(num)) {
                    return true;
                }
                return false;
            },
            /**
             * 判断一个数字是否包括在某个范围
             * @param num
             * @param begin
             * @param end
             */
            isNumberWith: function (num, begin, end) {
                if (this.isNumber(num)) {
                    if (num >= begin && num <= end) {
                        return true;
                    }
                    return false;
                }
            },
        },
//-----------------------弹框封装------------------------------------------------
        /**
         * confirm()函数二次封装
         * @param content
         * @param yesFunction
         */
        confirm: function (content, yesFunction) {
            let options = {skin: asucUtils.skinChoose(), icon: 3, title: "提示", anim: asucUtils.animChoose()};
            layer.confirm(content, options, yesFunction);
        },
        /**
         * open()函数二次封装,支持在table页面和普通页面打开
         * @param title
         * @param content
         * @param width
         * @param height
         * @param successFunction
         * @param endFunction
         */
        open: function (title, content, width, height, successFunction, endFunction) {
            layer.open({
                title: title,
                type: 2,
                maxmin: true,
                shade: 0.5,
                anim: asucUtils.animChoose(),
                area: [width, height],
                content: content,
                zIndex: layer.zIndex,
                skin: asucUtils.skinChoose(),
                success: successFunction,
                end: endFunction
            });
        },
        /**
         * msg()函数二次封装
         */
        // msg弹窗默认消失时间
        time: 1000,
        // 绿色勾
        greenTickMsg: function (content, callbackFunction) {
            let options = {icon: 1, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 红色叉
        redCrossMsg: function (content, callbackFunction) {
            let options = {icon: 2, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 黄色问号
        yellowQuestionMsg: function (content, callbackFunction) {
            let options = {icon: 3, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 灰色锁
        grayLockMsg: function (content, callbackFunction) {
            let options = {icon: 4, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 红色哭脸
        redCryMsg: function (content, callbackFunction) {
            let options = {icon: 5, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 绿色笑脸
        greenLaughMsg: function (content, callbackFunction) {
            let options = {icon: 6, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 黄色感叹号
        yellowSighMsg: function (content, callbackFunction) {
            let options = {icon: 7, time: asucUtils.time, anim: asucUtils.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        /**
         * 皮肤选择
         * @returns {string}
         */
        skinChoose: function () {
            let storage = window.localStorage;
            let skin = storage.getItem("skin");
            if (skin === 1) {
                // 灰白色
                return "";
            } else if (skin === 2) {
                // 墨绿色
                return "layui-layer-molv";
            } else if (skin === 3) {
                // 蓝色
                return "layui-layer-lan";
            } else if (!skin || skin === 4) {
                // 随机颜色
                var skinArray = ["", "layui-layer-molv", "layui-layer-lan"];
                return skinArray[Math.floor(Math.random() * skinArray.length)];
            }
        },
        /**
         * 动画选择
         * @returns {number}
         */
        animChoose: function () {
            let storage = window.localStorage;
            let anim = storage.getItem("anim");
            let animArray = ["0", "1", "2", "3", "4", "5", "6"];
            if (animArray.indexOf(anim) > -1) {
                // 用户选择的动画
                return anim;
            } else if (!anim || anim === 7) {
                // 随机动画
                return Math.floor(Math.random() * animArray.length);
            }
        }

    };
    exprots("asucUtils", asucUtils);
});

//格式化日期
var date_format = function (timestamp, format) {
    format = format || 'yyyy年MM月dd';
    timestamp = timestamp + "";
    if (timestamp * 1 > 0 && timestamp.length == 10) {
        timestamp = timestamp * 1000;
    }

    // 通过getDate()方法获取date类型的时间
    var regYear = new RegExp("(y+)", "i");
    var realDate = new Date(timestamp);

    function timeFormat(num) {
        return num < 10 ? '0' + num : num;
    }

    var date = [
        ["M+", timeFormat(realDate.getMonth() + 1)],
        ["d+", timeFormat(realDate.getDate())],
        ["h+", timeFormat(realDate.getHours())],
        ["m+", timeFormat(realDate.getMinutes())],
        ["s+", timeFormat(realDate.getSeconds())],
        ["q+", Math.floor((realDate.getMonth() + 3) / 3)],
        ["S+", realDate.getMilliseconds()],
    ];
    var reg1 = regYear.exec(format);

    if (reg1) {
        format = format.replace(reg1[1], (realDate.getFullYear() + '').substring(4 - reg1[1].length));
    }
    for (var i = 0; i < date.length; i++) {
        var k = date[i][0];
        var v = date[i][1];
        // getRegExp初始化一个正则表达式对象
        var reg2 = new RegExp("(" + k + ")").exec(format);
        if (reg2) {
            format = format.replace(reg2[1], reg2[1].length == 1
                ? v : ("00" + v).substring(("" + v).length));
        }
    }
    return format;
};




