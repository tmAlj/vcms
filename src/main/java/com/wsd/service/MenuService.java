package com.wsd.service;

import com.wsd.entity.Menu;

import java.util.List;

/**
 * 系统菜单service
 */
public interface MenuService {

    List<Menu> getAllMenu();

    List<Menu> getMenuListByUserId(Long userId);

    List<Menu> getMenuTree();

    List<Menu> getMenuList();

    void addMenu(Menu menu);

    void deleteMenu(Long[] menuIdList);

    void updateMenu(Menu menu);
}
