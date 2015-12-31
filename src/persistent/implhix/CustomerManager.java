package persistent.implhix;

import entity.Customer;
import persistent.ICustomerManager;

import java.util.List;

/**
 * Created by hyx on 2015/12/21.
 */
public class CustomerManager implements ICustomerManager {
    @Override
    public List<Customer> queryAllCustomer() {
        return null;
    }

    @Override
    public Customer loadCustomer(long ID) {
        return null;
    }

    @Override
    public Long addNewCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean modifyCustomer(Customer newCustomer) {
        return false;
    }

    @Override
    public boolean removeCustomer(long ID) {
        return false;
    }
}
