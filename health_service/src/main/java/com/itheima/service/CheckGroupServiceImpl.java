package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName CheckGroupServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/4 22:25
 * @Version 1.0
 **/
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;


    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //dao添加检查组到数据
        checkGroupDao.add(checkGroup);
        //获取检查组的编号
        Integer checkGroupId=checkGroup.getId();
        System.out.println("checkGroupId="+checkGroupId);
        //设置检查组与检查项的关系
        if (null!=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.setCheckGroupAndCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //实现模糊查询,拼接%
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //紧接着的查询会被分页
        Page<CheckGroup> page=checkGroupDao.findByCondition(queryPageBean.getQueryString());

        //封装返回的结果
        PageResult<CheckGroup> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> getCheckItemIds(int id) {
        return checkGroupDao.getCheckItemIds(id);
    }

    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组信息
        checkGroupDao.update(checkGroup);
        //更新检查组与检查项的关系
        //先删除关系 delete from t_checkgroup_checkitem where checkgroup_id=checkgroupId
        //检查组的编号
        int checkgroupId = checkGroup.getId();
        checkGroupDao.deleteCheckGroupCheckItemByCheckGroupId(checkgroupId);
        //再添加新的关系checkitemIds
        //非空判断,遍历检查项的id数组,往t_checkgroup_checkitem添加记录
        if (null!=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                //设置检查组与检查项的关系
                checkGroupDao.setCheckGroupAndCheckItem(checkgroupId,checkitemId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int count=checkGroupDao.countByCheckGroupId(id);
        if (count>0){
            //有引用了,自定义异常:删除检查组失败,当前检查组已被使用
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_FAIL_USED);
        }
        checkGroupDao.deleteCheckGroupCheckItemByCheckGroupId(id);
        checkGroupDao.deleteById(id);

    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


}
