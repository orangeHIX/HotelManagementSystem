package business.impl;

import business.entity.Deposit;
import business.inter.ICheckInBusinessWithoutReservation;
import business.utils.DateConvertor;
import entity.Order.OrderState;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class CheckInBusinessWithoutReservation extends NewOrderBusiness implements ICheckInBusinessWithoutReservation {

//    public CheckInBusinessWithoutReservation(){
//        super();
//    }

    @Override
    public OrderState getSubmitOrderState() {
        return OrderState.checkin_generate;
    }


    @Override
    public void charge(Deposit deposit) {
        if (order != null) {
            this.deposit = deposit;
            order.setPaid(deposit.getDeposit());
            orderManager.modifyOrder(order);
        }
    }

    @Override
    public boolean checkIn() {
        if (order != null) {
            Date d = new Date();
            Calendar currentDate = Calendar.getInstance();
            order.setOrderState(OrderState.checkin_confirm);
            order.setCheckinTime(new Timestamp(DateConvertor.toCheckInTime(currentDate).getTimeInMillis()));
            boolean modified = orderManager.modifyOrder(order);
            return modified;
        }
        return false;
    }

}
