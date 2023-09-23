package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

//体检预约服务接口
public interface OrderService {

    /**
     * @Author 传智播客
     * @Description 体检预约
     * @Date 2019/7/9 23:41
     * @Param [map]
     * @return com.itheima.entity.Result
     **/
    Result submitOrder(Map map) throws Exception;
    //根据id查询预约信息,包括体检人信息,套餐信息
    Result findById4Detail(int id) throws Exception;
}
