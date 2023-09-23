package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

/**
 * @ClassName PermissionDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 17:25
 * @Version 1.0
 **/
public interface PermissionDao {
     Set<Permission> findByRoleId(Integer roleId);
}
