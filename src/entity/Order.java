package entity;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/10.
 */
public class Order {
    long ID;
    /**下单人*/
    Customer orderCustomer;
    /**入住代表人*/
    Customer accommodateCustomer;
    /**入住的房间*/
    Room room;
    Timestamp generateTime;
    Timestamp closeTime;
    Timestamp checkinTime;
    Timestamp checkoutTime;
    int paid;
    OrderState orderState;


    public enum OrderState {
        reservation_generate("预订请求产生"), reservation_confirm("预订确认"),
        checkin_generate("入住请求产生"), checkin_confirm("入住确认"),
        complete("完成"), close("关闭");

        String name;

        OrderState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
