package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Vip_Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "vipListMapper")
public interface VipListMapper {
    /**
     * @Todo 按类型查询VIP信息
     * @param userName 用户名
     * @return VIP信息
     */
    @Select("SELECT * FROM ${userName}_vip")
    List<Vip_Info> getVipListSql(@Param("userName") String userName);

    @Select("SELECT * FROM ${userName}_vip WHERE vip_phone LIKE '%${vipPhone}%'")
    List<Vip_Info> getVipListByPhoneSql(@Param("userName") String userName,@Param("vipPhone") String vipPhone);

    //精确匹配
    @Select("SELECT * FROM ${userName}_vip WHERE vip_phone = '${vipPhone}'")
    Vip_Info getVipByPhoneSql(@Param("userName") String userName,@Param("vipPhone") String vipPhone);

    @Select("SELECT * FROM ${userName}_vip WHERE vip_name LIKE '%${vipName}%'")
    List<Vip_Info> getVipListByNameSql(@Param("userName") String userName,@Param("vipName") String vipName);

    @Select("SELECT * FROM ${userName}_vip WHERE vip_id LIKE '%${vipId}%'")
    List<Vip_Info> getVipListByIdSql(@Param("userName") String userName,@Param("vipId") String vipId);

    /**
     * @Todo VIP充值接口
     */
    @Update("Update ${userName}_vip SET vip_balance = (vip_balance + ${putNum})" +
            " WHERE vip_id = ${vipId}")
    void putMoneySql(@Param("userName") String userName,@Param("putNum") String putNum,@Param("vipId") String vipId);

    /**
     * @Todo 注销VIP接口
     */
    @Delete("DELETE FROM ${userName}_vip WHERE vip_id = ${vipId}")
    void deleteVipSql(@Param("userName") String userName,@Param("vipId") String vipId);

    /**
     * @Todo VIP销售接口
     */
    @Update("Update ${userName}_vip SET vip_times = (vip_times + 1)," +
            " vip_point = vip_point + ${thisPoint}" +
            " WHERE vip_phone = '${vipPhone}'")
    void vipSaleSql(@Param("userName") String userName,@Param("thisPoint") Double thisPoint,@Param("vipPhone") String vipPhone);

    @Update("Update ${userName}_vip SET vip_balance = (vip_balance - ${thisSalary})" +
            " WHERE vip_phone = '${vipPhone}'")
    void vipUseBalanceSql(@Param("userName") String userName,@Param("thisSalary") Double thisSalary,@Param("vipPhone") String vipPhone);
}
