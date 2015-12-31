package entity;

import utils.DateString;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/10.
 */
public class Order {
    long ID;
    /**
     * �µ���
     */
    Customer orderCustomer;
    /**
     * ��ס������
     */
    Customer accommodateCustomer;
    /**
     * ��ס�ķ���
     */
    Room room;
    Timestamp generateTime;
    Timestamp closeTime;
    Timestamp checkinTime;
    Timestamp checkoutTime;
    /**
     * �Ѹ����
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
        reservation_generate("Ԥ������"), reservation_confirm("Ԥ��"),
        checkin_generate("��ס����"), checkin_confirm("��ס"),
        complete("���"), close("�ر�");

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
