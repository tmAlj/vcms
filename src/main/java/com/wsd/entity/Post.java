package com.wsd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统职位实体类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 12:48
 * @updateDate 2020-3-15 12:48
 */
public class Post implements Serializable {

    /**
     * 职位id
     */
    private Long postId;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 职位编码
     */
    private String code;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
