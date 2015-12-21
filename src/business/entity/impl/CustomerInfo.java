package business.entity.impl;

import business.entity.ICustomerInfo;

/**
 * Created by hyx on 2015/12/16.
 */
public class CustomerInfo implements ICustomerInfo {
    String name;
    String IDNumber;
    String phone;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIDNumber() {
        return IDNumber;
    }

    @Override
    public String getPhone() {
        return phone;
    }
}
