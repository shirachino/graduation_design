package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.*;
import com.store.graduation_design.Pojo.Analysis_data;
import com.store.graduation_design.Pojo.Analysis_sug;
import com.store.graduation_design.Service.AnalysisService;
import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private RankForGoodsMapper rankForGoodsMapper;

    @Autowired
    private StatisticsForTurnoverMapper statisticsForTurnoverMapper;

    @Autowired
    private StatisticsForNetProfitMapper statisticsForNetProfitMapper;

    @Autowired
    private InfoDisplayMapper infoDisplayMapper;

    @Autowired
    private SaleCustomMapper saleCustomMapper;

    @Override
    public Analysis_data getAnalysisSer(String userName) {
        Analysis_data analysisData = new Analysis_data();
        analysisData.setSaleNumTop(rankForGoodsMapper.getTotalRank(userName).get(0));
        analysisData.setTurnoverTop(statisticsForTurnoverMapper.getTurnoverRankSql(userName).get(0));
        analysisData.setNetProfitTop(statisticsForNetProfitMapper.orderByNetProfitSql(userName).get(0));

        analysisData.setSaleNumLast(rankForGoodsMapper.getSaleNumLastOne(userName).get(0));
        analysisData.setTurnoverLast(statisticsForTurnoverMapper.getTurnoverLastSql(userName).get(0));
        analysisData.setNetProfitLast(statisticsForNetProfitMapper.netProfitLastSql(userName).get(0));

        List<Double> thisMonth = new ArrayList<>();
        thisMonth.add(0, MyJsonUtils.jsonArrayGetSalarySum(infoDisplayMapper.getSalesNum(userName, MyFormatDate.yyyyMMNowMonth(0))));
        thisMonth.add(1, Double.parseDouble(saleCustomMapper.getCustoms(userName, MyFormatDate.yyyyMMNowMonth(0)).toString()));
        thisMonth.add(2, MyJsonUtils.jsonArrayNetProfitSum(infoDisplayMapper.getSalesNum(userName, MyFormatDate.yyyyMMNowMonth(0))));
        analysisData.setThisMonthData(thisMonth);

        List<Double> lastMonth = new ArrayList<>();
        lastMonth.add(0, MyJsonUtils.jsonArrayGetSalarySum(infoDisplayMapper.getSalesNum(userName, MyFormatDate.yyyyMMNowMonth(1))));
        lastMonth.add(1, Double.parseDouble(saleCustomMapper.getCustoms(userName, MyFormatDate.yyyyMMNowMonth(1)).toString()));
        lastMonth.add(2, MyJsonUtils.jsonArrayNetProfitSum(infoDisplayMapper.getSalesNum(userName, MyFormatDate.yyyyMMNowMonth(1))));
        analysisData.setLastMonthData(lastMonth);

        return analysisData;
    }

    @Override
    public Analysis_sug getSuggestSer(String userName) {
        Analysis_sug analysis_sug = new Analysis_sug();
        analysis_sug.setSaleTop(rankForGoodsMapper.getTotalRank(userName));
        analysis_sug.setSaleLast(rankForGoodsMapper.getSaleNumLastOne(userName));
        analysis_sug.setTurnoverLast(statisticsForTurnoverMapper.getTurnoverLastSql(userName));
        return analysis_sug;
    }
}
