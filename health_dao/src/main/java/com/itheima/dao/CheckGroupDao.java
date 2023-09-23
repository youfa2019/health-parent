package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName CheckGroupDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/4 22:32
 * @Version 1.0
 **/
public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId,@Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findByCondition(String queryString);

    CheckGroup findById(int id);

    List<Integer> getCheckItemIds(int id);

    void update(CheckGroup checkGroup);

    void deleteCheckGroupCheckItemByCheckGroupId(int checkgroupId);

    void deleteById(int id);

    int countByCheckGroupId(int id);

    List<CheckGroup> findAll();
}
