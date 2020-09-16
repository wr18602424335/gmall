package com.aryun.gmall.ke.commons.enums;

import java.util.Calendar;

public enum WeekConvert {
    MONDAY(1, Calendar.MONDAY),
    TUESDAY(2, Calendar.TUESDAY),
    WEDNESDAY(3, Calendar.WEDNESDAY),
    THURSDAY(4, Calendar.THURSDAY),
    FRIDAY(5, Calendar.FRIDAY),
    SATURDAY(6, Calendar.SATURDAY),
    SUNDAY(7, Calendar.SUNDAY),
    ;

    private int dayOfWeek;
    private int calendarDayOfWeek;

    WeekConvert(int dayOfWeek, int calendarDayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.calendarDayOfWeek = calendarDayOfWeek;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getCalendarDayOfWeek() {
        return calendarDayOfWeek;
    }

    public static int getCalendarDayOfWeek(int dayOfWeek) {
        int calendarDayOfWeek = 0;
        for (WeekConvert one : WeekConvert.values()) {
            if (one.dayOfWeek == dayOfWeek) {
                calendarDayOfWeek = one.calendarDayOfWeek;
            }
        }
        return calendarDayOfWeek;
    }

    public static void main(String[] args) {
       int i= getCalendarDayOfWeek(2);
        System.out.println(i);
    }
}
