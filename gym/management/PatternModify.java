package gym.management;

import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatternModify {
    public static String strPattern(String str){
        int year,month,day,hour,minute;
        year=Integer.parseInt(str.substring(6,10));
        month=Integer.parseInt(str.substring(3,5));
        day=Integer.parseInt(str.substring(0,2));
        hour=Integer.parseInt(str.substring(11,13));
        minute=Integer.parseInt(str.substring(14));
        // "01-01-2025 14:00"
        //2025-01-23T10:00
        LocalDateTime dateTime =LocalDateTime.of(year,month,day,hour,minute);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String pattern_str=dateTime.format(formatter);
        return pattern_str;
    }

    public static LocalDate Dateptr(Session s){
        int y,m,d;
        y=Integer.parseInt(s.getDate().substring(6,10));
        m=Integer.parseInt(s.getDate().substring(3,5));
        d=Integer.parseInt(s.getDate().substring(0,2));
        return LocalDate.of(y,m,d);
    }

}
