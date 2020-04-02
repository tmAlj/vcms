package com.wsd.entity;

import java.io.Serializable;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户角色实体类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public class UserAndRole implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId; 
    /**
     * 角色id
     */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
