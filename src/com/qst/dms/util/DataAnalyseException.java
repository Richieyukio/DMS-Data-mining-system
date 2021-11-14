package com.qst.dms.util;

/**
 * @Author: Richie
 * @Date: 2021/07/12
 * @LastEditTime: 2021/07/12
 * @LastEditors: Richie
 */

/**
 * 自定义数据分析异常类DataAnalyseException，数据分析处理过程中抛出自定义异常
 */
public class DataAnalyseException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * 空构造方法
     */
    public DataAnalyseException() {
    }

    /**
     * 带参构造方法
     * @param msg
     */
	public DataAnalyseException(String msg) {
		super(msg);
	}
}