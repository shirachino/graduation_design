package com.store.graduation_design.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;

@Component(value = "infoDisplayMapper")
public interface InfoDisplayMapper {
    //计算顾客数
    @Select("SELECT a.c2 FROM (" +
            "SELECT sale_date AS c1,count(*) AS c2 " +
            "FROM ${name}" + "_saleRecord GROUP BY c1) " +
            "AS a " +
            "WHERE a.c1 LIKE '%${nowDate}%'")
    String getOrderNum(@Param("name") String name, @Param("nowDate") String nowDate);

    //计算当日营业额、净利润
    @Select("SELECT sale_info FROM ${name}_saleRecord " +
            "WHERE sale_date LIKE '%${nowDate}%'")
    List<String> getSalesNum(@Param("name") String name, @Param("nowDate") String nowDate);

    @Select("SELECT sale_info FROM ${name}_saleRecord ")
    List<String> getSalesAll(@Param("name") String name);

    @Select("SELECT goods_name FROM ${name}_stock WHERE goods_saleNum = (" +
            " SELECT MAX(goods_saleNum) FROM ${name}_stock) LIMIT 1")
    String getMostSaleName(@Param("name") String name);
}
