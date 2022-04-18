package com.store.graduation_design.Mapper.StockMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value = "stockUpdateMapper")
public interface StockUpdateMapper {
    @Update("UPDATE ${userName}" + "_stock" +
    " SET goods_name = #{goodsName},goods_type = #{goodsType},goods_num = #{goodsNum},goods_inPrice = #{goodsInPrice},goods_outPrice = #{goodsOutPrice},goods_SHLdate = #{goodsSHLdate},goods_EXPdate = #{goodsEXPdate}" +
    " WHERE goods_id = #{goodsId}")
    void UpdateGoodsById(@Param("userName")      String userName,
                         @Param("goodsId")       Integer goodsId,
                         @Param("goodsName")     String goodsName,
                         @Param("goodsType")     String goodsType,
                         @Param("goodsNum")      Integer goodsNum,
                         @Param("goodsInPrice")  Double goodsInPrice,
                         @Param("goodsOutPrice") Double goodsOutPrice,
                         @Param("goodsSHLdate")  String goodsSHLdate,
                         @Param("goodsEXPdate")  String goodsEXPdate);
    }
