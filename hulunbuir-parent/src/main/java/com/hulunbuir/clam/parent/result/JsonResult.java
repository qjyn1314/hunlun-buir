package com.hulunbuir.clam.parent.result;

import lombok.ToString;

/**
 * <p>
 * Explain:结果返回类，封装参数
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 11:51
 */
@ToString
public final class JsonResult<T> {

    final private static String SUCCESS = "success";
    final private static String ERROR = "error";

    private Boolean flag;

    private T data;

    private String message;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private JsonResult(Boolean flag, T data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public static JsonResult success() {
        return new JsonResult(Boolean.TRUE, null, SUCCESS);
    }

    public static <T> JsonResult success(T t) {
        return new JsonResult(Boolean.TRUE, t, SUCCESS);
    }

    public static JsonResult successMsg(String msg) {
        return new JsonResult(Boolean.TRUE, null, msg);
    }

    public static <T> JsonResult successObj(T t, String msg) {
        return new JsonResult(Boolean.TRUE, t, msg);
    }

    public static JsonResult error() {
        return new JsonResult(Boolean.FALSE, null, ERROR);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(Boolean.FALSE, null, msg);
    }

}
