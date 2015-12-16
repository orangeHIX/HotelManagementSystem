package business;

import business.business_entity.ICustomerInfo;
import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface ICustomerDisplay {

    void setCustomerFilterConditions(ICustomerInfo customerInfo);

    List<Customer> getCustomer();
}
