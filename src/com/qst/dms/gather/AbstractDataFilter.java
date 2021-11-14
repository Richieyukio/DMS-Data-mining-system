package com.qst.dms.gather;

import java.util.List;

import com.qst.dms.entity.DataBase;

/**
 * @Author: Richie
 * @Date: 2021/07/11 09:48:59
 * @LastEditTime: 2021/07/13
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\gather\AbstractDataFilter.java
 */

/**
 * 创建数据过滤抽象类
 */
public abstract class AbstractDataFilter {
    
    /**
     * 数据集合
     */
    List<? extends DataBase> datas;

    /**
     * 空构造方法
     */
    public AbstractDataFilter() {
    }

    /**
     * 带参构造方法
     * @param datas
     */
    public AbstractDataFilter(List<? extends DataBase> datas) {
        this.datas = datas;
    }

    /**
     * 读取数据集合
     * @return
     */
    public List<? extends DataBase> getDatas() {
        return datas;
    }

    /**
     * 设置数据集合
     * @param datas
     */
    public void setDatas(List<? extends DataBase> datas) {
        this.datas = datas;
    }

    /**
     * 数据过滤抽象方法
     */
    public abstract void doFilter();
}