// package com.qst.dms.dos;

// import com.qst.dms.entity.MatchedTransport;
// import com.qst.dms.entity.Transport;
// import com.qst.dms.gather.TransportAnalyse;
// import com.qst.dms.service.TransportService;

// /**
//  * @Author: Richie
//  * @Date: 2021/07/12
//  * @LastEditTime: 2021/07/12
//  * @LastEditors: Richie
//  * @FilePath: \093119122\src\com\qst\dms\dos\TransportDataAnalyseDemo.java
//  */

// /**
//  * 创建物流数据分析测试类，演示物流信息的采集、分析及打印输出
//  */
// public class TransportDataAnalyseDemo {
//     /**
//      * 采集4组物流信息，放到数组中，进行物流分析、过滤，对于匹配的物流信息打印输出
//      * 
//      * @param args
//      * @throws Exception
//      */
//     public static void main(String[] args) throws Exception {

//         // 创建一个物流业务类
//         TransportService transportservice=new TransportService();

//         // 创建一个物流对象数组，用于存放采集的四个物流信息
//         Transport []trans=new Transport[4];

//         // 数据采集
//         System.out.println("开始数据采集：");
//         try {
//             for (int i = 0; i < trans.length; i++) {
//                 System.out.println("请输入第" + (i + 1) + "组数据：");
//                 trans[i] = transportservice.inputTransport();
//                 System.out.println("第" + (i + 1) + "组数据输入完成！");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("程序异常");
//         } finally {
//             transportservice.scanner.close();
//             System.out.println("数据采集完成!");
//         }

//         // 创建日志数据分析对象
//         TransportAnalyse transportanalyse = new TransportAnalyse(trans);
        
//         // 日志数据过滤
//         transportanalyse.doFilter();

//         // 日志数据匹配分析
//         Object objs[] = transportanalyse.matchData();

//         // 判断objs数组是否是配置日志数组
//         if (objs instanceof MatchedTransport[]) {

//             // 将对象数组强制类型转换成配置日志数组
//             MatchedTransport transp[] = (MatchedTransport[]) objs;

//             // 输出匹配的日志信息
//             transportservice.showMatchTransport(transp);
//         }
//     }
// }