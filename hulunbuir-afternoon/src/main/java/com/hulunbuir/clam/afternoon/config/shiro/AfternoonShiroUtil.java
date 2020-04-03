package com.hulunbuir.clam.afternoon.config.shiro;

import com.hulunbuir.clam.afternoon.persistence.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * explain:shiro工具类，获取当前登录用户的基本信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/3/23 16:35
 */
public final class AfternoonShiroUtil {

    /**
     * 获取shiro中的总对象
     *
     * @author wangjunming
     * @since 2020/3/23 16:38
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取shiro中的会话
     *
     * @author wangjunming
     * @since 2020/3/23 16:38
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 退出登录
     *
     * @author wangjunming
     * @since 2020/3/23 16:38
     */
    public static void logout() {
        getSubject().logout();
    }

    /**
     * 生成随机盐-用于存储到系统
     *
     * @author wangjunming
     * @since 2020/3/23 16:39
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        return new SecureRandomNumberGenerator().nextBytes(3).toHex();
    }

    /**
     * 获取当前登录用户信息
     *
     * @author wangjunming
     * @since 2020/3/23 18:00
     */
    public static User getUser() {
        User user = null;
        Object obj = getSubject().getPrincipal();
        if (null != obj) {
            user = new User();
            BeanUtils.copyProperties(obj, user);
        }
        return user;
    }

    /**
     * 配置用户信息
     *
     * @author wangjunming
     * @since 2020/3/23 18:01
     */
    public static void setUser(User user) {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }


    /**
     * 验证密码的正确性,不正确就返回true
     *
     * @author wangjunming
     * @since 2020/3/23 18:05
     */
    public static boolean matches(String usermail, String password, String salt,String inPassWord) {
        return !password.equals(encryptPassword(usermail, inPassWord, salt));
    }

    /**
     * 生成密码
     *
     * @author wangjunming
     * @since 2020/3/23 18:06
     */
    public static String encryptPassword(String usermail, String password, String salt) {
        return new Md5Hash(usermail + password + salt).toHex();
    }

    public static void main(String[] args) {
        String s = encryptPassword("qjyn1314@163.com", "wangjunming.1514", "9242e8");
        System.out.println("加密后的字符串："+s);
        boolean matches = matches("qjyn1314@163.com", s, "9242e8", "wangjunming.1514");
        System.out.println("是否正确："+matches);
    }


}
