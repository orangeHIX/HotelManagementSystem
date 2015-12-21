package business.entity;

import entity.Order;
import entity.Room;

import java.util.Calendar;
import java.util.List;

/**
 * 房间和关联到该房间且涉及到一段特定时间的订单
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrder {

    Room geRoom();

    List<Order> getOrderList();

    Calendar getStartDate();

    Calendar getClosingDate();

}
