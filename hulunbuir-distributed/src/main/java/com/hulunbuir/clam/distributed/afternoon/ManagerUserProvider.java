package com.hulunbuir.clam.distributed.afternoon;

import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.parent.exception.HulunBuirException;

import java.util.HashMap;

/**
 * <p>
 * explain: 查询用户信息的dubbo接口
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/24 19:31
 */
public interface ManagerUserProvider {
    /**
     * 通过用户用户名称查询用户信息
     *
     * @author wangjunming
     * @since 2020/5/25 15:04
     */
    UserManager queryBuirUser(HashMap<String,Object> queryMap) throws HulunBuirException;


}
