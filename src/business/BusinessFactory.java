package business;

import business.inter.*;

/**
 * Created by hyx on 2015/12/21.
 */
public class BusinessFactory {


    IReservationBusiness createReservationBusiness(){
        //todo
        return null;
    }

    ICancelOrderBusiness createCancelOrderBusiness(){
        //todo
        return null;
    }

    //优先完成这个
    ICheckInBusinessWithReservation createICheckInBusinessWithReservation(){
        //todo
        return null;
    }

    ICheckInBusinessWithoutReservation createCheckInBusinessWithoutReservation(){
        //todo
        return null;
    }

    IExtentionBusiness createExtentionBusiness(){
        //todo
        return null;
    }

    IChangeRoomBusiness createChangeRoomBusiness(){
        //todo
        return null;
    }

    //优先完成这个
    ICheckOutBusiness createCheckOutBusiness(){
        //todo
        return null;
    }

}
