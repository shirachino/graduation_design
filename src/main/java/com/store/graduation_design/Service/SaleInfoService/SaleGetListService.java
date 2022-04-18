package com.store.graduation_design.Service.SaleInfoService;

import com.store.graduation_design.Pojo.User_sale;

import java.util.List;

public interface SaleGetListService {
    List<User_sale> getSaleList(String userName,Integer pageNow);
    Integer getSaleAll(String userName);


}
