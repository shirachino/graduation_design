package com.store.graduation_design.Service.StockService;

import com.store.graduation_design.Pojo.User_stock;

public interface StockSearchService {
    String searchGoods(String userName,String goodsName);
    User_stock searchGoodsById(String userName,String goodsId);
}
