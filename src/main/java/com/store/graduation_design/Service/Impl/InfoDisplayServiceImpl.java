package com.store.graduation_design.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Pojo.UserSaleInfo;
import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Pojo.User_saleInfo;
import com.store.graduation_design.Service.InfoDisplayService;

import com.store.graduation_design.Utils.MyFormatDate;
import com.store.graduation_design.Utils.MyJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class InfoDisplayServiceImpl implements InfoDisplayService {
    @Autowired
    private InfoDisplayMapper infoDisplayMapper;

    @Override
    public String getDisplayInfo(String userName) throws RuntimeException{
        try{
            //根据日期判断顾客数和订单数
            UserSaleInfo user_sale = new UserSaleInfo();
            String dateNowDay= MyFormatDate.yyyyMMddNowDay();
            //如果获取不到结果，置0
            if(infoDisplayMapper.getOrderNum(userName,dateNowDay) == null){
                user_sale.setCustomsNum("0");
            } else {
                user_sale.setCustomsNum(infoDisplayMapper.getOrderNum(userName,dateNowDay));
            }
            if (infoDisplayMapper.getSalesNum(userName,dateNowDay) == null){
                user_sale.setSalesNum(0.0);
            } else {
                //获取当天sale_info的json列表
                List<String> getResArr = infoDisplayMapper.getSalesNum(userName,dateNowDay);
                user_sale.setSalesNum(MyJsonUtils.jsonArrayGetSalarySum(getResArr));
            }
            if (infoDisplayMapper.getMostSaleName(userName) == null){
                user_sale.setSaleMost("没有数据哦");
            } else {
                user_sale.setSaleMost(infoDisplayMapper.getMostSaleName(userName));
            }
            //返回对象Json格式
            return JSON.toJSONString(user_sale);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return "Get user info error.HTTP Status: 500";
        }

    };

}
