package com.wsd.mapper;

import com.wsd.entity.OperateLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统操作日志mapper
 */
@Repository
public interface OperateLogMapper extends BaseMapper<OperateLog>{

    List<OperateLog> getOperateLogList(OperateLog operateLog);

    void deleteOperateLog(Long[] opetateIds);

}
