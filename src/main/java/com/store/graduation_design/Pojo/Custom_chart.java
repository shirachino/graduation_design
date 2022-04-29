package com.store.graduation_design.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class Custom_chart {
    private List<Integer> realData;
    private String nowYearMonth;
    private String nextYearMonth;
}
