package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.Goods_out;
import com.store.graduation_design.Pojo.User_stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="stockListMapper")
public interface StockListMapper {
    /**
     * @Todo 查询商品仓库数据库，每页返回20个结果
     * @param userName
     * @param pageNow
     * @return User_stock对象集合
     */
    @Select("SELECT * FROM ${userName}" + "_stock" +
            " LIMIT ${pageNow},20")
    List<User_stock> getStockListByUserName(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    @Select("SELECT * FROM ${userName}" + "_stock WHERE goods_type = #{goodsType}" +
            " LIMIT ${pageNow},20")
    List<User_stock> getStockListByType(@Param("userName") String userName,@Param("pageNow") Integer pageNow,@Param("goodsType") String goodsType);

    /**
     * @Todo 查询商品仓库数据库，返回总记录数
     * @param userName
     * @return 仓库记录数
     */
    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock")
    Integer getStockAll(@Param("userName") String userName);

    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock WHERE goods_type = #{goodsType}")
    Integer getStockAllByType(@Param("userName") String userName,@Param("goodsType") String goodsType);

    /**
     * @Todo 查询缺货（数量小于10件的商品）
     * @param userName
     * @param pageNow
     * @return User_stock对象集合
     */
    @Select("SELECT * FROM ${userName}" + "_stock" +
            " WHERE goods_num <= 10" +
            " LIMIT ${pageNow},10")
    List<User_stock> getStockOutList(@Param("userName") String userName,@Param("pageNow") Integer pageNow);

    /**
     * @Todo 查询商品仓库数据库，返回总缺货记录数
     * @param userName
     * @return 仓库记录数
     */
    @Select("SELECT COUNT(*) AS stock_all FROM ${userName}_stock"+
            " WHERE goods_num <= 10")
    Integer getStockOutAll(@Param("userName") String userName);

    /**
     * @Todo 查询缺货排行前五的商品，用于提醒补货
     * @param userName
     * @return 缺货排行前五的商品名和数量
     */
    @Select("SELECT goods_name,goods_num FROM ${userName}_stock WHERE goods_num <= 10 ORDER BY goods_num DESC LIMIT 5")
    List<Goods_out> getOutGoods(@Param("userName") String userName);

    /**
     * @Todo 获取销量前四个类型
     * @param userName
     * @return 类型字符串列表
     */
    @Select("SELECT goods_type FROM ${userName}_stock GROUP BY goods_type ORDER BY SUM(goods_saleNum) DESC LIMIT 4")
    List<String> getAllType(@Param("userName") String userName);

    /**
     * @Todo 获取全部类型
     * @param userName
     * @return 类型字符串列表
     */
    @Select("SELECT goods_type FROM ${userName}_stock GROUP BY goods_type")
    List<String> getAllTypeWithoutLimit(@Param("userName") String userName);
}
