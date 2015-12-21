package business.entity.impl;

import business.entity.IDeposit;
import business.entity.INewOrder;
import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/15.
 */
public class NewOrder implements INewOrder {

    Customer orderCustomer;
    Customer stayedCustomer;
    Room room;
    Calendar checkInTime;
    Calendar checkOutTime;
    IDeposit deposit;

    @Override
    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    @Override
    public Customer getStayedCustomer() {
        return stayedCustomer;
    }

    public void setStayedCustomer(Customer stayedCustomer) {
        this.stayedCustomer = stayedCustomer;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public Calendar getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Calendar checkInTime) {
        this.checkInTime = checkInTime;
    }

    @Override
    public Calendar getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Calendar checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    @Override
    public IDeposit getDeposit() {
        return deposit;
    }
}
