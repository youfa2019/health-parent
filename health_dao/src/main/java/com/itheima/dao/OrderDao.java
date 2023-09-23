package com.itheima.dao;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/9 23:44
 * @Version 1.0
 **/
public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findById4Detail(int id);

    Integer findOrderCountByDate(String today);

    Integer findVisitCountByDate(String today);

    Integer findVisitCountAfterDate(String monday);

    Integer findOrderCountBetweenDate(@Param("startDate") String monday, @Param("endDate")String sunday);
}
