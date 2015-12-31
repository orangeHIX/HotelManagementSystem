package business.impl;

import business.entity.CustomerInfo;
import business.inter.ICustomerDisplay;
import business.NoFilterConditionException;

import entity.Customer;
import persistent.implhix.CustomerManager;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hyx on 2015/12/21.
 */
public class CustomerDisplay implements ICustomerDisplay {

    CustomerInfo customerInfo;

    @Override
    public void setCustomerFilterConditions(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Override
    public List<Customer> getCustomer() throws NoFilterConditionException {
        if(customerInfo == null){
            throw new NoFilterConditionException("No Customer Condition");
        }

        List<Customer> list = new CustomerManager().queryAllCustomer();
        list.removeIf(new Predicate<Customer>() {
            @Override
            public boolean test(Customer customer) {
                if(customerInfo.getName() != null
                        && customerInfo.getName().compareTo(customer.getName()) != 0){
                    return true;
                }
                if( customerInfo.getIDNumber() != null
                        && customerInfo.getIDNumber().compareTo(customer.getIDNumber()) != 0){
                    return true;
                }
                if( customerInfo.getPhone() != null
                        && customerInfo.getPhone().compareTo(customer.getPhoneNumber())!=0){
                    return true;
                }
                return false;
            }
        });

        return list;
    }
}
