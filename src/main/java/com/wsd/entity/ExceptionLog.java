package com.wsd.entity;

import java.util.Date;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统异常日志实体
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-26 16:25
 * @updateDate 2020-3-26 16:25
 */
public class ExceptionLog {
    /**
     * ID
     */
    private Long exceptionId;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 异常名称
     */
    private String name;
    /**
     * 异常信息
     */
    private String message;
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
    private Date createTime;

    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
