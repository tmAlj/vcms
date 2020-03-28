package com.wsd.service;

import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;

import java.util.List;

/**
 * 系统异常日志service
 */
public interface ExceptionLogService {

    List<ExceptionLog> getExceptionLogList(ExceptionLog exceptionLog);

    void addExceptionLog(ExceptionLog exceptionLog);

    void deleteExceptionLog(Long[] exceptionIds);

}
