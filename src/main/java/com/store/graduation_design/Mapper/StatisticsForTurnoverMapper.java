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

    //季度
    @Select("SELECT sale_info FROM ${userName}_saleRecord "+
            "WHERE sale_date BETWEEN '${nowYear}-01' AND '${nowYear}-04'")
    List<String> getSalesInfoSeason1(@Param("userName") String userName, @Param("nowYear") String nowYear);

    @Select("SELECT sale_info FROM ${userName}_saleRecord "+
            "WHERE sale_date BETWEEN '${nowYear}-04' AND '${nowYear}-07'")
    List<String> getSalesInfoSeason2(@Param("userName") String userName, @Param("nowYear") String nowYear);

    @Select("SELECT sale_info FROM ${userName}_saleRecord "+
            "WHERE sale_date BETWEEN '${nowYear}-07' AND '${nowYear}-10'")
    List<String> getSalesInfoSeason3(@Param("userName") String userName, @Param("nowYear") String nowYear);

    @Select("SELECT sale_info FROM ${userName}_saleRecord "+
            "WHERE sale_date LIKE '%${nowYear}-10%' || sale_date LIKE '%${nowYear}-11%' || sale_date LIKE '%${nowYear}-12%'")
    List<String> getSalesInfoSeason4(@Param("userName") String userName, @Param("nowYear") String nowYear);

    //每月订单
    @Select("SELECT sale_info FROM ${userName}_saleRecord WHERE MONTH(sale_date) = ${perMonth} AND sale_date LIKE '%${nowYear}%'")
    List<String> getSalesInfoPerMonth(@Param("userName") String userName,@Param("perMonth") Integer perMonth, @Param("nowYear") String nowYear);

    //每日订单
    @Select("SELECT sale_info FROM ${userName}_saleRecord WHERE sale_date = '${nowDate}'")
    List<String> getSalesInfoPerDay(@Param("userName") String userName, @Param("nowDate") String nowDate);



//    @Select("SELECT JSON_UNQUOTE(JSON_EXTRACT(sale_info, '$[*].sale_salary')) sale_arr FROM ${userName}_salerecord" +
//            " WHERE sale_date = '2022-03-22' ")
//    List<String> dayTurnover(@Param("userName") String userName)
//    由于mybatis注解无法返回数组数据，不再使用
}
