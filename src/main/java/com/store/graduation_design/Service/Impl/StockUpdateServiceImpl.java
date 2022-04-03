package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.StockUpdateMapper;
import com.store.graduation_design.Service.StockUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockUpdateServiceImpl implements StockUpdateService {
    @Autowired
    private StockUpdateMapper stockUpdateMapper;

    @Override
    public String updateGoods(String userName,
                              Integer goodsId,
                              String goodsName,
                              String goodsType,
                              Integer goodsNum,
                              Double goodsInPrice,
                              Double goodsOutPrice,
                              String goodsSHLdate,
                              String goodsEXPdate) throws RuntimeException{
        try{
            stockUpdateMapper.UpdateGoodsById(
                     userName,
                     goodsId,
                     goodsName,
                     goodsType,
                     goodsNum,
                     goodsInPrice,
                     goodsOutPrice,
                     goodsSHLdate,
                     goodsEXPdate);
            return "HTTP Status: 200";
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return "Failed to update goods.HTTP Status: 500";
        }
    }

}
