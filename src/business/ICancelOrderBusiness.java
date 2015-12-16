package business;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICancelOrderBusiness {

    /**设置要取消的订单，*/
    boolean setCancelOrder(long id);

    boolean confirmCancel();
}
