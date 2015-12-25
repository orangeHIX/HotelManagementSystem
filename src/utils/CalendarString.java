package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hyx on 2015/12/22.
 */
public class CalendarString {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    public static String calendarToString(Calendar calendar){
        return simpleDateFormat.format(calendar.getTime());
    }

    public static void main(String[] args){

        Log.d(calendarToString(Calendar.getInstance()));
    }
}
