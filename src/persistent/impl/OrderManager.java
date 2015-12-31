package persistent.impl;

import entity.Order;
import persistent.IOrderManager;
import persistent.IQueryOrderByRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by yzh on 2015/12/23.
 */
public class OrderManager extends Pkid_from_add implements IOrderManager, IQueryOrderByRoom {
//    select * from orders where state != "close" and state !="complete"
//    and createdaytime >= '2015-12-21 12:12:12.0' and createdaytime <= '2015-12-23 12:12:12.0'
//    and room_pkid in
// (select room_pkid from room where room_type_pkid in
// (select room_type_pkid from room_type where room_type = "标准间"));

    public static final String addOrderSQL;

    static {
        addOrderSQL = "insert into orders " +
                "(orderpepole_pkid," +
                "checkinpepole_pkid," +
                //"order_type_pkid," +
                "room_pkid," +
                "createdaytime," +
                "dropdaytime, " +
                "paid_amount," +
                "indatetime," +
                "outdatetime," +
                "state," +
                "ifgroup) " +
                "values ( ?,?,?,?,?,?,?,?,?,0);";
    }

    @Override
    public List<Order> queryActiveOrder(Timestamp from, Timestamp to, String orderCustomerName, String roomType) {
        List<Order> lr = new ArrayList<>();
        ResultSet rs = null;
        if (roomType != null && orderCustomerName != null) {
            rs = con.querySQL("select * from orders where " +
                    "state != \"close\" and state !=\"complete\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and room_pkid in (select room_pkid from room where room_type_pkid in" +
                    "(select room_type_pkid from room_type where room_type = \"" + roomType + "\"))" +
                    "and orderpepole_pkid in (select customer_pkid from customer where customer_name = \"" + orderCustomerName + "\");");
        } else if (roomType == null && orderCustomerName != null) {
            rs = con.querySQL("select * from orders where " +
                    "state != \"close\" and state !=\"complete\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and orderpepole_pkid in (select customer_pkid from customer where customer_name = \"" + orderCustomerName + "\") ");
        } else if (roomType != null && orderCustomerName == null) {
            rs = con.querySQL("select * from orders where " +
                    "state != \"close\" and state !=\"complete\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and room_pkid in (select room_pkid from room where room_type_pkid in" +
                    "(select room_type_pkid from room_type where room_type = \"" + roomType + "\"));");
        } else if (roomType == null && orderCustomerName == null) {
            rs = con.querySQL("select * from orders where " +
                    "state != \"close\" and state !=\"complete\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to + "\' ;");
        }
        hu(lr, rs);
        return lr;
    }

    @Override
    public List<Order> queryRevervation(Timestamp from, Timestamp to, String orderCustomerName, String roomType) {
        List<Order> lr = new ArrayList<>();
        ResultSet rs = null;
        if (roomType != null && orderCustomerName != null) {
            String a1 = "select * from orders where " +
                    "state = \"" + Order.OrderState.reservation_confirm.name() + "\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and room_pkid in (select room_pkid from room where room_type_pkid in" +
                    "(select room_type_pkid from room_type where room_type = \"" + roomType + "\"))" +
                    "and orderpepole_pkid in (select customer_pkid from customer where customer_name = \"" + orderCustomerName + "\");";
            //System.out.println(a1);
            rs = con.querySQL(a1);
        } else if (roomType == null && orderCustomerName != null) {
            rs = con.querySQL("select * from orders where " +
                    "state = \"reservation_confirm\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and orderpepole_pkid in (select customer_pkid from customer where customer_name = \"" + orderCustomerName + "\") ");
        } else if (roomType != null && orderCustomerName == null) {
            rs = con.querySQL("select * from orders where " +
                    "state = \"reservation_confirm\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to +
                    "\' and room_pkid in (select room_pkid from room where room_type_pkid in" +
                    "(select room_type_pkid from room_type where room_type = \"" + roomType + "\"));");
        } else if (roomType == null && orderCustomerName == null) {
            rs = con.querySQL("select * from orders where " +
                    "state = \"reservation_confirm\" and createdaytime >= \'" + from + "\' and createdaytime <= \'" + to + "\' ;");
        }
        hu(lr, rs);
        return lr;
    }

    @Override
    public Order loadOrder(long ID) {
        Order order = new Order();
        RoomManager rm = new RoomManager();
        CustomerManager cm = new CustomerManager();
        ResultSet rs = con.querySQL("select * from orders where order_pkid = " + ID);
        try {
            if (rs.next()) {
                order.setID(rs.getLong(1));
                order.setOrderCustomer(cm.loadCustomer(rs.getLong(2)));
                order.setAccommodateCustomer(cm.loadCustomer(rs.getLong(3)));
                order.setRoom(rm.loadRoom(rs.getLong(4)));
                order.setGenerateTime(Timestamp.valueOf((rs.getString(5) == null ? "9999-12-21 12:12:12" : rs.getString(5))));
                //System.out.println("------------"+rs.getString(6));
                order.setCloseTime(Timestamp.valueOf((rs.getString(6) == null ? "9999-12-21 12:12:12" : rs.getString(6))));
                order.setCheckinTime(Timestamp.valueOf((rs.getString(7) == null ? "9999-12-21 12:12:12" : rs.getString(7))));
                order.setCheckoutTime(Timestamp.valueOf((rs.getString(8) == null ? "9999-12-21 12:12:12" : rs.getString(8))));
                order.setPaid(rs.getInt(11));
                order.setOrderState(Order.OrderState.fromString(rs.getString(9)));/////
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return order;
    }

    @Override
    public Long addNewOrder(Order order) {
        Long b;
//        String sql = "insert into orders " +
//                "(orderpepole_pkid," +
//                "checkinpepole_pkid," +
//                //"order_type_pkid," +
//                "room_pkid," +
//                "createdaytime," +
//                "dropdaytime, " +
//                "paid_amount," +
//                "indatetime," +
//                "outdatetime," +
//                "state," +
//                "ifgroup) " +
//                "values ( " +
//                //order.getOrderCustomer()+"," +
//                order.getOrderCustomer().getID() + "," +
//                order.getAccommodateCustomer().getID() + "," +
//                //order.get
//                order.getRoom().getID() + ",\"" +
//                order.getGenerateTime() + "\",\"" +
//                order.getCloseTime() + "\"," +
//                order.getPaid() + ",\"" +
//                order.getCheckinTime() + "\", \"" +
//                order.getCheckoutTime() + "\", \"" +
//                order.getSubmitOrderState().name() + "\"," +
//                " 0);";
        PreparedStatement statement = con.prepareSQL(addOrderSQL);
        try {
            statement.setLong(1, order.getOrderCustomer().getID());
            statement.setLong(2, order.getAccommodateCustomer().getID());
            statement.setLong(3, order.getRoom().getID());
            statement.setTimestamp(4, order.getGenerateTime());
            statement.setTimestamp(5, order.getCloseTime());
            statement.setInt(6, order.getPaid());
            statement.setTimestamp(7, order.getCheckinTime());
            statement.setTimestamp(8, order.getCheckoutTime());
            statement.setString(9,order.getOrderState().name());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int n = con.updateSQL(statement);
        b = get_pkid("select max(order_pkid) from orders;", n);
        return b;
    }

    @Override
    public boolean modifyOrder(Order newOrder) {
        Order order;
        order = loadOrder(newOrder.getID());
        if (newOrder.getOrderState() == null) {
            newOrder.setOrderState(order.getOrderState());
        } else if (newOrder.getCheckoutTime() == null) {
            newOrder.setCheckoutTime(order.getCheckoutTime());
        } else if (newOrder.getCheckinTime() == null) {
            newOrder.setCheckinTime(order.getCheckinTime());
        } else if (newOrder.getCloseTime() == null) {
            newOrder.setCloseTime(order.getCloseTime());
        } else if (newOrder.getAccommodateCustomer() == null) {
            newOrder.setAccommodateCustomer(order.getAccommodateCustomer());
        } else if (newOrder.getGenerateTime() == null) {
            newOrder.setGenerateTime(order.getGenerateTime());
        } else if (newOrder.getOrderCustomer() == null) {
            newOrder.setOrderCustomer(order.getOrderCustomer());
        } else if (newOrder.getPaid() == 0) {
            newOrder.setPaid(order.getPaid());
        } else if (newOrder.getRoom() == null) {
            newOrder.setRoom(order.getRoom());
        }
        int n = con.updateSQL("update  orders " +
                "set " +
                "createdaytime = \"" + newOrder.getGenerateTime() + "\"," +
                "paid_amount = " + newOrder.getPaid() + ",indatetime = \"" + newOrder.getCheckinTime() + "\"," +
                "outdatetime = \"" + newOrder.getCheckoutTime() + "\"," +
                "state = \"" + newOrder.getOrderState().name() + "\"" +
                "where order_pkid = " + newOrder.getID());
        //Long b = addNewOrder(newOrder);
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }


    private void hu(List<Order> lr, ResultSet rs) {
        try {
            CustomerManager cm = new CustomerManager();
            RoomManager rm = new RoomManager();
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    Order order = new Order();
                    order.setID(rs.getLong(1));
                    order.setOrderCustomer(cm.loadCustomer(rs.getLong(2)));
                    order.setAccommodateCustomer(cm.loadCustomer(rs.getLong(3)));
                    order.setRoom(rm.loadRoom(rs.getLong(4)));
                    order.setGenerateTime(rs.getTimestamp(5));
                    order.setCloseTime(rs.getTimestamp(6));
                    order.setCheckinTime(rs.getTimestamp(7));
                    order.setCheckoutTime(rs.getTimestamp(8));
                    order.setPaid(rs.getInt(11));
                    order.setOrderState(Order.OrderState.fromString(rs.getString(9)));///////////
                    lr.add(order);
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> queryActiveOrderByRoom(Timestamp from, Timestamp to, long roomID) {
        List<Order> l = new ArrayList<>();
        ResultSet rs = null;
        //PreparedStatement statement = new P
//        "SELECT * FROM hotel.orders where not ( indatetime > to or outdatetime < from)" +
//                " and room_pkid = roomID;"
        rs = con.querySQL("SELECT * FROM hotel.orders where " +
                " not (indatetime> \"" + to + "\" or outdatetime < \"" + from + "\") " +
                " and room_pkid = " + roomID +
                " and state != \"close\" and state !=\"complete\";");
        hu(l, rs);
        return l;
    }
}
