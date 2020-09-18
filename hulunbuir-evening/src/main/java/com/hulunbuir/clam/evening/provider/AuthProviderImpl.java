package com.hulunbuir.clam.evening.provider;

import com.hulunbuir.clam.distributed.evening.AuthProvider;
import com.hulunbuir.clam.distributed.evening.AuthUser;
import com.hulunbuir.clam.evening.persistence.entity.SysUser;
import com.hulunbuir.clam.evening.persistence.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 用于查询用户信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/18 16:43
 */
@Slf4j
@Component
@Service
public class AuthProviderImpl implements AuthProvider {

    @Autowired
    private ISysUserService userService;

    @Override
    public AuthUser queryUser(String username){
        final SysUser sysUser = userService.selOne(new SysUser(username));
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(sysUser,authUser);
        return authUser;
    }

}
