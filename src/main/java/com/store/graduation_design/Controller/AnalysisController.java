package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.Analysis_data;
import com.store.graduation_design.Pojo.Analysis_sug;
import com.store.graduation_design.Service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/getAnalysis")
    public Analysis_data getAnalysis(String userName) {
        return analysisService.getAnalysisSer(userName);
    }

    @RequestMapping(value = "/getSuggest")
    public Analysis_sug getSuggest(String userName){
        return analysisService.getSuggestSer(userName);
    }
}
