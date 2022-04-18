package com.store.graduation_design.Mapper.StockMapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value = "stockDeleteMapper")
public interface StockDeleteMapper {
    @Delete("delete from ${userName}" + "_stock" +
    " where goods_id = #{goodsId}")
    void DeleteGoodsById(@Param("userName") String userName,@Param("goodsId") String goodsId);
}
