package business.impl;

import business.entity.RoomInfo;
import business.entity.RoomWithOrder;
import entity.Order;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IQueryOrderByRoom;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        Order order = new Order();
//        CheckInBusinessWithReservation CIR = new CheckInBusinessWithReservation();
//        CheckInBusinessWithoutReservation CINR = new CheckInBusinessWithoutReservation();
        /*order.setOrderState(OrderState.checkin_confirm);
        //System.out.println(order.getSubmitOrderState());
		long ID=131616;
		CIR.setReservationOrder(ID);
		System.out.println(CIR.CheckInOrder.getSubmitOrderState());
		CIR.checkIn();
		System.out.println(CIR.CheckInOrder.getSubmitOrderState());
		CheckOutBusiness CO = new CheckOutBusiness();
		CO.setImminentCheckOutOrder(ID);
		System.out.println(CO.checkOutOrder.getRoom().getRoomState());
		CO.checkout();
		System.out.println(CO.checkOutOrder.getSubmitOrderState());
		System.out.println(CO.checkOutOrder.getRoom().getRoomState());
		NewOrder newOrder = null;
		*/
//        EntityManagerFactory fa = new EntityManagerFactory();
//        IOrderManager orderManager = fa.createOrderManager();
//        IRoomManager roomManager = fa.createRoomManager();
//        ICustomerManager customerManager = fa.createCustomerManager();
//        NewOrder newOrder = new NewOrder();
//        Calendar c = Calendar.getInstance();
//        Timestamp t = new Timestamp(c.getTimeInMillis());
//        Room r = new Room();
//        RoomType rt = new RoomType();
//        rt.setTypeName("double");
//        r.setRoomType(rt);
//        newOrder.setCheckInTime(c);
//        System.out.println(c.getTime());
//        c.add(c.DATE, 3);
//        System.out.println(c.getTime());
//        newOrder.setCheckOutTime(c);
//        newOrder.setRoom(r);
//
//        if (roomManager.queryEmptyRoom(new Timestamp(newOrder.getCheckInTime().getTimeInMillis()),
//                new Timestamp(newOrder.getCheckOutTime().getTimeInMillis()),
//                newOrder.getRoom().getRoomType().getTypeName()).isEmpty())
//            System.out.println("Empty");
//
//        else {
//            List<Room> rr = roomManager.queryEmptyRoom(new Timestamp(newOrder.getCheckInTime().getTimeInMillis()),
//                    new Timestamp(newOrder.getCheckOutTime().getTimeInMillis()),
//                    newOrder.getRoom().getRoomType().getTypeName());
//            for (int i = 0; i < rr.size(); i++)
//                System.out.println(rr.get(i).getRoomNo());
//        }
//
//        System.out.println(newOrder.getRoom().getRoomType().getTypeName());
//
//        Customer cust = new Customer();
//        cust.setName("AAA");
//        cust.setID(7);
//        cust.setIDNumber("61616");
//        cust.setName("sdssdd");
//        cust.setPhoneNumber("21661515666");
//        //customerManager.addNewCustomer(cust);
//        newOrder.setStayedCustomer(cust);
//        newOrder.setOrderCustomer(cust);
//
//        //order.setCloseTime(closeTime);
//        //orderManager.addNewOrder(order);
//        ReservationBusiness resrv = new ReservationBusiness();
//        order = resrv.submitOrder(newOrder);
//        if (order != null)
//            System.out.println(order.getID());


        RoomWithOrderDisplay R = new RoomWithOrderDisplay();
        RoomInfo RI = new RoomInfo();
        RI.setRoomNo("0106");
        R.setRoomInfoFilterConditions(RI);
        Calendar c = Calendar.getInstance();
		c.set(2015,11,25);
        System.out.println(c.getTime());
        Calendar a = Calendar.getInstance();
        a.set(2015,11,30);
        R.setTimeFilterConditions(c, a);

		EntityManagerFactory entityManagerFactory = new EntityManagerFactory();
		IQueryOrderByRoom orderManager = entityManagerFactory.createQueryOrderByRoom();
		List<Order> orders = orderManager.queryActiveOrderByRoom(new Timestamp(c.getTimeInMillis()),new Timestamp(a.getTimeInMillis()),1);

        List<RoomWithOrder> RR = R.searchRoomWithOrder();

        for (int i = 0; i < RR.size(); i++)
            for (int j = 0; j < RR.get(i).getOrderList().size(); j++)
                System.out.println(RR.get(i).getOrderList().get(j).getID() + "  " + RR.get(i).getOrderList().get(j).getRoom().getRoomNo());

		/*
		Factory fa = new Factory();
		IOrderManager orderManager = fa.createOrderManager();
		IRoomManager roomManager = fa.createRoomManager();
		RoomWithOrder RWO = new RoomWithOrder();
		Calendar c = Calendar.getInstance();
		Timestamp t = new Timestamp(c.getTimeInMillis());
		RWO.setOrderList(orderManager.queryActiveOrderByRoom(t, t, 1));
		for(int i = 0;i<RWO.getOrderList().size();i++)
		System.out.println(RWO.getOrderList().get(i).getID());
		System.out.println(RWO.getOrderList().size());
		
		List<Room> room = roomManager.queryAllRoom();
		//Room r = new Room();
		for(int i = 0;i<room.size();i++){
			if(!(room.get(i).getRoomNo().equals("0101"))){
				room.remove(i);
			}
		}
		for(int i = 0;i<room.size();i++)
		System.out.println(i+","+room.get(i).getID());*/
		/*for(int i = 0;i<room.size();i++){
			RoomWithOrder r = null;
			List<Order> list = orderManager.queryActiveOrderByRoom(new Timestamp(c.getTimeInMillis()), new Timestamp(c.getTimeInMillis()),
					room.get(i).getID());
			if(!list.isEmpty())
			r.setOrderList(list);
			if(!(r.getOrderList().isEmpty())){
				roomWithOrder.add(r);
				k++;
			}
		}*/
    }

}
