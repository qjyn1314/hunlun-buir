package com.hulunbuir.parent.result;

/**
 * <p>
 * Explain:结果返回类，封装参数
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 11:51
 */
public final class ServiceResult<T> {

    final private static String SUCCESS = "success";
    final private static String ERROR = "error";

    private Boolean flag;

    private T data;

    private String message;

    private ServiceResult(Boolean flag, T data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public static ServiceResult success() {
        return new ServiceResult(Boolean.TRUE, null, SUCCESS);
    }

    public static <T> ServiceResult success(T t) {
        return new ServiceResult(Boolean.TRUE, t, SUCCESS);
    }

    public static ServiceResult successMsg(String msg) {
        return new ServiceResult(Boolean.TRUE, null, msg);
    }

    public static <T> ServiceResult successObj(T t, String msg) {
        return new ServiceResult(Boolean.TRUE, t, msg);
    }

    public static ServiceResult error() {
        return new ServiceResult(Boolean.FALSE, null, ERROR);
    }

    public static ServiceResult error(String msg) {
        return new ServiceResult(Boolean.FALSE, null, msg);
    }

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

    @Override
    public String toString() {
        return "ServiceResult{" +
                "flag=" + flag +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
