package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Custom_month;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "saleCustomMapper")
public interface SaleCustomMapper {

    //季度客流量
    @Select("SELECT COUNT(*) AS season_1 FROM ${userName}_salerecord WHERE sale_date LIKE '%${nowYear}-01%' || sale_date LIKE '%${nowYear}-02%' ||  sale_date LIKE '%${nowYear}-03%'")
    Integer getSeason1Customs(@Param("userName") String userName,@Param("nowYear") String nowYear);

    @Select("SELECT COUNT(*) AS season_2 FROM ${userName}_salerecord WHERE sale_date LIKE '%${nowYear}-04%' || sale_date LIKE '%${nowYear}-05%' || sale_date LIKE '%${nowYear}-06%'")
    Integer getSeason2Customs(@Param("userName") String userName,@Param("nowYear") String nowYear);

    @Select("SELECT COUNT(*) AS season_3 FROM ${userName}_salerecord WHERE sale_date LIKE '%${nowYear}-07%' || sale_date LIKE '%${nowYear}-08%' || sale_date LIKE '%${nowYear}-09%'")
    Integer getSeason3Customs(@Param("userName") String userName,@Param("nowYear") String nowYear);

    @Select("SELECT COUNT(*) AS season_4 FROM ${userName}_salerecord WHERE sale_date LIKE '%${nowYear}-10%' || sale_date LIKE '%${nowYear}-11%' || sale_date LIKE '%${nowYear}-12%'")
    Integer getSeason4Customs(@Param("userName") String userName,@Param("nowYear") String nowYear);

    //七日内客流量
    @Select("SELECT COUNT(*) AS weekCustoms FROM ${userName}_salerecord WHERE sale_date = '${nowDate}'")
    Integer getWeekCustoms(@Param("userName") String userName,@Param("nowDate") String nowDate);

    @Select("SELECT COUNT(*) as customs,MONTH(sale_date) as permonth FROM ${userName}_salerecord WHERE sale_date BETWEEN '${nowYear}-01' AND '${nowYear}-12' GROUP BY MONTH(sale_date)")
    List<Custom_month> getMonthCustoms(@Param("userName") String userName, @Param("nowYear") String nowYear);
}
