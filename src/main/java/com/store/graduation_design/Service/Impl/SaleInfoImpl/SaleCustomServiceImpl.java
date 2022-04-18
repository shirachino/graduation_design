package com.store.graduation_design.Service.Impl.SaleInfoImpl;

import com.store.graduation_design.Mapper.SaleCustomMapper;
import com.store.graduation_design.Pojo.Custom_month;
import com.store.graduation_design.Service.SaleInfoService.SaleCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.store.graduation_design.Utils.MyFormatDate.*;

@Service
public class SaleCustomServiceImpl implements SaleCustomService {
    @Autowired
    private SaleCustomMapper saleCustomMapper;

    @Override
    public List<Integer> customBySeason(String userName) {
        List<Integer> resList = new ArrayList<>();
        resList.add(saleCustomMapper.getSeason1Customs(userName, yyyyNowYear()));
        resList.add(saleCustomMapper.getSeason2Customs(userName, yyyyNowYear()));
        resList.add(saleCustomMapper.getSeason3Customs(userName, yyyyNowYear()));
        resList.add(saleCustomMapper.getSeason4Customs(userName, yyyyNowYear()));
        return resList;
    }

    @Override
    public List<Integer> customByWeek(String userName) {
        List<Integer> resList = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        //System.out.println(resList);
        for (int i = 0; i < resList.size(); i++) {
            resList.set(i, saleCustomMapper.getWeekCustoms(userName, yyyyMMddNowDay(7 - (i + 1))));
        }
        return resList;
    }

    @Override
    public List<Integer> customByMonth(String userName) {
        List<Integer> resList = Arrays.asList(new Integer[12]);
        List<Custom_month> customsPerMonth = saleCustomMapper.getMonthCustoms(userName, yyyyNowYear());
//        System.out.println(customsPerMonth);
//        System.out.println(customsPerMonth.get(0).getPermonth());
        for (int i = 0; i < resList.size(); i++) {
            resList.set(i, 0);
            for (int j = 0; j < customsPerMonth.size(); j++) {
                if (i == customsPerMonth.get(j).getPermonth() - 1) {
                    resList.set(i, customsPerMonth.get(j).getCustoms());
                }
            }
        }
        return resList;
    }

    @Override
    public List<String> xSevenDays() {
        List<String> sevenDays = Arrays.asList(new String[7]);
        for (int i = sevenDays.size() - 1; i >= 0; i--) {
            sevenDays.set(i, ddNowDay(7 - (i + 1)) + "日");
        }
        return sevenDays;
    }
}
