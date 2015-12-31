package business.impl;

import business.entity.NewOrder;
import business.inter.INewOrderCreator;
import entity.Customer;
import entity.Room;
import persistent.EntityManagerFactory;
import persistent.ICustomerManager;
import persistent.IQueryOrderByRoom;
import persistent.IRoomManager;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/29.
 */
public class NewOrderCreator implements INewOrderCreator {
    IRoomManager roomManager = EntityManagerFactory.createRoomManager();
    ICustomerManager customerManager = EntityManagerFactory.createCustomerManager();
    IQueryOrderByRoom orderManager = EntityManagerFactory.createQueryOrderByRoom();
    NewOrder newOrder;

    public NewOrderCreator() {
        newOrder = new NewOrder();
    }

    @Override
    public boolean setOrderCustomer(Customer customer) {
        if (customer != null && checkCustomerExistence(customer)) {
            newOrder.setOrderCustomer(customer);
            return true;
        }
        return false;
    }

    @Override
    public boolean setStayedCustomer(Customer customer) {
        if (customer != null && checkCustomerExistence(customer)) {
            newOrder.setStayedCustomer(customer);
            return true;
        }
        return false;
    }

    private boolean checkCustomerExistence(Customer customer) {
        if (customer == null) {
            return false;
        }
        long ID = customer.getID();
        Customer c = customerManager.loadCustomer(ID);
        if (c == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean setSameStayedCustomer() {
        if (newOrder.getOrderCustomer() == null) {
            return false;
        } else {
            newOrder.setStayedCustomer(newOrder.getOrderCustomer());
            return true;
        }
    }

    @Override
    public boolean setRoom(Room room) {
        if (room != null) {
            long ID = room.getID();
            Room r = roomManager.loadRoom(ID);
            if (r != null) {
                newOrder.setRoom(room);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setTime(Calendar checkInTime, Calendar checkOutTime) {
        if (checkInTime != null && checkOutTime != null
                && checkInTime.compareTo(checkOutTime) < 0
                && Calendar.getInstance().compareTo(checkInTime) < 0) {
            newOrder.setCheckInTime(checkInTime);
            newOrder.setCheckOutTime(checkOutTime);
            return true;
        }
        return false;
    }

    @Override
    public NewOrder createNewOrder() {
        if(newOrder.getCheckInTime() != null
                && newOrder.getCheckOutTime() != null
                && newOrder.getOrderCustomer() != null
                && newOrder.getRoom() != null) {
            return newOrder;
        }
        return null;
    }
}
