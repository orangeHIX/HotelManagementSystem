package business;

import business.business_entity.NewOrder;

/**
 * Ԥ��ҵ��
 * Created by hyx on 2015/12/12.
 */
public interface IReservationBusiness {

    boolean setNewOrder(NewOrder newOrder);
    /**�ύԤ������*/
    boolean submitReservation();
    /**ȷ��Ԥ������*/
    boolean confirmReservation();
}
