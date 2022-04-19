package com.store.graduation_design.Service.Impl.SaleInfoImpl;

import com.alibaba.fastjson.JSONArray;
import com.store.graduation_design.Mapper.SaleGoodsMapper;
import com.store.graduation_design.Mapper.VipListMapper;
import com.store.graduation_design.Pojo.Vip_Info;
import com.store.graduation_design.Service.SaleInfoService.SaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDay;
import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDayWithoutLine;
import static com.store.graduation_design.Utils.MyJsonUtils.jsonGetSalarySum;

@Service
public class SaleGoodsServiceImpl implements SaleGoodsService {
    @Autowired
    private SaleGoodsMapper saleGoodsMapper;

    @Autowired
    private VipListMapper vipListMapper;

    @Override
    public String saleGoodsSer(String userName, String saleInfo,String vipPhone,Boolean isUseVipBalance) {

        //会员业务
        Double thisSalary = jsonGetSalarySum(saleInfo);

        if (isUseVipBalance){
            Vip_Info thisVip = vipListMapper.getVipByPhoneSql(userName, vipPhone);
            if (thisVip.getVip_balance() >= thisSalary){
                vipListMapper.vipUseBalanceSql(userName,thisSalary,vipPhone);
            } else {
                return "601"; //余额不足
            }
        }
        if(vipPhone != null && !vipPhone.equals("")){
            try {
                vipListMapper.vipSaleSql(userName,thisSalary,vipPhone);
            } catch (Exception e){
                return "500";
            }
        }

        //销售业务
        try{
            String TimeMillis = System.currentTimeMillis() + "";
            String saleDate = yyyyMMddNowDay(0);
            String saleId = yyyyMMddNowDayWithoutLine() + TimeMillis; //根据日期＋时间戳生成唯一的订单编号
            saleGoodsMapper.saleGoods(userName, saleId, saleInfo, saleDate);
            JSONArray jsonArr = JSONArray.parseArray(saleInfo);
            for (int i = 0; i < jsonArr.size(); i++) {
                Map jsonObj = (Map) jsonArr.get(i);
                saleGoodsMapper.updateGoodsSaleNum(userName,
                        Integer.parseInt((String) jsonObj.get("sale_goodsNum")),
                        Integer.parseInt((String) jsonObj.get("sale_goodsId")));
            }
            return "200";
        } catch (Exception e){
            return "500";
        }
    }
}
