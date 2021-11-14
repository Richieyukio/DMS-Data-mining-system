package com.qst.dms.entity;

import java.io.Serializable;

/**
 * @Author: Richie
 * @Date: 2021-07-10 11:24:25
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\MatchedTransport.java
 */

/**
 * 物流数据匹配类，对物流实体数据进行匹配，对发货、送货、签收物流信息进行匹配
 */
public class MatchedTransport implements Serializable {
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 发货、送货、签收实体
     */
    private Transport send;
    private Transport trans;
    private Transport receive;

    /**
     * 空构造方法
     */
    public MatchedTransport() {
    }

    /**
     * 带参构造方法
     * @param send
     * @param trans
     * @param receive
     */
    public MatchedTransport(Transport send, Transport trans, Transport receive) {
        this.send = send;
        this.trans = trans;
        this.receive = receive;
    }

    /**
     * 读取发货实体
     * @return
     */
    public Transport getSend() {
        return send;
    }

    /**
     * 设置发货实体
     * @param send
     */
    public void setSend(Transport send) {
        this.send = send;
    }

    /**
     * 读取送货实体
     * @return
     */
    public Transport getTrans() {
        return trans;
    }

    /**
     * 设置送货实体
     * @param trans
     */
    public void setTrans(Transport trans) {
        this.trans = trans;
    }

    public Transport getReceive() {
        return receive;
    }

    public void setReceive(Transport receive) {
        this.receive = receive;
    }

    @Override
    public String toString() {
        return "MatchedTransport [receive=" + receive + ", send=" + send + ", trans=" + trans + "]";
    }

    /**
     * 物流信息的匹配
     * @param send
     * @param tans
     * @param receive
     * @return
     */
    public boolean checkMatchedTransport(Transport send, Transport trans, Transport receive) {

        // 比配失败的常量
        final boolean MATCH_FAILURE = false;
        //比配成功的常量 
        final boolean MATCH_SUCCESS = true;

        // 匹配过程
        //第一条数据不是发货记录
        if (send.getTransportType() != Transport.SENDDING) {
            System.out.println("不是发货记录！");
            return MATCH_FAILURE;
        }

        //第二条数据不是送货记录
        if (trans.getTransportType() != Transport.TRANSPORTING) {
            System.out.println("不是送货记录！");
            return MATCH_FAILURE;
        }

        //第三条数据不是签收记录
        if (receive.getTransportType() != Transport.RECIEVED) {
            System.out.println("不是签收记录！");
            return MATCH_FAILURE;
        }

        //判断三条信息的时间逻辑
        if (!send.getTime().before(trans.getTime()) || !trans.getTime().before(receive.getTime())) {
            System.out.println("匹配失败！三者时间不符合逻辑！");
            return MATCH_FAILURE;
        }

        //判断三条信息的收件人
        if (send.getReciver()!=trans.getReciver()||trans.getReciver()!=receive.getReciver()) {
            System.out.println("匹配失败！三者收件人不同！");
            return MATCH_FAILURE;
        }

        return MATCH_SUCCESS;
    }
}