package business;

import business.business_entity.ICustomerInfo;
import entity.Customer;
import entity.Order;

import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderBusiness extends INewOrderCreator {

    /**Ã·Ωª∂©µ•*/
    boolean submitOrder();

    Order getSubmitedOrder();

    boolean cancelOrder();
}
