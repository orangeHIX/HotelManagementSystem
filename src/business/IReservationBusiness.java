package business;

/**
 * Ԥ��ҵ��
 * Created by hyx on 2015/12/12.
 */
public interface IReservationBusiness extends INewOrderBusiness {
    /**
     * ȷ��Ԥ������
     */
    boolean confirmReservation();
}
