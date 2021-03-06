package com.hulunbuir.evening.provider;

import com.hulunbuir.distributed.evening.AuthProvider;
import com.hulunbuir.distributed.evening.AuthUser;
import com.hulunbuir.evening.persistence.entity.SysUser;
import com.hulunbuir.evening.persistence.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
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
@DubboService
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
