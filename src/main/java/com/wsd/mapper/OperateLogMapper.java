package com.wsd.mapper;

import com.wsd.entity.OperateLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统操作日志mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface OperateLogMapper extends BaseMapper<OperateLog>{

    List<OperateLog> getOperateLogList(@Param("operateLog") OperateLog operateLog);

    void deleteOperateLog(@Param("operateIds") Long[] operateIds);

}
