package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Goods_rank;

import java.util.List;

public interface RankForGoodsService {
    List<Goods_rank> getTotalRankSer(String userName);
}
