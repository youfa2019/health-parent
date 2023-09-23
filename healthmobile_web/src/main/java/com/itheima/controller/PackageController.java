package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Package;
import com.itheima.service.PackageService;
import com.itheima.util.QiNiuUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PackageController
 * @Description
 * @Author 传智播客
 * @Date 2019/7/8 20:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/package")
public class PackageController {

    @Reference
    private PackageService packageService;

    @RequestMapping("/getPackage")
    public Result getPackage(){
        try {
            List<Package> packageList=packageService.findAll();
            //设置图片的链接地址
            packageList.forEach(pkg->{
                pkg.setImg(QiNiuUtil.DOMAIN+"/"+pkg.getImg());
            });
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,packageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @GetMapping("/findById")
    public Result findById(int id){
        try {
            Package pkg = packageService.findById(id);
            pkg.setImg(QiNiuUtil.DOMAIN+"/"+pkg.getImg());
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pkg);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}