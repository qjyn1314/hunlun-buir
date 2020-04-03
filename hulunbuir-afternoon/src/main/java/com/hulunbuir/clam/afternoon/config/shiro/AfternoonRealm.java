package com.hulunbuir.clam.afternoon.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class AfternoonRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制
     * 着用户能访问应用中的哪些功能；
     *
     * @author wangjunming
     * @since 2020/3/23 15:54
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        log.info("登录用户的信息：{}", primaryPrincipal);
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roles);
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menus);
        return info;
    }

    /**
     * 登录认证器，负责主体认证的，这是一个扩展点，如果用户觉得Shiro 默认的
     * 不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户
     * 认证通过了；
     *
     * @author wangjunming
     * @since 2020/3/23 15:55
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("shiro登录验证");
        // 在我们自己的登录流程中应该确保登录的用户信息已经插入AuthenticationToken中，这样才能通过shiro的认证流程
        String userMail = authenticationToken.getPrincipal().toString();
        //虽然在登录流程中我们给的是String的面，但是shiro中已经写死了密码是个字符数组，所以老老实实的把密码转成char[]吧
        char[] password = (char[]) authenticationToken.getCredentials();
        // 查询用户名对应的用户信息
        User user = userService.queryUser(userMail);
        log.info("验证用户信息：{}", JSONObject.toJSONString(user, SerializerFeature.PrettyFormat));
        if (null != user && StringUtils.isNoneBlank(user.getUserPassword())) {
            //直接把用户信息对象和密码塞进shiro验证器，shiro会自动判断密码是否正确
            return new SimpleAuthenticationInfo(user, password, getName());
        }
        return null;
    }
}
