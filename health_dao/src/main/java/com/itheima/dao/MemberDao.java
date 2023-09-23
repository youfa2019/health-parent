package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Member;

import java.util.List;

/**
 * @ClassName MemberDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/9 23:45
 * @Version 1.0
 **/
public interface MemberDao {
    List<Member> findAll();
    Page<Member> selectByCondition(String queryString);
    void add(Member member);
    void deleteById(Integer id);
    Member findById(Integer id);
    Member findByTelephone(String telephone);
    void edit(Member member);
    Integer findMemberCountBeforeDate(String date);
    Integer findMemberCountByDate(String date);
    Integer findMemberCountAfterDate(String date);
    Integer findMemberTotalCount();
}
