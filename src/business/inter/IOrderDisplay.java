package business.inter;

import business.NoFilterConditionException;
import business.entity.ICustomerInfo;
import business.entity.IRoomInfo;
import entity.Order;

import java.util.Calendar;
import java.util.List;

/**
 * 此接口用于查询订单
 * Created by hyx on 2015/12/16.
 */
public interface IOrderDisplay {

    /**
     * 设置时间订单产生时间筛选时间段。此筛选条件是必要筛选条件。
     * 如果订单的产生时间在该时间段内，订单符合条件
     */
    void setGeneratedTimeFilterConditions(Calendar startDate, Calendar closingDate);

    /**
     * 此方法用于筛选订单关联的房间。此筛选条件为不是必要的。
     * 如果房间符合参数中包含的筛选信息，那么该房间被视为符合筛选条件
     *
     * @param roomInfo 关于房间的筛选信息。如果roomInfo的某个信息字段为null，表示不用此信息段进行筛选。
     */
    void setRoomInfoFilterConditions(IRoomInfo roomInfo);

    /**
     * 此方法用于筛选订单关联的下单人。此筛选条件为不是必要的。
     * 如果下单人符合参数中包含的筛选信息，那么该下单人被视为符合筛选条件
     *
     * @param customerInfo 关于下单人的筛选信息。如果customerInfo的某个信息字段为null，表示不用此信息段进行筛选。
     */
    void setCustomerInfoFilterConditions(ICustomerInfo customerInfo);

    /**
     * 查找符合所用筛选条件的订单，订单按照订单产生时间的先后顺序排列。
     * 每次调用该方法，查询结果集合符合当前设置的筛选条件。
     * 如果没有设置某些必要筛选条件，就抛出NoFilterConditionException异常，异常信息指明那些必要筛选条件没有设置。
     *
     * @return 符合条件的有序订单。如果查询结果为空，返回null
     */
    List<Order> searchOrders() throws NoFilterConditionException;
}
