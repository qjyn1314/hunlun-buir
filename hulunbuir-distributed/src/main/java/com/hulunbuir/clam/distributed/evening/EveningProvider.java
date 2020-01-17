package com.hulunbuir.clam.distributed.evening;

/**
 * <p>
 * Explain:evening服务提供的dubbo服务接口，对外进行暴露服务
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 12:52
 */
public interface EveningProvider {

    /**
     * 获取当前时间
     *
     * @author wangjunming
     * @since 2020/1/17 10:17
     * @return java.lang.String
     */
    String getDateTimes();

}
