package com.wsd.mapper;

import com.wsd.entity.LoginLog;
import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统登录日志mapper
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog>{

    List<LoginLog> getLoginLogList(@Param("loginLog") LoginLog loginLog);

    void deleteLoginLog(@Param("loginIds") Long[] loginIds);

}
