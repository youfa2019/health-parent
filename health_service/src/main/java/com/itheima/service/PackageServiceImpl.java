package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PackageDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PackageServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 10:14
 * @Version 1.0
 **/
@Service(interfaceClass = PackageService.class)
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDao packageDao;

    //套餐占比统计
    @Override
    public List<Map<String, Object>> findPackageCount() {
        return packageDao.findPackageCount();
    }

    @Override
    @Transactional
    public void add(Package pkg, Integer[] checkgroupIds) {
        //dao添加套餐到数据
        packageDao.add(pkg);
        //获取套餐的编号
        Integer pkgId = pkg.getId();
        //设置套餐与检查组的关系
        if (null!=checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                packageDao.setPackageAndCheckGroup(pkgId,checkgroupId);
            }
        }
    }

    @Override
    public PageResult<Package> findPage(QueryPageBean queryPageBean) {
        //实现模糊查询,拼接%
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //紧接着的查询会被分页
        Page<Package> page=packageDao.findByCondition(queryPageBean.getQueryString());
        //封装返回的结果
        PageResult<Package> pageResult=new PageResult<>(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        packageDao.deletePackageCheckGroupByPackageId(id);
        packageDao.deleteById(id);
    }

    @Override
    public Package findById(int id) {
        return packageDao.findById(id);
    }

    @Override
    public List<Integer> getCheckGroupIds(int id) {
        return packageDao.getCheckGroupIds(id);
    }

    @Override
    @Transactional
    public void update(Package pkg, Integer[] checkGroupIds) {
        //更新套餐信息
        packageDao.update(pkg);
        //更新套餐与检查组的关系
        //先删除关系
        //套餐的编号
        int packageId=pkg.getId();
        packageDao.deletePackageCheckGroupByPackageId(packageId);
        //再添加新的关系checkgroupIds
        //分控判断,遍历检查组的id数组,往t_package_checkgroup添加记录
        if (null!=checkGroupIds){
            for (Integer checkGroupId : checkGroupIds) {
                //设置套餐与检查组的关系
                packageDao.setPackageAndCheckGroup(packageId,checkGroupId);
            }
        }
    }

    @Override
    public List<Package> findAll() {
        return packageDao.findAll();
    }
}
