package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description
 * @Author 传智播客
 * @Date 2019/7/11 11:33
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/check")
    public Result check(HttpServletResponse response,@RequestBody Map map){
        String telephone= (String) map.get("telephone");
        String validateCode= (String) map.get("validateCode");
        //从redis中获取缓存的验证码
        String codeInRedis=jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_LOGIN+"_"+telephone);
        if (codeInRedis==null || !codeInRedis.equals(validateCode)){
            //验证码输入错误
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }else{
            //验证码输入正确
            //判断当前用户是否为会员
            Member member=memberService.findByTelephone(telephone);
            if (member==null){
                //当前用户不是会员,自动完成注册
                member=new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            //用户跟踪,写手机号码到cookie,当用户再次访问我们的网站时
            //就会带上这个cookie,这样我们就知道是哪个用户了,方便日后做统计分析
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setMaxAge(60*60*24*30);//有效期秒
            cookie.setPath("/");//设置网站的根路径,当访问到这个网站时就会带上cookie
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        }

    }

}
