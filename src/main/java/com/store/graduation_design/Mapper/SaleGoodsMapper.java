package com.store.graduation_design.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value = "saleGoodsMapper")
public interface SaleGoodsMapper {
    @Update("INSERT INTO ${userName}_salerecord(sale_id,sale_info,sale_date)" +
    " VALUES (${saleId},#{saleInfo},#{saleDate})")
    void saleGoods(@Param("userName") String userName, @Param("saleId") String saleId, @Param("saleInfo") String saleInfo, @Param("saleDate") String saleDate);

    @Update("INSERT INTO ${userName}_salerecord(sale_id,sale_info,sale_date,sale_vip)" +
            " VALUES (${saleId},#{saleInfo},#{saleDate},#{vipName})")
    void saleGoodsWithVip(@Param("userName") String userName, @Param("saleId") String saleId, @Param("saleInfo") String saleInfo, @Param("saleDate") String saleDate,@Param("vipName") String vipName);

    @Update("UPDATE ${userName}_stock SET goods_saleNum = goods_saleNum + '${saleNum}'," +
            " goods_num = goods_num - '${saleNum}'" +
            " WHERE goods_id = '${goodsId}'")
    void updateGoodsSaleNum(@Param("userName") String userName,@Param("saleNum") Integer saleNum,@Param("goodsId") String goodsId);
}
