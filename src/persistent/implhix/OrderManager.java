package persistent.implhix;

import entity.Customer;
import entity.Order;
import entity.Room;
import entity.RoomType;
import persistent.IOrderManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/24.
 */
public class OrderManager implements IOrderManager {
    @Override
    public List<Order> queryActiveOrder(Timestamp from, Timestamp to, String orderCustomerName, String roomType) {
        List<Order> list= new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setName("小明");

        Customer customer2 = new Customer();
        customer2.setName("小红");

        Customer customer3 = new Customer();
        customer3.setName("小李");

        RoomType roomType1 = new RoomType();
        roomType1.setTypeName("商务间");

        RoomType roomType2 = new RoomType();
        roomType2.setTypeName("标准间");

        Room room1 = new Room();
        room1.setRoomNo("0501");
        room1.setRoomType(roomType1);

        Room room2 = new Room();
        room2.setRoomNo("0402");
        room2.setRoomType(roomType2);

        Calendar cal = Calendar.getInstance();

        Order o1 = new Order();
        o1.setID(1);
        o1.setCheckinTime(new Timestamp(cal.getTimeInMillis()));
        cal.add(Calendar.DATE, 4);
        o1.setCheckoutTime(new Timestamp(cal.getTimeInMillis()));
        o1.setRoom(room1);
        o1.setAccommodateCustomer(customer1);

        Order o2 = new Order();
        o2.setID(2);
        cal.add(Calendar.DATE, 2);
        o2.setCheckinTime(new Timestamp(cal.getTimeInMillis()));
        cal.add(Calendar.DATE, 1);
        o2.setCheckoutTime(new Timestamp(cal.getTimeInMillis()));
        o2.setRoom(room1);
        o2.setAccommodateCustomer(customer2);

        Order o3 = new Order();
        o3.setID(3);
        o3.setCheckinTime(o1.getCheckinTime());
        o3.setCheckoutTime(o2.getCheckoutTime());
        o3.setRoom(room2);
        o3.setAccommodateCustomer(customer3);

        //Log.d("queryActiveOrder"+o1.getRoom());
        list.add(o1);
        list.add(o2);
        list.add(o3);
        return list;
    }

    @Override
    public List<Order> queryRevervation(Timestamp from, Timestamp to, String orderCustomerName, String roomType) {
        return null;
    }

    @Override
    public Order loadOrder(long ID) {
        return null;
    }

    @Override
    public Long addNewOrder(Order order) {
        return null;
    }

    @Override
    public boolean modifyOrder(Order newOrder) {
        return false;
    }
}
