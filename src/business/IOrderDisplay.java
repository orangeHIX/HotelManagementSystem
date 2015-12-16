package business;

import business.business_entity.ICustomerInfo;
import business.business_entity.IRoomInfo;
import entity.Order;

import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface IOrderDisplay {

    void setGeneratedTimeFilterConditions(Calendar startDate, Calendar closingDate);

    void setRoomInfoFilterConditions(IRoomInfo roomInfo);

    void setCustomerInfoFilterConditions(ICustomerInfo customerInfo);

    void searchOrders();

    List<Order> getOrders();
}
