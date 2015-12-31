package entity;

import utils.DateString;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/10.
 */
public class Order {
    long ID;
    /**
     * 下单人
     */
    Customer orderCustomer;
    /**
     * 入住代表人
     */
    Customer accommodateCustomer;
    /**
     * 入住的房间
     */
    Room room;
    Timestamp generateTime;
    Timestamp closeTime;
    Timestamp checkinTime;
    Timestamp checkoutTime;
    /**
     * 已付金额
     */
    int paid;
    OrderState orderState;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Customer getAccommodateCustomer() {
        return accommodateCustomer;
    }

    public void setAccommodateCustomer(Customer accommodateCustomer) {
        this.accommodateCustomer = accommodateCustomer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Timestamp getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Timestamp generateTime) {
        this.generateTime = generateTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public Timestamp getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public enum OrderState {
        reservation_generate("预订产生"), reservation_confirm("预订"),
        checkin_generate("入住产生"), checkin_confirm("入住"),
        complete("完成"), close("关闭");

        String name;

        OrderState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static OrderState fromString(String name){
            if(name != null){
                for(OrderState orderState : OrderState.values()){
                    if(orderState.name().compareTo(name) == 0){
                        return orderState;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "accommodateCustomer=" + accommodateCustomer +
                ", ID=" + ID +
                ", orderCustomer=" + orderCustomer +
                ", room=" + room +
                ", generateTime=" + DateString.toString(generateTime) +
                ", closeTime=" + DateString.toString(closeTime) +
                ", checkinTime=" + DateString.toString(checkinTime) +
                ", checkoutTime=" + DateString.toString(checkoutTime) +
                ", paid=" + paid +
                ", orderState=" + orderState +
                '}';
    }
}
