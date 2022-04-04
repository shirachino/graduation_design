package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Goods_rank;
import com.store.graduation_design.Pojo.Goods_typeRank;

import java.util.List;

public interface RankForGoodsService {
    List<Goods_rank> getTotalRankSer(String userName);
    List<Goods_typeRank> getTypeTotalRankSer(String userName);
}
