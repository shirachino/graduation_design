package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Analysis_data;
import com.store.graduation_design.Pojo.Analysis_sug;

public interface AnalysisService {
    Analysis_data getAnalysisSer(String userName);
    Analysis_sug getSuggestSer(String userName);
}
