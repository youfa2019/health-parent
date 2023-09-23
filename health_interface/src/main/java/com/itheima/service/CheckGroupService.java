package com.itheima.service;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @ClassName CheckGroupService
 * @Description
 * @Author 传智播客
 * @Date 2019/7/4 22:23
 * @Version 1.0
 **/
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findById(int id);

    List<Integer> getCheckItemIds(int id);

    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    void deleteById(int id);

    List<CheckGroup> findAll();

}
