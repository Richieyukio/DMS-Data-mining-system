package com.qst.dms.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.qst.dms.db.DBUtil;
import com.qst.dms.entity.User;

/**
 * @Author: Richie
 * @Date: 2021/07/17
 * @LastEditTime: 2021/07/17
 * @LastEditors: Richie
 * @FilePath: \093119122\src\com\qst\dms\service\UserService.java
 */

/**
 * 用户业务类
 */
public class UserService {
    Connection conn = null;

    /**
     * 根据用户名查询用户，各用户的用户名不能相同
     * 
     * @param userName
     * @return
     */
    public User findUserByName(String userName) {
        // 查询语句
        String sql = "select id,username,password,sex,hobby,address,degree from user where username = ?";
        PreparedStatement ps = null;
        DBUtil dbUtil = new DBUtil();
        User users = null;

        try {
            conn = dbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                users = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return users;
    }

    /**
     * 保存用户信息
     * 
     * @param user
     * @return
     */
    public boolean saveUser(User user) {
        DBUtil dbUtil = new DBUtil();
        PreparedStatement ps = null;
        try {
            Connection conn = dbUtil.getConnection();
            String sql = "insert into user(username,password,sex,hobby,address,degree) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getSex());
            ps.setString(4, user.getHobby());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getDegree());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}