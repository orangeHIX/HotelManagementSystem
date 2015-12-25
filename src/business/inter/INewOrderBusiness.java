package business.inter;

import business.entity.NewOrder;
import entity.Order;

/**
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderBusiness {

    /**
     * 提交订单。参数newOrder各个属性保证有效合法，且不与现有订单冲突
     * @return 成功提交的订单。如果没有成功提交返回null
     */
    Order submitOrder(NewOrder newOrder);

    /**
     * 撤销应经提交的订单。
     * @return 成功撤销已经提交的订单订单或者当前还没有提交订单，返回true。其他情况返回false*/
    boolean cancelOrder();
}
