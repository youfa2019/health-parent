package com.itheima.controller;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.util.SMSUtils;
import com.itheima.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName ValidateCodeController
 * @Description
 * @Author 传智播客
 * @Date 2019/7/9 23:03
 * @Version 1.0
 **/

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/send4Order")
    public Result send4Order(String telephone){
        String key=RedisMessageConstant.SENDTYPE_ORDER+"_"+telephone;
        //判断是否发送过了,通过key(手机号码)获取redis中的验证码
        Jedis jedis = jedisPool.getResource();
        //redis中的验证码
        String codeInRedis = jedis.get(key);
        if (!StringUtils.isEmpty(codeInRedis)){
            return new Result(false,MessageConstant.SENT_VALIDATECODE_SUCCESS);
        }
        //不存在,生成验证码,再发送,存入redis中设置有效期(5mins)
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码

        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
            //存入redis
            jedis.setex(key,5*60,code.toString());
            //返回结果给前端
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    //手机快速登录时发送验证码
    @PostMapping("/send4Login")
    public Result send4Login(String telephone){
        //判断是否已经发送过了
        //看redis中是否存在
        Jedis jedis = jedisPool.getResource();
        String key=RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone;
        if (null !=jedis.get(key)){
            //发送了
            return new Result(false,MessageConstant.SENT_VALIDATECODE_SUCCESS);
        }
        //生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
            System.out.println("发送的手机验证码为:"+code);
            //将验证码缓存到redis中
            jedisPool.getResource().setex(RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone,5*60,code.toString());
            //验证码发送成功
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }


    }


}
