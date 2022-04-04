package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="stockListMapper")
public interface StockListMapper {
    @Select("SELECT * FROM ${userName}" + "_stock" +
            " LIMIT ${pageNow},20")
    List<User_stock> getStockListByUserName(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock")
    Integer getStockAll(@Param("userName") String userName);
}
