package com.wsd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统菜单实体类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public class Menu implements Serializable {

    private Long menuId; //菜单ID
    private Long parentId; //父菜单ID，一级菜单为0
    private String parentName; //父菜单名称
    private String name; //菜单名称
    private String url; //菜单URL
    //private String perms; //授权(多个用逗号分隔，如：user:list,user:create)
    //private Integer type; //资源类型 0：目录 1：菜单 2：按钮
    private String icon; //菜单图标
    private Integer orderNum; //排序
    //private Boolean open; //ztree属性
    //private Boolean enabled; //状态  0：禁用   1：正常
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime; //创建时间
    private List<Menu> menulist; //菜单列表
    private List<Role> roleList; //角色列表

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<Menu> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<Menu> menulist) {
        this.menulist = menulist;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
