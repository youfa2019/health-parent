package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    //根据预约日期查询是否有预约设置信息
    Long findCountByOrderDate(Date orderDate);
    //更新可预约人数
    void editNumberByOrderDate(OrderSetting orderSetting);
    //更新已预约人数
    void editReservationsByOrderDate(OrderSetting orderSetting);
    //增加预约设置
    void add(OrderSetting orderSetting);
    //根据日期范围查询预约设置信息
    List<OrderSetting> getOrderSettingByMonth(Map date);
    //根据预约日期查询预约设置信息
    OrderSetting findByOrderDate(Date date);

}
