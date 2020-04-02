package com.wsd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统登录日志实体
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-26 13:02
 * @updateDate 2020-3-26 13:02
 */
public class LoginLog implements Serializable {
    /**
     * 登录日志id
     */
    private Long loginId;
    /**
     * 账户名称
     */
    private String userName;
    /**
     * IP
     */
    private String ip;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String system;
    /**
     * 地理位置
     */
    private String location;
    /**
     * 登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 操作信息
     */
    private String info;
    /**
     * 主机名称
     */
    private String hostName;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
