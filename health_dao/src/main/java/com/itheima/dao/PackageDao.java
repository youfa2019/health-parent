package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Package;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PackageDao
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 10:19
 * @Version 1.0
 **/
public interface PackageDao {
     List<Map<String, Object>> getHotPackages();

    void add(Package pkg);

    void setPackageAndCheckGroup(@Param("pkgId") Integer pkgId,@Param("checkgroupId") Integer checkgroupId);

    Page<Package> findByCondition(String queryString);

    void deleteById(int id);

    void deletePackageCheckGroupByPackageId(int id);

    Package findById(int id);

    List<Integer> getCheckGroupIds(int id);

    void update(Package pkg);

    List<Package> findAll();

    List<Map<String, Object>> findPackageCount();
}
