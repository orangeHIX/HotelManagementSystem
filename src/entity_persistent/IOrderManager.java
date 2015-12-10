package entity_persistent;

import entity.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IOrderManager {
    /**查询所有符合限制条件的且不处于“关闭”和“完成”状态订单。<br/>
     * from和to时间戳参数规定了订单产生时间的查询范围。from和to参数保证为有效参数；<br/>
     * orderCustomerName表示下单人的名字。此参数可能为空。参数为空时不作为限制条件。<br/>
     * roomType表示房间的类型。此参数可能为空。参数为空时不作为限制条件。<br/>
     * @param from 查询起始时间
     * @param to 查询截止时间
     * @param orderCustomerName 下单人名字
     * @param roomType 房间类型
     * @return 符合限制条件的订单。如果没有应该返回一个空列表，而不返回null*/
    List<Order> queryActiveOrder(Timestamp from, Timestamp to,
                           String orderCustomerName, String roomType);
    /**查询所有符合限制条件的且不处于“确认预订”状态订单。<br/>
     * from和to时间戳参数规定了订单产生时间的查询范围。from和to参数保证为有效参数；<br/>
     * orderCustomerName表示下单人的名字。此参数可能为空。参数为空时不作为限制条件。<br/>
     * roomType表示房间的类型。此参数可能为空。参数为空时不作为限制条件。<br/>
     * @param from 查询起始时间
     * @param to 查询截止时间
     * @param orderCustomerName 下单人名字
     * @param roomType 房间类型
     * @return 符合限制条件的订单。如果没有应该返回一个空列表，而不返回null*/
    List<Order> queryRevervation(Timestamp from, Timestamp to,
                                 String orderCustomerName, String roomType);
    /**加载一个指定ID的订单
     * @param ID 订单在数据库中的ID
     * @return ID为指定ID的订单。如果查询失败，返回null*/
    Order loadOrder(long ID);

    /**增加一个订单。<br/>
     * 要插入的订单对象包含有多个实体对象。它们的ID如果此时不存在于数据库中，属于异常状况。<br/>
     * 出现异常状况订单插入失败。
     * @param order 要增加的订单。它的ID应该在方法内自动分配
     * @return 新增加的订单的ID。如果插入失败，应该返回null*/
    Long addNewOrder(Order order);
    /**修改一个订单，按照newOrder中的ID查询要修改的订单记录<br/>
     * 要修改的订单对象包含有除自身状态的多个实体对象。修改订单不应该更换或者修改这些实体对象，如果更换或者修改属于异常情况<br/>
     * 订单包含的实体对象不为空，但它们的ID如果此时不存在于数据库中，属于异常状况。<br/>
     * 出现异常状况订单插入失败。
     * @param newOrder 订单在数据库中的ID
     * @return 是否修改订单成功*/
    boolean modifyOrder(Order newOrder);
}
