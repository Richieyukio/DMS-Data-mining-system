package com.qst.dms.dos;

import com.qst.dms.entity.Transport;
import com.qst.dms.service.TransportService;

/**
 * @Author: Richie
 * @Date: 2021-07-07 19:45:09
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\TransportDemo.java
 */

/**
 * 演示物流信息的采集及打印输出
 */
public class TransportDemo {
    public static void main(String args[]) {
        // 存储两组日志数据
        Transport[] data = new Transport[2];

        // 创建一个日志
        TransportService logrecservice = new TransportService();

        try {
            for (int i = 0; i < 2; i++) {
                data[i] = logrecservice.inputTransport();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("程序异常");
            System.exit(0);
        } finally {
            logrecservice.scanner.close();
        }
        logrecservice.showTransport(data);
    }
}
