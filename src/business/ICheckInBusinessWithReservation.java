package business;

import business.business_entity.CustomerInfo;
import business.business_entity.IDeposit;
import entity.Order;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface ICheckInBusinessWithReservation {


    boolean setReservationOrder(long reservationOrderID);

    boolean charge(IDeposit deposit);

    boolean checkIn();
}
