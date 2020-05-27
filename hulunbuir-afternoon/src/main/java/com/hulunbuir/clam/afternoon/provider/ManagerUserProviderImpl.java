package com.hulunbuir.clam.afternoon.provider;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.distributed.afternoon.ManagerUserProvider;
import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/5/24 19:32
 */
@Slf4j
@Component
@Service(interfaceClass = ManagerUserProvider.class,timeout = 500000)
public class ManagerUserProviderImpl implements ManagerUserProvider {

    @Autowired
    private IBuirUserService buirUserService;

    /**
     * 通过用户用户名称查询用户信息
     *
     * @param queryMap
     * @author wangjunming
     * @since 2020/5/25 15:04
     */
    @Override
    public UserManager queryBuirUser(HashMap<String, Object> queryMap) throws HulunBuirException {
        BuirUser buirUser = buirUserService.queryBuirUser(queryMap);
        if(null == buirUser){
            HulunBuirException.build("该用户未注册！");
        }
        final UserManager userManager = new UserManager();
        BeanUtils.copyProperties(buirUser,userManager);
        return userManager;
    }


}
