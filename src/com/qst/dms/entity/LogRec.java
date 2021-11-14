package com.qst.dms.entity;

import java.util.Date;

/**
 * @Author: Richie
 * @Date: 2021-07-07 15:19:43
 * @LastEditTime: 2021/07/14
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\LogRec.java
 */

/**
 * 实现日志实体类
 */
public class LogRec extends DataBase{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录用户名
     */
    private String user;

    /**
     * 登录用户主机IP地址
     */
    private String ip;

    /**
     * 登录状态：登录、登出
     */
    private int logType;

    /**
     * 登录常量LOG_IN
     */
    public static final int LOG_IN = 1;

    /**
     * 登出常量LOG_OUT
     */
    public static final int LOG_OUT = 0;

    /**
     * 空构造方法
     */
    public LogRec() {
    }

    /**
     * 构造方法
     * 
     * @param user
     * @param ip
     * @param logType
     */
    public LogRec(String user, String ip, int logType) {
        this.user = user;
        this.ip = ip;
        this.logType = logType;
    }

    /**
     * 构造方法
     * 
     * @param id
     * @param time
     * @param address
     * @param type
     * @param user
     * @param ip
     * @param logType
     */
    public LogRec(int id, Date time, String address, int type, String user, String ip, int logType) {
        super(id, time, address, type);
        this.user = user;
        this.ip = ip;
        this.logType = logType;
    }

    /**
     * 读取user
     * 
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置user
     * 
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 读取ip
     * 
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip
     * 
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 读取登录状态
     * 
     * @return
     */
    public int getLogType() {
        return logType;
    }

    /**
     * 设置登录状态
     * 
     * @param logType
     */
    public void setLogType(int logType) {
        this.logType = logType;
    }

    @Override
    public String toString() {
        return "LogRec [ip=" + ip + ", logType=" + logType + ", user=" + user + "]" + super.toString();
    }

}