package business.entity;

import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * Created by hyx on 2015/12/15.
 */
public class NewOrder {

    Customer orderCustomer;
    Customer stayedCustomer;
    Room room;
    Calendar checkInTime;
    Calendar checkOutTime;
    Deposit deposit;


    public Calendar getCheckInTime() {
        return checkInTime;
    }


    public void setCheckInTime(Calendar checkInTime) {
        this.checkInTime = checkInTime;
    }


    public Calendar getCheckOutTime() {
        return checkOutTime;
    }


    public void setCheckOutTime(Calendar checkOutTime) {
        this.checkOutTime = checkOutTime;
    }


    public Deposit getDeposit() {
        return deposit;
    }


    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }


    public Customer getOrderCustomer() {
        return orderCustomer;
    }


    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }


    public Room getRoom() {
        return room;
    }


    public void setRoom(Room room) {
        this.room = room;
    }


    public Customer getStayedCustomer() {
        return stayedCustomer;
    }


    public void setStayedCustomer(Customer stayedCustomer) {
        this.stayedCustomer = stayedCustomer;
    }
}
