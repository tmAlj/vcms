package com.wsd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统组织表
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 12:52
 * @updateDate 2020-3-15 12:52
 */
public class Orgn implements Serializable {

    /**
     * 组织id
     */
    private Long orgnId;
    /**
     * 上级组织id
     */
    private Long parentId;
    /**
     * 上级组织名称
     */
    private String parentName;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 序号
     */
    private Integer orderNum;
    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 组织管理员
     */
    private String manager;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 子组织
     */
    private List<Orgn> children;

    public Long getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(Long orgnId) {
        this.orgnId = orgnId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Orgn> getChildren() {
        return children;
    }

    public void setChildren(List<Orgn> children) {
        this.children = children;
    }
}
