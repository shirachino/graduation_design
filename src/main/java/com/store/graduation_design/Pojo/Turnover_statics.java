package com.store.graduation_design.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class Turnover_statics {
    private List<Double> seasonTurnover;
    private List<Double> weekTurnover;
    private List<Double> monthTurnover;
    private List<String> sevenDays;
}
