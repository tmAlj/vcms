package com.wsd.service.impl;

import com.wsd.entity.OperateLog;
import com.wsd.mapper.OperateLogMapper;
import com.wsd.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统操作日志serviceImpl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    OperateLogMapper operateLogMapper;

    /**
     * 查询操作日志列表
     * @param operateLog
     * @return
     */
    @Override
    public List<OperateLog> getOperateLogList(OperateLog operateLog) {
        return operateLogMapper.getOperateLogList(operateLog);
    }

    /**
     * 新增操作日志
     * @param operateLog
     */
    @Override
    public void addOperateLog(OperateLog operateLog) {
        operateLogMapper.save(operateLog);
    }

    /**
     * 删除操作日志
     * @param opetateIds
     */
    @Override
    public void deleteOperateLog(Long[] opetateIds) {
        operateLogMapper.deleteOperateLog(opetateIds);
    }
}
