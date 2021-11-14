package com.qst.dms.entity;

/**
 * @Author: Richie
 * @Date: 2021/07/17
 * @LastEditTime: 2021/07/17
 * @LastEditors: Richie
 */

/**
 * 用户实体类
 */
public class User {

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private int sex;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 地址
     */
    private String address;

    /**
     * 学历
     */
    private String degree;

    /**
     * 空构造方法
     */
    public User() {
    }

    /**
     * 带参构造方法
     * @param username
     * @param password
     * @param sex
     * @param hobby
     * @param address
     * @param degree
     */
    public User(String username, String password, int sex, String hobby, String address, String degree) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
        this.degree = degree;
    }

    /**
     * 带参构造方法
     * @param id
     * @param username
     * @param password
     * @param sex
     * @param hobby
     * @param address
     * @param degree
     */
    public User(int id, String username, String password, int sex, String hobby, String address, String degree) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
        this.degree = degree;
    }
    
    /**
     * 读取id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id
     * @param id
     */
	public void setId(int id) {
		this.id = id;
	}
    
    /**
     * 读取用户名
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 读取密码
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "User [address=" + address + ", degree=" + degree + ", hobby=" + hobby + ", password=" + password
                + ", sex=" + sex + ", username=" + username + "]";
    }
}

	