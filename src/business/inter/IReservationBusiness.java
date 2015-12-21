package business.inter;

/**
 * 预订业务
 * Created by hyx on 2015/12/12.
 */
public interface IReservationBusiness extends INewOrderBusiness {
    /**
     * 确认预订订单
     */
    boolean confirmReservation();
}
