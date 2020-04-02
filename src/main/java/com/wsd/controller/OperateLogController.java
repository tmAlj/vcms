package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.annotation.ControllerLog;
import com.wsd.entity.LoginLog;
import com.wsd.entity.OperateLog;
import com.wsd.service.LoginLogService;
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
 * @description 操作日志controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 13:34
 * @updateDate 2020-3-15 13:34
 */
@RestController
public class OperateLogController {

    @Autowired
    OperateLogService operateLogService;

    /**
     * 操作日志列表页面
     * @return
     */
    @GetMapping("/operate/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("operate_log/list");
        return mv;
    }

    /**
     * 操作日志修改页面
     * @return
     */
    @GetMapping("/operate/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("operate_log/update");
        return mv;
    }

    /**
     * 查询操作日志列表
     * @return
     */
    @GetMapping("/operate_log/getOperateLogList")
    public ResultData getOperateLogList(Integer page, Integer limit, String userName){
        OperateLog operateLog = new OperateLog();
        operateLog.setUserName(userName);
        PageHelper.startPage(page, limit);
        List<OperateLog> operateLogList = operateLogService.getOperateLogList(operateLog);
        PageInfo<OperateLog> p = new PageInfo<OperateLog>(operateLogList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 删除操作日志
     * @return
     */
    @PostMapping("/operate_log/deleteOperateLog")
    @ControllerLog(model = "操作日志模块", type = "删除", describe = "日志删除")
    public ResultData deleteOperateLog(@RequestBody Long[] operateLogIds){
        operateLogService.deleteOperateLog(operateLogIds);
        return ResultData.ok();
    }

}
