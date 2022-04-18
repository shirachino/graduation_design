package com.store.graduation_design.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.store.graduation_design.Mapper.StockListMapper;
import com.store.graduation_design.Pojo.Goods_out;
import com.store.graduation_design.Pojo.User_stock;
import com.store.graduation_design.Service.StockGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockGetListServiceImpl implements StockGetListService {
    @Autowired
    private StockListMapper stockListMapper;

    /**
     * @Todo 获取全部列表 20组为一页
     * @param userName 用户名
     * @param pageNow 页数
     * @return 每页商品信息列表
     */
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

    @Override
    public String stockGetListByType(String userName, Integer pageNow, String goodsType) {
        try{
            List<User_stock> stockList;
            pageNow = (pageNow - 1)*20;
            stockList = stockListMapper.getStockListByType(userName,pageNow,goodsType);
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

    @Override
    public Integer stockAllByType(String userName,String goodsType) {
        return stockListMapper.getStockAllByType(userName,goodsType);
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

    @Override
    public List<String> getAllTypeSer(String userName) {
        return stockListMapper.getAllType(userName);
    }

    @Override
    public List<Goods_out> getOutGoodsSer(String userName) {
        return stockListMapper.getOutGoods(userName);
    }

    @Override
    public List<String> getAllTypeWithoutLimitSer(String userName) {
        return stockListMapper.getAllTypeWithoutLimit(userName);
    }
}
