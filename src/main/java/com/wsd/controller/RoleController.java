package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.entity.Role;
import com.wsd.entity.User;
import com.wsd.service.RoleAndMenuService;
import com.wsd.service.RoleService;
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
 * @description 角色controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-2-26 14:13
 * @updateDate 2020-2-26 14:13
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    RoleAndMenuService roleAndMenuService;

    /**
     * 角色列表页面
     * @return
     */
    @GetMapping("/role/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/list");
        return mv;
    }

    /**
     * 角色新增页面
     * @return
     */
    @GetMapping("/role/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/add");
        return mv;
    }

    /**
     * 角色修改页面
     * @return
     */
    @GetMapping("/role/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/update");
        return mv;
    }

    /**
     * 分配菜单页面
     * @return
     */
    @GetMapping("/role/menu/{roleId}")
    public ModelAndView menu(@PathVariable("roleId") Long roleId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleId", roleId);
        mv.setViewName("role/menu");
        return mv;
    }

    /**
     * 查询角色列表
     * @return
     */
    @GetMapping("/role/getRoleList")
    public ResultData getRoleList(Integer page, Integer limit, String name){
        Role role = new Role();
        role.setName(name);
        PageHelper.startPage(page, limit);
        List<Role> roleList = roleService.getRoleList(role);
        PageInfo<Role> p = new PageInfo<Role>(roleList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 新增角色
     * @return
     */
    @PostMapping("/role/addRole")
    public ResultData addRole(@RequestBody Role role){
        //判断角色标识是否可用
        Role role1 = roleService.getRole(role);
        if(role1 != null){
            return ResultData.error("当前角色标识已被使用!");
        }
        roleService.addRole(role);
        return ResultData.ok();
    }

    /**
     * 删除角色
     * @return
     */
    @PostMapping("/role/deleteRole")
    public ResultData deleteRole(@RequestBody Long[] roleIdList){
        if(ArrayUtils.contains(roleIdList, 1L)){
            return ResultData.error("系统管理员角色不能删除");
        }
        roleService.deleteRole(roleIdList);
        return ResultData.ok();
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PostMapping("/role/updateRole")
    public ResultData updateRole(@RequestBody Role role){
        roleService.updateRole(role);
        return ResultData.ok();
    }

    /**
     *  根据角色id查询对应菜单
     * @param roleId
     * @return
     */
    @GetMapping("/role/getMenuIdsByRoleId/{roleId}")
    public ResultData getMenuIdsByRoleId(@PathVariable("roleId") Long roleId){
        Role role = new Role();
        role.setRoleId(roleId);
        Role roleInfo = roleService.getRole(role);
        //查询角色对应的菜单
        List<Long> menuIdList = roleAndMenuService.getMenuIdsByRoleId(roleId);
        roleInfo.setMenuIdList(menuIdList);
        return ResultData.ok().put("roleInfo", roleInfo);
    }
}
