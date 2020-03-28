package com.wsd.service;

import com.wsd.entity.OperateLog;

import java.util.List;

/**
 * 系统操作日志service
 */
public interface OperateLogService {

    List<OperateLog> getOperateLogList(OperateLog operateLog);

    void addOperateLog(OperateLog operateLog);

    void deleteOperateLog(Long[] opetateIds);

}
