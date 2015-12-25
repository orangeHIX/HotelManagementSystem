package persistent;

import entity.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/25.
 */
public interface IQueryOrderByRoom {
    /**
     * 查询所有关联于给定房间且不处于“关闭”和“完成”状态订单。<br/>
     * from和to时间戳参数给定了时间范围，如果订单的入住和离店之间的这段时间与给定的时间范围有重叠部分，则这部分订单应该在返回结果集中。from和to参数保证为有效参数；<br/>
     * roomType表示房间的ID。此参数不能为空<br/>
     *
     * @param from              查询起始时间
     * @param to                查询截止时间
     * @param roomID          房间ID
     * @return 符合限制条件的订单。如果没有应该返回一个空列表，而不返回null
     */
    List<Order> queryActiveOrderByRoom(Timestamp from, Timestamp to,
                                long roomID);
}
