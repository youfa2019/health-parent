package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MemberServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/11 23:05
 * @Version 1.0
 **/
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    //新增会员
    @Override
    public void add(Member member) {
        memberDao.add(member);
    }
    //根据手机号查询会员
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //根据月份统计会员数量
    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list=new ArrayList<>();
        for (String m : month) {
            m=m+"-31";//格式:2019-04-31
           Integer count=memberDao.findMemberCountBeforeDate(m);
           list.add(count);
        }

        return list;
    }
}
