package com.store.graduation_design.Service;

public interface StockGetListService {
    String stockGetList(String userName,Integer pageNow);
    Integer stockAll(String userName);
    String stockOutList(String userName,Integer pageNow);
    Integer stockOutAll(String userName);
}
