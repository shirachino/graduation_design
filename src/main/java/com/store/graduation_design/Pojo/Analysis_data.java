package com.store.graduation_design.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class Analysis_data {
    private NetProfits_Rank netProfitTop;
    private Turnover_rank turnoverTop;
    private Goods_salenum_rank saleNumTop;

    private NetProfits_Rank netProfitLast;
    private Turnover_rank turnoverLast;
    private Goods_salenum_rank saleNumLast;

    private List<Double> thisMonthData;
    private List<Double> lastMonthData;
}
