package com.wsd.controller;

import com.wsd.annotation.ControllerLog;
import com.wsd.entity.Role;
import com.wsd.service.RoleAndMenuService;
import com.wsd.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @ControllerLog(model = "角色菜单模块", type = "修改", describe = "角色菜单修改")
    public ResultData updateRoleAndMenu(@RequestBody Role role){
        roleAndMenuService.updateRoleAndMenu(role);
        return ResultData.ok();
    }
}
