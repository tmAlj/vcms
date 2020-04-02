package com.wsd.service;

import com.wsd.entity.OperateLog;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统操作日志service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface OperateLogService {

    List<OperateLog> getOperateLogList(OperateLog operateLog);

    void addOperateLog(OperateLog operateLog);

    void deleteOperateLog(Long[] opetateIds);

}
