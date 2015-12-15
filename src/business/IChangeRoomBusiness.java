package business;

import entity.Room;

/**
 * Created by hyx on 2015/12/15.
 */
public interface IChangeRoomBusiness {
    boolean setExistingOrder(long existingOrderID);
    boolean setChangeRoom(Room newRoom);
    boolean confirmChange();
}
