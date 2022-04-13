package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Service.SaleDeleteRecService;
import com.store.graduation_design.Service.SaleGetListService;
import com.store.graduation_design.Service.SaleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/salesRecord")
public class SalesListController {
    //获取销售记录列表
    @Autowired
    private SaleGetListService saleGetListService;

    @RequestMapping(value = "/getSalesList")
    public List<User_sale> getSaleListControl(String userName){
        return saleGetListService.getSaleList(userName);
    }

    //删除销售记录
    @Autowired
    private SaleDeleteRecService saleDeleteRecService;

    @RequestMapping(value = "/salesDelete")
    public String deleteSaleRec(String userName,String salesId){
        return saleDeleteRecService.deleteSaleRecord(userName,salesId);
    }

    //查找销售记录by id
    @Autowired
    private SaleSearchService saleSearchService;

    @RequestMapping(value = "/salesSearchById")
    public List<User_sale> searchSaleRecById(String userName,String salesId){
        return saleSearchService.searchSalesById(userName,salesId);
    }

    @RequestMapping(value = "/salesSearchByDate")
    public List<User_sale> searchSaleRecByDate(String userName,String salesDate){
        return saleSearchService.searchSalesByDate(userName,salesDate);
    }
}
