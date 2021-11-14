package com.qst.dms.dos;

import java.util.Scanner;

/**
 * @Author: Richie
 * @Date: 2021-07-07 15:01:19
 * @LastEditTime: 2021-07-07 17:43:31
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\DataInput.java
 */

/**
 * 数据采集
 */
public class DataInput {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // 字符串数组存储收集到的4组数据
        String[] strings = new String[4];

        // 输入数据
        for (int i = 0; i < 4; i++) {
            System.out.println("收集的第" + (i + 1) + "个数据：");
            strings[i] = scanner.nextLine();
        }

        // 输出数据
        for (int i = 0; i < 4; i++) {
            System.out.print("第" + (i + 1) + "个数据是：");
            System.out.println(strings[i]);
        }
        
        scanner.close();
    }
}
