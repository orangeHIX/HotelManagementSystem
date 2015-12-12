package business;

import business.business_entity.CheckInOrder;
import business.business_entity.Deposit;
import business.business_entity.RoomRequest;
import business.business_entity.RoomResult;

import java.util.List;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICheckInBusiness {

    boolean setCheckInOrder(CheckInOrder checkInOrder);

    boolean removeCheckInOrder();

    void confirmOrder();

    boolean collectDeposit(Deposit deposit);

    boolean openRoom();

    boolean getRoomCard();
}
