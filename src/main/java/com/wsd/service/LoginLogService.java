package com.wsd.service;

import com.wsd.entity.LoginLog;
import com.wsd.entity.User;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统登录日志service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface LoginLogService {

    List<LoginLog> getLoginLogList(LoginLog loginLog);

    void addLoginLog(LoginLog loginLog);

    void deleteLoginLog(Long[] loginIds);

}
