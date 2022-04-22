package com.store.graduation_design.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value = "regMapper")
public interface RegMapper {
    //判断是否重名，手机号是否相同
    @Select("select * from user_info where name = #{name}")
    Integer isDoubleName(@Param("name") String name);

    @Select("select * from user_info where phonenum = #{phonenum}")
    Integer isDoublePhone(@Param("phonenum") String phonenum);

    //插入用户表
    @Insert("insert into user_info (name,phonenum,psw,isvip,regtime) values (#{name},#{phonenum},#{psw},0,#{regtime})")
    void insertRegUser(@Param("name") String name, @Param("phonenum") String phonenum, @Param("psw") String psw, @Param("regtime") String regtime);

    //注册时创建用户仓库，$不用#，#会带引号
    @Update({"CREATE TABLE ${name}_stock (" +
            " goods_id int NOT NULL," +
            " goods_name varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " goods_type varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " goods_num int NOT NULL," +
            " goods_inPrice double(10, 2) NOT NULL," +
            " goods_outPrice double(10, 2) NOT NULL," +
            " goods_SHLdate varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " goods_EXPdate varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            " goods_saleNum int NULL DEFAULT 0," +
            " PRIMARY KEY (goods_id) USING BTREE" +
            " ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;"})
    void createUserStock(@Param("name") String name);

    //创建用户销售表
    @Update({"CREATE TABLE ${name}_saleRecord (" +
            " sale_id varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " sale_info json NOT NULL," +
            " sale_date varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            " sale_netprofits varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            " sale_vip varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ''," +
            " PRIMARY KEY (sale_id) USING BTREE" +
            " ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;"
    })
    void createUserSaleTable(@Param("name") String name);

    @Update("CREATE TABLE ${name}_vip  (" +
            " vip_name varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " vip_id int NOT NULL," +
            " vip_phone varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " vip_join_date varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL," +
            " vip_birthday varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            " vip_point double NOT NULL DEFAULT 0," +
            " vip_balance double NOT NULL DEFAULT 0," +
            " vip_times int NOT NULL DEFAULT 0," +
            " PRIMARY KEY (vip_id) USING BTREE" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    void createUserVipTable(@Param("name") String name);

    //若表存在删除，不使用，有危险性,用用户名和手机号check住
    /*
    @Update({"drop table if exists ${name}"+"_stock ("})
    void dropExistStockTable(@Param("name") String name);

    @Update({"drop table if exists ${name}"+"_sale ("})
    void dropExistSaleTable(@Param("name") String name);
    */

}
