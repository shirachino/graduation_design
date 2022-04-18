package com.store.graduation_design.Service.SaleInfoService;

import java.util.List;

public interface SaleCustomService {
    List<Integer> customBySeason(String userName);
    List<Integer> customByWeek(String userName);
    List<Integer> customByMonth(String userName);
    List<String> xSevenDays();
}
