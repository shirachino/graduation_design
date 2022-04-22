package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.NetProfits_Rank;
import com.store.graduation_design.Service.StatisticsForNetProfitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsForNetProfitsController {
    @Autowired
    private StatisticsForNetProfitsService statisticsForNetProfitsService;

    @RequestMapping(value = "/getNetProfitsRank")
    public List<NetProfits_Rank> getNetProfitsRank(String userName){
        return statisticsForNetProfitsService.getNetProfitsRankSer(userName);
    }

    @RequestMapping(value = "/getPCR")
    public Double getPCR(String userName){
        return statisticsForNetProfitsService.getPCRSer(userName);
    }
}
