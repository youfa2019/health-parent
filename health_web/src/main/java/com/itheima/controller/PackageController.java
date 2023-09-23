package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import com.itheima.util.QiNiuUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName PackageController
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 9:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/package")
public class PackageController {
    @Reference
    private PackageService packageService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
        //原文件名
        String originalFilename=imgFile.getOriginalFilename();
        //文件的扩展名
        int lastIndexOf=originalFilename.lastIndexOf(".");
        String filesuffix=originalFilename.substring(lastIndexOf);
        //生成新的文件名
        String newFilename= UUID.randomUUID()+filesuffix;
        try {
            //调用七牛上传文件
            QiNiuUtil.uploadViaByte(imgFile.getBytes(), newFilename);
            //图片的链接地址
            String imgUrl=QiNiuUtil.DOMAIN+"/"+newFilename;
            //返回图片的链接地址,回显图片
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);

    }

    @PostMapping("/add")
    public Result add(@RequestBody Package pkg,Integer[] checkgroupIds){
        packageService.add(pkg,checkgroupIds);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Package> pageResult=packageService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }

    @PostMapping("/delete")
    public Result deleteById(int id){
        packageService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Package pkg,Integer[] checkGroupIds){
        System.out.println(checkGroupIds);
        packageService.update(pkg,checkGroupIds);
        return new Result(true,MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(int id){
       Package pkg=packageService.findById(id);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pkg);
    }

    @GetMapping("/getCheckGroupIds")
    public Result getCheckGroupIds(int id){
        List<Integer> checkGroupIds=packageService.getCheckGroupIds(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupIds);
    }
}
