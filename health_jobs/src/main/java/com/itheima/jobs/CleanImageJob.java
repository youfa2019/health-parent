package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @ClassName CleanImageJob
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 16:39
 * @Version 1.0
 **/
public class CleanImageJob {

    @Autowired
    private JedisPool jedisPool;
    //清理图片任务
    public void doCleanImage(){
        //注入redis,计算两个集合的差集,就是我们要删除的图片名称
        Jedis jedis = jedisPool.getResource();
        Set<String> picsNeed2Delete=jedis.sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        //调用七牛删除服务器上的文件
        QiNiuUtil.removeFiles(picsNeed2Delete.toArray(new String[]{}));
        //移除已经删除了的文件缓存
        for (String imageName : picsNeed2Delete) {
            jedis.srem(RedisConstant.SETMEAL_PIC_RESOURCES,imageName);
        }
    }
}
