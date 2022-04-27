package com.store.graduation_design.Service.Impl.StockImpl;

import com.store.graduation_design.Mapper.StockMapper.StockInsertMapper;
import com.store.graduation_design.Service.StockService.StockInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockInsertServiceImpl implements StockInsertService {
    @Autowired
    private StockInsertMapper stockInsertMapper;
    @Override
    public String insertIntoStock(String userName,
                                  String goodsId,
                                  String goodsName,
                                  String goodsType,
                                  Integer goodsNum,
                                  Double goodsInPrice,
                                  Double goodsOutPrice,
                                  String goodsSHLdate,
                                  String goodsEXPdate,
                                  String goodsCompany) throws RuntimeException
    {
        try{
            stockInsertMapper.insertIntoUserStock(
                    userName,
                    goodsId,
                    goodsName,
                    goodsType,
                    goodsNum,
                    goodsInPrice,
                    goodsOutPrice,
                    goodsSHLdate,
                    goodsEXPdate,
                    goodsCompany);
            return "200";
        } catch (RuntimeException e){
            System.out.println(e);
            return "500";
        }
    };
}
