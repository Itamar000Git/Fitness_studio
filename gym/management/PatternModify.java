package gym.management;

import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class responsible on changing string and local date objects patterns.
 */
public class PatternModify {

    /**
     * This class getting string in this pattern "01-01-2025 14:00",
     * and convert it to local date time object with the wanted pattern ("yyyy-MM-dd'T'HH:mm").
     * @param str
     * @return
     */
    public static String strPattern(String str){
        int year,month,day,hour,minute;
        year=Integer.parseInt(str.substring(6,10));
        month=Integer.parseInt(str.substring(3,5));
        day=Integer.parseInt(str.substring(0,2));
        hour=Integer.parseInt(str.substring(11,13));
        minute=Integer.parseInt(str.substring(14));
        LocalDateTime dateTime =LocalDateTime.of(year,month,day,hour,minute);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String pattern_str=dateTime.format(formatter);
        return pattern_str;
    }

    /**
     * The "Date ptr" function convert the date pattern that session have to the wanted one.
     * @param s
     * @return
     */
    public static LocalDate Dateptr(Session s){
        int y,m,d;
        y=Integer.parseInt(s.getDate().substring(6,10));
        m=Integer.parseInt(s.getDate().substring(3,5));
        d=Integer.parseInt(s.getDate().substring(0,2));
        return LocalDate.of(y,m,d);
    }

}
