package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Package;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PackageService
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 10:12
 * @Version 1.0
 **/
public interface PackageService {

    List<Map<String, Object>> findPackageCount() ;

    void add(Package pkg, Integer[] checkgroupIds);

    PageResult<Package> findPage(QueryPageBean queryPageBean);

    void deleteById(int id);

    Package findById(int id);

    List<Integer> getCheckGroupIds(int id);

    void update(Package pkg, Integer[] checkGroupIds);

    List<Package> findAll();

}
