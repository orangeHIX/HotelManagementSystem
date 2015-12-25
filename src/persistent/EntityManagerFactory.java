package persistent;

import persistent.impl.OrderManager;
import persistent.impl.RoomTypeManager;

/**
 * Created by hyx on 2015/12/21.
 */
public class EntityManagerFactory {
    public static ICustomerManager createCustomerManager(){
        return null;
    }

    public static IRoomTypeManager createRoomTypeManager(){
        return new RoomTypeManager();
    }

    public static IOrderManager createOrderManager(){
        return new OrderManager();
    }
}
