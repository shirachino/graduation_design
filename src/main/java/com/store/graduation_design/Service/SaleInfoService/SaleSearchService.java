package com.store.graduation_design.Service.SaleInfoService;

import com.store.graduation_design.Pojo.User_sale;

import java.util.List;

public interface SaleSearchService {
    List<User_sale> searchSalesById(String userName,String salesId);
    List<User_sale> searchSalesByDate(String userName,String salesDate);
}
