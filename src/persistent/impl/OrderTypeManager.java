package persistent.impl;

import entity.OrderType;
import persistent.IOrderTypeManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzh on 2015/12/22.
 */
public class OrderTypeManager extends Pkid_from_add implements IOrderTypeManager {
    //static ConnectionSQL con = new ConnectionSQL();

    @Override
    public List<OrderType> queryAllOrderType() {
        List<OrderType> lr= new ArrayList<>();
        ResultSet rs = con.querySQL("select * from hotel.order_type;");
        try{
            while (rs.next()){
                OrderType orderType = new OrderType();
                orderType.setID(rs.getLong(1));
                orderType.setTypeName(rs.getString(2));
                orderType.setDiscount(rs.getInt(3));
                lr.add(orderType);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lr;
    }

    @Override
    public OrderType loadOrderType(long ID) {
        OrderType ot = new OrderType();
        ResultSet rs = con.querySQL("select * from hotel.order_type where order_type_pkid = " + ID);
        try{rs.next();
            ot.setID(rs.getInt(1));
            ot.setTypeName(rs.getString(2));
            ot.setDiscount(rs.getInt(3));
        }catch(Exception e){
            e.printStackTrace();
        }
        return ot;
    }

    @Override
    public Long addNewOrderType(OrderType orderType) {
        Long b ;
        int n = con.updateSQL("insert into order_type" +
                "(order_type,discount,order_type_delmark) " +
                "values(\'"+orderType.getTypeName()+"\',"+orderType.getDiscount()+",false);");
        b = get_pkid("select max(order_type_pkid) from order_type",n);
        return b;
    }

    @Override
    public boolean removeOrderType(long ID) {
        int n;
        n = con.updateSQL("update order_type set order_type_delmark =true where order_type_pkid=" + ID);
        if(n>0){
            return true;
        }else{
            return false;
        }
    }
}
