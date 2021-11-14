package com.qst.dms.dos;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.service.LogRecService;

/**
 * @Author: Richie
 * @Date: 2021-07-10 11:42:41
 * @LastEditTime: 2021-07-10 13:16:01
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\MatchLogDemo.java
 */

public class MatchLogDemo {
    /**
     * 演示日志信息的匹配及打印输出
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        //创一个日志业务类
        LogRecService matchLog = new LogRecService();

        //将String类型的日期转换成Date类型的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //创建日志对象数组,存放五组信息
        LogRec[] logs = new LogRec[5];
        logs[0] = new LogRec(133,sdf.parse("2020-7-1"),"address1",3,"lee","10.12.12.124", 1);
        logs[1] = new LogRec(129,sdf.parse("2020-6-29"),"address1",3,"lee","10.12.12.124", 0);
        logs[2] = new LogRec(127,sdf.parse("2020-6-21"),"address1",3,"lee","10.12.12.124", 1);
        logs[3] = new LogRec(125,sdf.parse("2020-6-19"),"address1",3,"lee","10.12.12.124", 0);
        logs[4] = new LogRec(123, sdf.parse("2020-5-1"), "address1", 3, "lee", "10.12.12.124", 1);
        
        //创建一个日志记录匹配类
        MatchedLogRec matchedLog = new MatchedLogRec();

        //存放匹配成功的信息
        MatchedLogRec[] matchLogs = new MatchedLogRec[20];
        
        //matcglogs数组的下标
        int num = 0;

        //匹配数据的组号
        int count = 1;

        //循环匹配所有可能的数据组合
        for (int in = 0; in < 5; in++) {
            for (int out = 0; out < 5; out++) {
                if (in != out) {
                    System.out.println("匹配第" + count + "组信息:"+"第"+(in+1)+"条和第"+(out+1)+"条");
                    if (matchedLog.checkMatchedLog(logs[in], logs[out])) {
                        System.out.println("匹配成功!");
                        matchLogs[num++] = new MatchedLogRec(logs[in], logs[out]);
                    }
                    count++;
                }
            }
        }

        //显示所有匹配的数据
        System.out.println("\n以下为匹配成功的"+num+"组信息:");
        matchLog.showMatchLog(matchLogs);
    }
}