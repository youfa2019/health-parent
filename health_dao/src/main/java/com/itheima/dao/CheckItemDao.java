package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    void add(CheckItem checkItem);

    Page<CheckItem> findByCondition(String queryString);

    int countByCheckItemId(Integer id);

    void deleteById(Integer id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);

    List<CheckItem> findAll();
}
