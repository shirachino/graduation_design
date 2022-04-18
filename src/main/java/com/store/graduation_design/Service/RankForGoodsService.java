package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Goods_salenum_rank;
import com.store.graduation_design.Pojo.Goods_typeRank;

import java.util.List;

public interface RankForGoodsService {
    List<Goods_salenum_rank> getTotalRankSer(String userName);
    List<Goods_typeRank> getTypeTotalRankSer(String userName);
    List<Goods_salenum_rank> getPerTypeRankSer(String userName, String goodsType);
}
