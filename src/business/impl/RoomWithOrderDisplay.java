package business.impl;

import business.NoFilterConditionException;
import business.entity.RoomInfo;
import business.entity.RoomWithOrder;
import business.inter.IRoomWithOrderDisplay;
import entity.Order;
import persistent.EntityManagerFactory;
import utils.CalendarString;
import utils.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/25.
 */
public class RoomWithOrderDisplay implements IRoomWithOrderDisplay {
    @Override
    public void setTimeFilterConditions(Calendar startDate, Calendar closingDate) {
        Log.d("setTimeFilterConditions");
        Log.d("start time: " + CalendarString.calendarToString(startDate));
        Log.d("end time: "+ CalendarString.calendarToString(closingDate));
    }

    @Override
    public void setRoomInfoFilterConditions(RoomInfo roomInfo) {
        Log.d("setRoomInfoFilterConditions");
        Log.d(roomInfo.toString());
    }

    @Override
    public List<RoomWithOrder> searchRoomWithOrder() throws NoFilterConditionException {
        List<Order> orders = new EntityManagerFactory().createOrderManager().queryActiveOrder(null, null, null, null);
        //Log.d("loadRoomWithOrder " + orders.get(0).getRoom());
        RoomWithOrder roomWithOrder1 = new RoomWithOrder();
        roomWithOrder1.setOrderList(orders.subList(0, 2));
        RoomWithOrder roomWithOrder2 = new RoomWithOrder();
        roomWithOrder2.setOrderList(orders.subList(2, 3));
        List<RoomWithOrder> content = new ArrayList<>();
        content.add(roomWithOrder1);
        content.add(roomWithOrder2);
        return content;
    }
}
