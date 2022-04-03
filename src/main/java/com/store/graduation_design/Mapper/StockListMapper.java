package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="stockListMapper")
public interface StockListMapper {
    @Select("select * from ${userName}" + "_stock")
    List<User_stock> getStockListByUserName(@Param("userName") String userName);
}
