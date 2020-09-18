package com.hulunbuir.clam.distributed.evening;

/**
 * <p>
 * Explain:evening服务提供的dubbo服务接口，对外进行暴露服务，用于查询用户信息
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 12:52
 */
public interface AuthProvider {

    /**
     * 通过唯一标识用户名查询用户信息
     *
     * @author wangjunming
     * @since 2020/9/18 17:01
     */
    AuthUser queryUser(String username);

}
