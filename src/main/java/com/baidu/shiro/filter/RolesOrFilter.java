package com.baidu.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * @Author: WH
 * @Date: 2019/3/4 10:23
 * @Version 1.0
 */
public class RolesOrFilter extends AuthorizationFilter {


    protected boolean isAccessAllowed(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        //获得角色
        String[] roles = (String[]) mappedValue;

        if (roles == null || roles.length == 0) {
            return  true;
        }
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return  true;
            }
        }
        return  false;
    }
}
