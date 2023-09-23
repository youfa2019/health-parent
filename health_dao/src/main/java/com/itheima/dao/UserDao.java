package com.itheima.dao;

import com.itheima.pojo.User;

/**
 * @ClassName UserDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 17:25
 * @Version 1.0
 **/
public interface UserDao {

     User findByUsername(String username);

     User findUserRolePermissionByUsername(String username);
}
