package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SecurityService
 * @Description
 * @Author 传智播客
 * @Date 2019/7/12 11:04
 * @Version 1.0
 **/
@Service("userService")
public class SecurityService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名查询数据库,获得User
        User user=findUserByUname(username);
        //2.判断是否为null
        if (user==null){
            return null;
        }
        //3.把用户名,数据库的密码,以及查询出来的权限访问,创建UserDetails对象返回
        List<GrantedAuthority> list=new ArrayList<>();//先把角色和权限写死,后面再从数据库查询出来
        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("delete"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        org.springframework.security.core.userdetails.User userDetails=
                new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return userDetails;
    }

    //模拟从数据库查询
    private User findUserByUname(String username){
        if ("admin".equals(username)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(encoder.encode("123456"));
            return user;
        }
        return null;
    }
}
