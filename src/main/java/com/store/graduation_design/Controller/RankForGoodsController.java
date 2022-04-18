package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.Goods_rank;
import com.store.graduation_design.Pojo.Goods_typeRank;
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

    /**
     * @Todo 查询销量前20的接口
     * @param userName
     * @return 销量前20的商品数量
     */
    @RequestMapping(value = "/getTotalRank")
    public List<Goods_rank> totalRank(String userName) {
        return rankForGoodsService.getTotalRankSer(userName);
    }

    /**
     * @Todo 查询各类型销量
     * @param userName
     * @return 销量前20的商品数量
     */
    @RequestMapping(value = "/getTypeRank")
    public List<Goods_typeRank> typeTotalRank(String userName) {
        return rankForGoodsService.getTypeTotalRankSer(userName);
    }

    /**
     * @Todo 查询各类型销量最高的前五件商品
     * @param userName
     * @return 各类型销量最高的前五件商品
     */
    @RequestMapping(value = "/getPerTypeRank")
    public List<Goods_rank> perTypeRank(String userName, String goodsType){
        return rankForGoodsService.getPerTypeRankSer(userName,goodsType);
    }
}
