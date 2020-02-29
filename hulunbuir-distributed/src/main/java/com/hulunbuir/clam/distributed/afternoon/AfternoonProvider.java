package com.hulunbuir.clam.distributed.afternoon;

import com.hulunbuir.clam.distributed.model.UserQo;

/**
 * <p>
 * Explain:afternoon服务提供的dubbo服务接口，对外进行暴露服务
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 12:51
 */
public interface AfternoonProvider {


    /**
     * 测试分布式事务-通过evening项目调用afternoon项目
     *
     * @author wangjunming
     * @since 2020/2/25 16:45
     * @param userQo:
     * @return boolean
     */
    boolean insertKoUser(UserQo userQo);



}
