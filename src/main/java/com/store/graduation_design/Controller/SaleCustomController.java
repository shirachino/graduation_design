package com.store.graduation_design.Controller;


import com.store.graduation_design.Pojo.Custom_chart;
import com.store.graduation_design.Pojo.Custom_statics;
import com.store.graduation_design.Service.SaleInfoService.SaleCustomService;
import com.store.graduation_design.Utils.MyFormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleCustomController {
    @Autowired
    private SaleCustomService saleCustomService;

    /**
     * @Todo 获取季度、近七日、每月顾客数
     * @param userName 用户名
     * @return 返回季度、近七日、每月顾客数和近七日x轴
     */
    @RequestMapping(value = "/getCustomList")
    public Custom_statics getCustomList(String userName) {
        Custom_statics resCustom = new Custom_statics();
        resCustom.setSeasonCustom(saleCustomService.customBySeason(userName));
        resCustom.setWeekCustom(saleCustomService.customByWeek(userName));
        resCustom.setMonthCustom(saleCustomService.customByMonth(userName));
        resCustom.setSevenDays(saleCustomService.xSevenDays());
        return resCustom;
    }

    @RequestMapping(value = "/getCustomOneMonth")
    public Custom_chart getCustomOneMonth(String userName) {
        Custom_chart needData = new Custom_chart();
        needData.setRealData(saleCustomService.customPerMonth(userName));
        needData.setNowYearMonth(MyFormatDate.yyyyMMNowMonth());
        needData.setNextYearMonth(MyFormatDate.yyyyMMNextYear());
        return needData;
    }
}
