package com.store.graduation_design.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class Analysis_sug {
    private List<Goods_salenum_rank> saleTop;
    private List<Goods_salenum_rank> saleLast;
    private List<Turnover_rank> turnoverLast;
}
