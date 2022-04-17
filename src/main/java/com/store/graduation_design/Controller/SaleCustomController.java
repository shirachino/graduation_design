package com.store.graduation_design.Controller;


import com.store.graduation_design.Pojo.Custom_statics;
import com.store.graduation_design.Service.SaleCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleCustomController {
    @Autowired
    private SaleCustomService saleCustomService;

    @RequestMapping(value = "/getSeasonCustom")
    public Custom_statics getSeasonCustom(String userName) {
        Custom_statics resCustom = new Custom_statics();
        resCustom.setSeasonCustom(saleCustomService.customBySeason(userName));
        resCustom.setWeekCustom(saleCustomService.customByWeek(userName));
        resCustom.setMonthCustom(saleCustomService.customByMonth(userName));
        resCustom.setSevenDays(saleCustomService.xSevenDays());
        return resCustom;
    }
}
