package com.hulunbuir.clam.route.config.shiro;

import com.hulunbuir.clam.distributed.afternoon.ManagerUserProvider;
import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;

/**
 * 身份校验核心类
 *
 * @author wangjunming
 * @since 2020/5/25 16:52
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Reference(check = false, timeout = 500000, retries = 0)
    private ManagerUserProvider managerUserProvider;

    /**
     * 认证信息.(身份验证)
     * Authentication 是用来验证用户身份
     *
     * @author wangjunming
     * @since 2020/5/25 16:53
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");
        //用户名称
        String username = (String)token.getPrincipal();
        log.info("用户输入的用户名称：{}", username);
//        String username = (String) token.getPrincipal();
//        通过username从数据库中查找 UserManager
//        实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        HashMap<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("userName", username);
        UserManager userManager = null;
        try {
            userManager = managerUserProvider.queryBuirUser(queryMaps);
        } catch (HulunBuirException e) {
            throw new NoUserException();
        }
        //将用户输入的密码、用户名、加上数据库中的盐值进行生成一个md5的密文
//        String md5Password = ShiroTool.md5(new String(password), username + userManager.getSalt());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        //明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
        return new SimpleAuthenticationInfo(
                userManager, //此用户用于存放到shiro的当前登录用户中
                userManager.getPassword(), //注意这里是指从数据库中获取的password。
                ByteSource.Util.bytes(userManager.getSalt()),//数据库中的盐值
                getName()  //realm name
        );
    }

    /**
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @author wangjunming
     * @since 2020/5/25 16:57
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserManager managerInfo = (UserManager) principals.getPrimaryPrincipal();
        //设置相应角色的权限信息
//        for (SysRole role : managerInfo.getRoles()) {
//            //设置角色
//            authorizationInfo.addRole(role.getRole());
//            for (Permission p : role.getPermissions()) {
//                //设置权限
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return authorizationInfo;
    }

}
