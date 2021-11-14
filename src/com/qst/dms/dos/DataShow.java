package com.qst.dms.dos;

import java.util.Scanner;

/**
 * @Author: Richie
 * @Date: 2021-07-07 15:14:25
 * @LastEditTime: 2021-07-07 15:18:51
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\DataShow.java
 */

/**
 * 数据采集
 */
public class DataShow {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // 字符串数组存储收集到的10组数据
        String[] strings = new String[10];

        // 输入数据
        for (int i = 0; i < 10; i++) {
            System.out.println("收集的第" + (i + 1) + "个数据：");
            strings[i] = scanner.nextLine();
        }

        // 输出数据
        for (int i = 0; i < 10; i++) {
            if (i % 5 == 0)
                System.out.println();
            System.out.print(strings[i] + " ");
        }
        
        scanner.close();
    }
}
