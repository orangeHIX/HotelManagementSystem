package business;

import business.business_entity.RoomRequest;
import business.business_entity.RoomResult;

import java.util.List;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ISearchRoomBusiness {

    List<RoomResult> searchRoom(RoomRequest roomRequest);

}
