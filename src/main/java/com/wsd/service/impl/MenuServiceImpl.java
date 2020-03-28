package com.wsd.service.impl;

import com.wsd.entity.Menu;
import com.wsd.mapper.MenuMapper;
import com.wsd.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单service
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有系统菜单
     *
     * @return
     */
    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getMenuListByUserId(Long userId) {
        //查询用户系统菜单
        List<Menu> rootMenu = menuMapper.getMenusByUserId(userId);
        List<Menu> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            if (rootMenu.get(i).getParentId() == 0L) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单
        for (Menu menu : menuList) {
            menu.setMenulist(getChild(menu.getMenuId(), rootMenu));
        }
        return menuList;
    }

    /**
     * 查询菜单树
     * @return
     */
    @Override
    public List<Menu> getMenuTree() {
        return menuMapper.getMenuTree();
    }

    /**
     * 查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuList() {
        //查询用户系统菜单
        List<Menu> rootMenu = menuMapper.getMenuList();
        List<Menu> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            if (rootMenu.get(i).getParentId() == 0L) {
                menuList.add(rootMenu.get(i));
            }
        }
        // 为一级菜单设置子菜单
        for (Menu menu : menuList) {
            menu.setMenulist(getChild(menu.getMenuId(), rootMenu));
        }
        return menuList;
    }

    /**
     * 新增菜单
     */
    @Override
    public void addMenu(Menu menu) {
        menu.setCreateTime(new Date());
        menuMapper.save(menu);
    }

    /**
     * 删除菜单
     * @param menuIdList
     */
    @Override
    public void deleteMenu(Long[] menuIdList) {
        menuMapper.deleteMenu(menuIdList);
    }

    /**
     * 修改菜单
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.update(menu);
    }

    /**
     * 递归遍历菜单
     * @param id
     * @param rootMenu
     * @return
     */
    List<Menu> getChild(Long id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getParentId().toString())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            //if (menu.getUrl() != "") {
                // 递归
                menu.setMenulist(getChild(menu.getMenuId(), rootMenu));
           // }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
