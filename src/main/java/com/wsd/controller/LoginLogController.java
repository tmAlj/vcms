package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.annotation.ControllerLog;
import com.wsd.entity.LoginLog;
import com.wsd.entity.Post;
import com.wsd.entity.User;
import com.wsd.service.LoginLogService;
import com.wsd.service.PostService;
import com.wsd.service.UserService;
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
 * @description 登录日志controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 13:34
 * @updateDate 2020-3-15 13:34
 */
@RestController
public class LoginLogController {

    @Autowired
    LoginLogService loginLogService;

    /**
     * 登录日志列表页面
     * @return
     */
    @GetMapping("/login/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login_log/list");
        return mv;
    }

    /**
     * 登录日志修改页面
     * @return
     */
    @GetMapping("/login/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login_log/update");
        return mv;
    }

    /**
     * 查询登录日志列表
     * @return
     */
    @GetMapping("/login_log/getLoginLogList")
    public ResultData getLoginLogList(Integer page, Integer limit, String userName){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(userName);
        PageHelper.startPage(page, limit);
        List<LoginLog> loginLogList = loginLogService.getLoginLogList(loginLog);
        PageInfo<LoginLog> p = new PageInfo<LoginLog>(loginLogList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 删除登录日志
     * @return
     */
    @PostMapping("/login_log/deleteLoginLog")
    @ControllerLog(model = "登录日志模块", type = "删除", describe = "日志删除")
    public ResultData deleteLoginLog(@RequestBody Long[] loginLogIds){
        loginLogService.deleteLoginLog(loginLogIds);
        return ResultData.ok();
    }

}
