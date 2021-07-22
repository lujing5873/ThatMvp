package com.nhcz500.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String getTimeYYYYMMDD(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
        return f.format(date);
    }

    public static String getTimeMMDD(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("M月d日");
        return f.format(date);
    }

    public static String getTimeYYMM(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("yyyy年M月");
        return f.format(date);
    }


    public static String getTimeYYYYMMDD2(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
        return f.format(date);
    }

    public static String getTimeYYYYMMDDHHMMSS(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        return f.format(date);
    }


    public static String getTimeMMDDHHMMSS(long timeMills){
        Date date=new Date(timeMills);
        SimpleDateFormat f = new SimpleDateFormat("MM月dd日  HH:mm:ss");
        return f.format(date);
    }


    public static String getTimeDiff(long timeStart,long timeEnd){
        Calendar start = Calendar.getInstance();   //开始日期
        start.setTimeInMillis(timeStart);
        Calendar end = Calendar.getInstance();   //结束日期
        end.setTimeInMillis(timeEnd);

        int startYear=start.get(Calendar.YEAR);
        int endYear=start.get(Calendar.YEAR);
        int dayDiff=0;
        int endDay=end.get(Calendar.DAY_OF_YEAR);
        int startDay=start.get(Calendar.DAY_OF_YEAR);
        if(endYear>startYear){
            dayDiff=start.getActualMaximum(Calendar.DAY_OF_YEAR)-startDay;
        }
       int diff= dayDiff+endDay-startDay;
       return diff+"";
    }




    public static boolean getSameMonth(long timeStart,long timeEnd){
        Calendar start = Calendar.getInstance();   //开始日期
        start.setTimeInMillis(timeStart);
        Calendar end = Calendar.getInstance();   //结束日期
        end.setTimeInMillis(timeEnd);

        int startYear=start.get(Calendar.YEAR);
        int endYear=start.get(Calendar.YEAR);

        int endMoth=end.get(Calendar.MONTH);
        int startMoth=start.get(Calendar.MONTH);

        if(startYear==endYear&&endMoth==startMoth){
            return true;
        }
        return false;
    }






    public static String getTimeDiff(long timeStart){
       long now=System.currentTimeMillis();
       long diff= (timeStart-now)/1000;
        long hour1=diff/(60*60);
        long minute1=diff%3600/60;
        long second1=diff%60;
       if(diff>0){
          return hour1+","+minute1+","+second1;
       }
        return "";
    }
}
