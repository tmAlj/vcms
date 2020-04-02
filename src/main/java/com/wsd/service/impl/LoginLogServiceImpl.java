package com.wsd.service.impl;

import com.wsd.entity.LoginLog;
import com.wsd.entity.User;
import com.wsd.mapper.LoginLogMapper;
import com.wsd.mapper.UserMapper;
import com.wsd.service.LoginLogService;
import com.wsd.service.UserAndRoleService;
import com.wsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统登录日志serviceImpl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    /**
     * 查询登录日志列表
     * @param loginLog
     * @return
     */
    @Override
    public List<LoginLog> getLoginLogList(LoginLog loginLog) {
        return loginLogMapper.getLoginLogList(loginLog);
    }

    /**
     * 新增登录日志
     * @param loginLog
     */
    @Override
    public void addLoginLog(LoginLog loginLog) {
        loginLogMapper.save(loginLog);
    }

    /**
     * 删除登录日志
     * @param loginIds
     */
    @Override
    public void deleteLoginLog(Long[] loginIds) {
        loginLogMapper.deleteLoginLog(loginIds);
    }
}
