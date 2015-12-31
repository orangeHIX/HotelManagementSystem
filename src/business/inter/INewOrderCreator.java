package business.inter;

import business.entity.NewOrder;
import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * 此接口的功能是产生新的订单
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderCreator {
    /**
     * 设置新订单的下单人。<B>必要设置参数。</B><br/>
     * 下单人必须是已有客户，方法会检查是否有该ID的已有客户。如果不存在该客户记录，属于异常情况。
     * 异常情况返回值应该为false。
     *
     * @param customer 新订单的下单人
     * @return 成功设置新订单的下单人返回true，否则返回false
     */
    boolean setOrderCustomer(Customer customer);

    /**
     * 设置新订单的入住人。<B>不是必要设置参数。</B><br/>
     * 入住人必须是已有客户，方法会检查是否有该ID的已有客户。如果不存在该客户记录，属于异常情况。
     * 异常情况返回值应该为false。
     *
     * @param customer 新订单的入住人
     * @return 成功设置新订单的入住人返回true，否则返回false
     */
    boolean setStayedCustomer(Customer customer);

    /**
     * 设置新订单的入住人为下单人，即该订单的入住人和下单人是同一个人。<B>不是必要设置参数。</B><br/>
     * 如果此时还没有设置订单的下单人，属于异常情况。异常情况返回值应该为false。
     *
     * @return 成功设置新订单的入住人返回true，否则返回false
     */
    boolean setSameStayedCustomer();

    /**
     * 设置入住房间。<B>必要设置参数。</B><br/>
     * 入住人必须是已有房间，方法会检查是否有该ID的已有房间。如果不存在该房间记录，属于异常情况。
     * 异常情况返回值应该为false。
     *
     * @param room 要入住的房间
     * @return 成功设置新订单的入住房间返回true，否则返回false
     */
    boolean setRoom(Room room);

    /**
     * 设置入住时间段。<B>必要设置参数。</B><br/>
     * 对入住时间和离店时间做简单的有效性检查
     * @param checkInTime 预计入住时间
     * @param checkOutTime 预计离店时间
     */
    boolean setTime(Calendar checkInTime, Calendar checkOutTime);

    /**
     * 按照之前设置的参数产生新的订单原型。
     * 如果某些必要参数没有设置导致无法产生新订单原型，属于异常情况。
     * 异常情况返回值应该为null。
     *
     * @return 新的订单原型。无法生成订单原型返回null
     */
    NewOrder createNewOrder();
}
