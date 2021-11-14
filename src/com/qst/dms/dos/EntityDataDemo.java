package com.qst.dms.dos;

import com.qst.dms.entity.LogRec;
import com.qst.dms.entity.Transport;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;

/**
 * @Author: Richie
 * @Date: 2021-07-09 11:15:58
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\EntityDataDemo.java
 */

/**
 * 演示物流信息、日志信息的采集及打印输出
 */
public class EntityDataDemo {
    public static void main(String args[]) {

        // 初始化物流信息，日志信息数组
        LogRec[] logrec_data = new LogRec[2];
        Transport[] transport_data = new Transport[2];
        LogRecService logrecservice = new LogRecService();
        TransportService transportService = new TransportService();

        try {
            // 从键盘读入采集2组物流信息、2组日志信息，放到数组中
            System.out.println("采集日志信息");
            for (int i = 0; i < 2; i++) {
                logrec_data[i] = logrecservice.inputLog();
            }
            System.out.println("采集物流信息");
            for (int i = 0; i < 2; i++) {
                transport_data[i] = transportService.inputTransport();
            }
        } catch (Exception e) {
            // 异常报错
            e.printStackTrace();
            System.out.println("程序异常");
            System.exit(0);
        } finally {
            logrecservice.scanner.close();
        }

        // 输出采集的信息
        logrecservice.showLog(logrec_data);
        transportService.showTransport(transport_data);
    }
}
