package business.inter;

import business.entity.IDeposit;

/**
 * Created by hyx on 2015/12/17.
 */
public interface ICheckInBusiness {

    /**收取押金*/
    void charge(IDeposit deposit);

    /**客户入住
     * @return  成功将对应订单转换为入住状态返回true，否则返回false*/
    boolean checkIn();
}
