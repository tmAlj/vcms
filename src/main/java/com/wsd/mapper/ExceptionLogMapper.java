package com.wsd.mapper;

import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统异常日志mapper
 */
@Repository
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog>{

    List<ExceptionLog> getExceptionLogList(ExceptionLog exceptionLog);

    void deleteExceptionLog(Long[] exceptionIds);

}
