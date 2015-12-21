package business.inter;

/**
 * Created by hyx on 2015/12/16.
 */
public interface ICheckInBusinessWithReservation extends ICheckInBusiness {


    boolean setReservationOrder(long reservationOrderID);

}
