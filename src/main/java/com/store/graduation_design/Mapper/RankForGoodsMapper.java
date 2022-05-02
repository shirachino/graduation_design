package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Goods_salenum_rank;
import com.store.graduation_design.Pojo.Goods_typeRank;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "rankForGoodsMapper")
public interface RankForGoodsMapper {
    @Select("SELECT goods_name,goods_saleNum FROM ${userName}_stock ORDER BY goods_saleNum DESC LIMIT 20")
    List<Goods_salenum_rank> getTotalRank(@Param("userName") String userName);

    @Select("SELECT goods_name,goods_saleNum FROM ${userName}_stock ORDER BY goods_saleNum LIMIT 5")
    List<Goods_salenum_rank> getSaleNumLastOne(@Param("userName") String userName);

    @Select("SELECT goods_type, SUM(goods_saleNum) AS type_saleNum FROM ${userName}_stock GROUP BY goods_type")
    List<Goods_typeRank> getTypeTotalRank(@Param("userName") String userName);

    @Select("SELECT goods_name,goods_saleNum FROM ${userName}_stock WHERE goods_type = '${goodsType}' ORDER BY goods_saleNum DESC LIMIT 5")
    List<Goods_salenum_rank> getPerTypeRank(@Param("userName") String userName, @Param("goodsType") String goodsType);
}
