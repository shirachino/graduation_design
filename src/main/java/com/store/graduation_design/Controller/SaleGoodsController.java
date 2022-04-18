package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.SaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SaleGoodsController {
    @Autowired
    private SaleGoodsService saleGoodsService;

    /**
     * @Todo 销售商品接口
     * @param userName 用户名
     * @param saleInfo 销售信息json字符串
     * @return 状态200
     */
    @RequestMapping(value = "/saleGoods")
    public String saleGoods(String userName,String saleInfo){
        saleGoodsService.saleGoodsSer(userName,saleInfo);
        return "200";
    }
}