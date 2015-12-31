package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hyx on 2015/12/22.
 */
public class DateString {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

    public static String toString(Calendar calendar) {
        if (calendar != null) {
            return simpleDateFormat.format(calendar.getTime());
        }
        return null;
    }

    public static String toString(Timestamp timestamp) {
        if (timestamp != null) {
            return simpleDateFormat.format(new Date(timestamp.getTime()));
        }
        return null;
    }

    public static String toString(Date date) {
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    public static void main(String[] args) {

        Log.d(toString(Calendar.getInstance()));
    }
}
