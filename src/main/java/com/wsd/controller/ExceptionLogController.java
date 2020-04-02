package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.annotation.ControllerLog;
import com.wsd.entity.ExceptionLog;
import com.wsd.entity.OperateLog;
import com.wsd.service.ExceptionLogService;
import com.wsd.service.OperateLogService;
import com.wsd.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 异常日志controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 13:34
 * @updateDate 2020-3-15 13:34
 */
@RestController
public class ExceptionLogController {

    @Autowired
    ExceptionLogService exceptionLogService;

    /**
     * 异常日志列表页面
     * @return
     */
    @GetMapping("/exception/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("exception_log/list");
        return mv;
    }

    /**
     * 异常日志修改页面
     * @return
     */
    @GetMapping("/exception/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("exception_log/update");
        return mv;
    }

    /**
     * 查询异常日志列表
     * @return
     */
    @GetMapping("/exception_log/getExceptionLogList")
    public ResultData getExceptionLogList(Integer page, Integer limit, String userName){
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setUserName(userName);
        PageHelper.startPage(page, limit);
        List<ExceptionLog> exceptionLogList = exceptionLogService.getExceptionLogList(exceptionLog);
        PageInfo<ExceptionLog> p = new PageInfo<ExceptionLog>(exceptionLogList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 删除异常日志
     * @return
     */
    @PostMapping("/exception_log/deleteExceptionLog")
    @ControllerLog(model = "异常日志模块", type = "删除", describe = "日志删除")
    public ResultData deleteExceptionLog(@RequestBody Long[] operateLogIds){
        exceptionLogService.deleteExceptionLog(operateLogIds);
        return ResultData.ok();
    }

}
