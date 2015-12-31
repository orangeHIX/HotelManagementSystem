package business.impl;

import java.sql.Timestamp;
import java.util.Date;

import business.entity.Deposit;
import business.inter.ICheckInBusinessWithReservation;
import entity.Order;
import entity.Order.OrderState;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class CheckInBusinessWithReservation implements ICheckInBusinessWithReservation {
    Deposit deposit;
    Order CheckInOrder;
    protected EntityManagerFactory fa = new EntityManagerFactory();
	IOrderManager orderManager = fa.createOrderManager();
	IRoomManager roomManager = fa.createRoomManager();
	@Override
	public void charge(Deposit deposit) {
		// TODO Auto-generated method stub
		this.deposit = deposit;
		CheckInOrder.setPaid(deposit.getDeposit());
		orderManager.modifyOrder(CheckInOrder);
	}

	@Override
	public boolean checkIn() {
		// TODO Auto-generated method stub
		Date d = new Date();
		Timestamp currentDate = new Timestamp(d.getTime());

		CheckInOrder.setOrderState(OrderState.checkin_confirm);
		CheckInOrder.setCheckinTime(currentDate);
		boolean modified = orderManager.modifyOrder(CheckInOrder)&&
				roomManager.modifyRoom(CheckInOrder.getRoom().getID(), "occupied");
		return modified;
	}

	@Override
	public boolean setReservationOrder(long reservationOrderID) {
		// TODO Auto-generated method stub
		CheckInOrder = orderManager.loadOrder(reservationOrderID);
		CheckInOrder.setOrderState(OrderState.checkin_generate);
		boolean checkIn_gen = orderManager.modifyOrder(CheckInOrder);
		return checkIn_gen;
	}

}
