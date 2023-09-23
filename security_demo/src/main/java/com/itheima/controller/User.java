package com.itheima.controller;

/**
 * @ClassName User
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 15:08
 * @Version 1.0
 **/
public class User {

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
