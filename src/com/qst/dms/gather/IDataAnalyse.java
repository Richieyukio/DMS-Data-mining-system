package com.qst.dms.gather;

import java.util.List;

/**
 * @Author: Richie
 * @Date: 2021/07/11
 * @LastEditTime: 2021/07/13
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\gather\IDataAnalyse.java
 */

/**
 * 创建数据分析接口
 */
public interface IDataAnalyse {
    
   /**
    * 进行数据匹配 
    * @return Object[]
    */ 
    List<?> matchData();
}