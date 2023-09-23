package com.itheima.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SpringSecurityUserService
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 16:47
 * @Version 1.0
 **/

@Service("securityUserService")
public class SpringSecurityUserService implements UserDetailsService {

    @Reference //注意:此处要通过dubbo远程调用用户服务
    private UserService userService;

    //根据用户名查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //远程调用用户服务,根据用户名查询用户信息
        User user=userService.findByUsername(username);
        if (user==null){
            //用户名不存在
            return null;
        }
        List<GrantedAuthority> list=new ArrayList<>();
        Set<Role> roles=user.getRoles();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                //授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return userDetails;
    }
}
