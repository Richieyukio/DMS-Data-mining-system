package com.qst.dms.entity;

/**
 * @Author: Richie
 * @Date: 2021/07/14
 * @LastEditTime: 2021/07/14
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\entity\Student.java
 */

/**
 * 学生类
 */
public class Student {

    /**
     * id
     */
    private int id;

    /**
     * 姓名
     */
    private String sname;

    /**
     * 性别
     */
    private String ssex;

    /**
     * 年龄
     */
    private int sage;

    /**
     * 空构造方法
     */
    public Student() {
    }

    /**
     * 带参构造方法
     * 
     * @param id
     * @param sname
     * @param ssex
     * @param sage
     */
    public Student(int id, String sname, String ssex, int sage) {
        this.id = id;
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
    }

    /**
     * 读取id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", sage=" + sage + ", sname=" + sname + ", ssex=" + ssex + "]";
    }

}