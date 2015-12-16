package business.business_entity;

import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/16.
 */
public interface INewOrder {
    Customer getOrderCustomer();

    Customer getStayedCustomer();

    Room getRoom();

    Calendar getCheckInTime();

    Calendar getCheckOutTime();

    IDeposit getDeposit();
}
