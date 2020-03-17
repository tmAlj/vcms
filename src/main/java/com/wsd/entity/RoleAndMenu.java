package com.wsd.entity;

import java.io.Serializable;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色菜单实体
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public class RoleAndMenu implements Serializable {

    private Long id; //id
    private Long roleId; //角色id
    private Long menuId; //菜单id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
