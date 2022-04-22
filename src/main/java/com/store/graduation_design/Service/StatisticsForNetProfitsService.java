package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.NetProfits_Rank;

import java.util.List;

public interface StatisticsForNetProfitsService {
    List<NetProfits_Rank> getNetProfitsRankSer(String userName);
    Double getPCRSer(String userName);
}
