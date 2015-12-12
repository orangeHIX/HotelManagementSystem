package business.business_entity;

import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/12.
 */
public class CheckInOrder {
    long ID;
    /**下单人*/
    Customer orderCustomer;
    /**入住代表人*/
    Customer accommodateCustomer;
    /**入住的房间*/
    Room room;
    Calendar checkInTime;
    Calendar checkOutTime;
}
