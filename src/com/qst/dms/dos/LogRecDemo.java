package com.qst.dms.dos;

import com.qst.dms.entity.LogRec;
import com.qst.dms.service.LogRecService;

/**
 * @Author: Richie
 * @Date: 2021-07-07 18:25:51
 * @LastEditTime: 2021-07-12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\LogRecDemo.java
 */

/**
 * 演示日志信息的采集及打印输出
 */
public class LogRecDemo {
    public static void main(String args[]) {
        // 存储两组日志数据
        LogRec[] data = new LogRec[2];

        // 创建一个日志
        LogRecService logrecservice = new LogRecService();

        try {
            for (int i = 0; i < 2; i++) {
                data[i] = logrecservice.inputLog();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("程序异常");
            System.exit(0);
        } finally {
            logrecservice.scanner.close();
        }
        
        logrecservice.showLog(data);
    }
}
