package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Turnover_rank;
import com.store.graduation_design.Pojo.Turnover_type;

import java.util.List;

public interface StatisticsForTurnoverService {
    List<Turnover_type> turnoverType(String userName);
    List<Double> turnoverPerDay(String userName);
    List<Double> turnoverBySeason(String userName);
    List<Double> turnoverByWeek(String userName);
    List<Double> turnoverByMonth(String userName);
    Double perCustomerTransaction(String userName);
    List<Turnover_rank> turnoverRank(String userName);
}
