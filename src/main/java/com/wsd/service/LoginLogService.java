package com.wsd.service;

import com.wsd.entity.LoginLog;
import com.wsd.entity.User;

import java.util.List;

/**
 * 系统登录日志service
 */
public interface LoginLogService {

    List<LoginLog> getLoginLogList(LoginLog loginLog);

    void addLoginLog(LoginLog loginLog);

    void deleteLoginLog(Long[] loginIds);

}
