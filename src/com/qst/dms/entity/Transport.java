package com.qst.dms.entity;

import java.util.Date;

/**
 * @Author: Richie
 * @Date: 2021-07-07 19:26:02
 * @LastEditTime: 2021-07-10 13:05:09
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\Transport.java
 */

/**
 * 继承DataBase类，重构日志类
 */
public class Transport extends DataBase {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 经手人
     */
    private String handler;

    /**
     * 收货人
     */
    private String reciver;

    /**
     * 物流状态
     */
    private int transportType;

    /**
     * 物流状态常量:发货中, 送货中, 已签收
     */
    public static final int SENDDING = 1;// 发货中
    public static final int TRANSPORTING = 2;// 送货中
    public static final int RECIEVED = 3;// 已签收

    /**
     * 空构造方法
     */
    public Transport() {
    }

    /**
     * 构造方法
     * 
     * @param handler
     * @param reciver
     * @param transportType
     */
    public Transport(String handler, String reciver, int transportType) {
        this.handler = handler;
        this.reciver = reciver;
        this.transportType = transportType;
    }

    /**
     * 构造方法
     * 
     * @param id
     * @param time
     * @param address
     * @param type
     * @param handler
     * @param reciver
     * @param transportType
     */
    public Transport(int id, Date time, String address, int type, String handler, String reciver, int transportType) {
        super(id, time, address, type);
        this.handler = handler;
        this.reciver = reciver;
        this.transportType = transportType;
    }

    /**
     * 读取经手人
     * 
     * @return
     */
    public String getHandler() {
        return handler;
    }

    /**
     * 设置经手人
     * 
     * @param handler
     */
    public void setHandler(String handler) {
        this.handler = handler;
    }

    /**
     * 读取收货人
     * 
     * @return
     */
    public String getReciver() {
        return reciver;
    }

    /**
     * 设置收货人
     * 
     * @param reciver
     */
    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    /**
     * 读取物流状态
     * 
     * @return
     */
    public int getTransportType() {
        return transportType;
    }

    /**
     * 设置物流状态
     * 
     * @param transportType
     */
    public void setTransportType(int transportType) {
        this.transportType = transportType;
    }

    public static int getSendding() {
        return SENDDING;
    }

    public static int getTransporting() {
        return TRANSPORTING;
    }

    public static int getRecieved() {
        return RECIEVED;
    }

    @Override
    public String toString() {
        return "Transport [handler=" + handler + ", reciver=" + reciver + ", transportType=" + transportType + "]" + super.toString();
    }

}
