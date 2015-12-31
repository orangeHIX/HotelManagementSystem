package gui.room_with_order_display;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/22.
 */
public enum Period {
    today("今天"), in_10_days("十天内"), in_30_days("三十天内"), this_week("这个星期"),
    next_week("下个星期"), this_month("这个月"), next_month("下个月"),
    custom("自定义");

    String name;
    Period(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Period fromString(String s){
        if( s != null){
            for(Period p : Period.values()){
                if(p.getName().compareTo(s) == 0){
                    return p;
                }
            }
        }
        return null;
    }

    public static Period fromName(String name){
        if( name != null){
            for(Period p : Period.values()){
                if(p.name().compareTo(name) == 0){
                    return p;
                }
            }
        }
        return null;
    }

    public Calendar getStartDate(){
        Calendar cal = Calendar.getInstance();
        switch (this) {
            case today:
            case in_10_days:
            case in_30_days:
                break;
            case this_week:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case next_week:
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case this_month:
                cal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case next_month:
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                break;
            default:
                cal = null;
        }
        return cal;
    }

    public Calendar getEndDate() {
        Calendar cal = Calendar.getInstance();
        switch (this) {
            case today:
                break;
            case in_10_days:
                cal.add(Calendar.DATE, 10);
                break;
            case in_30_days:
                cal.add(Calendar.DATE, 30);
                break;
            case this_week:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case next_week:
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case this_month:
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                break;
            case next_month:
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                break;
            default:
                cal = null;
        }
        return cal;
    }

}
