package com.wsd.controller;

import com.wsd.entity.Menu;
import com.wsd.service.MenuService;
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
 * @description 菜单controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-2-26 14:14
 * @updateDate 2020-2-26 14:14
 */
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 菜单列表页面
     * @return
     */
    @GetMapping("/menu/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/list");
        return mv;
    }

    /**
     * 菜单新增页面
     * @return
     */
    @GetMapping("/menu/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/add");
        return mv;
    }

    /**
     * 菜单修改页面
     * @return
     */
    @GetMapping("/menu/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/update");
        return mv;
    }

    /**
     * 子菜单新增页面
     * @return
     */
    @GetMapping("/menu/addChild")
    public ModelAndView addChild() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("menu/addChild");
        return mv;
    }

    /**
     * 查询菜单树
     */
    @GetMapping("/menu/getMenuTree")
    public ResultData getMenuTree(){
        List<Menu> menuList = menuService.getMenuTree();
        return ResultData.ok().put("menuList", menuList);
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/menu/getMenuList")
    public ResultData getMenuList(){
        List<Menu> menuList = menuService.getMenuList();
        return ResultData.ok().put("menuList", menuList);
    }

    /**
     * 新增菜单
     * @return
     */
    @PostMapping("/menu/addMenu")
    public ResultData addMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
        return ResultData.ok();
    }

    /**
     * 删除菜单
     * @return
     */
    @PostMapping("/menu/deleteMenu")
    public ResultData deleteMenu(@RequestBody Long[] menuIdList){
        for(Long menuId : menuIdList){
            if(menuId.longValue() <= 16){
                return ResultData.error("系统菜单，不能删除");
            }
        }
        menuService.deleteMenu(menuIdList);
        return ResultData.ok();
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PostMapping("/menu/updateMenu")
    public ResultData updateRole(@RequestBody Menu menu){
        menuService.updateMenu(menu);
        return ResultData.ok();
    }
}
