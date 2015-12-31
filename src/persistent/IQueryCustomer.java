package persistent;

import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/30.
 */
public interface IQueryCustomer {

    /**
     * @param partOfName 顾客名字的一部分*/
    List<Customer> queryCustomerByName(String partOfName);
}
