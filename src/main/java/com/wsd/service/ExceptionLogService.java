package com.wsd.service;

import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统异常日志service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface ExceptionLogService {

    List<ExceptionLog> getExceptionLogList(ExceptionLog exceptionLog);

    void addExceptionLog(ExceptionLog exceptionLog);

    void deleteExceptionLog(Long[] exceptionIds);

}
