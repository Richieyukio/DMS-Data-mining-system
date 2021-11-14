package com.qst.dms.dos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
 * @Date: 2021-07-07 13:30:40
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\MenuDriver.java
 */

/**
 * 用户界面选择
 */
public class MenuDriver {

    static Scanner scanner = new Scanner(System.in);

    /**
     * 创建一个泛型ArrayList集合存储日志数据
     */
    static List<LogRec> logreclist = new ArrayList<LogRec>();

    /**
     * 创建一个泛型ArrrayList集合存储物流数据
     */
    static List<Transport> transportlist = new ArrayList<Transport>();

    /**
     * 创建一个日志业务类
     */
    static LogRecService logrecservice = new LogRecService();

    /**
     * 创建一个物流业务类
     */
    static TransportService transportservice = new TransportService();

    /**
     * 日志数据匹配集合
     */
    static List<MatchedLogRec> matchedlogreclist = null;

    /**
     * 物流数据匹配集合
     */
    static List<MatchedTransport> matchedtransportlist = null;

    public static void main(String args[]) {

        // 接收键盘输入的用户选择
        int userChoice;

        // 循环，直至用户输入0退出程序
        while (true) {

            // 打印菜单
            showMeun();

            // 判断用户输入，直至输入成功
            while (true) {
                userChoice = getUserChoice(0, 5);
                if (userChoice >= 0 && userChoice <= 5) {
                    break;
                }
            }

            // 用户选择
            switch (userChoice) {
                case 1:
                    System.out.println("数据采集");
                    dataCollection();
                    break;
                case 2:
                    System.out.println("数据匹配");
                    dataMatching();
                    break;
                case 3:
                    System.out.println("数据记录");
                    break;
                case 4:
                    System.out.println("数据显示");
                    dataDisplay();
                    break;
                case 5:
                    System.out.println("数据发送");
                    break;
                case 0:
                    System.out.println("退出应用");
                    break;
                default:
                    System.out.println("输入错误");
            }
            // 退出程序
            if (userChoice == 0) {
                new Scanner(System.in).close();
                break;
            }
        }
    }

    /**
     * 读入用户选择
     * 
     * @return
     */
    public static int getUserChoice(int minn, int maxn) {
        // 从键盘接收用户输入
        int userChoice;

        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice >= minn && userChoice <= maxn) {
                return userChoice;
            } else {
                System.out.println("输入的数字不在" + minn + "-" + maxn + "范围内,请重新输入");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("输入的数据异常,非int类型,请重新输入");
        }
        return -1;
    }

    /**
     * 打印菜单
     */
    private static void showMeun() {
        System.out.println("*****************************");
        System.out.println("* 1、数据采集   2、数据匹配 *");
        System.out.println("* 3、数据记录   4、数据显示 *");
        System.out.println("* 5、数据发送   0、退出应用 *");
        System.out.println("*****************************");
        System.out.println("请输入的你的选择(0~5)：");
    }

    /**
     * 数据显示
     */
    private static void dataDisplay() {
        System.out.println("请输入选项：1.数据显示(已匹配)   2.数据显示(未匹配)   3.返回");
        int userChoice = getUserChoice(1, 3);
        if (userChoice == 3) {
            return;
        } else {
            if (userChoice == 1) {
                dataDisplay_Mathed();
            } else {
                dataDisplay_UnMathed();
            }
        }
    }

    /**
     * 数据显示(已匹配)
     */
    private static void dataDisplay_Mathed() {
        System.out.println("请输入匹配数据类型：1.日志   2.物流");
        int type = getUserChoice(1, 2);
        if (type == 1) {
            if (matchedlogreclist == null || matchedlogreclist.size() == 0) {
                System.out.println("没有日志匹配数据");
            } else {
                logrecservice.showMatchLog(matchedlogreclist);
            }
        } else if (type == 2) {
            if (matchedtransportlist == null || matchedtransportlist.size() == 0) {
                System.out.println("没有物流匹配数据");
            } else {
                transportservice.showMatchTransport(matchedtransportlist);
            }
        }
    }

    /**
     * 数据显示(未匹配)
     */
    private static void dataDisplay_UnMathed() {
        System.out.println("请输入采集数据类型：1.日志   2.物流   3.返回");
        int type = getUserChoice(1, 3);
        if (type == 3) {
            return;
        } else if (type == 1) {
            if (logreclist.size() != 0) {
                logrecservice.showLog(logreclist);
            } else
                System.out.println("没有日志数据");
        } else if (type == 2) {
            if (transportlist.size() != 0) {
                transportservice.showTransport(transportlist);
            } else {
                System.out.println("没有物流数据");
            }
        }
    }

    /**
     * 数据采集
     * 
     * @throws Exception
     */
    private static void dataCollection() {
        System.out.println("请输入采集数据类型：1.日志   2.物流   3.返回");
        int type = getUserChoice(1, 3);
        if (type == 3) {
            return;
        } else {
            try {
                if (type == 1) {
                    System.out.println("正在采集日志数据，请输入正确信息，确保数据的正常采集！");
                    LogRec log = logrecservice.inputLog();
                    logreclist.add(log);
                } else if (type == 2) {
                    System.out.println("正在采集物流数据，请输入正确信息，确保数据的正常采集！");
                    Transport trans = transportservice.inputTransport();
                    transportlist.add(trans);
                }
            } catch (Exception e) {
                e.getMessage();
                System.out.println("输入异常");
            }
        }
    }

    /**
     * 数据匹配
     */
    private static void dataMatching() {
        System.out.println("请输入匹配数据类型：1.日志   2.物流");
        int type = getUserChoice(1, 2);
        if (type == 1) {
            LogRecAnalyse logrecanalyse = new LogRecAnalyse(logreclist);
            logrecanalyse.doFilter();
            matchedlogreclist = logrecanalyse.matchData();
            System.out.println("日志数据匹配成功！");
            logreclist.clear();
        } else if (type == 2) {
            TransportAnalyse transportanalyse = new TransportAnalyse();
            transportanalyse.doFilter();
            matchedtransportlist = transportanalyse.matchData();
            System.out.println("物流数据匹配成功！");
            transportlist.clear();
        } else {
            System.out.println("输入类型非法！");
        }
    }
}
