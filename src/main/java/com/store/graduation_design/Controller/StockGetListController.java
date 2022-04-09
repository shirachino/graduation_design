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
    public String getStockList(String userName ,Integer pageNow){
        return stockGetListService.stockGetList(userName,pageNow);
    }

    @RequestMapping(value = "/getStockAll")
    public Integer getStockAll(String userName){
        return stockGetListService.stockAll(userName);
    }

    @RequestMapping(value = "/getStockOut")
    public String getStockOutList(String userName ,Integer pageNow){
        return stockGetListService.stockOutList(userName,pageNow);
    }

    @RequestMapping(value = "/getStockOutAll")
    public Integer getStockOutAll(String userName){
        return stockGetListService.stockOutAll(userName);
    }

}