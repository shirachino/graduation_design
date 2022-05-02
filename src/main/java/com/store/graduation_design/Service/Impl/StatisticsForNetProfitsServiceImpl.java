package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Mapper.SalesListMapper;
import com.store.graduation_design.Mapper.StatisticsForNetProfitMapper;
import com.store.graduation_design.Mapper.StatisticsForTurnoverMapper;
import com.store.graduation_design.Pojo.NetProfits_Rank;
import com.store.graduation_design.Pojo.NetProfits_type;
import com.store.graduation_design.Service.StatisticsForNetProfitsService;
import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StatisticsForNetProfitsServiceImpl implements StatisticsForNetProfitsService {
    @Autowired
    private StatisticsForNetProfitMapper statisticsForNetProfitMapper;
    @Autowired
    private InfoDisplayMapper infoDisplayMapper;

    @Override
    public List<NetProfits_Rank> getNetProfitsRankSer(String userName) {
        return statisticsForNetProfitMapper.orderByNetProfitSql(userName);
    }

    @Override
    public Double getPCRSer(String userName) {
        return statisticsForNetProfitMapper.getPCRSql(userName);
    }

    @Override
    public List<NetProfits_type> getNetProfitsTypeSer(String userName) {
        return statisticsForNetProfitMapper.typeNetProfitSql(userName);
    }

    @Override
    public List<Double> getNetProfitsByDateSer(String userName) {
        String nowDay = MyFormatDate.yyyyMMddNowDay(0);
        String nowMonth = MyFormatDate.yyyyMMNowMonth(0);
        List<String> jsonArrDay = infoDisplayMapper.getSalesNum(userName, nowDay);
        List<String> jsonArrMonth = infoDisplayMapper.getSalesNum(userName, nowMonth);
        List<String> jsonArrAll = infoDisplayMapper.getSalesAll(userName);

        return new LinkedList<Double>() {{
            add(MyJsonUtils.jsonArrayNetProfitSum(jsonArrDay));
            add(MyJsonUtils.jsonArrayNetProfitSum(jsonArrMonth));
            add(MyJsonUtils.jsonArrayNetProfitSum(jsonArrAll));
        }};
    }
}
