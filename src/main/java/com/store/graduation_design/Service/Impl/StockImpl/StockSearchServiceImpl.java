package com.store.graduation_design.Service.Impl.StockImpl;

import com.alibaba.fastjson.JSON;
import com.store.graduation_design.Mapper.StockMapper.StockSearchMapper;
import com.store.graduation_design.Pojo.User_stock;
import com.store.graduation_design.Service.StockService.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSearchServiceImpl implements StockSearchService {
    @Autowired
    private StockSearchMapper stockSearchMapper;

    @Override
    public String searchGoods(String userName,String goodsName) throws RuntimeException{
        try {
            List<User_stock> searchResult = stockSearchMapper.searchByGoodsName(userName,goodsName);
            return JSON.toJSONString(searchResult);
        } catch (RuntimeException e){
            return "HTTP Status: 500";
        }
    }

    @Override
    public User_stock searchGoodsById(String userName, String goodsId) {
        return stockSearchMapper.searchByGoodsId(userName,goodsId);
    }
}
