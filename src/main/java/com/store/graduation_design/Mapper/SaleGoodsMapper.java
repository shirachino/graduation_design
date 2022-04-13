package com.store.graduation_design.Mapper;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value = "saleGoodsMapper")
public interface SaleGoodsMapper {
    @Update("INSERT INTO ${userName}_salerecord(sale_id,sale_info,sale_date)" +
    " VALUES (${saleId},#{saleInfo},#{saleDate})")
    void saleGoods(@Param("userName") String userName, @Param("saleId") String saleId, @Param("saleInfo") String saleInfo, @Param("saleDate") String saleDate);
}
