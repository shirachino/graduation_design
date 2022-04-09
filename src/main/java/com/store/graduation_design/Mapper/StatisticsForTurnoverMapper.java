package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Turnover_type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "statisticsForTurnoverMapper")
public interface StatisticsForTurnoverMapper {
    @Select("SELECT SUM(goods_saleNum*goods_outPrice) AS turnover,goods_type FROM ${userName}_stock GROUP BY goods_type")
    List<Turnover_type> getTurnoverByType(@Param("userName") String userName);

    //计算当日营业额
    @Select("SELECT sale_info FROM ${name}_saleRecord "+
            "WHERE sale_date LIKE '%${nowMonth}%'")
    List<String> getSalesNumPerMonth(@Param("name") String name, @Param("nowMonth") String nowMonth);

//    @Select("SELECT JSON_UNQUOTE(JSON_EXTRACT(sale_info, '$[*].sale_salary')) sale_arr FROM ${userName}_salerecord" +
//            " WHERE sale_date = '2022-03-22' ")
//    List<String> dayTurnover(@Param("userName") String userName)
//    由于mybatis注解无法返回数组数据，不再使用
}
