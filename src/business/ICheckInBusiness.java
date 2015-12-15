package business;

import business.business_entity.NewOrder;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICheckInBusiness{
    /**是否之前有预定*/
    boolean whetherCheckInWithReservation();

    boolean setReservationOrder(long reservationOrderID);

    boolean setNewOrder(NewOrder newOrder);

    boolean checkin();

}
