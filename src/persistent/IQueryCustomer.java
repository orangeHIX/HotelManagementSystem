package persistent;

import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/30.
 */
public interface IQueryCustomer {

    /**
     * @param partOfName �˿����ֵ�һ����*/
    List<Customer> queryCustomerByName(String partOfName);
}
