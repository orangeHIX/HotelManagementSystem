package persistent;

import entity.RoomType;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IRoomTypeManager {
    /**
     * 查询所有的未被移除的房间类型
     *
     * @return 所有的房间类型列表。如果没有应该返回一个空列表，而不返回null
     */
    List<RoomType> queryAllRoomType();

    /**
     * 加载一个指定ID的房间类型
     *
     * @param ID 房间类型在数据库中的ID
     * @return ID为指定ID的房间类型。如果查询失败，返回null
     */
    RoomType loadRoomType(long ID);

    /**
     * 增加一个房间类型。<br/>
     *
     * @param roomType 要增加的房间类型。它的ID应该在方法内自动分配
     * @return 新增加的房间类型的分配的ID。插入失败返回null
     */
    Long addNewRoomType(RoomType roomType);

    /**
     * 删除一个房间类型<br/>
     * 删除一个房间类型并不会真的删除数据库中的记录，而是将该记录标记为移除。
     * 如果删除该房间类型时，有存在房间关联到该房间类型，属于异常情况。
     * 出现异常状况房间类型插入失败。
     *
     * @param ID 房间类型在数据库中的ID
     * @return 是否成功移除房间类型信息。
     */
    boolean removeRoomType(long ID);
}
