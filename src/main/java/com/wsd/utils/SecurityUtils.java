package com.wsd.utils;

import com.wsd.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * spring security工具类
 */
public class SecurityUtils {
    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static User getCurrentUserInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
