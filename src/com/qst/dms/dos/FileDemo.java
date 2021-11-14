package com.qst.dms.dos;

import java.util.ArrayList;
import java.util.Date;
import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.entity.Transport;
import com.qst.dms.gather.LogRecAnalyse;
import com.qst.dms.gather.TransportAnalyse;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;

/**
 * @Author: Richie
 * @Date: 2021/07/14
 * @LastEditTime: 2021/07/14
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\FileDemo.java
 */

/**
 * 匹配的日志、物流信息的保存和读写功能
 */
public class FileDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建存储日志信息和物流信息的列表
        ArrayList<LogRec> logRecs = new ArrayList<>();
        ArrayList<Transport> transports = new ArrayList<>();

        // 创建日志业务、物流业务服务对象
        LogRecService logRecService = new LogRecService();
        TransportService transportService = new TransportService();

        // 创建日志、物流信息匹配列表
        ArrayList<MatchedLogRec> matchedLogRecs = null;
        ArrayList<MatchedTransport> matchedTransports = null;

        // 存放日志信息
        logRecs.add(new LogRec(1001, new Date(), "青島", DataBase.GATHER, "zhangsan", "192.168.1.1", 1));
        Thread.sleep(1000);
        logRecs.add(new LogRec(1002, new Date(), "青島", DataBase.GATHER, "zhangsan", "192.168.1.1", 0));
        Thread.sleep(1000);
        logRecs.add(new LogRec(1003, new Date(), "北京", DataBase.GATHER, "lisi", "192.168.1.6", 1));
        Thread.sleep(1000);
        logRecs.add(new LogRec(1005, new Date(), "济南", DataBase.GATHER, "wangwu", "192.168.1.89", 1));
        Thread.sleep(1000);
        logRecs.add(new LogRec(1006, new Date(), "济南", DataBase.GATHER, "wangwu", "192.168.1.89", 0));

        // 创建日志信息匹配对象
        LogRecAnalyse logAn = new LogRecAnalyse(logRecs);

        // 日志数据过滤分析
        logAn.doFilter();
        matchedLogRecs =  (ArrayList<MatchedLogRec>) logAn.matchData();

        // 保存匹配的数据信息
        logRecService.saveMatchedLogRec(matchedLogRecs);

        // 从文件中读取匹配的日志信息
        System.out.println("显示匹配成功的日志信息：");
        logRecService.showMatchLog(logRecService.readMatchedLogRec());
        System.out.println();


        // 存放物流信息
        transports.add(new Transport(2001, new Date(), "青島", DataBase.GATHER, "zhangsan", "zhaokel", 1));
        Thread.sleep(1000);
        transports.add(new Transport(2002, new Date(), "北京", DataBase.GATHER, "lisi", "zhaokel", 2));
        Thread.sleep(1000);
        transports.add(new Transport(2003, new Date(), "北京", DataBase.GATHER, "wangwu", "zhaokel", 3));
        Thread.sleep(1000);
        transports.add(new Transport(2004, new Date(), "青島", DataBase.GATHER, "maliu", "zhaokel", 1));
        Thread.sleep(1000);
        transports.add(new Transport(2005, new Date(), "北京", DataBase.GATHER, "sunqi", "zhaokel", 2));
        Thread.sleep(1000);
        transports.add(new Transport(2006, new Date(), "北京", DataBase.GATHER, "fengba", "zhaokel", 3));

        // 创建物流信息匹配对象
        TransportAnalyse transAn = new TransportAnalyse(transports);

        // 物流数据过滤分析
        transAn.doFilter();
        matchedTransports = (ArrayList<MatchedTransport>) transAn.matchData();

        // 保存匹配的数据信息
        transportService.saveMatchedTransport(matchedTransports);

        // 从文件中读取匹配的物流信息
        transportService.showMatchTransport(transportService.readTransport());
    }
}
