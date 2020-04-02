package com.wsd.service;

import com.wsd.entity.Menu;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统菜单service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
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
