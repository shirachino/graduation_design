package com.store.graduation_design.Controller.StockController;

import com.store.graduation_design.Pojo.Goods_list_page;
import com.store.graduation_design.Pojo.Goods_out;
import com.store.graduation_design.Service.StockService.StockGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockGetListController {
    @Autowired
    private StockGetListService stockGetListService;

    /**
     * @param userName 用户名
     * @param pageNow  当前页
     * @return 商品列表
     * @Todo 获取库存列表接口
     */
    @RequestMapping(value = "/getStockList")
    public String getStockList(String userName, Integer pageNow) {
        return stockGetListService.stockGetList(userName, pageNow);
    }

    /**
     * @return 商品列表
     * @Todo 按类型获取库存列表接口
     */
    @RequestMapping(value = "/getStockListByType")
    public Goods_list_page getStockListByType(String userName, Integer pageNow, String goodsType) {
        Goods_list_page res = new Goods_list_page();
        res.setGoodsList(stockGetListService.stockGetListByType(userName, pageNow, goodsType));
        res.setGoodsTotal(stockGetListService.stockAllByType(userName, goodsType));
        return res;
    }

    /**
     * @param userName 用户名
     * @return 库存总数
     * @Todo 获取库存总数接口，分页用
     */
    @RequestMapping(value = "/getStockAll")
    public Integer getStockAll(String userName) {
        return stockGetListService.stockAll(userName);
    }

    /**
     * @Todo 缺货商品列表接口，小于10件缺货
     */
    @RequestMapping(value = "/getStockOut")
    public String getStockOutList(String userName, Integer pageNow) {
        return stockGetListService.stockOutList(userName, pageNow);
    }

    /**
     * @Todo 缺货商品列表总数接口
     */
    @RequestMapping(value = "/getStockOutAll")
    public Integer getStockOutAll(String userName) {
        return stockGetListService.stockOutAll(userName);
    }

    /**
     * @Todo 前五个缺货商品列表
     */
    @RequestMapping(value = "/getStockOutGoods")
    public List<Goods_out> getOutGoods(String userName) {
        return stockGetListService.getOutGoodsSer(userName);
    }


    @RequestMapping(value = "/addOutGoodsNum")
    public String addOutGoodsNum(String userName,Integer goodsId,Integer addNum) {
        return stockGetListService.addOutGoodsSer(userName,addNum,goodsId);
    }
    /**
     * @return 类型列表
     * @Todo 获取商品销量排行前四的类型接口
     */
    @RequestMapping(value = "/getAllTypeLimit4")
    public List<String> getAllType(String userName) {
        return stockGetListService.getAllTypeSer(userName);
    }

    /**
     * @return 类型列表
     * @Todo 获取全部类型
     */
    @RequestMapping(value = "/getAllType")
    public List<String> getType(String userName) {
        return stockGetListService.getAllTypeWithoutLimitSer(userName);
    }
}