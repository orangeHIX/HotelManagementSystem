package utils;

import gui.ViewUtils;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/22.
 */
public class Log {
    public static void d(String info) {
        System.out.println(info);
    }

    public static void d(String tag, Object o) {
        if (o instanceof Calendar) {
            o = ViewUtils.getTimeFormat_yyyy_MM_dd_HH_mm_ss_E().format(((Calendar) o).getTime());
            d(tag + " " + o.toString());
        } else {
            d(tag + " " + o.toString());
        }
    }
}
