package business.utils;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/24.
 */
public class DateConvertor {

    public static Calendar toCheckInTime(Calendar dateCheckIn){
        Calendar datetime = Calendar.getInstance();
        datetime.set(dateCheckIn.get(Calendar.YEAR), dateCheckIn.get(Calendar.MONTH), dateCheckIn.get(Calendar.DATE), 18, 0, 0);
        datetime.set(Calendar.MILLISECOND, 0);
        return datetime;
    }

    public static Calendar toCheckOutTime(Calendar dateCheckOut){
        Calendar datetime = Calendar.getInstance();
        datetime.set(dateCheckOut.get(Calendar.YEAR), dateCheckOut.get(Calendar.MONTH), dateCheckOut.get(Calendar.DATE), 12, 0, 0);
        datetime.set(Calendar.MILLISECOND, 0);
        return datetime;
    }
}
