package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.RankForGoodsMapper;
import com.store.graduation_design.Pojo.Goods_rank;
import com.store.graduation_design.Pojo.Goods_typeRank;
import com.store.graduation_design.Service.RankForGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankForGoodsServiceImpl implements RankForGoodsService {
    @Autowired
    private RankForGoodsMapper rankForGoodsMapper;

    @Override
    public List<Goods_rank> getTotalRankSer(String userName) {
        return rankForGoodsMapper.getTotalRank(userName);
    }

    @Override
    public List<Goods_typeRank> getTypeTotalRankSer(String userName) {
        return rankForGoodsMapper.getTypeTotalRank(userName);
    }

    @Override
    public List<Goods_rank> getPerTypeRankSer(String userName, String goodsType) {
        return rankForGoodsMapper.getPerTypeRank(userName,goodsType);
    }
}
