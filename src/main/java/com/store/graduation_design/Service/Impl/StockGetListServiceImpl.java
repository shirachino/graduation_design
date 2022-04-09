package com.store.graduation_design.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.store.graduation_design.Mapper.StockListMapper;
import com.store.graduation_design.Pojo.User_stock;
import com.store.graduation_design.Service.StockGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockGetListServiceImpl implements StockGetListService {
    @Autowired
    private StockListMapper stockListMapper;

    //获取全部列表 20组为一页
    @Override
    public String stockGetList(String userName,Integer pageNow) throws RuntimeException{
        try{
            List<User_stock> stockList;
            pageNow = (pageNow - 1)*20;
            stockList = stockListMapper.getStockListByUserName(userName,pageNow);
            return JSON.toJSONString(stockList);
        } catch (RuntimeException e){
            System.out.println("Get Stock List Failed 500 \n"+e.getMessage());
            return "Failed to get stock list HTTP Status: 500";
        }
    }

    //获取总件数
    @Override
    public Integer stockAll(String userName){
        return stockListMapper.getStockAll(userName);
    }

    //获取缺货商品列表
    @Override
    public String stockOutList(String userName,Integer pageNow) throws RuntimeException{
        try{
            List<User_stock> stockOutList;
            pageNow = (pageNow - 1)*10;
            stockOutList = stockListMapper.getStockOutList(userName,pageNow);
            return JSON.toJSONString(stockOutList);
        } catch (RuntimeException e){
            System.out.println("Get OutStock List Failed 500 \n"+e.getMessage());
            return "Failed to get outStock list HTTP Status: 500";
        }
    }

    @Override
    public Integer stockOutAll(String userName){
        return stockListMapper.getStockOutAll(userName);
    }
}
