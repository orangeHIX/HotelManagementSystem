package entity;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/10.
 */
public class Order {
    long ID;
    /**�µ���*/
    Customer orderCustomer;
    /**��ס������*/
    Customer accommodateCustomer;
    /**��ס�ķ���*/
    Room room;
    Timestamp generateTime;
    Timestamp closeTime;
    Timestamp checkinTime;
    Timestamp checkoutTime;
    int paid;
    OrderState orderState;


    public enum OrderState {
        reservation_generate("Ԥ���������"), reservation_confirm("Ԥ��ȷ��"),
        checkin_generate("��ס�������"), checkin_confirm("��סȷ��"),
        complete("���"), close("�ر�");

        String name;

        OrderState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
