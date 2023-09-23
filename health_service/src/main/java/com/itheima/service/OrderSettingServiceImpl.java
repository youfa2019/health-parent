package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderSettingServiceImpl
 * @Description 预约设置服务
 * @Author 传智播客
 * @Date 2019/7/6 22:30
 * @Version 1.0
 **/
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    //批量添加
    public void add(List<OrderSetting> list) {
        if (list!=null && list.size()>0){
            for (OrderSetting orderSetting : list) {
                //检查此数据(日期)是否存在
                Long count=orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count>0){
                    //已经存在,执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    //不存在,执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }

        }
    }

    @Override
    //根据日期查询预约设置数据
    public List<Map> getOrderSettingByMonth(String date) {
        String dateBegin=date+"-1";
        String dateEnd=date+"-31";
        HashMap<Object, Object> map = new HashMap<>();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list=orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data=new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            HashMap<Object, Object> orderSettingMap = new HashMap<>();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期(几号)
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    @Override
    //根据日期修改可预约人数
    public void editNumberByDate(OrderSetting orderSetting) {
        Long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            //当前日期已经进行了预约设置,需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置,进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
