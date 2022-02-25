package com.portnov.ivan;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TestDatesToCronConverter implements DatesToCronConverter {

    @Override
    public String convert(String[] dates) throws DatesToCronConvertException{
        String cron;
        Arrays.sort(dates);
        int maxSeconds = 0, maxMinutes = 0, maxHours = 0, maxDays = 1, maxMonth = 1, maxDayWeek = 0;
        int minSeconds = 59, minMinutes = 59, minHour = 59, minDays = 31, minMonth = 12, minDayWeek = 6;
        for (int i = 0; i < dates.length; i++) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                sdf.parse(dates[i]);
            }catch (Exception e){
                throw new DatesToCronConvertException("Invalid format");
            }
            String[] date = dates[i].split("\\D");
            Calendar day = Calendar.getInstance();
            Date dayOfWeek = new Date(Integer.parseInt(date[0]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]), Integer.parseInt(date[3]), Integer.parseInt(date[4]), Integer.parseInt(date[5]));
            day.setTime(dayOfWeek);
            if (maxSeconds < Integer.parseInt(date[5])) {
                maxSeconds = Integer.parseInt(date[5]);
            }
            if (minSeconds > Integer.parseInt(date[5])) {
                minSeconds = Integer.parseInt(date[5]);
            }
            if (maxMinutes < Integer.parseInt(date[4])) {
                maxMinutes = Integer.parseInt(date[4]);
            }
            if (minMinutes > Integer.parseInt(date[4])) {
                minMinutes = Integer.parseInt(date[4]);
            }
            if (maxHours < Integer.parseInt(date[3])) {
                maxHours = Integer.parseInt(date[3]);
            }
            if (minHour > Integer.parseInt(date[3])) {
                minHour = Integer.parseInt(date[3]);
            }
            if (maxDays < Integer.parseInt(date[2])) {
                maxDays = Integer.parseInt(date[2]);
            }
            if (minDays > Integer.parseInt(date[2])) {
                minDays = Integer.parseInt(date[2]);
            }
            if (maxMonth < Integer.parseInt(date[1])) {
                maxMonth = Integer.parseInt(date[1]);
            }
            if (minMonth > Integer.parseInt(date[1])) {
                minMonth = Integer.parseInt(date[1]);
            }
            if (maxDayWeek < day.get(Calendar.DAY_OF_WEEK) - 1) {
                maxDayWeek = day.get(Calendar.DAY_OF_WEEK) - 1;
            }
            if (minDayWeek > day.get(Calendar.DAY_OF_WEEK) - 1) {
                minDayWeek = day.get(Calendar.DAY_OF_WEEK) - 1;
            }

        }

        if (maxSeconds == 59 && minSeconds == 0) {
            cron = "\"* ";
        } else if (maxSeconds == minSeconds) {
            cron = "\"" + minSeconds + " ";
        } else {
            cron = "\"" + minSeconds + "-" + maxSeconds + " ";
        }

        if (maxMinutes == 59 && minMinutes == 0) {
            cron += "* ";
        } else if (minMinutes == maxMinutes) {
            cron += minMinutes + " ";
        } else {
            cron += minMinutes + "-" + maxMinutes + " ";
        }

        if (maxHours == 59 && minHour == 0) {
            cron += "* ";
        } else if (minHour == maxHours) {
            cron += minHour + " ";
        } else {
            cron += minHour + "-" + maxHours + " ";
        }

        if (maxDays == 31 && minDays == 1) {
            cron += "* ";
        } else if (minDays == maxDays) {
            cron += minDays + " ";
        } else {
            cron += minDays + "-" + maxDays + " ";
        }

        if (maxMonth == 12 && minMonth == 1) {
            cron += "* ";
        } else if (minMonth == maxMonth) {
            cron += minMonth + " ";
        } else {
            cron += minMonth + "-" + maxMonth + " ";
        }

        if (maxDayWeek == 12 && minDayWeek == 1) {
            cron += "* ";
        } else if (minDayWeek == maxDayWeek) {
            cron += minDayWeek + "\"";
        } else {
            cron += minDayWeek + "-" + maxDayWeek + "\"";
        }
        return cron;
    }

    @Override
    public String getImplementationInfo() {
        return ("ФИО: Портнов Иван Максимович \n" +
                "Имя класса: " + getClass().getSimpleName() + "\n" +
                getClass().getPackage() + "\n" +
                "Ссылка на github https://github.com/IA1I/Digital-Design-Test"
                );
    }

}
