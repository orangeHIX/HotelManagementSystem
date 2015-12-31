package business.impl;


import business.entity.NewOrder;
import business.inter.IReservationBusiness;
import entity.Order;
import entity.Order.OrderState;

public class ReservationBusiness extends NewOrderBusiness implements IReservationBusiness {


	@Override
	public OrderState getSubmitOrderState() {
		return OrderState.reservation_generate;
	}

	@Override
	public boolean confirmReservation() {
		// TODO Auto-generated method stub
		/* Change the state to reservation_confirm */
		order.setOrderState(OrderState.reservation_confirm);
		boolean modified = orderManager.modifyOrder(order);
		return modified;
	}

}
