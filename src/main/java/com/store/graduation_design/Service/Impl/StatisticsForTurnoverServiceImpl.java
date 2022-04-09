package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Mapper.StatisticsForTurnoverMapper;
import com.store.graduation_design.Pojo.Turnover_type;
import com.store.graduation_design.Service.StatisticsForTurnoverService;
import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StatisticsForTurnoverServiceImpl implements StatisticsForTurnoverService {
    @Autowired
    private StatisticsForTurnoverMapper statisticsForTurnoverMapper;

    @Autowired
    private InfoDisplayMapper infoDisplayMapper;

    @Override
    public List<Turnover_type> turnoverType(String userName){
        return statisticsForTurnoverMapper.getTurnoverByType(userName);
    }

    /**
     * @param userName 用户名
     * @return 当日营业额，使用封装的函数计算
     */
    @Override
    public List<Double> turnoverPerDay(String userName){
        String nowDay = MyFormatDate.yyyyMMddNowDay();
        String nowMonth = MyFormatDate.yyyyMMNowMonth();
        String nowYear = MyFormatDate.yyyyNowYear();
        String priDay = MyFormatDate.yyyyMMddPriDay();
        List<String> jsonArrDay = infoDisplayMapper.getSalesNum(userName,nowDay);
        List<String> jsonArrMonth = infoDisplayMapper.getSalesNum(userName,nowMonth);
        List<String> jsonArrYear = infoDisplayMapper.getSalesNum(userName,nowYear);
        List<String> jsonArrPriDay = infoDisplayMapper.getSalesNum(userName,priDay);

        Double todayMinusPri = Math.abs(MyJsonUtils.jsonArrayGetSalarySum(jsonArrDay) - MyJsonUtils.jsonArrayGetSalarySum(jsonArrPriDay));
        return new LinkedList<Double>(){{
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrDay));
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrMonth));
            add(MyJsonUtils.jsonArrayGetSalarySum(jsonArrYear));
            add(todayMinusPri);
        }};
    }
}
