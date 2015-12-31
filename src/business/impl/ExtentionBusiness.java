package business.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import business.entity.NewOrder;
import business.inter.IExtentionBusiness;
import entity.Order;
import entity.Order.OrderState;
import entity.Room;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class ExtentionBusiness implements IExtentionBusiness {
	int XtentionDay;
	int MaxDay;
	Timestamp CheckOutDate;
	NewOrder newOrder;
	Order XtentionOrder;
	EntityManagerFactory fa = new EntityManagerFactory();
	IOrderManager orderManager = fa.createOrderManager();
	IRoomManager roomManager = fa.createRoomManager();
	@Override
	public boolean setExistingOrder(long existingOrderID) {
		// TODO Auto-generated method stub
		XtentionOrder = orderManager.loadOrder(existingOrderID);
		if(!(XtentionOrder.equals(null)))
		{
			return true;
		}
		return false;
	}

	@Override
	public int getMaxExtentionDays(Timestamp endDate) {
		// TODO Auto-generated method stub
		this.CheckOutDate = endDate;
		List<Order> Reservation = orderManager.queryRevervation(XtentionOrder.getCheckoutTime(),
				endDate, null, XtentionOrder.getRoom().getRoomType().getTypeName());
		List<Room> room = roomManager.queryEmptyRoom(XtentionOrder.getCheckoutTime(),
				endDate, XtentionOrder.getRoom().getRoomType().getTypeName());
		
		int maxDay=0,tmpDay=0;
		//ArrayList<Integer> order = new ArrayList<Integer>();
		if(!room.contains(XtentionOrder.getRoom()))
				maxDay=0;
		else{
			SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(XtentionOrder.getCheckoutTime());
			String Checkout = DateFormat.format(c.getTime());
			for(int i=0;i<Reservation.size();i++)
				{
				Order order=Reservation.get(i);
				Long ID = order.getRoom().getID();			
				c.setTime(order.getCheckinTime());
				String Checkin = DateFormat.format(c.getTime());
	    	
				if((XtentionOrder.getRoom().getID()==ID)&&!(Checkin.equals(Checkout)))
					{
					tmpDay = (int) 
						(order.getCheckoutTime().getTime()-order.getCheckinTime().getTime())
						/(1000*60*60*24);
					if(tmpDay>maxDay)
						maxDay = tmpDay;				
					}
				}
			}	
		return maxDay;
	}

	@Override
	public boolean setExtentionDays(int days) {
		// TODO Auto-generated method stub
		this.XtentionDay = days;
		if(MaxDay>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean confirmExtention() {
		// TODO Auto-generated method stub
		
		XtentionOrder.setOrderState(OrderState.checkin_confirm);
		Timestamp NewCheckOutTime = new Timestamp(XtentionOrder.getCheckoutTime().getTime());
		Calendar c = Calendar.getInstance();
		c.setTime(NewCheckOutTime);
		c.add(c.DATE, XtentionDay);
		Timestamp ConfirmCheckOutTime = new Timestamp(c.getTimeInMillis());		
		XtentionOrder.setCheckoutTime(ConfirmCheckOutTime);
		boolean Xtention = orderManager.modifyOrder(XtentionOrder);
		return Xtention;
	}

}
