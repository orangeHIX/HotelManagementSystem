package business;

import business.business_entity.NewOrder;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICheckInBusiness{
    /**�Ƿ�֮ǰ��Ԥ��*/
    boolean whetherCheckInWithReservation();

    boolean setReservationOrder(long reservationOrderID);

    boolean setNewOrder(NewOrder newOrder);

    boolean checkin();

}
