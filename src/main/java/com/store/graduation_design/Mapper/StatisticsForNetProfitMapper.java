package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.NetProfits_Rank;
import com.store.graduation_design.Pojo.NetProfits_type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "statisticsForNetProfitMapper")
public interface StatisticsForNetProfitMapper {

    @Select("SELECT goods_name,goods_saleNum*(goods_outPrice - goods_inPrice) AS goods_netProfits FROM ${userName}_stock ORDER BY goods_netProfits DESC LIMIT 20")
    List<NetProfits_Rank> orderByNetProfitSql(@Param("userName") String userName);

    @Select("SELECT Round(SUM(goods_saleNum*(goods_outPrice-goods_inPrice))/SUM(goods_saleNum*goods_outPrice) * 100 ,2) AS PCR FROM ${userName}_stock")
    Double getPCRSql(@Param("userName") String userName);

    @Select("SELECT goods_type,SUM(goods_saleNum*(goods_outPrice - goods_inPrice)) AS type_netProfits FROM ${userName}_stock GROUP BY goods_type")
    List<NetProfits_type> typeNetProfitSql(@Param("userName") String userName);

}
