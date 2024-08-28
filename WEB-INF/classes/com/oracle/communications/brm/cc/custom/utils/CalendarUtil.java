package com.oracle.communications.brm.cc.custom.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    
    public Calendar DateToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public Calendar StringToCalendar(String timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        return calendar;
    } 
}
