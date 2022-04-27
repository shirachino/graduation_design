package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.StatisticsForNetProfitMapper;
import com.store.graduation_design.Pojo.NetProfits_Rank;
import com.store.graduation_design.Pojo.NetProfits_type;
import com.store.graduation_design.Service.StatisticsForNetProfitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsForNetProfitsServiceImpl implements StatisticsForNetProfitsService {
    @Autowired
    private StatisticsForNetProfitMapper statisticsForNetProfitMapper;

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
}
