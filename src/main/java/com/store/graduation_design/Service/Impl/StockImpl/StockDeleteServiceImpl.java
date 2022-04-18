package com.store.graduation_design.Service.Impl.StockImpl;

import com.store.graduation_design.Mapper.StockMapper.StockDeleteMapper;
import com.store.graduation_design.Service.StockService.StockDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDeleteServiceImpl implements StockDeleteService {
    @Autowired
    private StockDeleteMapper stockDeleteMapper;

    @Override
    public String stockDelete(String userName,String goodsId) throws RuntimeException{
        try{
            stockDeleteMapper.DeleteGoodsById(userName,goodsId);
            return "200";
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return "HTTP Status: 500";
        }
    }
}
