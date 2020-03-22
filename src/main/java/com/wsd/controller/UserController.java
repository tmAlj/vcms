package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.entity.User;
import com.wsd.service.UserService;
import com.wsd.utils.ResultData;
import com.wsd.utils.SecurityUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 用户controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-2-26 14:13
 * @updateDate 2020-2-26 14:13
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表页面
     * @return
     */
    @GetMapping("/user/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/list");
        return mv;
    }

    /**
     * 用户新增页面
     * @return
     */
    @GetMapping("/user/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/add");
        return mv;
    }

    /**
     * 用户修改页面
     * @return
     */
    @GetMapping("/user/update")
    public ModelAndView update(@PathParam("postId") String postId, @PathParam("orgnId") String orgnId, @PathParam("roleIds") String roleIds) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("postId", postId);
        mv.addObject("orgnId", orgnId);
        mv.addObject("roleIds", roleIds);
        mv.setViewName("user/update");
        return mv;
    }

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("/user/getUserList")
    public ResultData getUserList(Integer page, Integer limit, String name){
        User user = new User();
        user.setName(name);
        PageHelper.startPage(page, limit);
        List<User> userList = userService.getUserList(user);
        PageInfo<User> p = new PageInfo<User>(userList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/user/addUser")
    public ResultData addUser(@RequestBody User user){
        //判断用户名称是否可用
        User u = userService.getUserByUsername(user.getUsername());
        if(u != null){
            return ResultData.error("当前账号已被使用!");
        }
        userService.addUser(user);
        return ResultData.ok();
    }

    /**
     * 删除用户
     * @return
     */
    @PostMapping("/user/deleteUser")
    public ResultData deleteUser(@RequestBody Long[] userIdList){
        Long userId = SecurityUtils.getCurrentUserInfo().getUserId();
        if(ArrayUtils.contains(userIdList, 1L)){
            return ResultData.error("系统管理员不能删除");
        }
        if(ArrayUtils.contains(userIdList, userId)){
            return ResultData.error("当前用户不能删除");
        }
        userService.deleteUser(userIdList);
        return ResultData.ok();
    }

    /**
     * 修改用户
     * @return
     */
    @PostMapping("/user/updateUser")
    public ResultData updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResultData.ok();
    }

    /**
     * 重置用户密码
     * @return
     */
    @PostMapping("/user/resetUser")
    public ResultData resetUser(@RequestBody User user){
        userService.resetUser(user);
        return ResultData.ok();
    }
}
