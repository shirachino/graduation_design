package com.store.graduation_design.Service.StockService;

public interface StockInsertService {
    String insertIntoStock(String userName,
                           String goodsId,
                           String goodsName,
                           String goodsType,
                           Integer goodsNum,
                           Double goodsInPrice,
                           Double goodsOutPrice,
                           String goodsSHLdate,
                           String goodsEXPdate,
                           String goodsCompany);
}
