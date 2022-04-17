package com.store.graduation_design.Service;

import java.util.List;

public interface StockGetListService {
    String stockGetList(String userName,Integer pageNow);
    Integer stockAll(String userName);
    String stockOutList(String userName,Integer pageNow);
    Integer stockOutAll(String userName);
    List<String> getAllTypeSer(String userName);
}
