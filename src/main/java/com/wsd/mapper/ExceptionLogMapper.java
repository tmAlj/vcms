package com.wsd.mapper;

import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统异常日志mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog>{

    List<ExceptionLog> getExceptionLogList(@Param("exceptionLog") ExceptionLog exceptionLog);

    void deleteExceptionLog(@Param("exceptionIds") Long[] exceptionIds);

}
