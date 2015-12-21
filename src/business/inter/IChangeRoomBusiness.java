package business.inter;

import entity.Room;

import java.util.List;

/**
 * Created by hyx on 2015/12/15.
 */
public interface IChangeRoomBusiness {

    void setOldRoom(String roomNo);

    List<Room> getRoomAvaliabletoChange();

    boolean setChangeRoom(Room newRoom);

    boolean confirmChange();

}
