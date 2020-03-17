package com.wsd.config;

import com.wsd.entity.Menu;
import com.wsd.entity.Role;
import com.wsd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 获取用户的权限
 */
@Component
public class UserSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService ms;

    //获取url匹配器
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        //获取当前请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //获取所有菜单
        List<Menu> menuList = ms.getAllMenu();
        for (Menu menu : menuList) {
            //判断当前请求的url是否与菜单url匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoleList().size() > 0) {
                List<Role> roleList = menu.getRoleList();
                int size = roleList.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roleList.get(i).getRoleName();
                }
                //参数为角色名称数组
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
