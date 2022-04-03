package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.Goods_rank;
import com.store.graduation_design.Service.RankForGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rank")
public class RankForGoodsController {
    @Autowired
    private RankForGoodsService rankForGoodsService;

    @RequestMapping(value = "/getTotalRank")
    public List<Goods_rank> totalRank(String userName){
        return rankForGoodsService.getTotalRankSer(userName);
    }
}
