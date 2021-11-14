package com.qst.dms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Richie
 * @Date: 2021-07-09 10:38:00
 * @LastEditTime: 2021/07/14
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\DataBase.java
 */

/**
 * 基础信息实体类
 */
public class DataBase implements Serializable{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID标识
     */
    private int id;

    /**
     * 时间
     */
    private Date time;

    /**
     * 地点
     */
    private String address;

    /**
     * 状态
     */
    private int type;

    /**
     * 状态常量
     */
    public static final int GATHER = 1;//采集常量
    public static final int MATHCH = 2;//匹配常量
    public static final int RECORD = 3;//记录常量
    public static final int SEND = 4;//发送常量
    public static final int RECIVE = 5;//接收常量
    public static final int WRITE = 6;//归档常量
    public static final int SAVE = 7;//保存常量

    /**
     * 空构造方法
     */
    public DataBase() {
    }

    /**
     * 构造方法
     * 
     * @param id
     * @param time
     * @param address
     * @param type
     */
    public DataBase(int id, Date time, String address, int type) {
        this.id = id;
        this.time = time;
        this.address = address;
        this.type = type;
    }

    /**
     * 读取id
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 读取time
     * 
     * @return
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置time
     * 
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "address=" + address + ", id=" + id + ", time=" + time + ", type=" + type;
    }

}
