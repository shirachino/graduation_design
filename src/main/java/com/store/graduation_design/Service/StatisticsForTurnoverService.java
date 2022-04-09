package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Turnover_type;

import java.util.List;

public interface StatisticsForTurnoverService {
    List<Turnover_type> turnoverType(String userName);
    List<Double> turnoverPerDay(String userName);
}
