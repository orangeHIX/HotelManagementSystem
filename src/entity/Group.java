package entity;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public class Group {
    long ID;
    String groupName;
    Customer representative;
    List<Customer> customerList;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Customer getRepresentative() {
        return representative;
    }

    public void setRepresentative(Customer representative) {
        this.representative = representative;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
