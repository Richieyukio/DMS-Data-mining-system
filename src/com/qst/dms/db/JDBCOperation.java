package com.qst.dms.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.qst.dms.entity.Student;

/**
 * @Author: Richie
 * @Date: 2021/07/14
 * @LastEditTime: 2021/07/15
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\db\Connection.java
 */

/**
 * 数据库操作
 */
public class JDBCOperation {
    /**
     * 获取连接
     *
     * @return
     * @throws ClassNotFoundException
     */
    private static Connection getConn_Student() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); // classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return conn;
    }

    /**
     * 插入数据
     *
     * @param student
     * @return
     */
    public static int insert_Student(Student student) {
        Connection conn = getConn_Student();
        int i = 0;
        String sql = "insert into student(id,sname,ssex,sage) values(?,?,?,?)";
        PreparedStatement pstmt = null;
        // 插入数据
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getSname());
            pstmt.setString(3, student.getSsex());
            pstmt.setInt(4, student.getSage());
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            if (conn != null) {
                try {
                    ((Statement) conn).close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        return i;
    }

    /**
     * 更新数据
     *
     * @param student
     * @return
     */
    public static int update_Student(Student student) {
        Connection conn = getConn_Student();
        int i = 0;
        String sql = "update student set sage='" + student.getSage() + "' where sname='" + student.getSname() + "'";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            if (conn != null) {
                try {
                    ((Statement) conn).close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        return i;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static Integer getAll_Student() {
        Connection conn = getConn_Student();
        String sql = "select * from student";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("=====================================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }

    /**
     * 删除数据
     *
     * @param name
     * @return
     */
    public static int delete_Student(String name) {
        Connection conn = getConn_Student();
        int i = 0;
        String sql = "delete from student where sname='" + name + "'";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            if (conn != null) {
                try {
                    ((Statement) conn).close();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        return i;
    }
}
