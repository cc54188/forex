package com.example.forex.utils;

/**
 * 共用function區
 */
public class Function {

    /**
     * 隨機產生一個西元日期字串
     * @return
     */
    public static String randomDateStr() {
        int yearInt = (int)(Math.random() * 2) + 2023;
        int monthInt = (int)(Math.random() * 12) + 1;
        int dayInt = (int)(Math.random() * 28) + 1;
        return yearInt + "/" +
                (monthInt < 10? "0" + monthInt: monthInt) + "/" +
                (dayInt < 10? "0" + dayInt: dayInt);
    }
}
