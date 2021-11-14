// package com.qst.dms.dos;

// import com.qst.dms.entity.LogRec;
// import com.qst.dms.entity.MatchedLogRec;
// import com.qst.dms.gather.LogRecAnalyse;
// import com.qst.dms.service.LogRecService;

// /**
//  * @Author: Richie
//  * @Date: 2021-07-11 10:41:34
//  * @LastEditTime: 2021/07/12
//  * @LastEditors: Richie
//  * @FilePath: \093119122\src\com\qst\dms\dos\DataGatherDemo.java
//  */

// /**
//  * 日志数据分析测试类，演示日志信息的采集、分析及打印输出
//  */
// public class DataGatherDemo {

//     /**
//      * 采集3组日志信息，放到数组中，进行日志分析、过滤，对于匹配的日志信息打印输出
//      * 
//      * @throws Exception
//      */
//     public static void main(String[] args) throws Exception {

//         // 创建一个日志业务类
//         LogRecService logrecservice = new LogRecService();

//         // 创建一个日志对象数组，用于存放采集的三个日志信息
//         LogRec[] logrecs = new LogRec[3];

//         //数据采集
//         System.out.println("开始数据采集：");
//         try {
//             for (int i = 0; i < logrecs.length; i++) {
//                 System.out.println("请输入第" + (i + 1) + "组数据：");
//                 logrecs[i] = logrecservice.inputLog();
//                 System.out.println("第" + (i + 1) + "组数据输入完成！");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("程序异常");
//             System.exit(0);
//         } finally {
//             logrecservice.scanner.close();
//             System.out.println("数据采集完成!");
//         }
        
//         // 创建日志数据分析对象
//         LogRecAnalyse logrecanalyse = new LogRecAnalyse(logrecs);

//         // 日志数据过滤
//         logrecanalyse.doFilter();

//         // 日志数据匹配分析
//         Object objs[] = logrecanalyse.matchedData();

//         // 判断objs数组是否是配置日志数组
//         if (objs instanceof MatchedLogRec[]) {

//             // 将对象数组强制类型转换成配置日志数组
//             MatchedLogRec logs[] = (MatchedLogRec[]) objs;
            
//             // 输出匹配的日志信息
//             logrecservice.showMatchLog(logs);
//         }
//     }
// }