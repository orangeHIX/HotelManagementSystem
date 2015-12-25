package gui.room_with_order_display;

/**
 * Created by hyx on 2015/12/22.
 */
public enum Period {
    today("今天"), in_30_days("三十天内"), this_week("这个星期"),
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

    public static Period fromName(String name){
        if( name != null){
            for(Period p : Period.values()){
                if(p.getName().compareTo(name) == 0){
                    return p;
                }
            }
        }
        return null;
    }

}
