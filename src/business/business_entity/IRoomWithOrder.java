package business.business_entity;

import entity.Order;
import entity.Room;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrder {
    Room geRoom();

    List<Order> getOrderList();

    Calendar getStartDate();

    Calendar getClosingDate();

}
