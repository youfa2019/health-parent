package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName CheckItemServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/2 17:48
 * @Version 1.0
 **/
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
    /**
     * @Author 传智播客
     * @Description 分页查询
     * @Date 2019/7/4 15:55
     * @Param [queryPageBean]
     * @return com.itheima.entity.PageResult<com.itheima.pojo.CheckItem>
     **/
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        //要实现模糊查询,就的拼接%
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //有查询条件时,拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //使用分页插件
        PageHelper.startPage(currentPage,queryPageBean.getPageSize());
        //紧接着的查询语句会被拦截进行分页,要查询所有
        Page<CheckItem> page=checkItemDao.findByCondition(queryPageBean.getQueryString());
        //pageResult 用pageResult包装
        PageResult<CheckItem> result = new PageResult<>(page.getTotal(), page.getResult());
        return result;
    }
    /**
     * @Author 传智播客
     * @Description 通过编号删除
     * @Date 2019/7/4 17:08
     * @Param [id]
     * @return void
     **/
    @Override
    public void delete(Integer id) {
        //判断是否有引用,当前的检查项是否有检查组在用它
        //通过查询t_checkitemgroup_checkitem where checkitem_id=要删除的编号
        int count=checkItemDao.countByCheckItemId(id);
        if (count>0){
            //有引用了,自定义异常:使用场景,终止已知不符合业务逻辑的操作继续执行
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_FAIL_USED);
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

}
