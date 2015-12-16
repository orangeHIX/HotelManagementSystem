package business;

import business.business_entity.IRoomInfo;
import business.business_entity.IRoomWithOrder;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrderDisplay {
    void setTimeFilterConditions(Calendar startDate, Calendar closingDate);

    void setRoomInfoFilterConditions(IRoomInfo roomInfo);

    void searchRoom();

    List<IRoomWithOrder> getAllRoomWithOrder();
}
