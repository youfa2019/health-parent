package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 17:14
 * @Version 1.0
 **/
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        /*User user=userDao.findByUsername(username);
        if (user==null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles=roleDao.findByUserId(userId);
        if (roles!=null && roles.size()>0){
            for (Role role : roles) {
                Integer roleId = role.getId();
                Set<Permission> permissions=permissionDao.findByRoleId(roleId);
                if (permissions!=null && permissions.size()>0){
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;*/
        return userDao.findUserRolePermissionByUsername(username);
    }
}
