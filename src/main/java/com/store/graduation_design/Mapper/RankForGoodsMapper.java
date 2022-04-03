package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Goods_rank;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "rankForGoodsMapper")
public interface RankForGoodsMapper {
    @Select("SELECT goods_name,goods_saleNum FROM ${userName}_stock ")
    List<Goods_rank> getTotalRank(@Param("userName") String userName);
}
