package com.qst.dms.dos;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.service.TransportService;

/**
 * @Author: Richie
 * @Date: 2021-07-10 12:19:44
 * @LastEditTime: 2021-07-10 13:19:04
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\MatchTransportDemo.java
 */

/**
 * 演示物流信息的匹配及打印输出
 */
public class MatchTransportDemo {
    public static void main(String[] args) throws ParseException {

        // 创建一个物流业务类
        TransportService matchTranport = new TransportService();

        // 将String类型的日期转换成Date类型的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 船舰物流对象数组,存放五条信息
        Transport[] transports = { new Transport(233, sdf.parse("2020-7-1"), "suzhou", 3, "lee", "wang", 1),
                new Transport(229, sdf.parse("2020-6-29"), "hangzhou", 2, "lee", "wang", 1),
                new Transport(227, sdf.parse("2020-6-21"), "nanjing", 4, "lee", "sun", 2),
                new Transport(225, sdf.parse("2020-6-19"), "suzhou", 5, "lee", "liu", 2),
                new Transport(223, sdf.parse("2020-5-1"), "nanjing", 6, "lee", "zhang", 3) };

        // 创建数组存放匹配成功的信息
        MatchedTransport[] matchTrans = new MatchedTransport[60];

        // 创建一个物流信息类
        MatchedTransport matchedTrans = new MatchedTransport();

        // 匹配成功数组的下标
        int num = 0;

        // 匹配数据的组号
        int count = 1;

        // 循环匹配所有可能的数据组合
        for (int s = 0; s < 5; s++) {
            for (int t = 0; t < 5; t++) {
                for (int r = 0; r < 5; r++) {
                    // 不可重复同一条数据
                    if ((s != t) & (t != r) & (s != r)) {
                        System.out.println("匹配第" + count + "组信息：");
                        count++;
                        if (matchedTrans.checkMatchedTransport(transports[s], transports[t], transports[r])) {
                            matchTrans[num] = new MatchedTransport(transports[s], transports[t], transports[r]);
                            num++;
                        }
                    }
                }
            }
        }

        // 显示匹配信息
        if (num == 0) {
            System.out.println("\n没有匹配成功的信息！");
        } else {
            System.out.println("\n以下为匹配成功的" + num + "组信息：");
            matchTranport.showMatchTransport(matchTrans);
        }
    }
}