package persistent.implhix;

import entity.RoomType;
import persistent.IRoomTypeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2015/12/22.
 */
public class RoomTypeManager implements IRoomTypeManager {
    @Override
    public List<RoomType> queryAllRoomType() {
        List<RoomType> list = new ArrayList<>();
        RoomType roomType1 = new RoomType();
        roomType1.setTypeName("商务房");
        list.add(roomType1);

        RoomType roomType2 = new RoomType();
        roomType2.setTypeName("标准房");
        list.add(roomType2);

        RoomType roomType3 = new RoomType();
        roomType3.setTypeName("家庭房");
        list.add(roomType3);

        return list;
    }

    @Override
    public RoomType loadRoomType(long ID) {
        return null;
    }

    @Override
    public Long addNewRoomType(RoomType roomType) {
        return null;
    }

    @Override
    public boolean removeRoomType(long ID) {
        return false;
    }
}
