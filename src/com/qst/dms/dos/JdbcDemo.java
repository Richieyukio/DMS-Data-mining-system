package com.qst.dms.dos;

import com.qst.dms.db.JDBCOperation;
import com.qst.dms.entity.Student;

/**
 * @Author: Richie
 * @Date: 2021/07/14
 * @LastEditTime: 2021/07/15
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\dos\JdbcDemo.java
 */

/**
 * 数据库操作测试
 */
public class JdbcDemo {
    public static void main(String args[]) {
        //输出
        System.out.println("原表：");
        JDBCOperation.getAll_Student();

        //插入数据
        System.out.println("插入数据后的表：");
        JDBCOperation.insert_Student(new Student(1, "Achilles", "male", 14));
        //输出
        JDBCOperation.getAll_Student();

        //修改数据
        System.out.println("修改数据后的表：");
        JDBCOperation.update_Student(new Student(1, "Bean", "", 7));
        //输出
        JDBCOperation.getAll_Student();

        //删除数据
        System.out.println("删除数据后的表：");
        JDBCOperation.delete_Student("Achilles");
        //输出
        JDBCOperation.getAll_Student();
    }
}