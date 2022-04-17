package com.example.asexperiment_end.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//时间按格式输出工具
public class DateUtil {

    public static String getFormattedTime(long timeStamp){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date(timeStamp));
    }

    //形如：年-月-日
    public static String getFormattedDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    private static Date strToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getWeekDay(String date){
//        String[] weekdays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        String[] weekdays = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        return  weekdays[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }

    public static String getDateTitle(String date){
//        String[] months ={"January", "February", "March", "April", "May", "June","July", "August","September","October", "November", "December"};
        String[] months ={"1月", "2月", "3月", "4月", "5月", "6月","7月", "8月","9月","10月", "11月", "12月"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int monthIndex = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return  months[monthIndex] +day+"日";
    }
}
