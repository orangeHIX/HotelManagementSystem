package business.entity;

import entity.Order;
import entity.Room;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/24.
 */
public class RoomWithOrder {


    Room room;
    List<Order> orderList;
    Calendar startDate;
    Calendar closingDate;

    public RoomWithOrder() {

    }

    public Room getRoom() {
        //Log.d("getRoom orderList" + orderList);
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Calendar closingDate) {
        this.closingDate = closingDate;
    }
}
