package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import com.itheima.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @ClassName OrderController
 * @Description
 * @Author 传智播客
 * @Date 2019/7/9 23:22
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/submit")
    public Result submitOrder(@RequestBody Map map){
        String telephone= (String) map.get("telephone");
        //从redis中获取缓存的验证码,key为手机号+RedisConstant.SENDTYPE_ORDER
        String codeInRedis=jedisPool.getResource().get( RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone);
        String validateCode= (String) map.get("validateCode");
        //校验手机验证码
        if (codeInRedis==null || !codeInRedis.equals(validateCode)){
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }
        Result result=null;
        //调用体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result=orderService.submitOrder(map);
        } catch (Exception e) {
            e.printStackTrace();
            //预约失败
            return result;
        }
        if (result.isFlag()){
            //预约成功,发送短信通知
            String orderDate= (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate );
            } catch (ClientException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @PostMapping("/findById")
    public Result findById(int id){
        Result result=null;
        try {
            result=orderService.findById4Detail(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,result.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }

}
