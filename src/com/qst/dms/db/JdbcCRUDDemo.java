package com.qst.dms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * @Author: Richie
 * @Date: 2021/07/14
 * @LastEditTime: 2021/07/15
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\db\JdbcCRUDDemo.java
 */

/**
 * 进行JDBC的数据库基本操作
 */

public class JdbcCRUDDemo {

    public static void main(String[] args) throws Exception {
        //要连接的数据库URL
        String url = "jdbc:mysql://localhost:3306/student";
        //连接的数据库时使用的用户名
        String username = "root";
        //连接的数据库时使用的密码
        String password = "";
        
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取与数据库的链接
        Connection conn = DriverManager.getConnection(url, username, password);
        
        //定义sql建表语句
        String sqlCreate = "create table matched_logrec(loginid integer,logoutid integer);";

        //定义数据库数据更新语句
        // String sqlInsert = "INSERT\n" +
        //         "INTO student\n" +
        //         "VALUES(1,'Ender','male',8 ),(2,'Bean','male',7),(3,'Petra','fema',9),(4,'peter','male',9),(5,'_Graff','male',40),(6,'GOD','fema',255);";

        //3.获取用于向数据库发送sql语句的statement
        Statement st = conn.createStatement();
        
        //执行sql语句
        int count1 = st.executeUpdate(sqlCreate);
        // int count2 = st.executeUpdate(sqlInsert);

        //处理结果
        System.out.println(count1);
        
        
        //6.关闭链接，释放资源
        st.close();
        st.close();
        conn.close();
    }
}
