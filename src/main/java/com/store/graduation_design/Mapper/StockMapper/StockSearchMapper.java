package com.store.graduation_design.Mapper.StockMapper;

import com.store.graduation_design.Pojo.User_stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "stockSearchMapper")
public interface StockSearchMapper {
    @Select("select * from ${userName}" + "_stock" +
    " where goods_name like '%${goodsName}%'")
    List<User_stock> searchByGoodsName(@Param("userName") String userName, @Param("goodsName") String goodsName);

    @Select("select * from ${userName}" + "_stock" +
            " where goods_id = ${goodsId}")
    User_stock searchByGoodsId(@Param("userName") String userName, @Param("goodsId") String goodsId);
}
