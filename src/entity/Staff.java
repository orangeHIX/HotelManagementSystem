package entity;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public class Staff {
    long ID;
    String name;
    String staffID;
    List<SystemAccount> systemAccounts;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public List<SystemAccount> getSystemAccounts() {
        return systemAccounts;
    }

    public void setSystemAccounts(List<SystemAccount> systemAccounts) {
        this.systemAccounts = systemAccounts;
    }
}
