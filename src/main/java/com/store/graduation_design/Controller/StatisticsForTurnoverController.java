package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Mapper.StatisticsForTurnoverMapper;
import com.store.graduation_design.Pojo.Turnover_rank;
import com.store.graduation_design.Pojo.Turnover_statics;
import com.store.graduation_design.Pojo.Turnover_type;
import com.store.graduation_design.Service.SaleInfoService.SaleCustomService;
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

    @Autowired SaleCustomService saleCustomService;
    /**
     * @Todo 计算每个类型的营业额并返回
     */
    @RequestMapping(value = "/getTurnoverType")
    public List<Turnover_type> getTurnoverType(String userName){
        return statisticsForTurnoverService.turnoverType(userName);
    }

    /**
     * @Todo 统计每日、月、年营业额，计算今日相较于昨日的营业额
     */
    @RequestMapping(value = "/getTurnoverList")
    public List<Double> getDayTurnover(String userName){
        return statisticsForTurnoverService.turnoverPerDay(userName);
    }

    /**
     * @Todo 获取营业额
     */
    @RequestMapping(value = "/getTurnoverStatics")
    public Turnover_statics getSeasonTurnover(String userName){
        Turnover_statics resObj = new Turnover_statics();
        resObj.setSeasonTurnover(statisticsForTurnoverService.turnoverBySeason(userName));
        resObj.setMonthTurnover(statisticsForTurnoverService.turnoverByMonth(userName));
        resObj.setWeekTurnover(statisticsForTurnoverService.turnoverByWeek(userName));
        resObj.setSevenDays(saleCustomService.xSevenDays());
        return resObj;
    }

    @RequestMapping(value = "/getPTC")
    public Double getPTC(String userName){
        return statisticsForTurnoverService.perCustomerTransaction(userName);
    }

    @RequestMapping(value = "/getTurnoverRank")
    public List<Turnover_rank> getTurnoverRank(String userName) {
        return statisticsForTurnoverService.turnoverRank(userName);
    }
}
