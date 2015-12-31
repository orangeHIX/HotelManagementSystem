package business;

import business.impl.*;
import business.inter.*;

/**
 * Created by hyx on 2015/12/21.
 */
public class BusinessFactory {


    public static IReservationBusiness createReservationBusiness(){
        return new ReservationBusiness();
    }

    public static ICancelOrderBusiness createCancelOrderBusiness(){
        return new CancelOrderBusiness();
    }


    public static ICheckInBusinessWithReservation createICheckInBusinessWithReservation(){
        return new CheckInBusinessWithReservation();
    }

    public static ICheckInBusinessWithoutReservation createCheckInBusinessWithoutReservation(){
        return new CheckInBusinessWithoutReservation();
    }

    public static IExtentionBusiness createExtentionBusiness(){
        return new ExtentionBusiness();
    }

    public static IChangeRoomBusiness createChangeRoomBusiness(){
        return new ChangeRoomBusiness();
    }

    //优先完成这个
    public static ICheckOutBusiness createCheckOutBusiness(){
        return new CheckOutBusiness();
    }

}
