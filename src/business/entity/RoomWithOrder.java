package business.entity;

import entity.Order;
import entity.Room;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/24.
 */
public class RoomWithOrder {

    List<Order> orderList;

    public RoomWithOrder() {

    }


    public Room geRoom() {
        //Log.d("geRoom orderList" + orderList);
        if (orderList != null && !orderList.isEmpty()) {
            return orderList.get(0).getRoom();
        }
        return null;
    }


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }


    public Calendar getStartDate() {
        return null;
    }


    public Calendar getClosingDate() {
        return null;
    }
}
