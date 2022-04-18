package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Service.SaleInfoService.SaleDeleteRecService;
import com.store.graduation_design.Service.SaleInfoService.SaleGetListService;
import com.store.graduation_design.Service.SaleInfoService.SaleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/salesRecord")
public class SalesListController {
    @Autowired
    private SaleGetListService saleGetListService;

    /**
     * @Todo 获取订单列表
     */
    @RequestMapping(value = "/getSalesList")
    public List<User_sale> getSaleListControl(String userName,Integer pageNow){
        return saleGetListService.getSaleList(userName,pageNow);
    }

    /**
     * @Todo 获取订单列表总数
     */
    @RequestMapping(value = "/getSalesAll")
    public Integer getSaleAllNum(String userName){
        return saleGetListService.getSaleAll(userName);
    }

    //删除销售记录
    @Autowired
    private SaleDeleteRecService saleDeleteRecService;

    /**
     * @Todo 删除记录byID
     */
    @RequestMapping(value = "/salesDelete")
    public String deleteSaleRec(String userName,String salesId){
        return saleDeleteRecService.deleteSaleRecord(userName,salesId);
    }

    @Autowired
    private SaleSearchService saleSearchService;

    /**
     * @Todo 按ID查找销售订单
     */
    @RequestMapping(value = "/salesSearchById")
    public List<User_sale> searchSaleRecById(String userName,String salesId){
        return saleSearchService.searchSalesById(userName,salesId);
    }

    /**
     * @Todo 按日期查找销售订单
     */
    @RequestMapping(value = "/salesSearchByDate")
    public List<User_sale> searchSaleRecByDate(String userName,String salesDate){
        return saleSearchService.searchSalesByDate(userName,salesDate);
    }
}
