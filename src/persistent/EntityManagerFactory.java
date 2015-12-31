package persistent;

import persistent.impl.CustomerManager;
import persistent.impl.OrderManager;
import persistent.impl.RoomManager;
import persistent.impl.RoomTypeManager;

/**
 * Created by hyx on 2015/12/21.
 */
public class EntityManagerFactory {
    public static ICustomerManager createCustomerManager() {
        return new CustomerManager();
    }

    public static IOrderManager createOrderManager() {
        return new OrderManager();
    }

    public static IRoomManager createRoomManager() {
        return new RoomManager();
    }

    public static IQueryOrderByRoom createQueryOrderByRoom() {
        return new OrderManager();
    }

    public static IRoomTypeManager createRoomTypeManager() {
        return new RoomTypeManager();
    }

    public static IQueryCustomer createQueryCustomer() {
        return new CustomerManager();
    }
}
