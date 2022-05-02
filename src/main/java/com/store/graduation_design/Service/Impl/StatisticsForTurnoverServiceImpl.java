package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Mapper.SaleCustomMapper;
import com.store.graduation_design.Mapper.StatisticsForTurnoverMapper;
import com.store.graduation_design.Pojo.Turnover_rank;
import com.store.graduation_design.Pojo.Turnover_type;
import com.store.graduation_design.Service.StatisticsForTurnoverService;
import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDay;

@Service
public class StatisticsForTurnoverServiceImpl implements StatisticsForTurnoverService {
    @Autowired
    private StatisticsForTurnoverMapper statisticsForTurnoverMapper;

    @Autowired
    private InfoDisplayMapper infoDisplayMapper;

    @Autowired
    private SaleCustomMapper saleCustomMapper;

    @Override
    public List<Turnover_type> turnoverType(String userName) {
        return statisticsForTurnoverMapper.getTurnoverByType(userName);
    }

    /**
     * @param userName 用户名
     * @return 当日营业额，使用封装的函数计算
     */
    @Override
    public List<Double> turnoverPerDay(String userName) {
        String nowDay = MyFormatDate.yyyyMMddNowDay(0);
        String nowMonth = MyFormatDate.yyyyMMNowMonth(0);
        String nowYear = MyFormatDate.yyyyNowYear();
        String priDay = MyFormatDate.yyyyMMddPriDay();
        List<String> jsonArrDay = infoDisplayMapper.getSalesNum(userName, nowDay);
        List<String> jsonArrMonth = infoDisplayMapper.getSalesNum(userName, nowMonth);
        List<String> jsonArrYear = infoDisplayMapper.getSalesNum(userName, nowYear);
        List<String> jsonArrPriDay = infoDisplayMapper.getSalesNum(userName, priDay);

        Double todayMinusPri = MyJsonUtils.jsonArrayGetSalarySum(jsonArrDay) - MyJsonUtils.jsonArrayGetSalarySum(jsonArrPriDay);
        return new LinkedList<Double>() {{
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrDay));
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrMonth));
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrYear));
            add(todayMinusPri);
        }};
    }

    @Override
    public List<Double> turnoverBySeason(String userName) {
        String nowYear = MyFormatDate.yyyyNowYear();

        List<Double> seasonTurnover = new LinkedList<Double>() {{
            add(MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoSeason1(userName, nowYear)));
            add(MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoSeason2(userName, nowYear)));
            add(MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoSeason3(userName, nowYear)));
            add(MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoSeason4(userName, nowYear)));
        }};
        return seasonTurnover;
    }

    @Override
    public List<Double> turnoverByWeek(String userName) {
        List<Double> resList = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        for (int i = 0; i < resList.size(); i++) {
            resList.set(i, MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoPerDay(userName, yyyyMMddNowDay(7 - (i + 1)))));
        }
        return resList;
    }

    @Override
    public List<Double> turnoverByMonth(String userName) {
        String nowYear = MyFormatDate.yyyyNowYear();
        List<Double> resList = Arrays.asList(new Double[12]);
        for (int i = 0; i < resList.size(); i++) {
            if (statisticsForTurnoverMapper.getSalesInfoPerMonth(userName, i + 1, nowYear) == null) {
                resList.set(i, 0.0);
            } else {
                resList.set(i, MyJsonUtils.jsonArrayGetSalarySum(statisticsForTurnoverMapper.getSalesInfoPerMonth(userName, i + 1, nowYear)));
            }
        }
        return resList;
    }

    @Override
    public Double perCustomerTransaction(String userName) {
        String nowYear = MyFormatDate.yyyyNowYear();
        List<String> jsonArrYear = infoDisplayMapper.getSalesNum(userName, nowYear);
        Double yearTurnover = MyJsonUtils.jsonArrayGetSalarySum(jsonArrYear);
        Double yearCustoms = saleCustomMapper.getCustoms(userName, nowYear).doubleValue();
        //System.out.println("T:" + yearTurnover + "C:" +yearCustoms);
        BigDecimal sumBD = new BigDecimal(yearTurnover/yearCustoms);
        return sumBD.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public List<Turnover_rank> turnoverRank(String userName) {
        return statisticsForTurnoverMapper.getTurnoverRankSql(userName);
    }
}
