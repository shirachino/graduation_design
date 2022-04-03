package com.store.graduation_design.Pojo;

import lombok.Data;

@Data
public class User_stock {
    //商品编号
    private Integer goods_id;
    //商品名称
    private String goods_name;
    //商品类型
    private String goods_type;
    //商品数量
    private Integer goods_num;
    //商品进价
    private Double goods_inPrice;
    //售价
    private Double goods_outPrice;
    //上架日期
    private String goods_SHLdate;
    //保质期
    private String goods_EXPdate;
    //销售数量
    private Integer goods_saleNum;
}