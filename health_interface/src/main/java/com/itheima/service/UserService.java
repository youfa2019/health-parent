package com.itheima.service;

import com.itheima.pojo.User;
/**
 * @Author 传智播客
 * @Description 用户服务接口
 * @Date 2019/7/12 17:12
 * @Param
 * @return
 **/
public interface UserService {

    User findByUsername(String username);
}
