package com.wsd.service;

import com.wsd.entity.Role;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色菜单service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public interface RoleAndMenuService {

    List<Long> getMenuIdsByRoleId(Long roleId);

    void updateRoleAndMenu(Role role);
}
