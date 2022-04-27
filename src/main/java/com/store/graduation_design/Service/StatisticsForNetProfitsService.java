package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.NetProfits_Rank;
import com.store.graduation_design.Pojo.NetProfits_type;

import java.util.List;

public interface StatisticsForNetProfitsService {
    List<NetProfits_Rank> getNetProfitsRankSer(String userName);
    Double getPCRSer(String userName);
    List<NetProfits_type> getNetProfitsTypeSer(String userName);
}
