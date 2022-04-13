package com.store.graduation_design.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyFormatDate {
    public static String yyyyMMddNowDay(){
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = dateNow.format(formatter);
        return dateStr;
    }

    public static String yyyyMMddNowDayWithoutLine(){
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = dateNow.format(formatter);
        return dateStr;
    }

    public static String yyyyMMNowMonth(){
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String dateStr = dateNow.format(formatter);
        return dateStr;
    }

    public static String yyyyNowYear(){
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String dateStr = dateNow.format(formatter);
        return dateStr;
    }

    public static String yyyyMMddPriDay(){
        LocalDate datePri = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = datePri.format(formatter);
        return dateStr;
    }
}
