package com.store.graduation_design.Service.StockService;

public interface StockUpdateService {
    String updateGoods(String userName,
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
