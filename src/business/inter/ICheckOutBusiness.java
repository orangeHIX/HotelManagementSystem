package business.inter;


/**
 * Created by hyx on 2015/12/15.
 */
public interface ICheckOutBusiness {

    /**
     * 设置即将离店的订单ID
     * @return 对应ID订单存在且处于入住状态返回true，其他情况返回false*/
    boolean setImminentCheckOutOrder(long checkOutID);

    /**收费*/
    boolean charge();

    /**离店*/
    boolean checkout();
}
