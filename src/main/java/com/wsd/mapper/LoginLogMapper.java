package com.wsd.mapper;

import com.wsd.entity.LoginLog;
import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统登录日志mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog>{

    List<LoginLog> getLoginLogList(@Param("loginLog") LoginLog loginLog);

    void deleteLoginLog(@Param("loginIds") Long[] loginIds);

}
