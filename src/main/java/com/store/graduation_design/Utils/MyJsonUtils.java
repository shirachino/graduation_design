package com.store.graduation_design.Utils;

import com.alibaba.fastjson.JSONArray;
import com.store.graduation_design.Pojo.User_saleInfo;
import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MyJsonUtils {
    /**
     * @param getResArr 数据库返回的List
     * @return 保留两位小数的double类型结果
     */
    //封装了一个从json类型的sale_info取营业额的函数
    public static Double jsonArrayGetSalarySum(@NotNull List<String> getResArr) {
        //遍历一个订单中的sale_info
        Double sum = 0.0;
        for (int i = 0; i < getResArr.size(); i++) {
            JSONArray jsonArr = JSONArray.parseArray(getResArr.get(i));
            //遍历一个订单sale_info数组的sale_salary并累加
            for (int j = 0; j < jsonArr.size(); j++) {
                Map jsonObj = (Map) jsonArr.get(j);
                //map.get返回的值是obj 先强转成string在转换成double
                sum = sum + Double.parseDouble((String) jsonObj.get("sale_salary"));
            }
        }
        //解决精度问题，并保留两位小数
        BigDecimal sumBD = new BigDecimal(sum);
        Double sumFin = sumBD.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return sumFin;
    }

    public static Double jsonGetSalarySum(@NotNull String jsonStr) {
        //遍历一个订单中的sale_info
        Double sum = 0.0;
        JSONArray jsonArr = JSONArray.parseArray(jsonStr);
        for (int i = 0; i < jsonArr.size(); i++) {
            Map jsonObj = (Map) jsonArr.get(i);
            sum = sum + Double.parseDouble((String) jsonObj.get("sale_salary"));
        }
        //解决精度问题，并保留两位小数
        BigDecimal sumBD = new BigDecimal(sum);
        Double sumFin = sumBD.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return sumFin;
    }
}
