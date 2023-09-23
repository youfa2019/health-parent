package com.itheima.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.PackageDao;
import com.itheima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ReportServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 2019/7/16 8:36
 * @Version 1.0
 **/
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private PackageDao packageDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Map<String, Object> getBusinessReportData() {
        Date date=new Date();
        //获得当前日期
        String today= DateUtils.parseDate2String(date,"yyyy-MM-dd");
        //星期一
        String monday=DateUtils.parseDate2String(DateUtils.getThisWeekMonday(),"yyyy-MM-dd");
        //一号
        String firstDayOfThisMonth=DateUtils.parseDate2String(DateUtils.getFirstDayOfThisMonth(),"yyyy-MM-dd");
        //星期天
        String sunday=DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek(),"yyyy-MM-dd");
        //本月最后一天
        String lastDayOfThisMonth=DateUtils.parseDate2String(DateUtils.getLastOfThisMonth(),"yyyy-MM-dd");
        //本日新增会员数
        Integer todayNewMember=memberDao.findMemberCountAfterDate(today);
        //会员总数
        Integer totalMember=memberDao.findMemberTotalCount();
        //本周新增会员数
        Integer thisWeekNewMember=memberDao.findMemberCountAfterDate(monday);
        //本月新增会员数
        Integer thisMonthNewMember=memberDao.findMemberCountAfterDate(firstDayOfThisMonth);
        //本日预约
        Integer todayOrderNumber=orderDao.findOrderCountByDate(today);
        //本日到诊数
        Integer todayVisitsNumber=orderDao.findVisitCountByDate(today);
        //本周预约数
        Integer thisWeekOrderNumber=orderDao.findOrderCountBetweenDate(monday,sunday);
        //本周到诊数
        Integer thisWeekVisitsNumber=orderDao.findVisitCountAfterDate(monday);
        //本月预约数
        Integer thisMonthOrderNumber=orderDao.findOrderCountBetweenDate(firstDayOfThisMonth,lastDayOfThisMonth);
        //本月到诊数
        Integer thisMonthVisitsNumber=orderDao.findVisitCountAfterDate(firstDayOfThisMonth);
        //热门套餐
        List<Map<String,Object>> hotPackage= packageDao.getHotPackages();

        //返回到controller,封装到map中
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("reportDate",today);
        resultMap.put("todayNewMember",todayNewMember);
        resultMap.put("totalMember",totalMember);
        resultMap.put("thisWeekNewMember",thisWeekNewMember);
        resultMap.put("thisMonthNewMember",thisMonthNewMember);
        resultMap.put("todayOrderNumber",todayOrderNumber);
        resultMap.put("todayVisitsNumber",todayVisitsNumber);
        resultMap.put("thisWeekOrderNumber",thisWeekOrderNumber);
        resultMap.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        resultMap.put("thisMonthOrderNumber",thisMonthOrderNumber);
        resultMap.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        resultMap.put("hotPackage",hotPackage);
        return resultMap;
    }
}
