package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="stockListMapper")
public interface StockListMapper {
    /**
     * @description 查询商品仓库数据库，每页返回20个结果
     * @param userName
     * @param pageNow
     * @return User_stock对象集合
     */
    @Select("SELECT * FROM ${userName}" + "_stock" +
            " LIMIT ${pageNow},20")
    List<User_stock> getStockListByUserName(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    /**
     * @description 查询商品仓库数据库，返回总记录数
     * @param userName
     * @return 仓库记录数
     */
    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock")
    Integer getStockAll(@Param("userName") String userName);

    /**
     * @description 查询缺货（数量小于10件的商品）
     * @param userName
     * @param pageNow
     * @return User_stock对象集合
     */
    @Select("SELECT * FROM ${userName}" + "_stock" +
            " WHERE goods_num <= 10" +
            " LIMIT ${pageNow},10")
    List<User_stock> getStockOutList(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    /**
     * @description 查询商品仓库数据库，返回总缺货记录数
     * @param userName
     * @return 仓库记录数
     */
    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock"+
            " WHERE goods_num <= 10")
    Integer getStockOutAll(@Param("userName") String userName);

}
