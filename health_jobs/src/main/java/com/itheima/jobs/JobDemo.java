package com.itheima.jobs;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName JobDemo
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 16:05
 * @Version 1.0
 **/
public class JobDemo {

    public void run(){
        System.out.println("hello world");
    }

    public void run2(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date())+":hello world");
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-job.xml");
    }
}
