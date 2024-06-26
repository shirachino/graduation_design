package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_sale;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "saleListMapper")
public interface SalesListMapper {
    @Select("SELECT * FROM ${userName}_saleRecord ORDER BY sale_date DESC LIMIT ${pageNow},15")
    List<User_sale> getSaleList(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    @Select("SELECT COUNT(*) AS saleTotal FROM ${userName}_saleRecord")
    Integer getSaleAll(@Param("userName") String userName);

    @Delete("DELETE FROM ${userName}" + "_saleRecord" +
            " WHERE sale_id = #{salesId}")
    void deleteSalesRecord(@Param("userName") String userName,@Param("salesId") String salesId);

    @Select("SELECT * FROM ${userName}_saleRecord" +
            " WHERE sale_id = #{salesId}")
    List<User_sale> searchSalesById(@Param("userName") String userName,@Param("salesId") String salesId);

    @Select("SELECT * FROM ${userName}_saleRecord" +
            " WHERE sale_date = #{salesDate}")
    List<User_sale> searchSalesByDate(@Param("userName") String userName,@Param("salesDate") String salesDate);
}
