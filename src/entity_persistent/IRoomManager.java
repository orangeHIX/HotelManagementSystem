package entity_persistent;

import entity.Order;
import entity.Room;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IRoomManager{
    /**查询所有的未被移除的客人
     * @return 所有的客人列表。如果没有应该返回一个空列表，而不返回null*/
    List<Room> queryAllRoom();
    
    /**查询所有给定条件限制后可以入住的空房间<br/>
     * checkinTime和checkoutTime为指定要入住的时间段，checkinTime和checkoutTime保证为有效参数<br/>
     * roomType表示房间的类型。此参数可能为空。参数为空时不作为限制条件。<br/>
     * @param checkinTime 预计入住时间
     * @param checkoutTime 预计离开时间 
     * @param roomType 房型 
     * @return 所有的符合条件的房间列表。如果没有应该返回一个空列表，而不返回null*/
    List<Room> queryEmptyRoom(Timestamp checkinTime, Timestamp checkoutTime,
                              String roomType);
    /**加载一个指定ID的房间
     * @param ID 房间在数据库中的ID
     * @return ID为指定ID的房间。如果查询失败，返回null*/
    Room loadRoom(long ID);
    /**增加一个房间。<br/>
     * 要插入的房间对象包含有多个实体对象。它们的ID如果此时不存在于数据库中，属于异常状况。<br/>
     * 出现异常状况房间插入失败。
     * @param room 要增加的房间。它的ID应该在方法内自动分配
     * @return 新增加的房间的ID。如果插入失败，应该返回null*/
    Long addNewRoom(Room room);
    /**删除一个房间<br/>
     * 删除一个房间并不会真的删除数据库中的记录，而是将该记录标记为移除。
     * 如果删除该房间时，有存在不处于“关闭”和“完成”状态的订单关联到该房间类型，属于异常情况。
     * 出现异常状况房间类型插入失败。
     * @param ID 房间在数据库中的ID
     * @return 是否成功移除房间信息。*/
    boolean removeRoom(long ID);
}
