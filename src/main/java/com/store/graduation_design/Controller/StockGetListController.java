package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.StockGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockGetListController {
    @Autowired
    private StockGetListService stockGetListService;

    @RequestMapping(value = "/getStockList")
    public String getStockList(String userName){
        return stockGetListService.stockGetList(userName);
    }
}
