package com.example.cowork.etcData;

import java.time.LocalDate;

public class Date {
    public static java.sql.Date getCurrentDate(){

        // 현재 날짜를 가져오기
        LocalDate today = LocalDate.now();

        // java.sql.Date로 변환
        return java.sql.Date.valueOf(today);
    }
}
