package business;

import business.entity.NewOrder;
import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * 此接口的功能是产生新的订单
 * Created by hyx on 2015/12/16.
 */
public class INewOrderCreator {
    /**
     * 设置新订单的下单人。<B>必要设置参数。</B><br/>
     * 如果下单人是已有客户，则customer中的ID为非负值，其他属性会被忽略。此时应该检查是否有该ID的已有客户。如果不存在该客户记录，属于异常情况。
     * 如果下单人是新客户，则customer中的ID为-1。此时应该检查customer的各项属性是否合法，如果customer有不合法的属性，属于异常情况。
     * 异常情况返回值应该为false。
     *
     * @param customer 新订单的下单人
     * @return 成功设置新订单的下单人返回true，否则返回false
     */
    boolean setOrderCustomer(Customer customer){
        return false;
    }

    /**
     * 设置新订单的入住人。<B>不是必要设置参数。</B><br/>
     * 如果入住人是已有客户，则customer中的ID为非负值，其他属性会被忽略。此时应该检查是否有该ID的已有客户。如果不存在该客户记录，属于异常情况。
     * 如果入住人是新客户，则customer中的ID为-1。此时应该检查customer的各项属性是否合法，如果customer有不合法的属性，属于异常情况。
     * 异常情况返回值应该为false。
     *
     * @param customer 新订单的入住人
     * @return 成功设置新订单的入住人返回true，否则返回false
     */
    boolean setStayedCustomer(Customer customer){
        return false;
    }

    /**
     * 设置新订单的入住人为下单人，即该订单的入住人和下单人是同一个人。<B>不是必要设置参数。</B><br/>
     * 如果此时还没有设置订单的下单人，属于异常情况。异常情况返回值应该为false。
     *
     * @return 成功设置新订单的入住人返回true，否则返回false
     */
    boolean setSameStayedCustomer(){
        return false;
    }
    /**
     * 设置入住房间，入住时间段。<B>必要设置参数。</B><br/>
     * 如果这段时间房间被其他订单占据，或者处于不可出售的状态，属于异常情况。
     * 异常情况返回值应该为false。
     * @param room 要入住的房间
     * @param checkInTime 预计入住时间
     * @param checkOutTime 预计离店时间
     * @return 成功设置新订单的入住人返回true，否则返回false
     */
    boolean setRoomWithTime(Room room, Calendar checkInTime, Calendar checkOutTime){
        return false;
    }
    /**按照之前设置的参数产生新的订单原型。
     * 如果某些必要参数没有设置导致无法产生新订单原型，属于异常情况。
     * 如果因为某些条件，订单原型与已有的订单有冲突，属于异常情况。
     * 异常情况返回值应该为null。
     *
     * @return 新的订单原型。无法生成订单原型返回null*/
    NewOrder createNewOrder(){
        return null;
    }
}
