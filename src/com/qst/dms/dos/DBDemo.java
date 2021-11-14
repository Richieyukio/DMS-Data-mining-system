package com.qst.dms.dos;

import java.util.ArrayList;
import java.util.Date;

import com.qst.dms.entity.DataBase;
import com.qst.dms.entity.Transport;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.gather.TransportAnalyse;
import com.qst.dms.service.TransportService;

/**
 * @Author: Richie
 * @Date: 2021/07/15
 * @LastEditTime: 2021/07/16
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\DBDemo.java
 */

/**
 * 测试匹配的日志信息的数据库保存和读写功能
 */

public class DBDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建存储日志信息的列表
        ArrayList<Transport> Transports = new ArrayList<>();

        // 创建日志业务服务对象
        TransportService TransportService = new TransportService();

        // 创建日志匹配列表
        ArrayList<MatchedTransport> matchedTransports = null;

        Transports.add(new Transport(2001, new Date(), "青島", DataBase.GATHER, "zhangsan", "zhaokel", 1));
        Thread.sleep(1000);
        Transports.add(new Transport(2002, new Date(), "北京", DataBase.GATHER, "lisi", "zhaokel", 2));
        Thread.sleep(1000);
        Transports.add(new Transport(2003, new Date(), "北京", DataBase.GATHER, "wangwu", "zhaokel", 3));
        Thread.sleep(1000);
        Transports.add(new Transport(2004, new Date(), "青島", DataBase.GATHER, "maliu", "zhaokel", 1));
        Thread.sleep(1000);
        Transports.add(new Transport(2005, new Date(), "北京", DataBase.GATHER, "sunqi", "zhaokel", 2));
        Thread.sleep(1000);
        Transports.add(new Transport(2006, new Date(), "北京",DataBase.GATHER,"fengba","zhaokel",3));

		// 创建日志信息匹配对象
        TransportAnalyse logAn = new TransportAnalyse(Transports);

        // 日志数据过滤分析
        logAn.doFilter();
        matchedTransports = logAn.matchData();
        
		TransportService.saveMatchTransportToDB(matchedTransports);
		System.out.println("数据存储匹配完成");
		
		TransportService.showMatchTransport(TransportService.readMatchedTransportFromDB());
	}
}
