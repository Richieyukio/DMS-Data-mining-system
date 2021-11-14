package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Richie
 * @Date: 2021-07-10 10:44:53
 * @LastEditTime: 2021/07/14
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\MatchedLogRec.java
 */

/**
 * 日志数据匹配类，对日志实体数据进行匹配，对同一个用户在同一个IP地址登录登出以及时间信息进行匹配
 */
public class MatchedLogRec implements Serializable {
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登入以及登出实体
     */
    private LogRec login;
    private LogRec logout;

    /**
     * 空构造方法
     */
    public MatchedLogRec() {
    }

    /**
     * 带参构造方法
     * 
     * @param login
     * @param logout
     */
    public MatchedLogRec(LogRec login, LogRec logout) {
        this.login = login;
        this.logout = logout;
    }

    /**
     * user用户登录名
     * 
     * @return
     */
    public String getUser() {
        return login.getUser();
    }

    /**
     * 登入时刻
     * 
     * @return
     */
    public Date getLogInTime() {
        return login.getTime();
    }

    /**
     * 登出时刻
     * 
     * @return
     */
    public Date getLogoutTime() {
        return logout.getTime();
    }

    /**
     * 读取登入记录
     * 
     * @return
     */
    public LogRec getLogin() {
        return login;
    }

    /**
     * 设置登入记录
     * 
     * @param login
     */
    public void setLogin(LogRec login) {
        this.login = login;
    }

    /**
     * 读取登出记录
     * 
     * @return
     */
    public LogRec getLogout() {
        return logout;
    }

    /**
     * 设置登出记录
     * 
     * @param logout
     */
    public void setLogout(LogRec logout) {
        this.logout = logout;
    }

    @Override
    public String toString() {
        return "MatchedLogRec [login=" + login + ", logout=" + logout + "]";
    }

    /**
     * 日志信息的匹配
     * @param logIn
     * @param logOut
     * @return
     */
    public boolean checkMatchedLog(LogRec logIn, LogRec logOut) {

        // 比配失败的常量
        final boolean MATCH_FAILURE = false;
        //比配成功的常量 
        final boolean MATCH_SUCCESS = true;

        // 匹配过程
        if (logIn.getLogType() != LogRec.LOG_IN) {
            System.out.println("不是登录记录！");
            return MATCH_FAILURE;
        }
        if (logOut.getLogType() != LogRec.LOG_OUT) {
            System.out.println("不是登出记录！");
            return MATCH_FAILURE;
        }
        if (!logIn.getUser().equals(logOut.getUser())) {
            System.out.println("登录登出必须是同一个用户！");
            return MATCH_FAILURE;
        }
        if (!logIn.getIp().equals(logOut.getIp())) {
            System.out.println("登录登出必须是同一个地址！");
            return MATCH_FAILURE;
        }
        if (!logIn.getTime().before(logOut.getTime())) {
            System.out.println("匹配失败！登录时间应该早于登出时间！");
            return MATCH_FAILURE;
        }
        System.out.println("匹配成功！");
        return MATCH_SUCCESS;
    }
}