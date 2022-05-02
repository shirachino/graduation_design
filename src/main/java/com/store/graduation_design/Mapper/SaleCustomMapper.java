package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Custom_per;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "saleCustomMapper")
public interface SaleCustomMapper {

    @Select("SELECT COUNT(*) AS yearCustom FROM ${userName}_salerecord WHERE sale_date LIKE '%${nowDate}%'")
    Integer getCustoms(@Param("userName") String userName,@Param("nowDate") String nowDate);
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

    @Select("SELECT COUNT(*) as customs,MONTH(sale_date) as per FROM ${userName}_salerecord WHERE sale_date BETWEEN '${nowYear}-01' AND '${nowYear}-12' GROUP BY MONTH(sale_date)")
    List<Custom_per> getMonthCustoms(@Param("userName") String userName, @Param("nowYear") String nowYear);

    @Select("SELECT COUNT(*) as customs,DAY(sale_date) as per FROM ${userName}_salerecord WHERE sale_date BETWEEN date_add(curdate(), interval - day(curdate()) + 1 day) AND last_day(curdate()) GROUP BY DAY(sale_date)")
    List<Custom_per> getDayCustoms(@Param("userName") String userName);
}
