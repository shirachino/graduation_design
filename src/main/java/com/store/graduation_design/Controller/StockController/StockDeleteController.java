package com.store.graduation_design.Controller.StockController;

import com.store.graduation_design.Service.StockService.StockDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockDeleteController {
    @Autowired
    private StockDeleteService stockDeleteService;

    @RequestMapping(value = "/stockDelete")
    public String goodsDelete(String userName,String goodsId){
        return stockDeleteService.stockDelete(userName,goodsId);
    }
}
