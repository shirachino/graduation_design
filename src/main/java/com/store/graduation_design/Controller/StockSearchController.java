package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockSearchController {
    @Autowired
    private StockSearchService stockSearchService;

    @RequestMapping(value = "/searchByName")
    public String getSearchByName(String userName,String goodsName) throws RuntimeException{
        try {
            return stockSearchService.searchGoods(userName,goodsName);
        }catch (RuntimeException e){
            return "Request Time-out HTTP 408";
        }
    }
}
