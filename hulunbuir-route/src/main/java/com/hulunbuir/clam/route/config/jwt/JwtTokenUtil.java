package com.hulunbuir.clam.route.config.jwt;

import com.hulunbuir.clam.parent.tool.JwtUtils;
import com.hulunbuir.clam.distributed.model.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * explain: jwt工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/3/23 12:26
 */
@Slf4j
@Component
public final class JwtTokenUtil {

    /**
     * 生成登录用户信息的token
     *
     * @param userManager 登录用户信息
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public String doUserInfoToken(UserManager userManager) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("loginUser", userManager);
        return JwtUtils.generateToken(claims, JwtUtils.secret, JwtUtils.expireTime);
    }

    /**
     * 获取登录用户信息,调用此接口之前请先调用parseToken(userToken)并保证返回true
     *
     * @param userToken 存储登录用户信息的token
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public UserManager getUserInfoByToken(String userToken) {
        return StringUtils.isNotBlank(userToken) ? JwtUtils.mapToEntity(JwtUtils.getClaimFromToken(userToken).get("loginUser", LinkedHashMap.class), UserManager.class) : null;
    }


}
