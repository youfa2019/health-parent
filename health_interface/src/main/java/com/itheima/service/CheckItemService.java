package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    void add(CheckItem checkItem);


    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);

    List<CheckItem> findAll();
}
