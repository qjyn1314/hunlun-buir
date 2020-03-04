
var BaseUrl = "http://127.0.0.1:8026";

/**
 * ajax封装
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * successfn 成功回调函数
 */
jQuery.axPost = function (url, data, successfn) {
    url = BaseUrl + url;
    data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (d) {
            successfn(d);
        }
    });
};

/**
 * ajax封装
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
 * successfn 成功回调函数
 * errorfn 失败回调函数
 */
jQuery.axsPost = function (url, data, successfn, errorfn) {
    url = BaseUrl + url;
    data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        // contentType: 'application/json;charset=UTF-8',
        success: function (d) {
            successfn(d);
        },
        error: function (e) {
            errorfn(e);
        }
    });
};

/**
 * ajax封装
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * successfn 成功回调函数
 */
jQuery.axGet = function (url, data, successfn) {
    url = BaseUrl + url;
    data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "get",
        data: data,
        url: url,
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (d) {
            successfn(d);
        }
    });
};

/**
 * ajax封装
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
 * successfn 成功回调函数
 * errorfn 失败回调函数
 */
jQuery.axsGet = function (url, data, successfn, errorfn) {
    url = BaseUrl + url;
    data = (data == null || data === "" || typeof (data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "get",
        data: data,
        url: url,
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (d) {
            successfn(d);
        },
        error: function (e) {
            errorfn(e);
        }
    });
};

