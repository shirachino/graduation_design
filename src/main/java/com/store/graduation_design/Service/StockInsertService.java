package com.store.graduation_design.Service;

public interface StockInsertService {
    String insertIntoStock(String userName,
                           Integer goodsId,
                           String goodsName,
                           String goodsType,
                           Integer goodsNum,
                           Double goodsInPrice,
                           Double goodsOutPrice,
                           String goodsSHLdate,
                           String goodsEXPdate);
}
