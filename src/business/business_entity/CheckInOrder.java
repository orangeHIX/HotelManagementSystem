package business.business_entity;

import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/12.
 */
public class CheckInOrder {
    long ID;
    /**�µ���*/
    Customer orderCustomer;
    /**��ס������*/
    Customer accommodateCustomer;
    /**��ס�ķ���*/
    Room room;
    Calendar checkInTime;
    Calendar checkOutTime;
}
