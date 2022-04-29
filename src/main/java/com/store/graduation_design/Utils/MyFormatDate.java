package com.store.graduation_design.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MyFormatDate {
    public static String yyyyMMddNowDay(Integer minus) {
        LocalDate dateNow = LocalDate.now().minusDays(minus);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateNow.format(formatter);
    }

    public static String yyyyMMddPriWeek() {
        LocalDate dateNowMinus7 = LocalDate.now().minusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateNowMinus7.format(formatter);
    }

    public static String yyyyMMddNowDayWithoutLine() {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return dateNow.format(formatter);
    }

    public static String yyyyMMNowMonth() {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return dateNow.format(formatter);
    }

    public static String yyyyMMNextYear() {
        LocalDate dateNow = LocalDate.now().plusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return dateNow.format(formatter);
    }

    public static String yyyyNowYear() {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return dateNow.format(formatter);
    }

    public static String ddNowDay(Integer minus) {
        LocalDate dateNow = LocalDate.now().minusDays(minus);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        return dateNow.format(formatter);
    }

    public static String yyyyMMddPriDay() {
        LocalDate datePri = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return datePri.format(formatter);
    }

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }
}
