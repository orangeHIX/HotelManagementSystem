package business.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import business.NoFilterConditionException;

import business.entity.CustomerInfo;
import business.entity.RoomInfo;
import business.inter.IOrderDisplay;
import entity.Order;

import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class OrderDisplay implements IOrderDisplay {
    Calendar startDate;
    Calendar closingDate;
    RoomInfo roomInfo;
    CustomerInfo customerInfo;
	@Override
	public void setGeneratedTimeFilterConditions(Calendar startDate, Calendar closingDate) {
		// TODO Auto-generated method stub
		this.startDate = startDate;
		this.closingDate = closingDate;
			}

	@Override
	public void setRoomInfoFilterConditions(RoomInfo roomInfo) {
		// TODO Auto-generated method stub
        this.roomInfo = roomInfo;
	}

	@Override
	public void setCustomerInfoFilterConditions(CustomerInfo customerInfo) {
		// TODO Auto-generated method stub
        this.customerInfo = customerInfo;
	}

	@Override
	public List<Order> searchOrders() throws NoFilterConditionException {
		// TODO Auto-generated method stub
		if(startDate.equals(null))
			throw new NoFilterConditionException("No StartDate Condition");
		if(closingDate.equals(null))
			throw new NoFilterConditionException("No ClosingDate Condition");
		if(roomInfo.equals(null))
			throw new NoFilterConditionException("No RoomInfo Condition");
		if(customerInfo.equals(null))
			throw new NoFilterConditionException("No CustomerInfo Condition");
		EntityManagerFactory fa = new EntityManagerFactory();
		IOrderManager orderManager = fa.createOrderManager();
		IRoomManager roomManager = fa.createRoomManager();
		List<Order> searchOrders = orderManager.queryRevervation(new Timestamp(startDate.getTimeInMillis()), 
				new Timestamp(closingDate.getTimeInMillis()),
				customerInfo.getName(), roomInfo.getRoomTypeName());
		if(searchOrders.isEmpty())
		    return null;
		else
			return searchOrders;
	}

}
