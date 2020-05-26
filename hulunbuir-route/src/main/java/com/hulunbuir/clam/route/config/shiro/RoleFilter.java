package com.hulunbuir.clam.route.config.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 角色过滤器,为了实现or的效果就使用这个过滤器,shiro默认是and的效果
 *
 * @author wangjunming
 * @since 2020/5/25 16:59
 */
public class RoleFilter extends RolesAuthorizationFilter {

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws IOException {

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }
        return false;
    }

}
