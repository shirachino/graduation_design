package com.store.graduation_design.Mapper.StockMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component(value ="stockInsertMapper")
public interface StockInsertMapper {
    @Select("insert into ${userName}" + "_stock " +
            "(goods_id,goods_name,goods_type,goods_num,goods_inPrice,goods_outPrice,goods_SHLdate,goods_EXPdate) "+
            "values (#{goodsId},#{goodsName},#{goodsType},#{goodsNum},#{goodsInPrice},#{goodsOutPrice},#{goodsSHLdate},#{goodsEXPdate})")
    void insertIntoUserStock(@Param("userName")      String userName,
                             @Param("goodsId")       Integer goodsId,
                             @Param("goodsName")     String goodsName,
                             @Param("goodsType")     String goodsType,
                             @Param("goodsNum")      Integer goodsNum,
                             @Param("goodsInPrice")  Double goodsInPrice,
                             @Param("goodsOutPrice") Double goodsOutPrice,
                             @Param("goodsSHLdate")  String goodsSHLdate,
                             @Param("goodsEXPdate")  String goodsEXPdate);
}
