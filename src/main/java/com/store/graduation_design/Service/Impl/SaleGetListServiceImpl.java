package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.SalesListMapper;
import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Service.SaleGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.List;

@Service
public class SaleGetListServiceImpl implements SaleGetListService {
    @Autowired
    private SalesListMapper salesListMapper;

    @Override
    public List<User_sale> getSaleList(String userName,Integer pageNow){
//        List<User_sale> res = salesListMapper.getSaleList(userName);
//        for(int i = 0; i <= res.size(); i++){
//            res.get(i).setSale_info() ;
//        }System.out.println(StringEscapeUtils.unescapeJava(res.get(0).getSale_info()));
        pageNow = (pageNow - 1) *15;
        return salesListMapper.getSaleList(userName,pageNow);
    }

    @Override
    public Integer getSaleAll(String userName) {
        return salesListMapper.getSaleAll(userName);
    }

}
