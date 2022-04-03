package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.SalesListMapper;
import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Service.SaleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleSearchServiceImpl implements SaleSearchService {
    @Autowired
    private SalesListMapper salesListMapper;

    @Override
    public List<User_sale> searchSalesById(String userName, Integer salesId){
        return salesListMapper.searchSalesById(userName,salesId);
    }

    @Override
    public List<User_sale> searchSalesByDate(String userName,String salesDate){
    return salesListMapper.searchSalesByDate(userName,salesDate);
    }
}
