package business;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICancelOrderBusiness {

    /**����Ҫȡ���Ķ�����*/
    boolean setCancelOrder(long id);

    boolean confirmCancel();
}
