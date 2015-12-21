package business;

import business.impl.CustomerDisplay;
import business.inter.ICustomerDisplay;
import business.inter.IOrderDisplay;
import business.inter.IRoomWithOrderDisplay;

/**
 * Created by hyx on 2015/12/21.
 */
public class DisplayFactory {

    ICustomerDisplay createCustomerDisplay(){
        return new CustomerDisplay();
    }

    //优先完成这个
    IOrderDisplay createOrderDisplay(){
        //todo
        return null;
    }

    //优先完成这个
    IRoomWithOrderDisplay createRoomWithOrderDisplay(){
        //todo
        return null;
    }


}
