package persistent.impl;

import entity.Customer;
import persistent.ICustomerManager;
import persistent.IQueryCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzh on 2015/12/11.
 */
public class CustomerManager extends Pkid_from_add implements ICustomerManager, IQueryCustomer {
    //static ConnectionSQL con = new ConnectionSQL();

    @Override
    public List<Customer> queryAllCustomer() {
        List<Customer> lc = new ArrayList<>();
        ResultSet rs = con.querySQL("select * from hotel.customer;");
        try {
            addCustomerIntoList(rs,lc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lc;
    }

    @Override
    public Customer loadCustomer(long ID) {
        Customer customer = new Customer();
        ResultSet rs = con.querySQL("select * from hotel.customer where customer_pkid=" + ID + ";");
        try {
            rs.next();
            customer.setID(rs.getLong(1));
            customer.setIDNumber(rs.getString(2));
            customer.setPhoneNumber(rs.getString(4));
            customer.setName(rs.getString(3));
            //System.out.println("load ok");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("load failed");
            return null;
        }
        return customer;
    }

    @Override
    public Long addNewCustomer(Customer customer) {
        Long b;
        String num = customer.getIDNumber();
        int n = con.updateSQL(
                "insert into customer (customer_id,customer_name,customer_phnum) values( \""
                        + customer.getIDNumber() + "\",\""
                        + customer.getName() + "\",\""
                        + customer.getPhoneNumber() + "\");"
        );
        b = get_pkid("select max(customer_pkid) from customer where customer_id=\"" + num + "\";", n);
        return b;
    }

    @Override
    public boolean modifyCustomer(Customer newCustomer) {
        long id = newCustomer.getID();
        int n;
        n = con.updateSQL("update customer set "
                + "customer.customer_name= \"" + newCustomer.getName() + "\","
                + "customer.customer_id = \"" + newCustomer.getIDNumber() + "\","
                + "customer.customer_phnum=\"" + newCustomer.getPhoneNumber()
                + "\" where customer.customer_pkid = " + id);
        if (n <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean removeCustomer(long ID) {
        int n;
        ResultSet rs1;
        int n1 = 0;
        ResultSet rs2;
        int n2 = 0;

        rs1 = con.querySQL("select count(*) from "
                + "(select * ,if(orderpepole_pkid=" + ID + " or checkinpepole_pkid=" + ID + ",1,0) "
                + "as op from orders) as a where a.op = 1;");
        try {
            rs1.next();
            n1 = rs1.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        rs2 = con.querySQL("select count(*) from cgroup where customer_pkid=" + ID + ";");
        try {
            rs2.next();
            n2 = rs2.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (n1 > 0 || n2 > 0) {
            n = con.updateSQL("update customer set customer_del_mark=1 where customer_pkid= " + ID + ";");
            if (n > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Customer> queryCustomerByName(String partOfName) {
        List<Customer> lc =new ArrayList<>();
        if (partOfName != null) {
            ResultSet rs = con.querySQL("select * from hotel.Customer where customer_name like \"" + partOfName + "%\"");
            try{
                addCustomerIntoList(rs,lc);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lc;
    }

    private static void addCustomerIntoList(ResultSet rs, List<Customer> lc) throws SQLException{
        if(rs != null && lc != null){
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setID(rs.getLong(1));
                customer.setIDNumber(rs.getString(2));
                customer.setPhoneNumber(rs.getString(4));
                customer.setName(rs.getString(3));
                lc.add(customer);
            }
        }
    }
}
