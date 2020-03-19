package com.hulunbuir.clam.afternoon.provider;

import com.hulunbuir.clam.afternoon.persistence.entity.KoUser;
import com.hulunbuir.clam.afternoon.persistence.service.IKoUserService;
import com.hulunbuir.clam.distributed.afternoon.AfternoonProvider;
import com.hulunbuir.clam.distributed.model.UserQo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 13:13
 */
@Service(interfaceClass = AfternoonProvider.class)
@Component
@Slf4j
public class AfternoonProviderImpl implements AfternoonProvider {

    @Autowired
    private IKoUserService userService;

    /**
     * 测试分布式事务-通过evening项目调用afternoon项目
     *
     * @param userQo :
     * @return boolean
     * @author wangjunming
     * @since 2020/2/25 16:45
     */
    @Override
    public boolean insertKoUser(UserQo userQo) {
        KoUser user = new KoUser();
        BeanUtils.copyProperties(userQo,user);
//        int i = 10/0;
        return userService.insertUser(user);
    }
}
