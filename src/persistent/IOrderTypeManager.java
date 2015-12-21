package persistent;

import entity.OrderType;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IOrderTypeManager {
    /**
     * 查询所有的未被移除的订单类型
     *
     * @return 所有的订单类型列表。如果没有应该返回一个空列表，而不返回null
     */
    List<OrderType> queryAllOrderType();

    /**
     * 加载一个指定ID的订单类型
     *
     * @param ID 订单类型在数据库中的ID
     * @return ID为指定ID的订单类型。如果查询失败，返回null
     */
    OrderType loadOrderType(long ID);

    /**
     * 增加一个订单类型。<br/>
     *
     * @param orderType 要增加的订单类型。它的ID应该在方法内自动分配
     * @return 新增加的订单类型的分配的ID。插入失败返回null
     */
    Long addNewOrderType(OrderType orderType);

    /**
     * 删除一个订单类型<br/>
     * 删除一个订单类型并不会真的删除数据库中的记录，而是将该记录标记为移除。
     * 如果删除该订单类型时，有存在不处于“关闭”和“完成”状态订单关联到该房间类型，属于异常情况。
     * 出现异常状况订单类型插入失败。
     *
     * @param ID 订单类型在数据库中的ID
     * @return 是否成功移除订单类型信息。
     */
    boolean removeOrderType(long ID);
}
