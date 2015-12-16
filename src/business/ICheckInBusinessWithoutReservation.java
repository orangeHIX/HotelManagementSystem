package business;

import business.business_entity.IDeposit;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICheckInBusinessWithoutReservation extends INewOrderBusiness {

    boolean charge(IDeposit deposit);

    public boolean checkIn();

}
