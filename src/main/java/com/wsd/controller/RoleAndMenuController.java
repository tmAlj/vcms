package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.entity.Role;
import com.wsd.entity.RoleAndMenu;
import com.wsd.entity.User;
import com.wsd.service.RoleAndMenuService;
import com.wsd.service.UserService;
import com.wsd.utils.ResultData;
import com.wsd.utils.SecurityUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 角色菜单controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-2-26 14:13
 * @updateDate 2020-2-26 14:13
 */
@RestController
public class RoleAndMenuController {

    @Autowired
    private RoleAndMenuService roleAndMenuService;

    /**
     * 修改角色菜单
     * @param role
     * @return
     */
    @PostMapping("/rolemenu/updateRoleAndMenu")
    public ResultData updateRoleAndMenu(@RequestBody Role role){
        roleAndMenuService.updateRoleAndMenu(role);
        return ResultData.ok();
    }
}
