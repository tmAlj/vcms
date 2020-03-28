package com.wsd.service.impl;

import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;
import com.wsd.mapper.ExceptionLogMapper;
import com.wsd.mapper.OperateLogMapper;
import com.wsd.service.ExceptionLogService;
import com.wsd.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统操作日志serviceImpl
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    ExceptionLogMapper exceptionLogMapper;

    /**
     * 查询异常日志列表
     * @param exceptionLog
     * @return
     */
    @Override
    public List<ExceptionLog> getExceptionLogList(ExceptionLog exceptionLog) {
        return exceptionLogMapper.getExceptionLogList(exceptionLog);
    }

    /**
     * 新增异常日志
     * @param exceptionLog
     */
    @Override
    public void addExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.save(exceptionLog);
    }

    /**
     * 删除异常日志
     * @param exceptionIds
     */
    @Override
    public void deleteExceptionLog(Long[] exceptionIds) {
        exceptionLogMapper.deleteExceptionLog(exceptionIds);
    }
}
