package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CheckItemController
 * @Description 体检检查项管理
 * @Author 传智播客
 * @Date 2019/7/2 17:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {


    @Reference
    private CheckItemService checkItemService;


    //新增
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    public Result add(@RequestBody CheckItem checkItem){
        //调用业务层实现添加
        checkItemService.add(checkItem);
        //响应操作结果给前端
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    //分页查询
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckItem> pageResult=checkItemService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    //删除
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    public Result delete(Integer id){
        checkItemService.delete(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findById")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")//权限校验
    public Result findById(int id){
        CheckItem checkItem=checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    //编辑
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    public Result update(@RequestBody CheckItem checkItem){
        //调用业务层实现修改
        checkItemService.update(checkItem);
        //响应操作结果给前端
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> checkitemList=checkItemService.findAll();
        if (checkitemList!=null && checkitemList.size()>0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }
}
