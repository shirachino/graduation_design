package com.store.graduation_design.Controller.StockController;

import com.store.graduation_design.Service.StockService.StockUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockUpdateController {
    @Autowired
    private StockUpdateService stockUpdateService;

    @RequestMapping(value = "/updateStock")
    public String updateStock(String userName,
                              String goodsId,
                              String goodsName,
                              String goodsType,
                              Integer goodsNum,
                              Double goodsInPrice,
                              Double goodsOutPrice,
                              String goodsSHLdate,
                              String goodsEXPdate,
                              String goodsCompany){
        return stockUpdateService.updateGoods(userName,
                goodsId,
                goodsName,
                goodsType,
                goodsNum,
                goodsInPrice,
                goodsOutPrice,
                goodsSHLdate,
                goodsEXPdate,
                goodsCompany);
    }
}
