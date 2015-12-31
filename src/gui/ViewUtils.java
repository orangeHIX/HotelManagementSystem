package gui;

import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by hyx on 2015/12/27.
 */
public class ViewUtils {
    final static DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
    final static SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy.MM.dd E");
    final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
    public static DecimalFormat getMoneyDecimalFormat() {
        return decimalFormat;
    }

    public static SimpleDateFormat getDateFormat_yyyy_MM_dd_E(){
        return dateFormat;
    }

    public static  SimpleDateFormat getTimeFormat_yyyy_MM_dd_HH_mm_ss_E(){
        return timeFormat;
    }
}
