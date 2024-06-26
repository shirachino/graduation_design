package com.store.graduation_design.Service.StockService;

import com.store.graduation_design.Pojo.Goods_out;
import com.store.graduation_design.Pojo.User_stock_exp;

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
    String addOutGoodsSer(String userName,Integer addNum,Integer goodsId);
    List<User_stock_exp> getExpiredListSer(String userName);
}
