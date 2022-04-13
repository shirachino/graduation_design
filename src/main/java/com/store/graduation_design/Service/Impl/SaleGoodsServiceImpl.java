package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.SaleGoodsMapper;
import com.store.graduation_design.Service.SaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDay;
import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDayWithoutLine;

@Service
public class SaleGoodsServiceImpl implements SaleGoodsService {
    @Autowired
    private SaleGoodsMapper saleGoodsMapper;

    @Override
    public void saleGoodsSer(String userName, String saleInfo) {
         String TimeMillis = System.currentTimeMillis() + "";
         String saleDate = yyyyMMddNowDay();
         String saleId = yyyyMMddNowDayWithoutLine() + TimeMillis; //根据日期＋时间戳生成唯一的订单编号
         saleGoodsMapper.saleGoods(userName,saleId,saleInfo,saleDate);
    }
}
