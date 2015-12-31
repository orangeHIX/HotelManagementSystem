package business;

import business.impl.CustomerDisplay;
import business.impl.RoomWithOrderDisplay;
import business.inter.ICustomerDisplay;
import business.inter.IOrderDisplay;
import business.inter.IRoomWithOrderDisplay;

/**
 * Created by hyx on 2015/12/21.
 */
public class DisplayFactory {

    public static ICustomerDisplay createCustomerDisplay(){
        return new CustomerDisplay();
    }

    //����������
    public static IOrderDisplay createOrderDisplay(){
        //todo
        return null;
    }

    //����������
    public static IRoomWithOrderDisplay createRoomWithOrderDisplay(){
        return new RoomWithOrderDisplay();
    }


}
