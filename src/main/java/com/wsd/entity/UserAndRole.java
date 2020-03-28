package com.wsd.entity;

import java.io.Serializable;

/**
 * Created by tm on 2018/8/28.
 * 系统用户角色实体类
 */
public class UserAndRole implements Serializable {

    private Long id; //id
    private Long userId; //用户id
    private Long roleId; //角色id

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
