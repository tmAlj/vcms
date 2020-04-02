package com.wsd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统操作日志实体
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-26 16:24
 * @updateDate 2020-3-26 16:24
 */
public class OperateLog {
    /**
     * ID
     */
    private Long operateId;
    /**
     * 系统模块
     */
    private String model;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 描述
     */
    private String details;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 返回参数
     */
    private String responseParam;
    /**
     * 操作用户
     */
    private Long userId;
    /**
     * 操作用户名称
     */
    private String userName;
    /**
     * 操作方法
     */
    private String method;
    /**
     * 请求URI
     */
    private String uri;
    /**
     * IP
     */
    private String ip;
    /**
     * 操作时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(String responseParam) {
        this.responseParam = responseParam;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
