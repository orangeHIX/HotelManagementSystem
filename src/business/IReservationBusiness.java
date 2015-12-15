package business;

import business.business_entity.NewOrder;

/**
 * 预订业务
 * Created by hyx on 2015/12/12.
 */
public interface IReservationBusiness {

    boolean setNewOrder(NewOrder newOrder);
    /**提交预订订单*/
    boolean submitReservation();
    /**确认预订订单*/
    boolean confirmReservation();
}
