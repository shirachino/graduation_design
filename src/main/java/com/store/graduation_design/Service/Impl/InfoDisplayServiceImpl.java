package com.store.graduation_design.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.store.graduation_design.Mapper.InfoDisplayMapper;
import com.store.graduation_design.Pojo.UserSaleInfo;
import com.store.graduation_design.Pojo.User_sale;
import com.store.graduation_design.Pojo.User_saleInfo;
import com.store.graduation_design.Service.InfoDisplayService;

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
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //如果获取不到结果，置0
            if(infoDisplayMapper.getOrderNum(userName,date.format(formatter)) == null){
                user_sale.setCustomsNum("0");
            } else {
                user_sale.setCustomsNum(infoDisplayMapper.getOrderNum(userName,date.format(formatter)));
            }
            if (infoDisplayMapper.getSalesNum(userName,date.format(formatter)) == null){
                user_sale.setSalesNum(0.0);
            } else {
                //获取当天sale_info的json列表
                List<String> getResArr = infoDisplayMapper.getSalesNum(userName,date.format(formatter));
                Double sum = 0.0;
                //遍历一个订单中的sale_info
                for (int i = 0; i< getResArr.size();i++) {
                    JSONArray jsonArr = JSONArray.parseArray(getResArr.get(i));
                    //System.out.println(jsonArr);
                    //遍历一个订单sale_info数组的sale_salary并累加
                    for (int j = 0; j < jsonArr.size(); j++) {
                        Map jsonObj = (Map) jsonArr.get(j);
                        //System.out.println(Double.parseDouble((String) jsonObj.get("sale_salary")) );
                        //map.get返回的值是obj 先强转成string在转换成double
                        sum = sum + Double.parseDouble((String) jsonObj.get("sale_salary"));
                    }
                }
                //System.out.println(sum);
//                System.out.println(getResArr.get(0));
//                System.out.println(infoDisplayMapper.getSalesNum(userName,date.format(formatter)));
                user_sale.setSalesNum(sum);
            }
            //返回对象Json格式
            return JSON.toJSONString(user_sale);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return "Get user info error.HTTP Status: 500";
        }

    };

}
