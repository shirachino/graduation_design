package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.User_sale;

import java.util.List;

public interface SaleGetListService {
    List<User_sale> getSaleList(String userName);
}
