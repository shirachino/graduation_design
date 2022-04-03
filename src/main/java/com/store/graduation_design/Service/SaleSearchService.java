package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.User_sale;

import java.util.List;

public interface SaleSearchService {
    List<User_sale> searchSalesById(String userName,Integer salesId);
    List<User_sale> searchSalesByDate(String userName,String salesDate);
}
