package com.store.graduation_design.Controller.StockController;

import com.store.graduation_design.Service.StockService.StockInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockInsertController {
    @Autowired
    private StockInsertService stockInsertService;

    @RequestMapping(value = "/stockInsert")
    public String goodsInsert(String userName,
                              Integer goodsId,
                              String goodsName,
                              String goodsType,
                              Integer goodsNum,
                              Double goodsInPrice,
                              Double goodsOutPrice,
                              String goodsSHLdate,
                              String goodsEXPdate) {
        if(stockInsertService.insertIntoStock(
                userName,
                goodsId,
                goodsName,
                goodsType,
                goodsNum,
                goodsInPrice,
                goodsOutPrice,
                goodsSHLdate,
                goodsEXPdate) == "200"){
            return "200";
        } else {
            return "500";
        }
    }
}
