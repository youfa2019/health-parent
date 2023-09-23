package com.itheima.dao;

import com.itheima.pojo.Role;

import java.util.Set;

/**
 * @ClassName RoleDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 17:25
 * @Version 1.0
 **/
public interface RoleDao {
     Set<Role> findByUserId(Integer userId);
}
