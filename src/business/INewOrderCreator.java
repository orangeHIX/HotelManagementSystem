package business;

import business.business_entity.INewOrder;
import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderCreator {

    boolean setOrderCustomer(Customer customer);

    boolean setStayedCustomer(Customer customer);

    boolean setRoomWithTime(Room room, Calendar checkInTime, Calendar checkOutTime);

    INewOrder createNewOrder();
}
