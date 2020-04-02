package com.wsd.utils;

import com.wsd.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author tm
 * @version 1.0.0
 * @description spring security工具类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
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
