package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Mapper.StatisticsForTurnoverMapper;
import com.store.graduation_design.Pojo.Turnover_type;
import com.store.graduation_design.Service.StatisticsForTurnoverService;
import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StatisticsForTurnoverController {
    @Autowired
    private StatisticsForTurnoverService statisticsForTurnoverService;

    @RequestMapping(value = "/getTurnoverType")
    public List<Turnover_type> getTurnoverType(String userName){
        return statisticsForTurnoverService.turnoverType(userName);
    }

    @RequestMapping(value = "/getDayTurnover")
    public List<Double> getDayTurnover(String userName){
        return statisticsForTurnoverService.turnoverPerDay(userName);
    }
}
