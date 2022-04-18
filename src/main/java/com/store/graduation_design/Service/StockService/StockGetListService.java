package com.store.graduation_design.Service.StockService;

import com.store.graduation_design.Pojo.Goods_out;

import java.util.List;

public interface StockGetListService {
    String stockGetList(String userName,Integer pageNow);
    String stockGetListByType(String userName,Integer pageNow,String goodsType);
    Integer stockAll(String userName);
    Integer stockAllByType(String userName,String goodsType);
    String stockOutList(String userName,Integer pageNow);
    Integer stockOutAll(String userName);
    List<String> getAllTypeSer(String userName);
    List<Goods_out> getOutGoodsSer(String userName);
    List<String> getAllTypeWithoutLimitSer(String userName);
}
