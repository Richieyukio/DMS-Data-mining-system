package com.qst.dms.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @Author: Richie
 * @Date: 2021/07/15
 * @LastEditTime: 2021/07/15
 * @LastEditors: Richie
 */

/**
 * 属性文件
 */
public class Config {
    private static Properties p = null;
    static {
        try {
            p = new Properties();
            //加载配置类
            p.load(new FileInputStream("config/mysql.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取配置类的参数
    public static String getValue(String key){
        return p.get(key).toString();
    }
}