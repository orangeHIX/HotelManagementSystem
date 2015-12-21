package business.entity;

import entity.Order;
import entity.Room;

import java.util.Calendar;
import java.util.List;

/**
 * ����͹������÷������漰��һ���ض�ʱ��Ķ���
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrder {

    Room geRoom();

    List<Order> getOrderList();

    Calendar getStartDate();

    Calendar getClosingDate();

}
