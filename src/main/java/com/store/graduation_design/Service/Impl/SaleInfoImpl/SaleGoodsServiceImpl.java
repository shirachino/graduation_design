package com.store.graduation_design.Service.Impl.SaleInfoImpl;

import com.alibaba.fastjson.JSONArray;
import com.store.graduation_design.Mapper.SaleGoodsMapper;
import com.store.graduation_design.Service.SaleInfoService.SaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDay;
import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDayWithoutLine;

@Service
public class SaleGoodsServiceImpl implements SaleGoodsService {
    @Autowired
    private SaleGoodsMapper saleGoodsMapper;

    @Override
    public void saleGoodsSer(String userName, String saleInfo) {
        String TimeMillis = System.currentTimeMillis() + "";
        String saleDate = yyyyMMddNowDay(0);
        String saleId = yyyyMMddNowDayWithoutLine() + TimeMillis; //根据日期＋时间戳生成唯一的订单编号
        saleGoodsMapper.saleGoods(userName, saleId, saleInfo, saleDate);
        JSONArray jsonArr = JSONArray.parseArray(saleInfo);
        for (int i = 0; i < jsonArr.size(); i++) {
            Map jsonObj = (Map) jsonArr.get(i);
            saleGoodsMapper.updateGoodsSaleNum(userName,
                    Integer.parseInt((String) jsonObj.get("sale_goodsNum")),
                    Integer.parseInt((String) jsonObj.get("sale_goodsId")));
        }
    }
}
